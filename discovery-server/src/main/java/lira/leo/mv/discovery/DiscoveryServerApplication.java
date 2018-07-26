package lira.leo.mv.discovery;

import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lira.leo.mv.discovery.util.Constants;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		System.setProperty(Constants.KEY_SPRING_CONFIG_NAME, Constants.VALUE_SPRING_CONFIG_NAME);
		System.setProperty(Constants.KEY_SPRING_APPLICATION_NAME, Constants.VALUE_SPRING_APPLICATION_NAME);
		
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_FAILFAST, "true");
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_RETRY_INITIAL_INTERVAL, "30000");
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_RETRY_MAX_ATTEMPTS, "50");
		System.setProperty(Constants.KEY_ENDPOINTS_RESTART_ENABLED, "true");
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_ENABLED, "true");
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_URI, Constants.DEFAULT_VALUE_SPRING_CLOUD_CONFIG_URI);
		System.setProperty(Constants.KEY_MANAGEMENT_SECURITY_ENABLED,"false");
		
		
		if (Objects.nonNull(System.getProperty(Constants.PARAM_CONFIG_SERVER_URI))) {
			System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_URI, System.getProperty(Constants.PARAM_CONFIG_SERVER_URI));
		}
		
		if (Objects.nonNull(System.getProperty(Constants.PARAM_CONFIG_PROFILE))) {
			System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_PROFILE, System.getProperty(Constants.PARAM_CONFIG_PROFILE));
		}
		
		if (Objects.nonNull(System.getProperty(Constants.PARAM_CONFIG_LABEL))) {
			System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_LABEL, System.getProperty(Constants.PARAM_CONFIG_LABEL));
		}
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}
}
