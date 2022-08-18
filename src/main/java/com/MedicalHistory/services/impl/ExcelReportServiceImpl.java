package com.MedicalHistory.services.impl;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.ExcelReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelReportServiceImpl implements ExcelReportService {

    static Logger logger = LogManager.getLogger(ExcelReportServiceImpl.class);


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientService patientService;

    @Override
    public ByteArrayInputStream export(List<Patient> patientDto) {



        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Medical History Report");
            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("Slip ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Disease Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Doctor Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Hospital Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Medicine Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Types of Disease");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Treatment Date");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("Next Appointment Date");
            cell.setCellStyle(headerCellStyle);


            // Creating data rows for each contact
            for (int i = 0; i < patientDto.size(); i++) {
                Row dataRow = sheet.createRow(i + 2);
                dataRow.createCell(0).setCellValue(patientDto.get(i).getPId());
                dataRow.createCell(1).setCellValue(patientDto.get(i).getDiseaseName());
                dataRow.createCell(2).setCellValue(patientDto.get(i).getDoctorName());
                dataRow.createCell(3).setCellValue(patientDto.get(i).getHospitalName());
                dataRow.createCell(4).setCellValue(patientDto.get(i).getMedicineName());
                dataRow.createCell(5).setCellValue(patientDto.get(i).getTypeOfDisease());
                dataRow.createCell(6).setCellValue(patientDto.get(i).getTreatmentDate());
                dataRow.createCell(7).setCellValue(patientDto.get(i).getNexAppt());

            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }

    @Override
    public User getUserById(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));

        return user;
    }

    @Override
    public ByteArrayInputStream reportByDate(Integer id) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Medical History Report");
            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("Slip ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Disease Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Doctor Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Hospital Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Medicine Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Types of Disease");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Treatment Date");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("Next Appointment Date");
            cell.setCellStyle(headerCellStyle);


            List<PatientDto> patients = patientService.getAllSlips();


            User user = getUserById(id);
            String name = user.getName();
            List<Patient> patient = patientService.getPatients(user);
            // Creating data rows for each contact
            for (int i = 0; i < patients.size(); i++)
          {
                Row dataRow = sheet.createRow(i + 2);
                dataRow.createCell(0).setCellValue(patients.get(i).getPId());
                dataRow.createCell(1).setCellValue(patients.get(i).getDiseaseName());
                dataRow.createCell(2).setCellValue(patients.get(i).getDoctorName());
                dataRow.createCell(3).setCellValue(patients.get(i).getHospitalName());
                dataRow.createCell(4).setCellValue(patients.get(i).getMedicineName());
                dataRow.createCell(5).setCellValue(patients.get(i).getTypeOfDisease());
                dataRow.createCell(6).setCellValue(patients.get(i).getTreatmentDate());
                dataRow.createCell(7).setCellValue(patients.get(i).getNexAppt());

            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }
}
