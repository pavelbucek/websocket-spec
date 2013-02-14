
package examples;

import javax.websocket.*;


@WebSocketClient(
        subprotocols="yoga", // no-one can override this
        configuration=MyClientConfigurator.class)
public class AnnotatedClientEndpoint_CustomConfig {
     @WebSocketOpen
    public void init(Session session, ClientEndpointConfiguration ec) {
         // custom handshake behavior, can obtain reference to the configurator here
       ((MyClientConfigurator) ec.getClientEndpointConfigurator()).calculateFoo(this);
    }
}
