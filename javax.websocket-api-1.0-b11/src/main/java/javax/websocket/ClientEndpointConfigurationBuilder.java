/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.util.List;
import java.util.Map;

/**
 * Builder class for DefaultClientEndpointConfiguration instances.
 * @author dannycoward
 */
public class ClientEndpointConfigurationBuilder {
    private List<String> preferredSubprotocols;
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    private ClientEndpointConfigurator clientHandshakeConfigurator;
    
    /**
     * Creates a new builder object.
     */
    public static ClientEndpointConfigurationBuilder createBuilder() {
        return new ClientEndpointConfigurationBuilder();
    }
    
    /**
     * build the configuration object once you are done setting the data.
     * @return 
     */
    public ClientEndpointConfiguration build() {
        return new DefaultClientEndpointConfiguration(
            this.preferredSubprotocols,
            this.extensions,
            this.encoders,
            this.decoders,
            this.clientHandshakeConfigurator);
    }
    
    /**
     * Return the configurator this builder will use.
     * @return 
     */
    public ClientEndpointConfigurator getClientHandshakeConfigurator() {
        return this.clientHandshakeConfigurator;
    }
    
    /**
     * Sets the handshake configurator for the configuration this builder will build.
     * @param clientHandshakeConfigurator
     * @return 
     */
    public ClientEndpointConfigurationBuilder setClientHandshakeConfigurator(ClientEndpointConfigurator clientHandshakeConfigurator) {
        this.clientHandshakeConfigurator = clientHandshakeConfigurator;
        return this;
    }

    /**
     * Return the protocols, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the preferred subprotocols.
     */
    public List<String> getPreferredSubprotocols() {
        return this.preferredSubprotocols;
    }
    /**
     * set the preferred sub protocols for the configuration this builder will build.
     * @param preferredSubprotocols
     * @return 
     */
    public ClientEndpointConfigurationBuilder setPreferredSubprotocols(List<String> preferredSubprotocols) {
        this.preferredSubprotocols = preferredSubprotocols;
        return this;
    }



    /**
     * Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the extension list.
     */
    public List<Extension> getExtensions() {
        return this.extensions;
    }
    /**
     * set the extensions for the configuration this builder will build.
     * @param preferredSubprotocols
     * @return 
     */
    public ClientEndpointConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }


    /**
     * Return the list of encoders this client will use.
     *
     * @return the encoder list.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    /**
     * Assign the list of encoders this client will use.
     *
     * @return this.
     */
    public ClientEndpointConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }



    /**
     * Return the list of decoders this client will use.
     *
     * @return the decoders to use.
     */
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
        /**
     * Assign the list of decoders this client will use.
     *
     * @return this.
     */
    public ClientEndpointConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }
    
   
}
