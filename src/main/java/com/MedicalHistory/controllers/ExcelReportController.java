package com.MedicalHistory.controllers;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.ExcelReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ExcelReportController {

    static Logger logger= LogManager.getLogger(ExcelReportController.class);
    @Autowired
    private ExcelReportService reportService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;


    @GetMapping("/downloadExcelFile/{id}")
    public void downloadExcelFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {


        logger.info("Excel Report Generate Process");
        User user = reportService.getUserById(id);
        String name = user.getName();
        List<Patient> patients = patientService.getPatients(user);
        ByteArrayInputStream byteArrayInputStream = reportService.export(patients);
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss a");
        String currentDateTime = dateFormatter.format(new Date());

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+name+"_"+ currentDateTime +".xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }

    @PostMapping("/excelReport/{id}")
    public void ExcelReport(@PathVariable("id") Integer id, @ModelAttribute("patientDto") PatientDto patientDto
                           ,HttpServletResponse response) throws IOException {

        logger.info("Excel Report Generate Process with using date");
        String startDate=patientDto.getStartDate();
        String endDate= patientDto.getEndDate();
        List<Patient> listPatients=patientRepo.findByTreatmentDateBetween(startDate,endDate);

        for(Patient patients:listPatients)
        {
            if(patients.getUser().getId().equals(id))
            {
                System.out.println("--Printing Disease Name "+ patients.getDiseaseName());
                ByteArrayInputStream byteArrayInputStream = reportService.export(listPatients);
                DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss a");
                String currentDateTime = dateFormatter.format(new Date());
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + "_" + currentDateTime + ".xlsx");
                IOUtils.copy(byteArrayInputStream, response.getOutputStream());
            }
        }
    }
}