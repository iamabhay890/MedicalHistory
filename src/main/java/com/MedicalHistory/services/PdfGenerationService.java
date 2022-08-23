package com.MedicalHistory.services;

import com.MedicalHistory.payloads.PatientDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface PdfGenerationService {


    void exportToPdf(List<PatientDto> patientDtoList, HttpServletResponse response, Integer id, Principal principal) throws IOException;

    void exportToFormattedPdf(HttpServletResponse response, Integer id,
                              String fromDate, String upToDate, Principal principal) throws IOException;
}
