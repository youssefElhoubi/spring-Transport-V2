package com.transports.transport.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.Vehicul;
import com.transports.transport.entities.Warehouse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class RouteOptimizationService {
    private final ChatModel chatModel;
    private final ObjectMapper objectMapper;

    @Autowired
    public RouteOptimizationService(ChatModel chatModel, ObjectMapper objectMapper) {
        this.chatModel = chatModel;
        this.objectMapper = objectMapper;
    }
    public List<Delivery> optimizeDeliveries(Warehouse warehouse, List<Delivery>  deliveries , Vehicul vehicle) throws JsonProcessingException {
        List<Map<String, Object>> deliveriesSimple = deliveries.stream().map(
                d ->{
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", d.getId());
                    map.put("lat", d.getUser().getLatitude());
                    map.put("lon", d.getUser().getLongitude());
                    map.put("weight", d.getWeight());
                    return map;
                }
        ).collect(Collectors.toList());

        String deliveriesJson = objectMapper.writeValueAsString(deliveriesSimple);
        String wareHouseJson = objectMapper.writeValueAsString(Map.of("lat", warehouse.getLatitude(), "lon", warehouse.getLongitude()));

        // 2. PROMPT ENGINEERING (The instructions for the AI)
        String promptText = """
                    You are an expert logistics AI. Optimize this delivery route for minimal distance.
                    
                    WAREHOUSE LOCATION (lat/lon): %s
                    
                    DELIVERIES TO OPTIMIZE (JSON):
                    %s
                    
                    CONSTRAINTS:
                    - Start at Warehouse -> Visit ALL deliveries.
                    - Vehicle max capacity: %.2f kg. Total load for this trip is %.2f kg.
                    - Return ONLY the raw JSON output.
                    
                    OUTPUT FORMAT:
                    {
                      "ordered_ids": [id_first_delivery, id_second_delivery, ...],
                      "reasoning": "Explain briefly why this route is optimal."
                    }
                    """.formatted(
                wareHouseJson,
                deliveriesJson,
                vehicle.getMaxWeight(),
                deliveries.stream().mapToDouble(Delivery::getWeight).sum() // Add total load for better context
        );
        String aiResponseRaw = chatModel.call(promptText); // Simple call using String input/output

        // 4. ROBUST JSON PARSING (Handling potential extra text/markdown from the LLM)
        String cleanJson = aiResponseRaw;
        int firstBracket = aiResponseRaw.indexOf("{");
        int lastBracket = aiResponseRaw.lastIndexOf("}");

        if (firstBracket != -1 && lastBracket != -1 && firstBracket < lastBracket) {
            cleanJson = aiResponseRaw.substring(firstBracket, lastBracket + 1);
        } else {
            System.err.println("🚨 Could not find clean JSON in AI response. Raw: " + aiResponseRaw);
        }

        JsonNode rootNode = objectMapper.readTree(cleanJson);

        // 5. MAPPING BACK TO JAVA OBJECTS
        JsonNode idsNode = rootNode.get("ordered_ids");
        List<Long> orderedIds = new ArrayList<>();
        if (idsNode != null && idsNode.isArray()) {
            for (JsonNode idNode : idsNode) {
                orderedIds.add(idNode.asLong());
            }
        }

        // Reconstruct the ordered list of Delivery entities
        Map<Long, Delivery> deliveryMap = deliveries.stream()
                .collect(Collectors.toMap(Delivery::getId, d -> d));

        List<Delivery> orderedDeliveries = new ArrayList<>();
        for (Long id : orderedIds) {
            if (deliveryMap.containsKey(id)) {
                orderedDeliveries.add(deliveryMap.get(id));
                deliveryMap.remove(id); // Remove to track unplaced deliveries
            }
        }

        // OPTIONAL: Add any deliveries the AI missed to the end (fallback)
        orderedDeliveries.addAll(deliveryMap.values());

        return orderedDeliveries;
    }
}
