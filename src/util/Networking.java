package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


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

    public String fetchSettings() {
        String website = "http://www.snapbasecode.com/settings.php?action=get&script="+script;
        try {

            URL url = new URL(website);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String s;
            if (in.readLine() != null) {
                s = in.readLine();
                in.close();
                return s;
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("Your firewall blocked the settings loader.");
        } catch (IOException e) {
            System.out.println("Unable to connect to "+website);
        }
     return null;
    }


}
