package com.uabc.database.example.examplejpa.services;

import com.uabc.database.example.examplejpa.entity.Log;
import com.uabc.database.example.examplejpa.model.LogModel;

import java.util.List;

public interface LogService {
    public abstract LogModel addLog(LogModel logModel);

    public abstract Log findLogById(int id);

    public abstract List<LogModel> listAllLogs();

    public abstract void removeLog(int id);

    public abstract LogModel findLogModelById(int id);
}
