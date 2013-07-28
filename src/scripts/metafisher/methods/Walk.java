package scripts.metafisher.methods;

import metapi.AStar;
import metapi.MWalking;
import metapi.enums.Banks;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.metafisher.enums.FishPools;

import static org.tribot.api.General.println;
import static org.tribot.api.General.random;


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


    RSTile[] Path;


    public Walk(Banks chosenBank, FishPools chosenPool) {
        this.bank = chosenBank;
        this.pool = chosenPool;

        switch (bank) {
           case CATHERBY:
               Path = new RSTile[]{new RSTile(2856, 3427, 0),new RSTile(2849, 3431, 0),new RSTile(2842, 3433, 0),new RSTile(2835, 3435, 0),new RSTile(2828, 3436, 0),
                       new RSTile(2821, 3438, 0),new RSTile(2814, 3438, 0), new RSTile(2809, 3440, 0)};
                break;
           case AL_KHARID:
               Path = new RSTile[]{new RSTile(3276, 3141, 0),new RSTile(3271, 3146, 0),new RSTile(3269, 3153, 0),new RSTile(3273, 3160, 0),new RSTile(3269, 3167, 0)};
                break;
            case DRAYNOR:
                Path = new RSTile[]{new RSTile(3090, 3228, 0),new RSTile(3090, 3235, 0),new RSTile(3087, 3242, 0),new RSTile(3092, 3247, 0),new RSTile(3093, 3243, 0)};
                break;
            case EDGEVILLE:
                Path = new RSTile[]{new RSTile(3103, 3430, 0),new RSTile(3098, 3435, 0),new RSTile(3094, 3441, 0),new RSTile(3091, 3448, 0),new RSTile(3090, 3455, 0)
                        ,new RSTile(3088, 3462, 0),new RSTile(3081, 3466, 0),new RSTile(3080, 3473, 0),new RSTile(3080, 3480, 0),new RSTile(3084, 3486, 0),new RSTile(3094, 3489, 0)};
                break;
            case FISHING_GUILD:
                Path = new RSTile[]{new RSTile(2606, 3402, 0),new RSTile(2604, 3409, 0),new RSTile(2597, 3409, 0),new RSTile(2593, 3415, 0),new RSTile(2587, 3419, 0)};
                break;
           case SEER_VILLAGE:
               Path = new RSTile[]{new RSTile(2717, 3533, 0),new RSTile(2724, 3533, 0),new RSTile(2729, 3528, 0),new RSTile(2734, 3523, 0),new RSTile(2737, 3516, 0),new RSTile(2740, 3509, 0),
                       new RSTile(2740, 3502, 0),new RSTile(2740, 3495, 0),new RSTile(2735, 3489, 0),new RSTile(2728, 3486, 0), new RSTile(2726, 3491, 0)};
                break;
        }



        Walking.walking_timeout = random(2000, 4000);

    }



    public boolean walkToBank() {
        return MWalking.walkPath(Path,false);
    }

    public boolean walkToFish() {
        return MWalking.walkPath(Path,true);
    }

    public boolean bankIsNear() {



        RSNPC banker[] = NPCs.find(bank.getBankerID());

        if (banker.length > 0) {
            println("banker = "+banker[0].getID());
            println("distanceToBank = "+Player.getPosition().distanceTo(banker[0].getPosition()));
        }


        return banker.length > 0 && banker != null && Player.getPosition().distanceTo(banker[0].getPosition()) <= bank.getMaxDistance();
    }

    public boolean boothIsNear() {
        RSObject booth[] = Objects.findNearest(bank.getMaxDistance(), bank.getBoothID());
        return booth.length > 0 && booth != null && Player.getPosition().distanceTo(booth[0].getPosition()) <= bank.getMaxDistance() && PathFinding.canReach(booth[0].getPosition(), true);
    }

    public boolean fishIsNear() {
        RSNPC spot[] = NPCs.find(pool.getID());
        return spot.length > 0 && spot != null;
    }

}
