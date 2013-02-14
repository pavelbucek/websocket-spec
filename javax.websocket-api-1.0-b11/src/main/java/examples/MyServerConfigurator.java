/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.server.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

    // obviously this would have more interesting behavior in real life !

public class MyServerConfigurator extends ServerEndpointConfigurator {
    
    public MyServerConfigurator() { 
    }
    
    // only override this one, all the other methods use container defaults.
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response) {
        // insert random headers
        // etc.
    }
    
    public int calculateFoo(Object data) {
        return 42; // optimistic 
    }
}
