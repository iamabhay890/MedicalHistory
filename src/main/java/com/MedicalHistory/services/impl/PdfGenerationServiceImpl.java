package com.MedicalHistory.services.impl;


import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PdfGenerationService;
import com.MedicalHistory.services.UserService;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserService userService;

    @Override
    public void exportToPdf(List<PatientDto> patientDtoList, HttpServletResponse response, Integer id, Principal principal) throws IOException {
        setResponseHeader(response, "application/pdf", ".pdf", "MedicalReport_");
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("Medical History Report", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);

        User user = userService.findByEmail(principal.getName());
        Paragraph userPara = new Paragraph("Welcome Mr. " + user.getName());

        document.add(userPara);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writePdfHeader(table);
        writePdfBody(table, patientDtoList, id);

        document.add(table);
        document.close();
    }


    @Override
    public void exportToFormattedPdf(HttpServletResponse response, Integer id, String startDate, String endDate, Principal principal) throws IOException {
        setResponseHeader(response, "application/pdf", ".pdf", "MedicalReport_");
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("Medical History Report", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);

        User user = userService.findByEmail(principal.getName());
        Paragraph userPara = new Paragraph("Welcome Mr. " + user.getName());

        document.add(userPara);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        List<Patient> patientList = patientRepo.findByTreatmentDateBetween(startDate, endDate);

        writePdfHeader(table);
        writeFormattedPdfBody(table, patientList, id);

        document.add(table);
        document.close();

    }


    public void setResponseHeader(HttpServletResponse response, String contentType,
                                  String extension, String prefix) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;
        response.setContentType(contentType);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=" + fileName;
        response.setHeader(headerKey, headerValue);
    }

    private void writePdfHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Disease Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Doctor Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Hospital Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Medicines Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type of Disease", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Previous Appointment", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Next Appointment", font));
        table.addCell(cell);

    }

    private void writePdfBody(PdfPTable table, List<PatientDto> patientDtoList, Integer id) {

        for (PatientDto patient : patientDtoList) {

            if (id.equals(patient.getUser().getId())) {
                table.addCell(String.valueOf(patient.getPId()));
                table.addCell(patient.getDiseaseName());
                table.addCell(patient.getDoctorName());
                table.addCell(patient.getHospitalName());
                table.addCell(patient.getMedicineName());
                table.addCell(patient.getTypeOfDisease());
                table.addCell(patient.getTreatmentDate());
                table.addCell(patient.getNexAppt());
            }
        }

    }

    private void writeFormattedPdfBody(PdfPTable table, List<Patient> patientDtoList,
                                       Integer id) {
        for (Patient patient : patientDtoList) {

            if (id.equals(patient.getUser().getId())) {
                table.addCell(String.valueOf(patient.getPId()));
                table.addCell(patient.getDiseaseName());
                table.addCell(patient.getDoctorName());
                table.addCell(patient.getHospitalName());
                table.addCell(patient.getMedicineName());
                table.addCell(patient.getTypeOfDisease());
                table.addCell(patient.getTreatmentDate());
                table.addCell(patient.getNexAppt());
            }
        }
    }
}
