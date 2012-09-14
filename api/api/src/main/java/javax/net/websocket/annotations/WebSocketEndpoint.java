/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package javax.net.websocket.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class level annotation declares that the class it decorates
 * is a web socket endpoint. Developer can use this annotation
 * to declare the properties of the endpoint to the websocket runtime.
 * @since Draft 002
 * @author dannycoward
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebSocketEndpoint {
    /** The relative URI where the endpoint will be deployed, for example '/chat' */
    public String path();
    /** The ordered array of web socket protocols this endpoint supports. For example, {'superchat', 'chat'}.*/
    public String[] subprotocols() default {};
    /** The ordered array of decoder classes this endpoint will need to use. For example,
     if the developer have provided a MysteryObject decoder, this class will be able to
     send MysteryObjects as web socket messages. The websocket runtime will use the first
     decoder in the list able to decode a message, ignoring the remaining decoders.*/
    public Class[] decoders() default {};
    /** The ordered array of encoder classes this endpoint will need to use. For example,
     if the developer have provided a MysteryObject encoder, this class will be able to
     receive web socket messages in the form of MysteryObjects. The websocket runtime will use the first
     encoder in the list able to decode a message, ignoring the remaining encoders. */
    public Class[] encoders() default {};
}
