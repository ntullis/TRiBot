/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
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

package metapi.mail.remote;

import metapi.mail.Folder;
import metapi.mail.Store;
import metapi.mail.mbox.MboxFolder;
import metapi.mail.mbox.MboxStore;

/**
 * The default folder for the "remote" protocol.
 *
 * @author Bill Shannon
 */
public class RemoteDefaultFolder extends MboxFolder {

    protected RemoteDefaultFolder(RemoteStore store, String name) {
        super(store, name);
    }

    /**
     * Depending on the name of the requested folder, create an
     * appropriate <code>Folder</code> subclass.  If the name is
     * <code>null</code>, create a <code>RemoteDefaultFolder</code>.
     * If the name is "INBOX" (ignoring case), create a
     * <code>RemoteInbox</code>.  Otherwise, create an <code>MboxFolder</code>.
     *
     * @return the new <code>Folder</code>
     */
    protected Folder createFolder(Store store, String name) {
        if (name == null)
            return new RemoteDefaultFolder((RemoteStore) store, null);
        else if (name.equalsIgnoreCase("INBOX"))
            return new RemoteInbox((RemoteStore) store, name);
        else
            return new MboxFolder((MboxStore) store, name);
    }
}
