/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.*;


@WebSocketClient(
        subprotocols="yoga",
        configuration=MyClientConfigurator.class)
public class AnnotatedClientEndpoint_CustomConfig {
     @WebSocketOpen
    public void init(Session session, ClientEndpointConfiguration ec) {
         // custom behavior
       ((MyClientConfigurator) ec.getClientHandshakeConfigurator()).calculateFoo(this);
    }
}
