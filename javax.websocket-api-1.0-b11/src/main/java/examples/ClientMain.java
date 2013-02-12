/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.net.*;
import java.util.*;
import javax.websocket.*;


public class ClientMain {

    public static void main(String[] args) throws Exception {
        WebSocketContainer wsc = ContainerProvider.getClientContainer();
        
        // 1. deploy the simple annotated endpoint
        wsc.connectToServer(AnnotatedClientEndpoint_NormalConfig.class, new URI("ws://server.com/app/foo"));
        // deploy the annotated endpoint with handshake interceptor
        wsc.connectToServer(AnnotatedClientEndpoint_CustomConfig.class, new URI("ws://server.com/app/bar"));
        
        // 2. deploy the simple programmatic endpoint endpoint
        List<String> subprotocols = new ArrayList<String>();
        subprotocols.add("yoga");
        
        // 3. deploy the programmatic endpoint simply
        ClientEndpointConfiguration cec = ClientEndpointConfigurationBuilder.createBuilder().build();
        wsc.connectToServer(ProgrammaticEndpoint.class, cec, new URI("ws://server.com/app/yoga"));
        
        // 4. deploy the programmatic endpoint with subprotocols and handshake interceptor
        ClientEndpointConfiguration customCec = ClientEndpointConfigurationBuilder.createBuilder()
                .setPreferredSubprotocols(subprotocols)
                .setClientHandshakeConfigurator(new MyClientConfigurator())
                .build();
        wsc.connectToServer(ProgrammaticEndpoint.class, customCec, new URI("ws://server.com/app/yogaboga"));
        
    }
        
}
