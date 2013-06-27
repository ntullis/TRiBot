package metapi;

import metapi.enums.Banks;
import metapi.util.Timing;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;

import static org.tribot.api.General.random;
import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
public class MBank {


    public static boolean openBooth(final Banks bank) {
        RSObject[] booth = Objects.findNearest(bank.getMaxDistance(), bank.getBoothID());

        if (booth.length > 0 && booth != null) {
            RSObject o = booth[0];

            if (o.isOnScreen()) {
                o.click("Bank");

                Timing.CSleep(new Timing.Condition() {
                    @Override
                    public boolean validate() {
                        return Banking.isBankScreenOpen();
                    }
                }, random(3000, 5000));
                if (Banking.isBankScreenOpen()) return true;

            } else {
                Camera.turnToTile(o.getPosition());
            }


        }

        return false;
    }

    public static boolean openBanker(final Banks bank) {
        RSNPC[] banker = NPCs.findNearest(bank.getBankerID());

        if (banker.length > 0 && banker != null) {
            RSNPC n = banker[0];

            if (n.isOnScreen()) {
                if (DynamicClicking.clickRSNPC(n, "Bank Banker")) {
                    Timing.CSleep(new Timing.Condition() {
                        @Override
                        public boolean validate() {
                            return Banking.isBankScreenOpen();
                        }
                    }, random(3000, 5000));
                    if (Banking.isBankScreenOpen()) return true;
                }
            } else {
                Camera.turnToTile(n.getPosition());
            }


        }
        return false;
    }


    public static void withdraw(int count, int... ids) {
        int BANK_SECTION = 0;

        int section, scrollTo, itemX, itemY;

        for (int i = 0; i < ids.length; i++) {
            RSItem[] items = Banking.find(ids[i]);

            if (items.length == 0) continue;


            section = (int) Math.floor((items[0].getIndex()) / 8.0 / 6.0);

            scrollTo = (int) Math.round((section * 23.5) + 86);

            itemX = (int) Math.ceil((items[0].getIndex()) % 8);

            itemY = (int) (Math.floor(items[0].getIndex() / 8) % 6);


            if (section != BANK_SECTION) {
                Mouse.moveBox(469, scrollTo, 481, scrollTo);
                Mouse.click(Mouse.getPos(), 1);
                BANK_SECTION = section;
                sleep(150);
            }

            Mouse.move(80 + (itemX * 47) + General.random(7, 22), 59 + (itemY * 38) + General.random(13, 25));
            sleep(400);

            int countBefore = Inventory.getCount(ids[i]);

            if (count == 1) {
                Mouse.click(Mouse.getPos(), 1);
            } else {
                Mouse.click(Mouse.getPos(), 3);
                sleep(200);

                if (count == 5 || count == 10) ChooseOption.select("Withdraw " + count);
                else if (count == 0) ChooseOption.select("Withdraw All");
                else {
                    ChooseOption.select("Withdraw X");


                    while (Interfaces.get(548, 93) == null)
                        sleep(100);

                    sleep(1500);
                    Keyboard.typeString(count + "");
                    Keyboard.pressEnter();
                }
            }
            sleep(300);

            if (Inventory.getCount(ids[i]) < countBefore)
                i--;
        }
        return;
    }




}
