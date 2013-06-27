package scripts.metacrafter.enums;

import org.tribot.api2007.types.RSTile;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public enum Pathes {
    LUMB_FORGE_PLANE0(new RSTile[]{new RSTile(3212,3210,0), new RSTile(3222,3218,0), new RSTile(3234,3222,0), new RSTile(3228,3229,0),
            new RSTile(3223,3239,0), new RSTile(3218,3247,0), new RSTile(0,0,0), new RSTile(3225,3253,0)}),
    LUMB_FORGE_PLANE1(new RSTile[]{new RSTile(0,0,0), new RSTile(0,0,0)});

    private RSTile[] PATH;

    private Pathes(RSTile[] PATH) {
        this.PATH = PATH;
    }

}
