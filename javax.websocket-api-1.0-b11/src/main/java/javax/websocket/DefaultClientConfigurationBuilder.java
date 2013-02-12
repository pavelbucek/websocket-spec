/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.util.List;
import java.util.Map;

/**
 *
 * @author dannycoward
 */
public class DefaultClientConfigurationBuilder {
    private List<String> preferredSubprotocols;
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    private ClientHandshakeConfigurator clientHandshakeConfigurator;
    
    public static DefaultClientConfigurationBuilder createBuilder() {
        return new DefaultClientConfigurationBuilder();
    }
    
    
    public DefaultClientConfiguration build() {
        return new DefaultClientConfiguration(
            this.preferredSubprotocols,
            this.extensions,
            this.encoders,
            this.decoders,
            this.clientHandshakeConfigurator);
    }
    
    public ClientHandshakeConfigurator getClientHandshakeConfigurator() {
        return this.clientHandshakeConfigurator;
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
    
    public DefaultClientConfigurationBuilder setPreferredSubprotocols(List<String> preferredSubprotocols) {
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
    
    public DefaultClientConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }


    /**
     * Assign the list of encoders this client will use.
     *
     * @return the encoder list.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    
    public DefaultClientConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }



    /**
     * Assign the list of decoders this client will use.
     *
     * @return the decoders to use.
     */
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    public DefaultClientConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }
    
   
}
