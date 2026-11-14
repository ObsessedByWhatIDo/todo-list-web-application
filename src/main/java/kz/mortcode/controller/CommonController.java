package kz.mortcode.controller;

import kz.mortcode.Service.RecordService;
import kz.mortcode.entity.Record;
import kz.mortcode.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class CommonController {
    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/home")
    public String showHomePage(Model model){
        List<Record> records = recordService.findAllRecords();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        return "main-page";
    }
}
