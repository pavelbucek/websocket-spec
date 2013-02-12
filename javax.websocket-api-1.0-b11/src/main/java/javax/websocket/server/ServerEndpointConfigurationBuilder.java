/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.util.List;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Extension;

/**
 *
 * @author dannycoward
 */
public class ServerEndpointConfigurationBuilder {
    private static ServerHandshakeConfigurator defaultHandshakeConfigurator;
    private ServerHandshakeConfigurator handshakeConfigurator;
    private String path;
    private Class endpointClass;
    private List<String> subprotocols;
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    
    
    public static ServerEndpointConfigurationBuilder createBuilder(Class endpointClass, String path) {
        return new ServerEndpointConfigurationBuilder(endpointClass, path);
    }
    
    public DefaultServerEndpointConfiguration build() {
        return new DefaultServerEndpointConfiguration(
                this.endpointClass,
                this.path,
                this.subprotocols,
                this.extensions,
                this.encoders,
                this.decoders,
                this.handshakeConfigurator
                );
    }
    
    private ServerEndpointConfigurationBuilder(Class endpointClass, String path) {
        this.endpointClass = endpointClass;
        this.path = path;
    }
    /**
     * Returns the class of the programmatic or annotated endpoint that this configuration configures.
     *
     * @return the class of the Endpoint.
     */
    public Class getEndpointClass() {
        return this.endpointClass;
    }

    /**
     * Return the Encoder implementations configured. These
     * will be used by the container to encode outgoing messages.
     *
     * @return the encoders.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    
    public ServerEndpointConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }


    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    public ServerEndpointConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }


    public String getPath() {
        return path;
    }
    
    public ServerEndpointConfigurationBuilder setPath(String path) {
        this.path = path;
        return this;
    }
    
    public ServerEndpointConfigurationBuilder setSubprotocols(List<String> subprotocols) {
        this.subprotocols = subprotocols;
        return this;
    }
    
    public List<String> getSubprotocols() {
        return this.subprotocols;
    }
    
    public ServerEndpointConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }
    
    public List<Extension> getExtensions() {
        return this.extensions;
    }
    
    public ServerHandshakeConfigurator getHandshakeConfigurator() {
        return this.getHandshakeConfigurator();
    } 
    
    public ServerEndpointConfigurationBuilder setHandshakeConfigurator(ServerHandshakeConfigurator handshakeConfigurator) {
        this.handshakeConfigurator = handshakeConfigurator;
        return this;
    }

}
