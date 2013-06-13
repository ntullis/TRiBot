package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public enum Banks {
    CATHERBY("Catherby", 0, 57),
    AL_KHARID("Al Kharid", 7126, 65),
    DRAYNOR("Draynor", 7126, 57, 56),
    EDGEVILLE("Edgeville", 7126, 56);

    private final String BANK_NAME;
    private final int BOOTH_ID;
    private final int[] BANKER_IDS;

    private Banks(String BANK_NAME, int BOOTH_ID, int... BANKER_IDS) {
        this.BANK_NAME = BANK_NAME;
        this.BOOTH_ID = BOOTH_ID;
        this.BANKER_IDS = BANKER_IDS;
    }

    public String getName() {
        return BANK_NAME;
    }

    public int getBoothID() {
        return BOOTH_ID;
    }

    public int[] getBankerID() {
        return BANKER_IDS;
    }


}
