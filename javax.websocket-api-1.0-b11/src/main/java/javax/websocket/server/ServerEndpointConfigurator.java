/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import javax.websocket.ContainerProvider;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;

   /** 
    * The ServerEndpointConfigurator interface may be implemented by developers who want to
    * provide custom configuration algorithms, such as intercepting the opening handshake, or
    * providing arbitrary methods and algorithms that can be accessed from each endpoint
    * instance configured with this configurator.
    * 
    * The implementation must provide a platform default configurator loading using the service
    * loader.
    */
public abstract class ServerEndpointConfigurator {
    private ServerEndpointConfigurator containerDefaultConfigurator;
    
    static ServerEndpointConfigurator fetchContainerDefaultConfigurator() {
        for (ServerEndpointConfigurator impl : ServiceLoader.load(ServerEndpointConfigurator.class)) {
            return impl;
        }
        throw new RuntimeException("Cannot load platform configurator");
    }
    
    ServerEndpointConfigurator getContainerDefaultConfigurator() {
        if (this.containerDefaultConfigurator == null) {
            this.containerDefaultConfigurator = fetchContainerDefaultConfigurator();
        }
        return this.containerDefaultConfigurator;
        
    }
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        return this.getContainerDefaultConfigurator().getNegotiatedSubprotocol(supported, requested);
    }
    
    // implementation of this method has to query container for installed extensions.
    public List<Extension> getNegotiatedExtensions(List<Extension> requested) {
        return this.getContainerDefaultConfigurator().getNegotiatedExtensions(requested);
    }
    
    public boolean checkOrigin(String originHeaderValue) {
        return this.getContainerDefaultConfigurator().checkOrigin(originHeaderValue);
    }
    
    public boolean matchesURI(String uriOrTemplate, URI uri, Map<String, String> templateExpansion) {
        return this.getContainerDefaultConfigurator().matchesURI(uriOrTemplate, uri, templateExpansion);
    }
    
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response) {
        // nothing.
    }

}
