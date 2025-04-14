package com.castro.gym.progress.tracker.controller;

import com.castro.gym.progress.tracker.model.entity.log.SetEntry;
import com.castro.gym.progress.tracker.service.SetEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/set-entries")
public class SetEntryController {
    private final SetEntryService setEntryService;

    @GetMapping("/log/{logId}")
    public List<SetEntry> getSetsByExerciseLog(@PathVariable Long logId) {
        return setEntryService.findByExerciseLog(logId);
    }

    @PostMapping
    public SetEntry createSetEntry(@RequestBody SetEntry entry) {
        return setEntryService.save(entry);
    }
}
