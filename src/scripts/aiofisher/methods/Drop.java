package scripts.aiofisher.methods;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import scripts.aiofisher.enums.FishTools;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 6.6.2013
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class Drop {

    private HashMap<Integer, Integer> dropList = new HashMap<Integer, Integer>();
    private boolean power = false;
    private FishTools tool;


    public Drop(HashMap dropList, boolean power, FishTools tool) {
        this.dropList = dropList;
        this.power = power;
        this.tool = tool;
    }

    public boolean invContainsDropItem() {

        if (power) return true;

        for (int i = 0; i < dropList.size(); i++) {

            for (RSItem item : Inventory.find(Integer.parseInt(dropList.get(i).toString()))) {
                if (item != null) {
                    int count = Inventory.getCount(item.getID());
                    if (count > 0) return true;
                }

            }
        }

        return false;
    }


    public boolean dropAll() {

        if (power) {
            for (RSItem item : Inventory.getAll()) {
                if (item != null) {
                    if (item.getID() == tool.getID() || item.getID() == tool.getIngredientID()) continue;


                }

            }
            return true;
        } else {
            for (int i = 0; i < dropList.size(); i++) {

                for (RSItem item : Inventory.find(Integer.parseInt(dropList.get(i).toString()))) {
                    if (item != null) {
                        Inventory.drop(item.getID());

                    }

                }
            }

        }


        return false;
    }


}
