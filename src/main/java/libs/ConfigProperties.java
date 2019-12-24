package libs;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/libs/ConfigProperties.properties"})
public interface ConfigProperties extends Config {
	String base_url();
	String valid_password();
	long TIME_FOR_DEFAULT_WAIT();
	long TIME_FOR_EXPLICIT_WAIT_LOW();
}
