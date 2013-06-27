package scripts.metacrafter.methods;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import scripts.metacrafter.enums.Craftables;
import scripts.metacrafter.enums.Stations;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Craft {

    private Craftables craftable;
    private Stations station;

    public Craft(Craftables craftable) {
        this.craftable = craftable;

    }

    public boolean useInteract() {

        if (Inventory.getCount(craftable.getRawID()) > 0) {
            RSItem[] items = Inventory.find(craftable.getRawID());

            int size = items.length;

            RSItem item = items[General.random(0,size)];

            if (item.click("Use")) {
                RSObject oa[] = Objects.findNearest(station.getMaxDist(), station.getID());

                if (oa != null && oa.length > 0) {
                    RSObject o = oa[0];

                    if (o.isOnScreen()) {
                        o.click(station.getOption());
                        General.sleep(400,800);
                    }

                }




            }



        }


        return false;
    }

    public boolean forgeRing() {

        RSObject oa[] = Objects.findNearest(station.getMaxDist(), station.getID());


        if (oa != null && oa.length > 0) {
            RSObject o = oa[0];
            RSInterface menu = Interfaces.get(0,0);

            if (o.isOnScreen()) {
                if (menu != null) {

                } else {
                    useInteract();
                }

            } else {
                Walking.walkTo(o.getPosition());
            }

        }

        return false;
    }

    public boolean Craft() {

        switch (craftable.getType()) {
            case 1:
                station = Stations.FURNACE;
                    return forgeRing();


        }

        return false;
    }

}
