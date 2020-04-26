package org.thesaastech.chart.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.jboss.logging.Logger;
import org.thesaastech.chart.lambda.service.AwsS3Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named("summary")
public class ProcessingSummary implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOGGER = Logger.getLogger(ProcessingSummary.class);

    @Inject
    AwsS3Service awsS3Service;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {

        Map<String, String> query = apiGatewayProxyRequestEvent.getQueryStringParameters();

        if (apiGatewayProxyRequestEvent.getHttpMethod().equalsIgnoreCase("GET")) {
            String countryName = null;
            if (query != null)
                countryName = query.get("countryName");

            if (countryName == null || countryName.length() == 0) {
                LOGGER.info("run summary api");
                awsS3Service.createSeObject("filename.csv", "test text, 123");
            } else {
                LOGGER.info("run country api");
            }

        }

        return new APIGatewayProxyResponseEvent().withBody("OK").withStatusCode(200);
    }
}
