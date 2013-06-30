/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2013 Oracle and/or its affiliates. All rights reserved.
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

package metapi.javamail.mail.gimap;

import metapi.javamail.mail.*;

import metapi.javamail.mail.imap.*;
import metapi.javamail.mail.gimap.protocol.*;

/**
 * A Gmail message.  Adds methods to access Gmail-specific per-message data.
 *
 * @since JavaMail 1.4.6
 * @author Bill Shannon
 */

public class GmailMessage extends IMAPMessage {
    /**
     * Constructor.
     */
    protected GmailMessage(IMAPFolder folder, int msgnum) {
	super(folder, msgnum);
    }

    /**
     * Constructor, for use by IMAPNestedMessage.
     */
    protected GmailMessage(Session session) {
	super(session);
    }

    /**
     * Return the Gmail unique message ID.
     */
    public long getMsgId() throws MessagingException {
	Long msgid = (Long)getItem(GmailProtocol.MSGID_ITEM);
	if (msgid != null)
	    return msgid.longValue();
	else
	    return -1;
    }

    /**
     * Return the Gmail unique thread ID.
     */
    public long getThrId() throws MessagingException {
	Long thrid = (Long)getItem(GmailProtocol.THRID_ITEM);
	if (thrid != null)
	    return thrid.longValue();
	else
	    return -1;
    }

    /**
     * Return the Gmail labels associated with this message.
     */
    public String[] getLabels() throws MessagingException {
	String[] labels = (String[])getItem(GmailProtocol.LABELS_ITEM);
	if (labels != null)
	    return (String[])(labels.clone());
	else
	    return null;
    }

}