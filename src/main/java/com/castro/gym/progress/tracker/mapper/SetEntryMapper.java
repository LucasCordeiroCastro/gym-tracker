package com.castro.gym.progress.tracker.mapper;

import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.domain.entity.workout.SetEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SetEntryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "difficulty", expression = "java(dto.difficulty() != null ? dto.difficulty() : SetDifficulty.UNSPECIFIED)")
    SetEntry toEntity(SetEntryRequest dto);

    @Mapping(target = "volume", expression = "java(entity.getWeight() * entity.getReps())")
    SetEntryResponse toResponse(SetEntry entity);

    @Mapping(target = "difficulty", expression = "java(dto.difficulty() != null ? dto.difficulty() : SetDifficulty.UNSPECIFIED)")
    void updateFromDto(SetEntryRequest dto, @MappingTarget SetEntry entity);
}
