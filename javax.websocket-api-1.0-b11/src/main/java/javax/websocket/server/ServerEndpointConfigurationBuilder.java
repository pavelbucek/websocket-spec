/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.util.Collections;
import java.util.List;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Extension;
/**
 * Builder class that creates ServerEndpointConfiguration objects that can
 * be used to deploy server endpoints.
 * @author dannycoward
 */
public class ServerEndpointConfigurationBuilder {
    private static ServerEndpointConfigurator defaultHandshakeConfigurator;
    private ServerEndpointConfigurator handshakeConfigurator;
    private String path;
    private Class endpointClass;
    private List<String> subprotocols = Collections.emptyList();
    private List<Extension> extensions = Collections.emptyList();
    private List<Encoder> encoders = Collections.emptyList();
    private List<Decoder> decoders = Collections.emptyList();
    
    /**
     * Creates the builder with the mandatory information of the endpoint class (programmatic
     * or annotated) and the relative URI or URI-template to use.
     * @param endpointClass
     * @param path
     * @return 
     */
    public static ServerEndpointConfigurationBuilder createBuilder(Class endpointClass, String path) {
        return new ServerEndpointConfigurationBuilder(endpointClass, path);
    }
    /**
     * Builds the configuration object.
     * @return 
     */
    public ServerEndpointConfiguration build() {
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
        if (endpointClass == null) {
            throw new IllegalArgumentException("endpointClass cannot be null");
        }
        this.endpointClass = endpointClass;
        this.setPath(path);
    }
    /**
     * Returns the class of the programmatic or annotated endpoint.
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
    
    /** 
     * Sets the list of encoders to use.
     */
    public ServerEndpointConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }

    /** 
     * Return the decoders to use.
     */ 
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    /**
     * Sets the decoders to use.
     * 
     * @param decoders
     * @return 
     */
    public ServerEndpointConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }

    /** 
     * Returns the path to use.
     */
    public String getPath() {
        return path;
    }
    /** 
     * Resets the path to use.
     */
    public ServerEndpointConfigurationBuilder setPath(String path) {
        if (path == null) {
            throw new IllegalStateException("Path cannot be null");
        }
        this.path = path;
        return this;
    }
    
    /**
     * Sets the subprotocols to use.
     * 
     * @param subprotocols
     * @return 
     */
    public ServerEndpointConfigurationBuilder setSubprotocols(List<String> subprotocols) {
        this.subprotocols = subprotocols;
        return this;
    }
    /**
     * Returns the subprotocols to use.
     * 
     * @return 
     */
    public List<String> getSubprotocols() {
        return this.subprotocols;
    }
    
    /**
     * Sets the extensions to use.
     * 
     * @param extensions
     * @return 
     */
    public ServerEndpointConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }
    /**
     * Gets the extensions to use.
     * @return 
     */
    public List<Extension> getExtensions() {
        return this.extensions;
    }
    
    /** 
     * Returns the custom configurator to use.
     */
    public ServerEndpointConfigurator getServerEndpointConfigurator() {
        return this.handshakeConfigurator;
    } 
    /** 
     * Sets the custom configurator to use.
     */
    public ServerEndpointConfigurationBuilder setServerEndpointConfigurator(ServerEndpointConfigurator handshakeConfigurator) {
        this.handshakeConfigurator = handshakeConfigurator;
        return this;
    }
    


}
