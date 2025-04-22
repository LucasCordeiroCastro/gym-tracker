package com.castro.gym.progress.tracker.service;

import com.castro.gym.progress.tracker.config.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<
        Entity,
        Id,
        RequestDTO,
        ResponseDTO> {

    protected final JpaRepository<Entity, Id> repo;
    protected final Function<RequestDTO, Entity> toEntity;
    protected final Function<Entity, ResponseDTO> toResponse;
    protected final BiConsumer<RequestDTO, Entity> updateFromDto;

    protected AbstractCrudService(
            JpaRepository<Entity, Id> repo,
            Function<RequestDTO, Entity> toEntity,
            Function<Entity, ResponseDTO> toResponse,
            BiConsumer<RequestDTO, Entity> updateFromDto
    ) {
        this.repo = repo;
        this.toEntity = toEntity;
        this.toResponse = toResponse;
        this.updateFromDto = updateFromDto;
    }

    public List<ResponseDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(toResponse)
                .collect(Collectors.toList());
    }

    public ResponseDTO findById(Id id) {
        return repo.findById(id)
                .map(toResponse)
                .orElseThrow(() -> new NotFoundException("Not found: " + id));
    }

    public ResponseDTO create(RequestDTO dto) {
        Entity e = toEntity.apply(dto);
        return toResponse.apply(repo.save(e));
    }

    public ResponseDTO update(Id id, RequestDTO dto) {
        return repo.findById(id)
                .map(existing -> {
                    updateFromDto.accept(dto, existing);
                    return repo.save(existing);
                })
                .map(toResponse)
                .orElseThrow(() -> new NotFoundException("Not found: " + id));
    }

    public void delete(Id id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Not found: " + id);
        }
        repo.deleteById(id);
    }
}

