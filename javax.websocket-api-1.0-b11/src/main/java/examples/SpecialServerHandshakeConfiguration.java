/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.*;
import javax.websocket.*;
import javax.websocket.server.*;
/**
 *
 * @author dannycoward
 */
public class SpecialServerHandshakeConfiguration extends DefaultServerConfiguration {
    
    public SpecialServerHandshakeConfiguration(String path,
                                    Class endpointClass,
                                    List<String> subprotocols,
                                    List<Extension> extensions,
                                    List<Encoder> encoders,
                                    List<Decoder> decoders) {

        super(path, endpointClass, new ArrayList<String>(), extensions, encoders, decoders);
    }
    
    public void modifyHandshake(HandshakeRequest hr, HandshakeResponse hrr) {
        // custom stuff
    }
    
    public Object getSpringInitParamsAndProcessDodadForEndpointsInTomcatAndGetDatabaseConnection() {
        return null;
    }
}
