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
package javax.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The DefaultClientConfiguration is a concrete implementation of a client configuration. Developers
 * may subclass this class in order to provide their own custom configuration behaviors.
 *
 * @author dannycoward
 */
public class DefaultClientConfiguration implements ClientEndpointConfiguration {
    private List<String> preferredSubprotocols;
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    private Map<String, Object> userProperties = new HashMap<String, Object>();

    /**
     * ADDED
     * Creates a client configuration with preferred sub protocols, extensions, decoders and encoders.
     */
    public DefaultClientConfiguration(
            List<String> preferredSubprotocols,
            List<Extension> extensions,
            List<Encoder> encoders,
            List<Decoder> decoders) {
        this.preferredSubprotocols = Objects.requireNonNull(Collections.unmodifiableList(preferredSubprotocols), "preferredSubprotocols cannot be null");
        this.extensions = Objects.requireNonNull(Collections.unmodifiableList(extensions), "extensions cannot be null");
        this.encoders = Objects.requireNonNull(Collections.unmodifiableList(encoders), "encoders cannot be null");
        this.decoders = Objects.requireNonNull(Collections.unmodifiableList(decoders), "decoders cannot be null");
    }

    /**
     * Return the protocols, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the preferred subprotocols.
     */
    @Override
    public List<String> getPreferredSubprotocols() {
        return this.preferredSubprotocols;
    }



    /**
     * Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the extension list.
     */
    @Override
    public List<Extension> getExtensions() {
        return this.extensions;
    }


    /**
     * Assign the list of encoders this client will use.
     *
     * @return the encoder list.
     */
    @Override
    public List<Encoder> getEncoders() {
        return this.encoders;
    }

    /**
     * Assign the list of encoders this client will use.
     *
     * @param encoders the encoders to use.
     * @return this endpoint configuration.
     */
    public ClientEndpointConfiguration setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }

    /**
     * Assign the list of decoders this client will use.
     *
     * @return the decoders to use.
     */
    @Override
    public List<Decoder> getDecoders() {
        return this.decoders;
    }

    /**
     * Assign the list of decoders this client will use.
     *
     * @param decoders the extensions.
     * @return this endpoint configuration.
     */
    public ClientEndpointConfiguration setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }

    /**
     * The default implementation of this method performs no actions on the HandshakeRequest headers.
     *
     * @param hr handshake request the implementation has formulated.
     */
    @Override
    public void beforeRequest(Map<String, List<String>> hr) {
    }

    /**
     * The default implementation of this method performs no actions on the HandshakeResponse.
     *
     * @param hr the handshake response sent by the server.
     */
    public void afterResponse(HandshakeResponse hr) {
    }
    
    /**
     * Editable map of user properties.
     */
    public final Map<String, Object> getUserProperties() {
        return this.userProperties;
    }

}
