package com.uabc.database.example.examplejpa.services.impl;

import com.uabc.database.example.examplejpa.components.LogConverter;
import com.uabc.database.example.examplejpa.entity.Log;
import com.uabc.database.example.examplejpa.model.LogModel;
import com.uabc.database.example.examplejpa.respository.LogRepository;
import com.uabc.database.example.examplejpa.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("logServiceImpl")
public class LogServiceImpl implements LogService {
    @Autowired
    @Qualifier("logRepository")
    private LogRepository logRepository;

    @Autowired
    @Qualifier("logConverter")
    private LogConverter logConverter;


    @Override
    public LogModel addLog(LogModel logModel) {
        Log temp = logConverter.convertLogModelToLog(logModel);
        Log log = logRepository.save(temp);

        return logConverter.convertLogToLogModel(log);
    }

    @Override
    public Log findLogById(int id) {
        return logRepository.findById(id);
    }

    @Override
    public List<LogModel> listAllLogs() {
        List<Log> logs = logRepository.findAll();
        List<LogModel> logModels = new ArrayList<>();

        for (Log log: logs) {
            logModels.add(logConverter.convertLogToLogModel(log));
        }

        return logModels;
    }

    @Override
    public void removeLog(int id) {
        Log log = findLogById(id);

        if(log != null)
            logRepository.delete(log);
    }

    @Override
    public LogModel findLogModelById(int id) {
        return logConverter.convertLogToLogModel(findLogById(id));
    }
}
