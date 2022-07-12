package com.MedicalHistory.services;


import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.repositories.MedicineRepo;

import java.util.List;

public interface MedicineService {

    MedicineDto createMedicineData(MedicineDto medicine);

    List<MedicineDto> getAllMedicines();

    List<Medicine> searchMedicine(String name);

    List<Medicine> allMedicine();
}