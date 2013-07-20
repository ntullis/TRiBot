package metapi.util;



import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public boolean sendNotification(String event, String description){

        String website = "https://www.notifymyandroid.com/publicapi/notify?apikey=c096ba6f0d191f0c966a34ec5f3d38cd6ec8432671085171&application="+script+"&event="+event+"&description="+description;
        URL url = null;
        try {
            url = new URL(website);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.2 Safari/537.36");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            println("return: "+conn.getInputStream().read());

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }





}
