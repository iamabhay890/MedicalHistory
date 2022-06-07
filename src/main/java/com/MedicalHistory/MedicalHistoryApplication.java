package com.MedicalHistory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MedicalHistoryApplication {

	static Logger logger = LogManager.getLogger( MedicalHistoryApplication.class);




	public static void main(String[] args) {

		SpringApplication.run(MedicalHistoryApplication.class, args);
		System.out.println("Abhay");

		//Level
		logger.trace("This is an trace msg");
		logger.debug("This is an debug msg");
		logger.info("this is an info msg");
	}



}
