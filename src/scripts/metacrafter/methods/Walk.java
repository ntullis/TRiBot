package scripts.metacrafter.methods;

import metapi.enums.Banks;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import scripts.metacrafter.enums.Stations;


/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Walk {

    private Stations station;
    private Banks bank;

    public Walk(final Stations station, final Banks bank)  {
        this.station = station;
        this.bank = bank;
    }


    public boolean stationNear() {
        RSObject o[] = Objects.findNearest(station.getMaxDist(), station.getID());
        return o.length > 0 && o != null;
    }

    public boolean bankNear() {
        RSNPC n[] = NPCs.findNearest(bank.getBankerID());
        return n != null && n.length > 0 && Player.getPosition().distanceTo(n[0].getPosition()) < bank.getMaxDistance();
    }
}
