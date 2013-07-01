package metapi.enums;

import org.tribot.api2007.types.RSTile;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public enum Banks {
    LUMBRIDGE("Lumbridge", 4, null, 18492, 18491, 4907),
    CATHERBY("Catherby", 6, new RSTile(2809, 3439, 0), 0, 57, 56),
    AL_KHARID("Al Kharid", 4, new RSTile(3270, 3165, 0), 7126, 69, 65),
    DRAYNOR("Draynor", 7, new RSTile(3093, 3242, 0), 7126, 57, 56),
    EDGEVILLE("Edgeville", 4, new RSTile(3093, 3489, 0), 7126, 56),
    FISHING_GUILD("Fishing Guild", 8, new RSTile(2587, 3420, 0), 23961, 57, 56),
    SEER_VILLAGE("Seer's Village", 8, new RSTile(2739, 3496, 0), 25809, 57, 56);


    private final String BANK_NAME;
    private final int MAX_DISTANCE;
    private final RSTile TILE;
    private final int BOOTH_ID;
    private final int[] BANKER_IDS;


    private Banks(String BANK_NAME, int MAX_DISTANCE, RSTile TILE, int BOOTH_ID, int... BANKER_IDS) {
        this.BANK_NAME = BANK_NAME;
        this.MAX_DISTANCE = MAX_DISTANCE;
        this.TILE = TILE;
        this.BOOTH_ID = BOOTH_ID;
        this.BANKER_IDS = BANKER_IDS;
    }

    public String getName() {
        return BANK_NAME;
    }

    public int getMaxDistance() {
        return MAX_DISTANCE;
    }

    public int getBoothID() {
        return BOOTH_ID;
    }

    public int[] getBankerID() {
        return BANKER_IDS;
    }

    public RSTile getLocation() {
        return TILE;
    }


}
