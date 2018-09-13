package dataProvider;

import enums.MobileDriverType;
import enums.PlatformToRun;
import enums.WebDriverType;
import enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath= System.getProperty("user.dir")+"/src/test/java/config/device.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }
    public String getChromeDriverPath(){
        String driverPath = properties.getProperty("chromedriverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:chromedriverPath");
    }
    public String getMozillaDriverPath(){
        String driverPath = properties.getProperty("mozziladriverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:mozziladriverPath");
    }
    public String getIEDriverPath(){
        String driverPath = properties.getProperty("iedriverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:iedriverPath");
    }


    public PlatformToRun getPlatform() {
        String platformName = properties.getProperty("platFormToRun");
        if(platformName == null){
            throw new RuntimeException("Set Platform to Run");
        }
        else if (platformName.equals("WEB")){
            return PlatformToRun.WEB;
        }
        else if(platformName.equals("MOBILE"))
        {
            return PlatformToRun.MOBILE;
        }
        else {
            return PlatformToRun.API;
        }
    }

    public WebDriverType getBrowser(){
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return WebDriverType.CHROME;
        else if(browserName.equalsIgnoreCase("firefox")) return WebDriverType.FIREFOX;
        else if(browserName.equals("iexplorer")) return WebDriverType.INTERNETEXPLORER;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public MobileDriverType getDeviceType(){
        String deviceType= properties.getProperty("platform");
        if(deviceType==null){
            throw new RuntimeException(" Device Platform Key value in Configuration.properties is not matched : " + deviceType);
        }else if (deviceType.equalsIgnoreCase("android")) return MobileDriverType.ANDROID;
         else return MobileDriverType.IOS;
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
        else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
    }


    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }
    public String getReportConfigPath(){
        String reportConfigPath = System. getProperty("user.dir")+properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

}
