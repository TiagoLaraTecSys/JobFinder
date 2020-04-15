package com.laratecsys.jobfinder.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3Cliente;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	
	public void uploadFile(String localFile) {
		
		try {
			File file = new File(localFile);
			LOG.info("Iniciando UPLOAD");
			s3Cliente.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));	
			LOG.info("UPLOAD Finalizado");
		}catch(AmazonServiceException e) {
			LOG.info(e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
		}catch(AmazonClientException e) {
			LOG.info(e.getMessage());
					
		}
		
	}
}
