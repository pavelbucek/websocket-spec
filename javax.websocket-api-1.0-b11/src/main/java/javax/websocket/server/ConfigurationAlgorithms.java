/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.net.URI;
import java.util.*;
import javax.websocket.*;

public interface ConfigurationAlgorithms {
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested);
    // implementation of this method has to query container for installed extensions.
    public List<Extension> getNegotiatedExtensions(List<Extension> requested);
    public boolean checkOrigin(String originHeaderValue);
    public boolean matchesURI(String uriOrTemplate, URI uri, Map<String, String> templateExpansion);
    
}
