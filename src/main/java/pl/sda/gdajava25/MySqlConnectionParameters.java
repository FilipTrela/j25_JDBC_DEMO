package pl.sda.gdajava25;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class MySqlConnectionParameters {
    private static final String PROPERTIES_FILE_NAME = "/jdbc.poperties";
    private Properties properties;
    private String dbHost;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String dbName;

    public MySqlConnectionParameters() throws IOException {
        loadConfigFrom(PROPERTIES_FILE_NAME);

        dbHost = loadParameter("jdbc.database.host");
        dbPort = loadParameter("jdbc.database.port");
        dbUsername = loadParameter("jdbc.username");
        dbPassword = loadParameter("jdbc.password");
        dbName = loadParameter("jdbc.database.name");
    }

    private void loadConfigFrom(String resorceName) throws IOException {
        properties = new Properties();

        InputStream propertiesStream = this.getClass().getResourceAsStream(resorceName);
        if (propertiesStream == null) {
            throw new FileNotFoundException("Resource files cannot be loaded.");
        }
        properties.load(propertiesStream);
    }

    private String loadParameter(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
