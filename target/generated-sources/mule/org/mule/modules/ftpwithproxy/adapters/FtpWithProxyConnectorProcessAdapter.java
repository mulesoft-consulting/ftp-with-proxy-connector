
package org.mule.modules.ftpwithproxy.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.ftpwithproxy.FtpWithProxyConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>FtpWithProxyConnectorProcessAdapter</code> is a wrapper around {@link FtpWithProxyConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-12-09T01:00:54-03:00", comments = "Build UNNAMED.2613.77421cc")
public class FtpWithProxyConnectorProcessAdapter
    extends FtpWithProxyConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<FtpWithProxyConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, FtpWithProxyConnectorCapabilitiesAdapter> getProcessTemplate() {
        final FtpWithProxyConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,FtpWithProxyConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, FtpWithProxyConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, FtpWithProxyConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
