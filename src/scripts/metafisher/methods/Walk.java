package scripts.metafisher.methods;

import metapi.AStar;
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


    RSTile spot;

    public Walk(Banks chosenBank, FishPools chosenPool) {
        this.bank = chosenBank;
        this.pool = chosenPool;

        switch (bank) {
            case CATHERBY:
                spot = new RSTile(2854, 3430, 0);
                break;
            case AL_KHARID:
                spot = new RSTile(3277, 3143, 0);
                break;
            case DRAYNOR:
                spot = new RSTile(3087, 3228, 0);
                break;
            case EDGEVILLE:
                spot = new RSTile(3102, 3430, 0);
                break;
            case FISHING_GUILD:
                spot = new RSTile(2606, 3400, 0);
                break;
            case SEER_VILLAGE:
                spot = new RSTile(2724, 3530, 0);
                break;
        }



        Walking.walking_timeout = random(2000, 4000);

    }


    public boolean walkToBank() {
        return PathFinding.aStarWalk(bank.getLocation());
    }

    public boolean walkToFish() {
        return PathFinding.aStarWalk(spot);
    }

    public boolean bankIsNear() {
        RSNPC banker[] = NPCs.find(bank.getBankerID());
        return banker.length > 0 && banker != null && Player.getPosition().distanceTo(banker[0].getPosition()) < bank.getMaxDistance();
    }

    public boolean boothIsNear() {
        RSObject booth[] = Objects.findNearest(bank.getMaxDistance(), bank.getBoothID());
        println("boothLength = " + booth.length);
        return booth.length > 0 && booth != null && Player.getPosition().distanceTo(booth[0].getPosition()) < bank.getMaxDistance();
    }

    public boolean fishIsNear() {
        RSNPC spot[] = NPCs.find(pool.getID());
        return spot.length > 0 && spot != null;
    }

}
