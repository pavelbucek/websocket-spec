/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.net.*;
import java.util.*;
import javax.websocket.*;


public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        WebSocketContainer wsc = ContainerProvider.getClientContainer();
        
        wsc.connectToServer(AnnotatedClientEndpoint_NormalConfig.class, new URI("ws://server.com/app/foo"));
        
        List<String> subprotocols = new ArrayList<String>();
        subprotocols.add("yoga");
        ClientEndpointConfiguration cec = DefaultClientConfigurationBuilder.createBuilder()
                .setPreferredSubprotocols(subprotocols)
                .build();
        
        
        wsc.connectToServer(ProgrammaticEndpoint.class, cec, new URI("ws://server.com/app/yoga"));
        
    }
        
}
