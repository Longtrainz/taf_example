package framework.config;

import org.aeonbits.owner.ConfigFactory;


public class ConfigHelper {

	public static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class, System.getProperties());
	public static EnvConfig envConfig = ConfigFactory.create(EnvConfig.class, System.getProperties());

	private static DriverConfig getDriverConfig() {
		return ConfigFactory.newInstance().create(DriverConfig.class, System.getProperties());
	}

	private static EnvConfig getEnvConfig() {
		return ConfigFactory.newInstance().create(EnvConfig.class, System.getProperties());
	}

	public static String getWebUrl() {
		return getEnvConfig().webUrl();
	}

	public static String getApiUrl() {
		return getEnvConfig().apiUrl();
	}
}
