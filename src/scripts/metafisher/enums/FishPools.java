package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public enum FishPools {
    MACKEREL("Bass/Mackerel/Cod/Junk", 322),
    TUNA("Swordfish/Tuna", 321),
    LOBSTER("Lobster", 321),
    SHRIMPS("Anchovies/Shrimps", 330, 327),
    HERRING("Herring/Sardine", 330),
    SALMON("Salmon/Trout", 328),
    SHARK("Shark", 322);


    private final int[] POOL_IDs;
    private final String POOL_NAME;

    private FishPools(String POOL_NAME, int... POOL_IDs) {
        this.POOL_IDs = POOL_IDs;
        this.POOL_NAME = POOL_NAME;
    }

    public String getName() {
        return POOL_NAME;
    }

    public int[] getID() {
        return POOL_IDs;
    }


}
