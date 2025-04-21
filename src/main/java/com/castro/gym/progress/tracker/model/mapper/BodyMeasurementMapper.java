package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.model.dto.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.model.entity.user.BodyMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface BodyMeasurementMapper {
    BodyMeasurement toEntity(BodyMeasurementRequest dto);

    //    @Mapping(
//            target = "userId",
//            expression = "java(entity.getUser().getId())"
//    )
    @Mapping(source = "user.id", target = "userId")
    BodyMeasurementResponse toResponse(BodyMeasurement entity);
}
