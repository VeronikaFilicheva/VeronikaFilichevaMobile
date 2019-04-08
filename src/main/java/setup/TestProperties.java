package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestProperties {
    String currentPropertyFile;
    String resourcePath = ".\\src\\main\\resources\\";
    Properties currentProps = new Properties();

    protected void setPropertyFile(setup.Properties propertyFile) {
        currentPropertyFile = propertyFile.getFileName();
    }

    Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(resourcePath + currentPropertyFile);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) currentProps = getCurrentProps();
        return currentProps.getProperty(propKey, null);
    }
}
