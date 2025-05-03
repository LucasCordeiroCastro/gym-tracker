package com.castro.gym.progress.tracker.api.workout.controller;

import com.castro.gym.progress.tracker.api.workout.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.api.workout.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.domain.service.workout.SetEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Set", description = "Manage set entry templates")
@RequestMapping("/api/v1/exercise-logs/{exerciseLogId}/sets")
@RestController
public class SetEntryController {
    private final SetEntryService setEntryService;

    @Operation(summary = "Get all set entries by exercise log ID")
    @GetMapping
    public List<SetEntryResponse> getSetsByExerciseLogId(@PathVariable Long exerciseLogId) {
        return setEntryService.findAllByExerciseLogId(exerciseLogId);
    }

    @Operation(summary = "Get a set entry by ID")
    @GetMapping("/{setId}")
    public SetEntryResponse getSetEntryById(@PathVariable Long exerciseLogId, @PathVariable Long setId) {
        return setEntryService.findById(setId, exerciseLogId);
    }

    @Operation(summary = "Create a new set entry")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetEntryResponse createSetEntry(@PathVariable Long exerciseLogId,
                                           @Valid @RequestBody SetEntryRequest setEntryRequest) {
        return setEntryService.create(exerciseLogId, setEntryRequest);
    }

    @Operation(summary = "Update a set entry")
    @PutMapping("/{setId}")
    public SetEntryResponse updateSetEntry(@PathVariable Long exerciseLogId, @PathVariable Long setId,
                                           @Valid @RequestBody SetEntryRequest updatedSetEntry) {
        return setEntryService.update(exerciseLogId, setId, updatedSetEntry);
    }

    @Operation(summary = "Delete a set entry")
    @DeleteMapping("/{setId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSetEntry(@PathVariable Long exerciseLogId, @PathVariable Long setId) {
        setEntryService.delete(setId, exerciseLogId);
    }
}
