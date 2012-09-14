/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.net.websocket.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  This method level annotation can be used to decorate a Java method that wishes to be called in order
 * to handle errors. The method must have a Throwable parameter and optionally a Session parameter, in any
 * order.
 * @author dannycoward
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebSocketError {
    
}
