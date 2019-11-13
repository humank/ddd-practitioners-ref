package solid.humank.coffeeshop.infra.adapters;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.serverless.ssmcachingclient.SsmParameterCachingClient;

import java.time.Duration;

public class SSMUtil {

    public static String getParameter(String paramName) {
        SsmClient ssm = SsmClient.create();
        SsmParameterCachingClient client = new SsmParameterCachingClient(ssm, Duration.ofMinutes(1));
        return client.getAsString(paramName);
    }
}
