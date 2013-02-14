/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.Session;
import javax.websocket.WebSocketOpen;
import javax.websocket.server.*;

    @WebSocketEndpoint(
            value="/ae_normal", // no-one can override this 
            subprotocols = "yoga", // no-one can override this
            configuration=MyServerConfigurator.class  // provides custom handshake overrides 
            )
public class AnnotatedServerEndpoint_CustomConfig {
    @WebSocketOpen
    public void init(Session session, ServerEndpointConfiguration ec) {
        ec.getEndpointClass(); // this is AnnotatedEndpoint_NormalConfig.class
        ec.getPath(); // this is "/ae_normal"
        // and handshake override, and/or arbitrary
        ((MyServerConfigurator) ec.getServerHandshakeConfigurator()).calculateFoo(session.getId());
    }
}
