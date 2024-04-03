package com.example.demo.Controllers;

import com.example.demo.Services.ActionLogService;
import com.example.demo.entity.ActionLog;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/action-logs")
public class ActionLogController {

    private final ActionLogService actionLogService;

    @GetMapping("/all")
    public List<ActionLog> getAllActionLogs() {
        return actionLogService.getAllActionLogs();
    }

    @PostMapping("/create")
    public ActionLog createActionLog(@RequestBody ActionLog actionLog) {
        return actionLogService.createActionLog(actionLog);
    }
}
