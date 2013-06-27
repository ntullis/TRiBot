package metapi.util;

import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 8.6.2013
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */

public class Timing {


    public interface Condition {

        public boolean validate();

    }

    public static void CSleep(final Condition c,final long timeout) {

        long startT = System.currentTimeMillis();
        long timeoutT = (System.currentTimeMillis()+timeout)-startT;

        while ((System.currentTimeMillis() - startT) < timeoutT ) {
            sleep(50);
            if (c.validate()) break;
        }

    }
}
