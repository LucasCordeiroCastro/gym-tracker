package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BodyMeasurementMapper {
    BodyMeasurement toEntity(BodyMeasurementRequest dto);

    @Mapping(source = "user.id", target = "userId")
    BodyMeasurementResponse toResponse(BodyMeasurement entity);

    void updateFromDto(BodyMeasurementRequest dto, @MappingTarget BodyMeasurement entity);
}
