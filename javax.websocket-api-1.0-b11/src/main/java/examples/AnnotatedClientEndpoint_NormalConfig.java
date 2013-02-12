/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.*;


@WebSocketClient
public class AnnotatedClientEndpoint_NormalConfig {
     @WebSocketOpen
    public void init(Session session, ClientEndpointConfiguration ec) {
       
    }
}
