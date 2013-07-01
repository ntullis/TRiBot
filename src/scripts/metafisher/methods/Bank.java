package scripts.metafisher.methods;

import metapi.MBank;
import metapi.enums.Banks;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.metafisher.enums.FishTools;

import static org.tribot.api.General.println;
import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class Bank {


    private Banks chosenBank;
    private FishTools chosenTool;

    public Bank(Banks bank, FishTools tool) {
        this.chosenBank = bank;
        this.chosenTool = tool;
    }


    public boolean withdrawTool() {
        boolean ing = false;
        boolean tool = false;
        if (chosenTool.getIngredientID() != -1 && Inventory.getCount(chosenTool.getIngredientID()) == 0) ing = true;
        if (Inventory.getCount(chosenTool.getID()) == 0) tool = true;

        if (!Banking.isBankScreenOpen()) {
            MBank.openBanker(chosenBank);
        } else {
            if (tool) {
                MBank.withdraw(1, chosenTool.getID());
                sleep(1000, 2000);
            }
            if (ing) {
                MBank.withdraw(0, chosenTool.getIngredientID());
                sleep(1000, 2000);
            }
            return true;
        }


        return false;
    }

    public boolean deposit() {
        if (!Banking.isBankScreenOpen()) {
            MBank.openBanker(chosenBank);
        } else {


            int IDs[] = new int[2];
            if (chosenTool.getIngredientID() != -1) {
                IDs[1] = chosenTool.getIngredientID();

            }
            IDs[0] = chosenTool.getID();
            Banking.depositAllExcept(IDs);
            return true;

        }
        return false;
    }

}
