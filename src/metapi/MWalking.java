package metapi;

import metapi.util.Timer;
import metapi.util.Timing;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static metapi.util.Timing.CSleep;
import static org.tribot.api.General.random;
import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */

public class MWalking {
    public static boolean toggleRun(boolean enable, Timer timer) {

        if (!timer.isRunning()) {
            final int settings[] = Game.getSettingsArray();

            if (settings != null) {
                if (settings[173] == 0) {
                    if (enable) {
                        if (GameTab.open(GameTab.TABS.OPTIONS)) {
                            if (Interfaces.get(261, 0) != null) {

                                Interfaces.get(261, 0).click("Toggle Run");

                                if (settings[173] == 1) {
                                    GameTab.open(GameTab.TABS.OPTIONS);

                                    CSleep(new Timing.Condition() {
                                        @Override
                                        public boolean validate() {
                                            return settings[173] == 1;
                                        }
                                    }, random(2000, 3000));
                                }


                                GameTab.open(GameTab.TABS.INVENTORY);
                                CSleep(new Timing.Condition() {
                                    @Override
                                    public boolean validate() {
                                        return GameTab.open(GameTab.TABS.INVENTORY);
                                    }
                                }, random(2000, 3000));
                                timer.reset();
                                return true;
                            }
                        } else {

                            return true;
                        }
                    }
                } else {
                    if (!enable) {
                        if (GameTab.open(GameTab.TABS.OPTIONS)) {
                            if (Interfaces.get(261, 0) != null) {

                                Interfaces.get(261, 0).click("Toggle Run");
                            }
                        } else {
                            GameTab.open(GameTab.TABS.OPTIONS);
                            sleep(1000, 2000);
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }


    public static boolean walkPath(RSTile[] path, boolean reverse) {
        if (path != null) {

            RSTile[] tempPath = new RSTile[path.length];
            if (reverse) {
                int y = 0;
                for (int i = path.length - 1; i >= 0; i--) {
                    tempPath[y] = path[i];
                    y++;
                }

                path = tempPath;
            }



                  Walking.walkPath(path);
                  CSleep(new Timing.Condition() {
                      @Override
                      public boolean validate() {
                          return !Player.isMoving();
                      }
                  },random(2000,3000));








        }
        return false;
    }

}
