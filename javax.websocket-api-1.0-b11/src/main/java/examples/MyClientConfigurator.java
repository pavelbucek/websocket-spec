/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.List;
import java.util.Map;
import javax.websocket.*;

/**
 *
 * @author dannycoward
 */
public class MyClientConfigurator extends ClientEndpointConfigurator {

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        // fancy stuff
    }


    @Override
    public void afterResponse(HandshakeResponse hr) {
        // fancy stuff
    }
    
    public int calculateFoo(Object o) {
        return 42;
    }
}
