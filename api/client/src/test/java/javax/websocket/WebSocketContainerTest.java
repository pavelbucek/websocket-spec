/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved.
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

import java.io.IOException;
import java.net.URI;
import java.util.Set;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class WebSocketContainerTest implements MessageHandler.Whole<String> {

    public static void main(String[] args) throws IOException, DeploymentException {
        new WebSocketContainerTest().run();
    }

    public void run() throws IOException, DeploymentException {


        final WebSocketContainer.SessionBuilder sessionBuilder = webSocketContainer.sessionBuilder()
                .path(URI.create("ws://localhost:8025/sample-echo/echo"))
                .messageHandler(String.class, this::onMessage)
                .onOpen(this::onOpen)
                .onError(this::onError)
                .onClose(this::onClose);

        sessionBuilder.connect();


    }

    @Override
    public void onMessage(String message) {

    }

    public void onOpen(Session session, EndpointConfig config) {

    }

    public void onError(Session session, Throwable thr) {

    }

    public void onClose(Session session, CloseReason closeReason) {

    }


    private final WebSocketContainer webSocketContainer = new WebSocketContainer() {
        @Override
        public long getDefaultAsyncSendTimeout() {
            return 0;
        }

        @Override
        public void setAsyncSendTimeout(long timeoutmillis) {

        }

        @Override
        public Session connectToServer(Object annotatedEndpointInstance, URI path) throws DeploymentException, IOException {
            return null;
        }

        @Override
        public Session connectToServer(Class<?> annotatedEndpointClass, URI path) throws DeploymentException, IOException {
            return null;
        }

        @Override
        public Session connectToServer(Endpoint endpointInstance, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException {
            return null;
        }

        @Override
        public Session connectToServer(Class<? extends Endpoint> endpointClass, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException {
            return null;
        }

        @Override
        public long getDefaultMaxSessionIdleTimeout() {
            return 0;
        }

        @Override
        public void setDefaultMaxSessionIdleTimeout(long timeout) {

        }

        @Override
        public int getDefaultMaxBinaryMessageBufferSize() {
            return 0;
        }

        @Override
        public void setDefaultMaxBinaryMessageBufferSize(int max) {

        }

        @Override
        public int getDefaultMaxTextMessageBufferSize() {
            return 0;
        }

        @Override
        public void setDefaultMaxTextMessageBufferSize(int max) {

        }

        @Override
        public Set<Extension> getInstalledExtensions() {
            return null;
        }
    };


}


