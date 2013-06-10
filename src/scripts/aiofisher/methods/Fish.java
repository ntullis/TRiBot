package scripts.aiofisher.methods;

import org.tribot.api.DynamicClicking;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;
import scripts.aiofisher.enums.FishPools;
import scripts.aiofisher.enums.FishTools;
import util.Timing;

import static org.tribot.api.General.println;
import static org.tribot.api.General.random;
import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
public class Fish {

    private FishPools POOL;
    private FishTools TOOL;

    public Fish(FishPools POOL,FishTools TOOL) {
        this.POOL = POOL;
        this.TOOL = TOOL;
    }

    public boolean startFishing() {
        final RSNPC pool[] = NPCs.findNearest(POOL.getID());
        if (pool[0].isOnScreen()) {
            if (!pool[0].isInteractingWithMe()) {



                if (DynamicClicking.clickRSNPC(pool[0], TOOL.getOption())) {
                    Timing.CSleep(new Timing.Condition() {
                        @Override
                        public boolean validate() {
                            return pool[0].isInteractingWithMe();
                        }
                    },random(4000,5000));
                    return true;
                }


            }
        } else {
            Walking.walkTo(pool[0].getPosition());
            Timing.CSleep(new Timing.Condition() {
                @Override
                public boolean validate() {
                    return !Player.isMoving();
                }
            },5000);
        }

        return false;
    }
}
