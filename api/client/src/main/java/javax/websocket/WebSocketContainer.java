/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012-2015 Oracle and/or its affiliates. All rights reserved.
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiConsumer;

/**
 * A WebSocketContainer is an implementation provided object that provides applications
 * a view on the container running it. The WebSocketContainer container various
 * configuration parameters that control default session and buffer properties
 * of the endpoints it contains. It also allows the developer to
 * deploy websocket client endpoints by initiating a web socket handshake from 
 * the provided endpoint to a supplied URI where the peer endpoint is presumed to
 * reside. 
 *
 * <p>A WebSocketContainer may be accessed by concurrent threads, so
 * implementations must ensure the integrity of its mutable attributes in such 
 * circumstances.  
 *
 * @author dannycoward
 */
public interface WebSocketContainer {

    /**
     * Return the number of milliseconds the implementation will timeout
     * attempting to send a websocket message for all RemoteEndpoints associated
     * with this container. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. Note this default may be overridden in each RemoteEndpoint.
     *
     * @return the timeout time in millsenconds.
     */
    long getDefaultAsyncSendTimeout();

    /**
     * Sets the number of milliseconds the implementation will timeout
     * attempting to send a websocket message  for all RemoteEndpoints associated
     * with this container. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. Note this default may be overridden in each RemoteEndpoint.
     */
    void setAsyncSendTimeout(long timeoutmillis);

    /**
     * Connect the supplied annotated endpoint instance to its server. The supplied 
     * object must be a class decorated with the class level
     * {@link javax.websocket.server.ServerEndpoint} annotation. This method 
     * blocks until the connection is established, or throws an error if either 
     * the connection could not be made or there was a problem with the supplied 
     * endpoint class. If the developer uses this method to deploy the client
     * endpoint, services like dependency injection that are supported, for 
     * example, when the implementation is part of the Java EE platform
     * may not be available. If the client endpoint uses dependency injection,
     * use {@link WebSocketContainer#connectToServer(java.lang.Class, java.net.URI)}
     * instead.
     *
     * @param annotatedEndpointInstance the annotated websocket client endpoint 
     * instance.
     * @param path the complete path to the server endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the annotated endpoint instance is not valid.
     * @throws IOException if there was a network or protocol problem that 
     * prevented the client endpoint being connected to its server.
     * @throws IllegalStateException if called during the deployment phase
     * of the containing application.
     */
    Session connectToServer(Object annotatedEndpointInstance, URI path) throws DeploymentException, IOException;             
         
    /**
     * Connect the supplied annotated endpoint to its server. The supplied object must be a
     * class decorated with the class level
     * {@link javax.websocket.server.ServerEndpoint} annotation. This method blocks until the connection
     * is established, or throws an error if either the connection could not be made or there
     * was a problem with the supplied endpoint class.
     *
     * @param annotatedEndpointClass the annotated websocket client endpoint.
     * @param path                   the complete path to the server endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the class is not a valid annotated endpoint class.
     * @throws IOException if there was a network or protocol problem that 
     * prevented the client endpoint being connected to its server.
     * @throws IllegalStateException if called during the deployment phase
     * of the containing application.
     */
    Session connectToServer(Class<?> annotatedEndpointClass, URI path) throws DeploymentException, IOException;

    
    /**
     * Connect the supplied programmatic client endpoint instance to its server 
     * with the given configuration. This method blocks until the connection
     * is established, or throws an error if the connection could not be made. 
     * If the developer uses this method to deploy the client
     * endpoint, services like dependency injection that are supported, for 
     * example, when the implementation is part of the Java EE platform
     * may not be available. If the client endpoint uses dependency injection,
     * use {@link WebSocketContainer#connectToServer(java.lang.Class, javax.websocket.ClientEndpointConfig, java.net.URI) }
     * instead.
     *
     * @param endpointInstance the programmatic client endpoint instance {@link Endpoint}.
     * @param path          the complete path to the server endpoint.
     * @param cec           the configuration used to configure the programmatic endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the configuration is not valid
     * @throws IOException if there was a network or protocol problem that 
     * prevented the client endpoint being connected to its server
     * @throws IllegalStateException if called during the deployment phase
     * of the containing application.
     */
    Session connectToServer(Endpoint endpointInstance, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException;
    
    /**
     * Connect the supplied programmatic endpoint to its server with the given 
     * configuration. This method blocks until the connection
     * is established, or throws an error if the connection could not be made.
     *
     * @param endpointClass the programmatic client endpoint class {@link Endpoint}.
     * @param path          the complete path to the server endpoint.
     * @param cec           the configuration used to configure the programmatic endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the configuration is not valid
     * @throws IOException if there was a network or protocol problem that prevented the client endpoint being connected to its server
     * @throws IllegalStateException if called during the deployment phase
     * of the containing application.
     */
    Session connectToServer(Class<? extends Endpoint> endpointClass, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException;

    public default SessionBuilder sessionBuilder() {
        return new SessionBuilder(this);
    }

    public static class SessionBuilder {

        private final WebSocketContainer container;

        URI path;
        ClientEndpointConfig clientEndpointConfig;

        MessageHandler.Whole<?> wholeMessageHandler;
        Class<?> wholeMessageHandlerType;

        BiConsumer<Session, EndpointConfig> onOpen;
        BiConsumer<Session, Throwable> onError;
        BiConsumer<Session, CloseReason> onClose;

        /**
         * Package private constructor.
         */
        SessionBuilder(WebSocketContainer container) {
            this.container = container;
        }

        public SessionBuilder clientEndpointConfig(ClientEndpointConfig clientEndpointConfig) {
            this.clientEndpointConfig = clientEndpointConfig;
            return this;
        }

        public SessionBuilder path(URI path) {
            this.path = path;
            return this;
        }

        // TODO: MessageHandler#Partial
        public <T> SessionBuilder messageHandler(Class<T> clazz, MessageHandler.Whole<T> messageHandler) {
            this.wholeMessageHandler = messageHandler;
            this.wholeMessageHandlerType = clazz;
            return this;
        }

        public SessionBuilder onOpen(BiConsumer<Session, EndpointConfig> onOpen) {
            this.onOpen = onOpen;
            return this;
        }

        public SessionBuilder onError(BiConsumer<Session, Throwable> onError) {
            this.onError = onError;
            return this;
        }

        public SessionBuilder onClose(BiConsumer<Session, CloseReason> onClose) {
            this.onClose = onClose;
            return this;
        }

        public Session connect() throws IOException, DeploymentException {
            // TODO: validation

            final URI path = this.path;
            final ClientEndpointConfig clientEndpointConfig = this.clientEndpointConfig;

            final BiConsumer<Session, EndpointConfig> onOpen = this.onOpen;
            final BiConsumer<Session, Throwable> onError = this.onError;
            final BiConsumer<Session, CloseReason> onClose = this.onClose;

            final MessageHandler.Whole wholeMessageHandler = this.wholeMessageHandler;
            final Class wholeMessageHandlerType = this.wholeMessageHandlerType;


            final Endpoint endpoint = new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    // TODO: validation/condition
                    session.addMessageHandler(wholeMessageHandlerType, wholeMessageHandler);

                    onOpen.accept(session, config);
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    onClose.accept(session, closeReason);
                }

                @Override
                public void onError(Session session, Throwable thr) {
                    onError.accept(session, thr);
                }
            };

            return container.connectToServer(endpoint, clientEndpointConfig, path);
        }

        public CompletableFuture<Session> connectAsync() {

            final CompletableFuture<Session> completableFuture = new CompletableFuture<>();

            final ForkJoinTask<Void> forkJoinTask = new ForkJoinTask<Void>() {

                @Override
                public final Void getRawResult() { return null; }

                @Override
                public final void setRawResult(Void v) { }

                @Override
                protected boolean exec() {
                    try {
                        completableFuture.complete(connect());
                        return true;
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                    return false;
                }
            };

            // TODO: Can we use ForkJoinPool#commonPool?
            ForkJoinPool.commonPool().execute(forkJoinTask);

            return completableFuture;
        }

        public CompletableFuture<Session> connectAsync(ExecutorService executorService) {

            final CompletableFuture<Session> completableFuture = new CompletableFuture<>();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        completableFuture.complete(connect());
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }
            };

            executorService.execute(runnable);

            return completableFuture;
        }
    }

    /**
     * Return the default time in milliseconds after which any web socket sessions in this
     * container will be closed if it has been inactive. A value that is
     * 0 or negative indicates the sessions will never timeout due to inactivity.
     * The value may be overridden on a per session basis using
     * {@link Session#setMaxIdleTimeout(long) }
     *
     * @return the default number of milliseconds after which an idle session in this container
     * will be closed
     */
    long getDefaultMaxSessionIdleTimeout();

    /**
     * Sets the default time in milliseconds after which any web socket sessions in this
     * container will be closed if it has been inactive. A value that is
     * 0 or negative indicates the sessions will never timeout due to inactivity.
     * The value may be overridden on a per session basis using
     * {@link Session#setMaxIdleTimeout(long) }
     *
     * @param timeout the maximum time in milliseconds.
     */
    void setDefaultMaxSessionIdleTimeout(long timeout);

    /**
     * Returns the default maximum size of incoming binary message that this container
     * will buffer. This default may be overridden on a per session basis using
     * {@link Session#setMaxBinaryMessageBufferSize(int) }
     *
     * @return the maximum size of incoming binary message in number of bytes.
     */
    int getDefaultMaxBinaryMessageBufferSize();

    /**
     * Sets the default maximum size of incoming binary message that this container
     * will buffer.
     *
     * @param max the maximum size of binary message in number of bytes.
     */
    void setDefaultMaxBinaryMessageBufferSize(int max);

    /**
     * Returns the default maximum size of incoming text message that this container
     * will buffer. This default may be overridden on a per session basis using
     * {@link Session#setMaxTextMessageBufferSize(int) }
     *
     * @return the maximum size of incoming text message in number of bytes.
     */
    int getDefaultMaxTextMessageBufferSize();

    /**
     * Sets the maximum size of incoming text message that this container
     * will buffer.
     *
     * @param max the maximum size of text message in number of bytes.
     */
    void setDefaultMaxTextMessageBufferSize(int max);

    /**
     * Return the set of Extensions installed in the container.
     *
     * @return the set of extensions.
     */
    Set<Extension> getInstalledExtensions();
}



