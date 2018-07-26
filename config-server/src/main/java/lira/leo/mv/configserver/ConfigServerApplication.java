package lira.leo.mv.configserver;

import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import lira.leo.mv.configserver.util.Constants;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		System.setProperty(Constants.KEY_MANAGEMENT_SECURITY_ENABLED,"false");
		System.setProperty(Constants.KEY_SPRING_CONFIG_NAME, Constants.VALUE_SPRING_CONFIG_NAME);
		System.setProperty(Constants.KEY_SPRING_APPLICATION_NAME, Constants.VALUE_SPRING_APPLICATION_NAME);
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_ALLOWOVERRIDE, "true");
		System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_ALLOWOVERRIDE_NONE, "true");
		
		
		if (Objects.isNull(System.getProperty(Constants.PARAM_SERVER_PORT))
				|| "".equals(System.getProperty(Constants.PARAM_SERVER_PORT))) {
			System.setProperty(Constants.PARAM_SERVER_PORT,Constants.VALUE_DEFAULT_SERVER_PORT);			
		}
		
		if (Objects.isNull(System.getProperty(Constants.PARAM_GIT_URL))
				|| "".equals(System.getProperty(Constants.PARAM_GIT_URL))) {
			
			if (Objects.isNull(System.getProperty(Constants.PARAM_CONFIGS_DIR))
					|| "".equals(System.getProperty(Constants.PARAM_CONFIGS_DIR))) {
				System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS, Constants.VALUE_DEFAULT_FILE_DIR);
				System.setProperty(Constants.KEY_SPRING_PROFILES_ACTIVE, Constants.VALUE_NATIVE_PROFILE);							
			}else{
				System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS, System.getProperty(Constants.PARAM_CONFIGS_DIR));
				System.setProperty(Constants.KEY_SPRING_PROFILES_ACTIVE, Constants.VALUE_NATIVE_PROFILE);							
			}			
		}else{
			System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_URI, System.getProperty(Constants.PARAM_GIT_URL));
			System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_FORCE_PULL, "true");
			if (Objects.nonNull(System.getProperty(Constants.PARAM_GIT_USERNAME))
					|| "".equals(System.getProperty(Constants.PARAM_GIT_USERNAME))) {
				System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME, System.getProperty(Constants.PARAM_GIT_USERNAME));
			}
			if (Objects.nonNull(System.getProperty(Constants.PARAM_GIT_PASSWORD))
					|| "".equals(System.getProperty(Constants.PARAM_GIT_PASSWORD))) {
				System.setProperty(Constants.KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD, System.getProperty(Constants.PARAM_GIT_PASSWORD));
			}
		}
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
