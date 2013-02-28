/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import javax.websocket.*;
/**
 * hi Pavel !
 * 
 * From a developer provided ServletContextListener.contextInitialized method
 * 
 * it should be possible to do this:
 * // obtain the instance of the server container for this application
 * // (presumably by doing some sort of magic inside your impl of ServerContainerProvider.getContainer())
 * ServerContainer sc = ServerContainerProvider.getServerContainer();
 * then
 * try {
 *  sc.deploy(MyAnnotatedHello.class)
 *  sc.deploy(new MyProgrammaticConfig(MyProgrammaticEndpoint.class, "/hiya"));
 * } catch (DeploymentException de) {
 *  //
 * }
 * 
 * Also, the point at which to flip the switch on the deploy methods to start them throwing IllegalStateExceptions
 * because the application has already been deployed could easily be the time the
 * websocket impl gets the first handshake request - seems simple and failsafe ?
 * 
 * 
 * 
 */
public interface ServerContainer extends WebSocketContainer {
    
    /**
     * 
     * @param annotatedEndpoint
     * @throws DeploymentException 
     * @throws IllegalStateException if the application has been deployed already.
     */
    public void deploy(Class<?> annotatedEndpointClazz) throws DeploymentException;
    /**
     * 
     * @param sec
     * @throws DeploymentException 
     * @throws IllegalStateException if the application has been deployed already.
     */
    public void deploy(ServerEndpointConfiguration sec) throws DeploymentException;
    
}
