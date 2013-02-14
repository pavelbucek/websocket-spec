/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;

    /** 
     * The ServerEndpointConfigurator interface may be implemented by developers who want to
    * provide custom configuration algorithms, such as intercepting the opening handshake, or
    * providing arbitrary methods and algorithms that can be accessed from each endpoint
    * instance configured with this configurator.
    */
public interface ServerEndpointConfigurator {
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested);
    // implementation of this method has to query container for installed extensions.
    public List<Extension> getNegotiatedExtensions(List<Extension> requested);
    public boolean checkOrigin(String originHeaderValue);
    public boolean matchesURI(String uriOrTemplate, URI uri, Map<String, String> templateExpansion);
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response);

}
