package com.castro.gym.progress.tracker.model.mapper;

import com.castro.gym.progress.tracker.model.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.model.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.model.entity.log.SetEntry;
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
    SetEntry toEntity(SetEntryRequest dto);

    @Mapping(target = "volume", expression = "java(entity.getWeight() * entity.getReps())")
    SetEntryResponse toResponse(SetEntry entity);

    void updateFromDto(SetEntryRequest dto, @MappingTarget SetEntry entity);
}
