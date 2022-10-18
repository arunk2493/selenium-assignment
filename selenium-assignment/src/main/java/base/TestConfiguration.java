package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
   
    public Properties loadConfig() throws IOException{
        Properties props = new Properties();
        String propsPath = System.getProperty("user.dir")+"//config.properties";
        File propFile = new File(propsPath);
        FileInputStream inputProp = new FileInputStream(propFile);
        props.load(inputProp);
        return props;   
    }
}
