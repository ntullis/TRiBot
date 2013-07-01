package metapi.util;


import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Login;
import org.tribot.api2007.types.RSInterfaceChild;

import static org.tribot.api.General.sleep;


/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 14.6.2013
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public class Logout {


    public static boolean Logout() {

        final RSInterfaceChild tab = Interfaces.get(548, 33);

        if (Login.getLoginState() == Login.STATE.INGAME) {
            if (GameTab.getOpen() == GameTab.TABS.INVENTORY) {
                if (tab != null) tab.click("Logout");
            } else {
                Login.logout();
            }
            sleep(1000, 2000);
        } else {
            return true;
        }


        return false;
    }


}
