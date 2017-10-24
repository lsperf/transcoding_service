package com.test.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;


/**
 * Created by ls on 1/28/17.
 */
@Service
public class S3Service {

	@Value("${aws.key}")
	private String key;

	@Value("${aws.secret}")
	private String secret;

	@Value("${aws.s3.bucket}")
	private String baseBucket;

	private AmazonS3 s3;
	private TransferManager tx;

	public AmazonS3 s3() {
		if (s3 == null) {
			AWSCredentials credentials = new BasicAWSCredentials(this.key, this.secret);
			s3 = new AmazonS3Client(credentials);
		}
		return s3;
	}

	public TransferManager tx(){
		if (tx == null) {
			AWSCredentials credentials = new BasicAWSCredentials(this.key, this.secret);
			tx = new TransferManager(credentials);
		}
		return tx;

	}

	public boolean uploadFileAsync(String content, String fileName, String contentType, String bucket) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		byte[] arr = content.getBytes();
		InputStream inputStream = new ByteArrayInputStream(arr);
		metadata.setContentLength(arr.length);
		PutObjectRequest putRequest = new PutObjectRequest(baseBucket+"/"+bucket, fileName, inputStream, metadata);
		putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		tx().upload(putRequest);
		return true;
	}


	public boolean uploadFile(String content, String fileName, String contentType, String bucket) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		byte[] arr = content.getBytes();
		InputStream inputStream = new ByteArrayInputStream(arr);
		metadata.setContentLength(arr.length);
		PutObjectRequest putRequest = new PutObjectRequest(baseBucket+"/"+bucket, fileName, inputStream, metadata);
		putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		s3().putObject(putRequest);
		return true;
	}

	public boolean uploadFile(byte[] bytes, String fileName, String contentType, String bucket) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		InputStream inputStream = new ByteArrayInputStream(bytes);
		metadata.setContentLength(bytes.length);
		PutObjectRequest putRequest = new PutObjectRequest(baseBucket+"/"+bucket, fileName, inputStream, metadata);
		putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		s3().putObject(putRequest);
		return true;
	}

	public boolean removeFile(String key, String bucket) throws IOException {
		s3().deleteObject(baseBucket+"/"+bucket, key);
		return true;
	}
}
