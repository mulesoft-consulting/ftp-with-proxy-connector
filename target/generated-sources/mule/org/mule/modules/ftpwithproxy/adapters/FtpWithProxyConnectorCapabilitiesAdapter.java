
package org.mule.modules.ftpwithproxy.adapters;

import javax.annotation.Generated;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;
import org.mule.modules.ftpwithproxy.FtpWithProxyConnector;


/**
 * A <code>FtpWithProxyConnectorCapabilitiesAdapter</code> is a wrapper around {@link FtpWithProxyConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-12-09T01:00:54-03:00", comments = "Build UNNAMED.2613.77421cc")
public class FtpWithProxyConnectorCapabilitiesAdapter
    extends FtpWithProxyConnector
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(ModuleCapability capability) {
        if (capability == ModuleCapability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == ModuleCapability.CONNECTION_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

}
