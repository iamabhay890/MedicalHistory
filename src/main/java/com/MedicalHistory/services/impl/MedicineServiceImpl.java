package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.repositories.MedicineRepo;
import com.MedicalHistory.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;

    @Override
    public MedicineDto createMedicineData(MedicineDto medicineDto) {

        Medicine medicine = this.dtoToMedicine(medicineDto);
        Medicine savedMedicine = this.medicineRepo.save(medicine);
        return this.medicineToDto(savedMedicine);

    }

    @Override
    public List<MedicineDto> getAllMedicines() {

        List<Medicine> medicines = this.medicineRepo.findAll();
        List<MedicineDto> medicineDtos = medicines.stream().map(medicine -> this.medicineToDto(medicine)).collect(Collectors.toList());

        return medicineDtos;
    }

    @Override
    public List<Medicine> searchMedicine(String name) {
        return this.medicineRepo.findByNameContaining(name);
    }

    @Override
    public List<Medicine> allMedicine() {
        return this.medicineRepo.findAll();
    }

    private MedicineDto medicineToDto(Medicine medicine) {


        MedicineDto medicineDto = new MedicineDto();

        medicineDto.setMId(medicine.getMId());
        medicineDto.setName(medicine.getName());

        return medicineDto;

    }

    private Medicine dtoToMedicine(MedicineDto medicineDto) {


        Medicine medicine = new Medicine();

        medicine.setMId(medicineDto.getMId());
        medicine.setName(medicineDto.getName());

        return medicine;

    }

}
