/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.websocket.server.ServerHandshakeConfigurator;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

    // obviously this would have more interesting behavior in real life !

public class MyServerHandshakeConfigurator implements ServerHandshakeConfigurator {
    
    public MyServerHandshakeConfigurator() {
        
    }
    
   public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
       return null;
   }
    // implementation of this method has to query container for installed extensions.
    public List<Extension> getNegotiatedExtensions(List<Extension> requested) {
        return null;
    }
    public boolean checkOrigin(String originHeaderValue) {
        return true;
    }
    public boolean matchesURI(String uriOrTemplate, URI uri, Map<String, String> templateExpansion) {
        return false;
    }
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response) {
        
    }
    
    public boolean calculateFoo(Object data) {
        return true; // optimistic 
    }
}
