package org.thesaastech.chart.lambda.service;


import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class AwsS3Service {

    public static final String BUCKET = "s3-stage-dadj872td7g328dw";

    private S3Client s3;


    @PostConstruct
    public void init() {

        s3 = S3Client.builder()
                .region(Region.EU_WEST_2)
                .httpClient(software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient.builder().build())
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void createSeObject(String key, String text) {

        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        s3.putObject(PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build(), RequestBody.fromString(text));
    }
}
