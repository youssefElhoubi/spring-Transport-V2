package com.transports.transport.utils;
import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.Tour;
import com.transports.transport.entities.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.*;


public class TourOptimizer {
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of Earth in km
        double dLat = toRadians(lat2 - lat1);
        double dLon = toRadians(lon2 - lon1);
        double a = sin(dLat / 2) * sin(dLat / 2) +
                cos(toRadians(lat1)) * cos(toRadians(lat2)) *
                        sin(dLon / 2) * sin(dLon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return R * c; // distance in km
    }
    public static List<Delivery> sortDeliveriesByNearest(Tour tour) {
        Warehouse warehouse = tour.getWarehouse();
        List<Delivery> deliveries = new ArrayList<>(tour.getDeliveries());

        if (warehouse == null || deliveries.isEmpty()) {
            return deliveries;
        }

        List<Delivery> sorted = new ArrayList<>();
        Delivery current = null;
        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        while (!deliveries.isEmpty()) {
            double finalCurrentLat = currentLat;
            double finalCurrentLon = currentLon;
            Delivery nearest = deliveries.stream()
                    .min(Comparator.comparingDouble(d ->
                            haversine(finalCurrentLat, finalCurrentLon, d.getUser().getLatitude(), d.getUser().getLongitude())))
                    .orElseThrow();

            sorted.add(nearest);
            deliveries.remove(nearest);

            currentLat = nearest.getUser().getLatitude();
            currentLon = nearest.getUser().getLongitude();
        }
        // Assign sequence order
        for (int i = 0; i < sorted.size(); i++) {
            sorted.get(i).setSequenceOrder(i + 1);
        }
        return sorted;
    }
    public static Double totalDistance(Tour tour){
        List<Delivery> deliveries = sortDeliveriesByNearest(tour);
        Warehouse warehouse = tour.getWarehouse();

        // Start point
        double startLat = warehouse.getLatitude();
        double startLon = warehouse.getLongitude();

        // Create a combined list with warehouse at start
        List<double[]> points = new ArrayList<>();
        points.add(new double[]{startLat, startLon});
        deliveries.forEach(d -> points.add(new double[]{d.getUser().getLatitude(), d.getUser().getLongitude()}));

        // Stream over consecutive pairs
        return IntStream.range(0, points.size() - 1)
                .mapToDouble(i -> haversine(
                        points.get(i)[0], points.get(i)[1],
                        points.get(i + 1)[0], points.get(i + 1)[1]
                ))
                .sum();
    }

}
