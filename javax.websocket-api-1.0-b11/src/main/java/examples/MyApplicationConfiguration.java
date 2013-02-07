/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.*;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.Extension;
import javax.websocket.server.*;


public class MyApplicationConfiguration implements ServerApplicationConfiguration {
    
    /** This is the old method for deploying programmatic endpoints that is replaced by the new one below.*/
    @Override
    public Set<Class<? extends ServerEndpointConfiguration>> getEndpointConfigurationClasses(Set<Class<? extends ServerEndpointConfiguration>> scanned) {
        return null;
    }
    
    @Override
    public Set<ServerEndpointConfiguration> getEndpointConfigurations(Set<Class<? extends Endpoint>> endpointClasses) {
       // there is just one endpoint class that got scanned. But I am going to deploy it twice !!
        Set<ServerEndpointConfiguration> logicalEndpoints = new HashSet<ServerEndpointConfiguration>();
        ServerEndpointConfiguration config;
        
        config = new DefaultServerConfiguration("/pe-simple1",
                                    ProgrammaticEndpoint.class,
                                    new ArrayList(),
                                    new ArrayList(),
                                    new ArrayList(),
                                    new ArrayList()
                                    new CustomAlgorithmImpl()); // yes we could provide a couple of convenience constructors.
        logicalEndpoints.add(config);
        
        config = new SpecialServerHandshakeConfiguration("/pe-custom",
                                    ProgrammaticEndpoint.class,
                                    new ArrayList(),
                                    new ArrayList(),
                                    new ArrayList(),
                                    new ArrayList());
        logicalEndpoints.add(config);
        
        return logicalEndpoints;
        
    }


    /** I want all the annotated endpoints to be deployed. This method is implemented
     in the same way as the container would implement it if there was no
     ServerApplicationConfiguration class. */
    public Set<Class> getAnnotatedEndpointClasses(Set<Class> scanned) {
        // the scan reveals two annotated endpoint classes
        // so I will return them both
        // and the container will deploy the both.
        return scanned;
    }
}
