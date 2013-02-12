/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.*;
import javax.websocket.Endpoint;
import javax.websocket.server.*;


public class MyServerDeployment implements ServerApplicationConfiguration {

    // I am going to deploy the endpoint in two configurations
    @Override
    public Set<ServerEndpointConfiguration> getEndpointConfigurations(Set<Class<? extends Endpoint>> endpointClasses) {
       // there is just one endpoint class that got scanned. But I am going to deploy it twice !!
        Set<ServerEndpointConfiguration> logicalEndpoints = new HashSet<ServerEndpointConfiguration>();
        ServerEndpointConfiguration config;
        
        List subprotocols = new ArrayList();
        subprotocols.add("yoga");
        // deploy the endpoint using a basic configuration
        config = ServerEndpointConfigurationBuilder.createBuilder(ProgrammaticEndpoint.class, "/pe-vanilla")
                    .setSubprotocols(subprotocols)
                    .build();
        logicalEndpoints.add(config);
        
        // deploy the endpoint using a custom configuration
        config = ServerEndpointConfigurationBuilder.createBuilder(ProgrammaticEndpoint.class, "/pe-custom")
                .setSubprotocols(subprotocols)
                .setHandshakeConfigurator(new MyServerConfigurator())
                .build();
        logicalEndpoints.add(config);
        
        return logicalEndpoints;
        
    }


    /** I want all the annotated endpoints to be deployed. So I just
     pass on the list of ones the container scanned in. */
    public Set<Class> getAnnotatedEndpointClasses(Set<Class> scanned) {
        // the scan reveals two annotated endpoint classes
        // so I will return them both
        // and the container will deploy the both.
        return scanned;
    }
}
