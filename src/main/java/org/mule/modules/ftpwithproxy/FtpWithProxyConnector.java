package org.mule.modules.ftpwithproxy;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.modules.ftpwithproxy.config.ConnectorConfig;

@Connector(name="ftp-with-proxy", friendlyName="Ftp With Proxy")
public class FtpWithProxyConnector {

    @Config
    ConnectorConfig config;

    /**
     * Gets the file content
     *
     * {@sample.xml ../../../doc/ftp-with-proxy-connector.xml.sample ftp-with-proxy:getFile}
     *
     * @param path file absolute path
     * @param fileName file name with extension
     * @return the file
     * @throws Exception 
     */
    @Processor
    public FileInputStream getFile(String path, String fileName) throws Exception {
    	File tempFile = new File(this.getTempDirectory());

    	FTPClient client = this.config.getClient();
    	client.changeDirectory(path);
    	client.download(fileName, tempFile);
    	client.disconnect(true);
    	return new FileInputStream(tempFile);
    }
    
    /**
     * List all the files
     *
     * {@sample.xml ../../../doc/ftp-with-proxy-connector.xml.sample ftp-with-proxy:list}
     *
     * @param path file absolute path
     * @return the file
     * @throws Exception 
     */
    @Processor
    public List<FTPFile> list(String path) throws Exception {
    	FTPClient client = this.config.getClient();
    	client.changeDirectory(path);

    	FTPFile[] list = client.list();
    	client.disconnect(true);
		return Arrays.asList(list);
    }
    
    /**
     * List all the files
     *
     * {@sample.xml ../../../doc/ftp-with-proxy-connector.xml.sample ftp-with-proxy:put}
     *
     * @param path file absolute path
     * @return the file
     * @throws Exception 
     */
    @Processor
    public void put(String sourceFileName, String destinationPath) throws Exception {
    	FTPClient client = this.config.getClient();
    	client.changeDirectory(destinationPath);
    	client.upload(new File(sourceFileName));
    	client.disconnect(true);
    }
        
    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

    protected String getTempDirectory() {
    	if (isWindows()) {
    		return "C:\\temp";
    	} else {
    		return "/tmp";
    	}
    }
    
    protected Boolean isWindows() {
    	String osName = System.getProperty("os.name");
    	return osName.toLowerCase().contains("window");
    }
}