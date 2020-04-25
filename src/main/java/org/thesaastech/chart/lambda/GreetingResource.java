package org.thesaastech.chart.lambda;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    AwsS3Service awsS3Service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

       awsS3Service.createSeObject("file-001.csv", "test,123");
        return "hello jaxrs";
    }
}
