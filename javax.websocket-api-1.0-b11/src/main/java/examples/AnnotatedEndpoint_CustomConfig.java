/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.Session;
import javax.websocket.WebSocketOpen;
import javax.websocket.server.ServerEndpointConfiguration;
import javax.websocket.server.WebSocketEndpoint;

    @WebSocketEndpoint(
            value="/ae_normal",
            configuration=SpecialServerHandshakeConfiguration.class
            )
public class AnnotatedEndpoint_CustomConfig {
    @WebSocketOpen
    public void init(Session session, ServerEndpointConfiguration ec) {
        // ec object is an instantiated SpecialServerHandshakeConfiguration
        // (by virtue of its mandated multi-arg constructor)
        ec.getClass(); // this is AnnotatedEndpoint_NormalConfig.class
        ec.getPath(); // this is "/ae_normal"
        SpecialServerHandshakeConfiguration sshc = (SpecialServerHandshakeConfiguration) ec; // this works
    }
}
