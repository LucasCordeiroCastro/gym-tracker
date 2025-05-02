package com.castro.gym.progress.tracker.domain.service.user;

import com.castro.gym.progress.tracker.exception.NotFoundException;
import com.castro.gym.progress.tracker.api.user.dto.request.BodyMeasurementRequest;
import com.castro.gym.progress.tracker.api.user.dto.response.BodyMeasurementResponse;
import com.castro.gym.progress.tracker.domain.entity.user.BodyMeasurement;
import com.castro.gym.progress.tracker.domain.entity.user.User;
import com.castro.gym.progress.tracker.mapper.BodyMeasurementMapper;
import com.castro.gym.progress.tracker.domain.repository.user.BodyMeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BodyMeasurementService {
    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final BodyMeasurementMapper bodyMeasurementMapper;
    private final UserAuthorizationHelper userAuthorizationHelper;

    public BodyMeasurementResponse create(BodyMeasurementRequest dto) {
        User user = userAuthorizationHelper.getAuthenticatedUser();
        BodyMeasurement bodyMeasurement = bodyMeasurementMapper.toEntity(dto);
        bodyMeasurement.setUser(user);

        return bodyMeasurementMapper.toResponse(bodyMeasurementRepository.save(bodyMeasurement));
    }

    public List<BodyMeasurementResponse> findByUser() {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return bodyMeasurementRepository.findByUserIdOrderByDateDesc(userId).stream()
                .map(bodyMeasurementMapper::toResponse).toList();
    }

    public BodyMeasurementResponse findById(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        return bodyMeasurementRepository.findByIdAndUserId(id, userId)
                .map(bodyMeasurementMapper::toResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Body measurement with id %d was not found", id)));
    }

    public BodyMeasurementResponse update(Long id, BodyMeasurementRequest updatedBodyMeasurement) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        BodyMeasurement existing = bodyMeasurementRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException(String.format("Body measurement with id %d was not found", id)));

        bodyMeasurementMapper.updateFromDto(updatedBodyMeasurement, existing);
        return bodyMeasurementMapper.toResponse(bodyMeasurementRepository.save(existing));
    }

    public void delete(Long id) {
        Long userId = userAuthorizationHelper.getAuthenticatedUserId();
        BodyMeasurement existing = bodyMeasurementRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException(String.format("Body measurement with id %d was not found", id)));

        bodyMeasurementRepository.delete(existing);
    }
}
