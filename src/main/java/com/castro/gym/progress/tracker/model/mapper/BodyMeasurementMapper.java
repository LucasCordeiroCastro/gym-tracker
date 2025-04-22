package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.request.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.response.BodyMeasurementResponse;
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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    BodyMeasurement toEntity(BodyMeasurementRequest dto);

    @Mapping(source = "user.id", target = "userId")
    BodyMeasurementResponse toResponse(BodyMeasurement entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateFromDto(BodyMeasurementRequest dto, @MappingTarget BodyMeasurement entity);
}
