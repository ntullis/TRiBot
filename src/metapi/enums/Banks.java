package metapi.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public enum Banks {
    LUMBRIDGE("Lumbridge", 4, 18492, 18491, 4907),
    CATHERBY("Catherby", 6, 0, 57, 56),
    AL_KHARID("Al Kharid", 4, 7126, 69, 65),
    DRAYNOR("Draynor", 7, 7126, 57, 56),
    EDGEVILLE("Edgeville", 4, 7126, 56),
    FISHING_GUILD("Fishing Guild", 8, 23961, 57, 56),
    SEER_VILLAGE("Seer's Village", 8, 25809, 57, 56);


    private final String BANK_NAME;
    private final int MAX_DISTANCE;
    private final int BOOTH_ID;
    private final int[] BANKER_IDS;

    private Banks(String BANK_NAME,int MAX_DISTANCE, int BOOTH_ID, int... BANKER_IDS) {
        this.BANK_NAME = BANK_NAME;
        this.MAX_DISTANCE = MAX_DISTANCE;
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



}
