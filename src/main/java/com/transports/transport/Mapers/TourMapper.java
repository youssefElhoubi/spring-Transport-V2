package com.transports.transport.Mapers;

import com.transports.transport.DTOS.TourDto;
import com.transports.transport.entities.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TourMapper {
    TourMapper INSTANCE = Mappers.getMapper(TourMapper.class);

    Tour toEntity (TourDto dto);
    TourDto toDto (Tour tour);
}
