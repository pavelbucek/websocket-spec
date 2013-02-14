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

public class MyServerConfigurator implements ServerEndpointConfigurator {
    
    public MyServerConfigurator() { 
    }
    
    @Override
   public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        // just use the container default algorith
       return ServerEndpointConfigurationBuilder.getContainerDefaultServerEndpointConfigurator().getNegotiatedSubprotocol(supported, requested);
   }
    // implementation of this method has to query container for installed extensions.
    public List<Extension> getNegotiatedExtensions(List<Extension> requested) {
        // just use the container default algorith
       return ServerEndpointConfigurationBuilder.getContainerDefaultServerEndpointConfigurator().getNegotiatedExtensions(requested);
    }
    public boolean checkOrigin(String originHeaderValue) {
        // everything is fine !
        return true; 
    }
    public boolean matchesURI(String uriOrTemplate, URI uri, Map<String, String> templateExpansion) {
        // just use the container default algorith
       return ServerEndpointConfigurationBuilder.getContainerDefaultServerEndpointConfigurator().matchesURI(uriOrTemplate, uri, templateExpansion);
    }
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response) {
        // insert random headers
        // etc.
    }
    
    public int calculateFoo(Object data) {
        return 42; // optimistic 
    }
}
