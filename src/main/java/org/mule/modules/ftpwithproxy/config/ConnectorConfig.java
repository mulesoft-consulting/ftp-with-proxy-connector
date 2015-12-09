package org.mule.modules.ftpwithproxy.config;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.connectors.HTTPTunnelConnector;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

@ConnectionManagement(friendlyName = "Ftp Proxy Configuration")
public class ConnectorConfig {
    
    @Configurable
    @Placement(order=0, group="Proxy Configuration")
    private String proxyHost;

    @Configurable
    @Placement(order=1, group="Proxy Configuration")
    private Integer proxyPort;

    @Configurable
    @Optional
    @Placement(order=2, group="Proxy Configuration")
    private String proxyUsername;

    @Configurable
    @Optional
    @Placement(order=3, group="Proxy Configuration")
    private String proxyPassword;


    @Configurable
    @Placement(order=0, group="Connection")
    @FriendlyName(value="FTP Host")
    private String ftpHost;
    
    @Configurable 
    @Default(value="21")
    @Placement(order=1, group="Connection")
    @FriendlyName(value="FTP Port")
    private Integer ftpPort;

    private String ftpUsername;
    private String ftpPassword;
    
	private FTPClient client;
    
    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey @FriendlyName(value="FTP Username") String username, @Password @FriendlyName(value="FTP Password") String password) throws ConnectionException {
    	this.ftpUsername = username;
    	this.ftpPassword = password;
    	try {
			this.client = this.createFtpClient();
		} catch (Exception e) {
        	throw new ConnectionException(org.mule.api.ConnectionExceptionCode.INCORRECT_CREDENTIALS, e.getMessage(), e.getMessage(), e);
		}
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
    	try {
			this.client.disconnect(true);
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
	    	this.client = null;
		}
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
    	return (this.client != null && this.client.isConnected());
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "ftp_with_proxy_001_" + this.client.getConnector().hashCode();
    }

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public String getProxyUsername() {
		return proxyUsername;
	}

	public void setProxyUsername(String proxyUsername) {
		this.proxyUsername = proxyUsername;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public FTPClient getClient() {
		return client;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	private FTPClient createFtpClient() throws Exception {
		HTTPTunnelConnector proxy = null;
		
		if (StringUtils.isEmpty(this.proxyUsername) || StringUtils.isEmpty(this.proxyPassword)) {
			proxy = new HTTPTunnelConnector(this.proxyHost, this.proxyPort);
		} else {
			proxy = new HTTPTunnelConnector(this.proxyHost, this.proxyPort, this.proxyUsername, this.proxyPassword);
		}
		proxy.setConnectionTimeout(30000);
		
		

		FTPClient client = new FTPClient();
		client.setType(FTPClient.TYPE_BINARY);
		client.setConnector(proxy);
		client.connect(this.ftpHost, this.ftpPort);
		client.login(this.ftpUsername, this.ftpPassword);
		return client;
	}
	
}