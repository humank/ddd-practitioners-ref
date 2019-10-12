package solid.humank.coffeeshop.order;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.substrate.RuntimeInitializedClassBuildItem;

public class CloudWatchEventProcessor {
    @BuildStep
    RuntimeInitializedClassBuildItem regionConfiguration() {
        return new RuntimeInitializedClassBuildItem("sun.security.provider.NativePRNG");
    }

}
