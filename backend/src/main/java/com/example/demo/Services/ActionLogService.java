package com.example.demo.Services;

import com.example.demo.Repository.ActionLogRepository;
import com.example.demo.entity.ActionLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    public List<ActionLog> getAllActionLogs() {
        return actionLogRepository.findAll();
    }

    public ActionLog createActionLog(ActionLog actionLog) {
        actionLog.setTimestamp(LocalDateTime.now());
        return actionLogRepository.save(actionLog);
    }
}
