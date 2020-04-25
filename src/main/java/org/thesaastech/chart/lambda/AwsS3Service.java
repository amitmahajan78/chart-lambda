package org.thesaastech.chart.lambda;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AwsS3Service {

    public static final String BUCKET = "s3-stage-dadj872td7g328dw";

    private AmazonS3 s3;


    @PostConstruct
    public void init() {

        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.EU_WEST_2)
                .build();

    }

    public void createSeObject(String key, String text) {
        s3.putObject(BUCKET, key, text);
    }
}
