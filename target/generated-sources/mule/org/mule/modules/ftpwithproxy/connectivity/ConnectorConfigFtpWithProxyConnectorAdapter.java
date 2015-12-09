
package org.mule.modules.ftpwithproxy.connectivity;

import javax.annotation.Generated;
import org.mule.api.ConnectionException;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionAdapter;
import org.mule.devkit.internal.connection.management.TestableConnection;
import org.mule.modules.ftpwithproxy.config.ConnectorConfig;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-12-09T01:00:54-03:00", comments = "Build UNNAMED.2613.77421cc")
public class ConnectorConfigFtpWithProxyConnectorAdapter
    extends ConnectorConfig
    implements ConnectionManagementConnectionAdapter<ConnectorConfig, ConnectionManagementConfigFtpWithProxyConnectorConnectionKey> , TestableConnection<ConnectionManagementConfigFtpWithProxyConnectorConnectionKey>
{


    @Override
    public void connect(ConnectionManagementConfigFtpWithProxyConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getUsername(), connectionKey.getPassword());
    }

    @Override
    public void test(ConnectionManagementConfigFtpWithProxyConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getUsername(), connectionKey.getPassword());
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }

    @Override
    public String connectionId() {
        return super.connectionId();
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override
    public ConnectorConfig getStrategy() {
        return this;
    }

}
