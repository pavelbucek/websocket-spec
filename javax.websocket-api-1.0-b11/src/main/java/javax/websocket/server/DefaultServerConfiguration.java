/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.websocket.server;

import java.net.URI;
import java.util.*;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
 

/**
 * The DefaultServerConfiguration is a concrete class that embodies all the configuration
 * parameters for an endpoint that is to be published as a server endpoint. Developers may
 * subclass this class in order to override the configuration behavior.
 *
 * @author dannycoward
 */
public final class DefaultServerConfiguration implements ServerEndpointConfiguration {
    private ConfigurationAlgorithms implementationAlgorithms;
    private String path;
    private Class endpointClass;
    private List<String> subprotocols;
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    private Map<String, Object> userProperties = new HashMap<String, Object>();

    /**
     * ADDED constructor, immutable
     * @param path
     * @param endpointClass
     * @param subprotocols
     * @param extensions
     * @param encoders
     * @param decoders 
     */
    // design choice: equivalent representation using builder.
    public DefaultServerConfiguration(String path,
                                    Class endpointClass,
                                    List<String> subprotocols,
                                    List<Extension> extensions,
                                    List<Encoder> encoders,
                                    List<Decoder> decoders) {
        this.path = Objects.requireNonNull(path, "path may not be null");
        this.endpointClass = Objects.requireNonNull(endpointClass, "endpoint class may not be null");
        this.subprotocols = Objects.requireNonNull(Collections.unmodifiableList(subprotocols), "subprotocols may not be null");
        this.extensions = Objects.requireNonNull(Collections.unmodifiableList(extensions), "extensions may not be null");
        this.encoders = Objects.requireNonNull(Collections.unmodifiableList(encoders), "encoders may not be null");
        this.decoders = Objects.requireNonNull(Collections.unmodifiableList(decoders), "decoders may not be null");
    }
    
    public ConfigurationAlgorithms getConfigurationAlgorithms() {
        return this.implementationAlgorithms;
        
    }


    /**
     * Editable map of user properties.
     */
    public final Map<String, Object> getUserProperties() {
        return this.userProperties;
    }
    /**
     * CHANGED final and Class not Class<? extends Endpoint>
     * Returns the class of the programmatic or annotated endpoint that this configuration configures.
     *
     * @return the class of the Endpoint.
     */
    @Override
    public final Class getEndpointClass() {
        return this.endpointClass;
    }

    /**
     * Return the Encoder implementations configured. These
     * will be used by the container to encode outgoing messages.
     *
     * @return the encoders.
     */
    @Override
    public final List<Encoder> getEncoders() {
        return this.encoders;
    }

    /**
     * Return the Decoder implementations configured. These
     * will be used by the container to decode incoming messages
     * into the expected custom objects on MessageHandler
     * callbacks.
     *
     * @return the encoders.
     */
    @Override
    public final List<Decoder> getDecoders() {
        return this.decoders;
    }

    /**
     * Return the path of this server configuration. The path is a relative URI
     * or URI-template.
     *
     * @return the path
     */
    @Override
    public final String getPath() {
        return path;
    }
    
    @Override
    public final List<String> getSubprotocols() {
        return this.subprotocols;
    }
    
    @Override
    public final List<Extension> getExtensions() {
        return this.extensions;
    }

    /**
     * The default implementation of this method returns, the first subprotocol in the list sent by the client that
     * the server supports, or null if there isn't one none. Subclasses may provide custom algorithms based on other factors.
     *
     * @param requestedSubprotocols the list of requested subprotocols.
     * @return the negotiated subprotocol.
     */
    @Override
    public String getNegotiatedSubprotocol(List<String> requestedSubprotocols) {
        return this.implementationAlgorithms.getNegotiatedSubprotocol(this.subprotocols, requestedSubprotocols);
    }

    /**
     * Provides a simple algorithm to return the list of extensions this server will
     * use for the web socket session: the configuration returns a list containing all of the requested
     * extensions passed to this method that it supports, using the order in the requested
     * extensions, the empty list if none. Subclasses may provide custom algorithms based on other factors.
     *
     * @param requestedExtensions the list of extensions requested by the client
     * @return the list of extensions that may be used.
     */
    @Override
    public List<Extension> getNegotiatedExtensions(List<Extension> requestedExtensions) {
        return this.implementationAlgorithms.getNegotiatedExtensions(requestedExtensions);
    }

    /**
     * Makes a check of the validity of the Origin header sent along with the opening
     * handshake following the recommendation at: <a href="http://tools.ietf.org/html/rfc6455#section-4.2">Sending the Server's Opening Handshake<a>.
     *
     * @param originHeaderValue The value of the Origin header.
     * @return whether the check passed or not.
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        return this.implementationAlgorithms.checkOrigin(originHeaderValue);
    }

    /**
     * This default implementation matches the incoming path to the configuration's URI or URI template if and only if
     * it is an exact match in the case the configuration is a URI, and if and only if it is a valid
     * expansion of the configuration URI template, in the case where the configuration is a URI template. Subclasses may override this method to provide
     * different matching policies.
     *
     * @param uri the URL of the incoming request
     * @return whether it matched this configuration or not.
     */
    @Override
    public boolean matchesURI(URI uri, Map<String, String> templateExpansion) {
        return this.implementationAlgorithms.matchesURI(this.path, uri, templateExpansion);
    }

    /**
     * The default server configuration does not make any changes to the response. Subclasses may
     * override this method in order to inspect the Http request headers of the openinghandshake, for example to track cookies
     * sent by the client. Additionally subclasses may choose to override this method to modify the outgoing
     * handshake response.
     * the outgoing handshake response
     *
     * @param request  the handshake request from the client
     * @param response the handshake response formulated by the container.
     */
    @Override
    public void modifyHandshake(HandshakeRequest request, HandshakeResponse response) {
    }
}
