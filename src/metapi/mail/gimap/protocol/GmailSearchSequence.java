/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2012 Oracle and/or its affiliates. All rights reserved.
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

package metapi.mail.gimap.protocol;

import metapi.mail.gimap.GmailMsgIdTerm;
import metapi.mail.gimap.GmailRawSearchTerm;
import metapi.mail.gimap.GmailThrIdTerm;
import metapi.mail.iap.Argument;
import metapi.mail.imap.protocol.SearchSequence;
import metapi.mail.search.SearchException;
import metapi.mail.search.SearchTerm;

import java.io.IOException;

/**
 * Support Gmail-specific search extensions.
 *
 * @author Bill Shannon
 * @since JavaMail 1.4.6
 */

public class GmailSearchSequence extends SearchSequence {
    public Argument generateSequence(SearchTerm term, String charset)
            throws SearchException, IOException {
        if (term instanceof GmailMsgIdTerm)
            return gmailMsgidSearch((GmailMsgIdTerm) term);
        else if (term instanceof GmailThrIdTerm)
            return gmailThridSearch((GmailThrIdTerm) term);
        else if (term instanceof GmailRawSearchTerm)
            return gmailRawSearch((GmailRawSearchTerm) term, charset);
        else
            return super.generateSequence(term, charset);
    }

    protected Argument gmailMsgidSearch(GmailMsgIdTerm term)
            throws IOException {
        Argument result = new Argument();
        result.writeAtom("X-GM-MSGID");
        result.writeNumber(term.getNumber());
        return result;
    }

    protected Argument gmailThridSearch(GmailThrIdTerm term)
            throws IOException {
        Argument result = new Argument();
        result.writeAtom("X-GM-THRID");
        result.writeNumber(term.getNumber());
        return result;
    }

    protected Argument gmailRawSearch(GmailRawSearchTerm term, String charset)
            throws IOException {
        Argument result = new Argument();
        result.writeAtom("X-GM-RAW");
        result.writeString(term.getPattern(), charset);
        return result;
    }
}
