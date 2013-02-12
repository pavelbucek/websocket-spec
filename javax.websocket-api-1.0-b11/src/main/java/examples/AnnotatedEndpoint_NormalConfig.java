/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.*;
import javax.websocket.server.*;

@WebSocketEndpoint("/ae_normal")

public class AnnotatedEndpoint_NormalConfig {
    @WebSocketOpen
    public void init(Session session, ServerEndpointConfiguration ec) {
        // ec object is some container implemented object that implements
        // ServerEndpointConfiguration
        ec.getEndpointClass(); // this is AnnotatedEndpoint_NormalConfig.class
        ec.getPath(); // this is "/ae_normal"
    }
}
