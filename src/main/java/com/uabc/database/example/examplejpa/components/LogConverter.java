package com.uabc.database.example.examplejpa.components;

import com.uabc.database.example.examplejpa.entity.Log;
import com.uabc.database.example.examplejpa.model.LogModel;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("logConverter")
public class LogConverter {
    public Log convertLogModelToLog(LogModel logModel){
        Log log = new Log();

        log.setId(logModel.getId());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(logModel.getDate());
        } catch (ParseException e) {
            System.out.println("Error: " + e);
        }
        log.setDate(date);
        log.setDetails(logModel.getDetails());
        log.setUsername(logModel.getUsername());
        log.setUrl(logModel.getUrl());

        return log;
    }

    public LogModel convertLogToLogModel(Log log){
        LogModel logModel = new LogModel();

        logModel.setId(log.getId());
        String[] split = log.getDate().toString().split(" ");
        logModel.setDate(split[0]);
        logModel.setDetails(log.getDetails());
        logModel.setUsername(log.getUsername());
        logModel.setUrl(log.getUrl());

        return logModel;
    }
}
