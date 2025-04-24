package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.dto.request.SetEntryRequest;
import com.castro.gym.progress.tracker.model.dto.response.SetEntryResponse;
import com.castro.gym.progress.tracker.service.SetEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/set-entries")
public class SetEntryController {
    private final SetEntryService setEntryService;

    @GetMapping("/log/{logId}")
    public List<SetEntryResponse> getSetsByExerciseLog(@PathVariable Long logId) {
        return setEntryService.findByExerciseLog(logId);
    }

    @GetMapping
    public List<SetEntryResponse> getAllSetEntries() {
        return setEntryService.findAll();
    }

    @GetMapping("/{id}")
    public SetEntryResponse getSetEntryById(@PathVariable Long id) {
        return setEntryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetEntryResponse createSetEntry(@Valid @RequestBody SetEntryRequest setEntryRequest) {
        return setEntryService.create(setEntryRequest);
    }

    @PutMapping("/{id}")
    public SetEntryResponse updateSetEntry(@PathVariable Long id, @Valid @RequestBody SetEntryRequest updatedSetEntry) {
        return setEntryService.update(id, updatedSetEntry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSetEntry(@PathVariable Long id) {
        setEntryService.delete(id);
    }
}
