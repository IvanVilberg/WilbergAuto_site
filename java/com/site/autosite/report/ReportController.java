package com.site.autosite.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }
    
    @GetMapping("")
    public String showFormReport(Model model) {
        Report reportInfo = new Report();
        model.addAttribute("reportInfo", reportInfo);
        return "report";
    }

    @PostMapping("")
    public String registerReport(@Valid @ModelAttribute Report reportInfo) {
        Report report = new Report();
        report.setEmail(reportInfo.getEmail());
        report.setMessage(reportInfo.getMessage());

        reportService.save(report);
        
        return "index";
    }
    

}
