package lira.leo.mv.configserver.util;

import java.io.File;
import java.nio.file.Paths;

public interface Constants {
	public static final String PARAM_GIT_URL = "git.url";
	public static final String PARAM_GIT_USERNAME = "git.username";
	public static final String PARAM_GIT_PASSWORD = "git.password";
	public static final String PARAM_SERVER_PORT = "server.port";
	public static final String PARAM_CONFIGS_DIR = "configs.dir";

	
	public static final String KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_URI = "spring.cloud.config.server.git.uri";
	public static final String KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_FORCE_PULL = "spring.cloud.config.server.git.force-pull";
	public static final String KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME = "spring.cloud.config.server.git.username";
	public static final String KEY_SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD = "spring.cloud.config.server.git.password";
	public static final String KEY_SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS = "spring.cloud.config.server.native.search-locations";
	public static final String KEY_SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	public static final String KEY_MANAGEMENT_SECURITY_ENABLED = "management.security.enabled";
	public static final String KEY_SPRING_CONFIG_NAME = "spring.config.name";
	public static final String KEY_SPRING_APPLICATION_NAME = "spring.application.name";
	public static final String KEY_SPRING_CLOUD_CONFIG_ALLOWOVERRIDE = "spring.cloud.config.allowOverride";
	public static final String KEY_SPRING_CLOUD_CONFIG_ALLOWOVERRIDE_NONE = "spring.cloud.config.override-none";
	
	
	
	public static final String VALUE_SPRING_CONFIG_NAME = "config-server";
	public static final String VALUE_SPRING_APPLICATION_NAME = "config-server";
	public static final String VALUE_DEFAULT_SERVER_PORT = "8888";
	public static final String VALUE_NATIVE_PROFILE = "native";
	public static final String VALUE_DEFAULT_FILE_DIR = "file:" + Paths.get(System.getProperty("user.dir"))
			.getFileSystem().getRootDirectories().iterator().next().toFile().getAbsolutePath() + "configserver" + File.separator
			+ "conf" + File.separator + "MS";
}
