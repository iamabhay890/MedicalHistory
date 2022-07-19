package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.MedicineService;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/mh/user/records")
public class AddMedicalRecordController {

    static Logger logger= LogManager.getLogger(AddMedicalRecordController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineService medicineService;



    @ModelAttribute("patientDto")
    public PatientDto patientDto(){

        return new PatientDto();
    }


    @GetMapping("/addMedicalRecords/{id}")
    public String addmedicalRecords(@PathVariable(value = "id") Integer id,Model model) {

        logger.info("Running Add medical records..");

        PatientDto patientDto=new PatientDto();
        UserDto userDto= userService.getUserById(id);

        logger.info("User has logged in through this user id "+userDto.getId());


        model.addAttribute("patientDto",patientDto);
        model.addAttribute("userDto",userDto);

        //it was required for header1 fragment of user block
        model.addAttribute("user",userDto);

        logger.info("passed controller to 'addUserMedicalRecords' url");

        return "User/AddMedicalRecords";
    }

    @PostMapping("/addUserMedicalRecords")
    public String adduserMedicalRecords(@Valid @ModelAttribute("patientDto") PatientDto patientDto,
                                        BindingResult result,
                                        @ModelAttribute("userDto") User userDto,
                                        Model model, HttpSession session,
                                        @RequestParam("reportFile") MultipartFile file)  {


        try {
            logger.info("running 'addUserMedicalRecords'  controller");

            logger.info("Id Number " + userDto.getId() + " adding their medical Records");


            //fetching the user by id
            UserDto user = userService.getUserById(userDto.getId());


            if(result.hasErrors()){
                System.out.println(result);
                logger.info("Add medicine form has some validation error");

                logger.info("Passing User data to show the header content");
                model.addAttribute("user", user);

                model.addAttribute("patientDto",patientDto);
                return "User/AddMedicalRecords";


            }else {
                patientDto.setUser(userDto);

                System.out.println("medicine Name "+patientDto.getMedicineName());
                String medicines[]=patientDto.getMedicineName().split(",");
                for(String medicine : medicines){
                     medicine = medicine.replaceAll("\\s", "");
                    List<Medicine> medicineList=medicineService.searchMedicine(medicine);
                    if(medicineList.size()==0){
                        MedicineDto medicineDto=new MedicineDto();
                        medicineDto.setName(medicine);
                        medicineService.createMedicineData(medicineDto);
                    }
                }
                patientService.createPatientData(patientDto,file);

                logger.info("Data Has Been Saved");

                logger.info("Passing User data to show the header content");

                model.addAttribute("user", user);

                model.addAttribute("listPatients", patientService.getPatients(userDto));


                return "redirect:/mh/index";

            }

        } catch (Exception e) {
            e.printStackTrace();
            return "User/userHome";
        }
    }


    //Download File from database using link.
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id){

        Patient patientDto=patientService.getPatinetById(id);
        logger.info("Requested image name is  "+patientDto.getReportName());

        byte[] temp=Base64.getDecoder().decode(patientDto.getReport());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(patientDto.getReportType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+patientDto.getReportName())
                .body(new ByteArrayResource(temp));
    }
}
