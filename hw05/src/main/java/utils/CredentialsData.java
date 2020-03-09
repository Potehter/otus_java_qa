package utils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CredentialsData {
    public static Properties getProps() {
        Properties props = new Properties();
        Path paths = Paths.get("src/main/resources/test.properties");
        FileReader fReader;
        try {
            fReader = new FileReader(paths.toString());
            props.load(fReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
