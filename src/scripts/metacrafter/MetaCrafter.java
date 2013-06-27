package scripts.metacrafter;

import metapi.enums.Banks;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.api2007.Player;
import org.tribot.script.EnumScript;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.metacrafter.enums.Craftables;
import scripts.metacrafter.enums.States;
import scripts.metacrafter.enums.Stations;
import scripts.metacrafter.methods.Bank;
import scripts.metacrafter.methods.Craft;
import scripts.metacrafter.methods.Walk;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 25.6.2013
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */

@ScriptManifest(authors = {"Merphz"}, category = "Crafting", name = "MetaCrafter[Alpha]", version = 0.01)
public class MetaCrafter extends EnumScript<States> implements Painting {



    private Banks chosenBank = Banks.LUMBRIDGE;
    private Craftables chosenCraftable = Craftables.GOLD_RING;
    private Stations chosenStation = Stations.FURNACE;

    private Walk walk;
    private Bank bank;
    private Craft craft;

    private States scriptState = null;


    public States getState() {

      if (Login.getLoginState() != Login.STATE.INGAME) return null;

      if (Player.getAnimation() == -1) {
          if (walk.stationNear()) {
              println("isNear");
              if (Inventory.getCount(chosenCraftable.getRawID()) != 0) return States.CRAFT;
          } else {
              if (Inventory.getCount(chosenCraftable.getRawID()) != 0) return States.WALK_TO_CRAFT;
          }

          if (walk.bankNear()) {
              if (Inventory.getCount(chosenCraftable.getRawID()) == 0) return States.BANK;
          } else {
              if (Inventory.getCount(chosenCraftable.getRawID()) == 0) return States.WALK_TO_BANK;
          }


      }




      return null;
    }

    @Override
    public States getInitialState() {

        bank = new Bank(chosenBank);
        craft = new Craft(chosenCraftable);
        walk = new Walk(chosenStation, chosenBank);

        return getState();
    }

    @Override
    public States handleState(States states) {

        scriptState = states;

        switch(scriptState) {
            case BANK:
                break;
            case WALK_TO_BANK:
                break;
            case WALK_TO_CRAFT:
                break;
            case CRAFT:
                craft.Craft();
                break;

            default:
                break;
        }

        return null;
    }

    @Override
    public void onPaint(Graphics graphics) {

    }
}
