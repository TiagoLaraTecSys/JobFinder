package com.laratecsys.jobfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laratecsys.jobfinder.services.S3Service;

@SpringBootApplication
public class JobfinderApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(JobfinderApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		s3Service.uploadFile("C:\\Users\\tiago.ribeiro.safetycontrol.000\\Pictures\\70762558_713598865779407_3879831364841820385_n.jpg");
	}

}
