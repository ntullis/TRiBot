package scripts.metafisher.methods;

import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.metafisher.enums.Banks;
import scripts.metafisher.enums.FishPools;
import util.Timer;
import util.Timing;

import static org.tribot.api.General.*;
import static util.Timing.CSleep;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class Walk {

    Banks bank;
    FishPools pool;

    private RSTile[] walkPath;

    private Timer runTimer;


    public Walk(Banks chosenBank, FishPools chosenPool) {
        this.bank = chosenBank;
        this.pool = chosenPool;

        switch (bank) {
            case CATHERBY:
                walkPath = new RSTile[]{new RSTile(2854, 3430, 0), new RSTile(2842, 3433, 0), new RSTile(2830, 3437, 0), new RSTile(2817, 3436, 0), new RSTile(2809, 3439, 0)};
                break;
            case AL_KHARID:
                walkPath = new RSTile[]{new RSTile(3277, 3143, 0), new RSTile(3271, 3150, 0), new RSTile(3276, 3159, 0), new RSTile(3270, 3165, 0)};
                break;
            case DRAYNOR:
                walkPath = new RSTile[]{new RSTile(3087, 3228, 0), new RSTile(3085, 3237, 0), new RSTile(3093, 3242, 0)};
                break;
            case EDGEVILLE:
                walkPath = new RSTile[]{new RSTile(3102, 3430, 0), new RSTile(3098, 3438, 0), new RSTile(3093, 3445, 0), new RSTile(3090, 3452, 0), new RSTile(3087, 3461, 0),
                        new RSTile(3079, 3472, 0), new RSTile(3080, 3485, 0), new RSTile(3087, 3491, 0), new RSTile(3093, 3489, 0)};
                break;
            case FISHING_GUILD:
                walkPath = new RSTile[]{new RSTile(2606, 3400, 0), new RSTile(2598, 3408, 0), new RSTile(2595, 3417, 0), new RSTile(2587, 3420, 0)};
                break;
        }

        runTimer = new Timer(random(180000, 300000));
        Walking.walking_timeout = random(2000, 4000);

    }

    public boolean toggleRun(boolean enable) {

        if (!runTimer.isRunning()) {
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
                                runTimer.reset();
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

    public boolean walkPath(RSTile[] path, boolean reverse) {
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
        }
        return false;
    }

    public boolean walkToBank() {
        return walkPath(walkPath, false);
    }

    public boolean walkToFish() {
        return walkPath(walkPath, true);
    }

    public boolean bankIsNear() {
        RSNPC banker[] = NPCs.find(bank.getBankerID());
        //println("distance = "+Player.getPosition().distanceTo(banker[0].getPosition()));
        return banker.length > 0 && banker != null && Player.getPosition().distanceTo(banker[0].getPosition()) < 4;
    }

    public boolean boothIsNear() {
        RSObject booth[] = Objects.findNearest(bank.getBoothID());
        println("boothLength = " + booth.length);
        return booth.length > 0 && booth != null && Player.getPosition().distanceTo(booth[0].getPosition()) < 4;
    }

    public boolean fishIsNear() {
        RSNPC spot[] = NPCs.find(pool.getID());
        return spot.length > 0 && spot != null;
    }

}
