
package javax.websocket.server;

import java.util.ServiceLoader;


public abstract class ServerContainerProvider {
 
    
    public static ServerContainer getServerContainer() {
         ServerContainer sc = null;
        for (ServerContainerProvider impl : ServiceLoader.load(ServerContainerProvider.class)) {
            sc = impl.getContainer(ServerContainer.class);
            if (sc != null) {
                return sc;
            } 
        }
        if (sc == null) {
            throw new RuntimeException("Could not find an implementation class.");
        } else {
            throw new RuntimeException("Could not find an implementation class with a non-null ServerContainer.");
        }
    }
 
    /**
     * Load the server container implementation.
     * @param <T>
     * @param containerClass
     * @return 
     */
    protected abstract <T> T getContainer(Class<T> containerClass);
}



