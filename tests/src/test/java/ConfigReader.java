import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigReader {
    private static Config conf = ConfigFactory.load("application.conf");

    public static String getProperty(String keyword) {
        return conf.getString(keyword);
    }
}