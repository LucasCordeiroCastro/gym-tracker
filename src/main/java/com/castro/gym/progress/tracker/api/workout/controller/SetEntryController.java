package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.domain.service.workout.SetEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercise-logs/{exerciseLogId}/sets")
public class SetEntryController {
    private final SetEntryService setEntryService;

    @GetMapping
    public List<SetEntryResponse> getSetsByExerciseLogId(@PathVariable Long exerciseLogId) {
        return setEntryService.findAllByExerciseLogId(exerciseLogId);
    }

    @GetMapping("/{setId}")
    public SetEntryResponse getSetEntryById(@PathVariable Long exerciseLogId, @PathVariable Long setId) {
        return setEntryService.findById(setId, exerciseLogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetEntryResponse createSetEntry(@PathVariable Long exerciseLogId,
                                           @Valid @RequestBody SetEntryRequest setEntryRequest) {
        return setEntryService.create(exerciseLogId, setEntryRequest);
    }

    @PutMapping("/{setId}")
    public SetEntryResponse updateSetEntry(@PathVariable Long exerciseLogId, @PathVariable Long setId,
                                           @Valid @RequestBody SetEntryRequest updatedSetEntry) {
        return setEntryService.update(exerciseLogId, setId, updatedSetEntry);
    }

    @DeleteMapping("/{setId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSetEntry(@PathVariable Long exerciseLogId, @PathVariable Long setId) {
        setEntryService.delete(setId, exerciseLogId);
    }
}
