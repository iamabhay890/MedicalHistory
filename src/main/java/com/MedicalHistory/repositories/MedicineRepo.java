package com.MedicalHistory.repositories;
        import com.MedicalHistory.entities.Medicine;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine,Integer>
{
}
