package metapi.util;


import metapi.mail.Message;
import metapi.mail.MessagingException;
import metapi.mail.Session;
import metapi.mail.Transport;
import metapi.mail.internet.InternetAddress;
import metapi.mail.internet.MimeMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import static org.tribot.api.General.println;


/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 14.6.2013
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class Networking {

    private String script;


    public Networking(String script) {
        this.script = script;

    }


    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String readURL(String website) throws Exception {
        URL url = new URL(website);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.2 Safari/537.36");

        InputStream in = conn.getInputStream();

        String body = convertStreamToString(in);

        in.close();

        return body;
    }

    public String fetchSettings() throws Exception {
        println("Loading settings from the server...");
        return readURL("http://www.snapbasecode.com/settings.php?action=get&script=" + script);
    }


    public boolean sendEmail(String email, String event, String msg) {
        ;


        // Sender's email ID needs to be mentioned
        String from = "MetaScripts1@gmail.com";
        String pass = "BmXGIT4rcM6QDcu";
        // Recipient's email ID needs to be mentioned.

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup javamail server
        properties.put("javamail.smtp.starttls.enable", "true");
        properties.put("javamail.smtp.host", host);
        properties.put("javamail.smtp.user", from);
        properties.put("javamail.smtp.password", pass);
        properties.put("javamail.smtp.port", "587");
        properties.put("javamail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("MetaFisher | " + event);

            // Now set the actual message
            message.setText(msg);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return false;
    }


}
