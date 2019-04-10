package com.uabc.database.example.examplejpa.controllers;

import com.uabc.database.example.examplejpa.constant.ViewConstant;
import com.uabc.database.example.examplejpa.model.LogModel;
import com.uabc.database.example.examplejpa.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logs")
public class LogController {
    @Autowired
    @Qualifier("logServiceImpl")
    private LogService logService;

    @GetMapping("/cancel")
    private String cancel(){
        return "redirect:/logs/showLogs";
    }

    @GetMapping("/logForm")
    public String redirectLogForm(Model model, @RequestParam(value = "id", required = false)int id){
        LogModel logModel = new LogModel();

        if(id != 0){
            logModel = logService.findLogModelById(id);
        }
        model.addAttribute("logModel", logModel);
        return ViewConstant.LOG_FORM;
    }

    @PostMapping("/addLog")
    public String addLog(Model model, @ModelAttribute(name = "logModel")LogModel logModel){
        if(logService.addLog(logModel) != null)
            model.addAttribute("result", 0);
        else
            model.addAttribute("result", 1);

        return "redirect:/logs/showLogs";
    }

    @GetMapping("/showLogs")
    public ModelAndView showLogs(){
        ModelAndView mav = new ModelAndView(ViewConstant.LOGS);

        mav.addObject("logs", logService.listAllLogs());

        return mav;
    }

    @GetMapping("/removeLog")
    public ModelAndView removeLog(@RequestParam(name = "id", required = true)int id){
        logService.removeLog(id);

        return showLogs();
    }
}
