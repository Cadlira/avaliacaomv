package lira.leo.mv.userrs.util;

public interface Constants {
	public static final String PARAM_CONFIG_SERVER_URI = "config.server.uri";
	public static final String PARAM_CONFIG_PROFILE = "config.profile";
	public static final String PARAM_CONFIG_LABEL = "config.label";

	public static final String KEY_SPRING_CONFIG_NAME = "spring.config.name";
	public static final String KEY_SPRING_APPLICATION_NAME = "spring.application.name";
	public static final String KEY_SPRING_CLOUD_CONFIG_URI = "spring.cloud.config.uri";
	public static final String KEY_SPRING_CLOUD_CONFIG_PROFILE = "spring.cloud.config.profile";
	public static final String KEY_SPRING_CLOUD_CONFIG_LABEL = "spring.cloud.config.label";
	public static final String KEY_SPRING_CLOUD_CONFIG_ENABLED = "spring.cloud.config.enabled";
	public static final String KEY_ENDPOINTS_RESTART_ENABLED = "endpoints.restart.enabled";
	public static final String KEY_MANAGEMENT_SECURITY_ENABLED = "management.security.enabled";
	public static final String KEY_SPRING_CLOUD_CONFIG_FAILFAST = "spring.cloud.config.failFast";
	public static final String KEY_SPRING_CLOUD_CONFIG_RETRY_INITIAL_INTERVAL = "spring.cloud.config.retry.initialInterval";
	public static final String KEY_SPRING_CLOUD_CONFIG_RETRY_MAX_ATTEMPTS = "spring.cloud.config.retry.maxAttempts";


	public static final String VALUE_SPRING_CONFIG_NAME = "user-rs";
	public static final String VALUE_SPRING_APPLICATION_NAME = "user-rs";
	public static final String DEFAULT_VALUE_SPRING_CLOUD_CONFIG_URI = "http://localhost:8888";
}
