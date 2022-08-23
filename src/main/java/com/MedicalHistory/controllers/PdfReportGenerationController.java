package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.PdfGenerationService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/mh/user/")
public class
PdfReportGenerationController {

    static Logger logger = LogManager.getLogger(PdfReportGenerationController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private UserService userService;


    @GetMapping("export/pdf")
    public void exportToPdf(HttpServletResponse response, Principal principal) throws IOException {

        User user = userService.findByEmail(principal.getName());
        List<PatientDto> patientList = patientService.getAllSlips();
        logger.info("downloading full report for " + user.getId());
        pdfGenerationService.exportToPdf(patientList, response, user.getId(), principal);
    }


    @PostMapping("export/FormattedPdf")
    public void exportToFormattedPdf(Principal principal, HttpServletResponse response,
                                     @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws IOException {


        User user = userService.findByEmail(principal.getName());
        logger.info("downloading custom report for " + user.getId());
        pdfGenerationService.exportToFormattedPdf(response, user.getId(), startDate, endDate, principal);
    }
}
