package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public enum FishPools {
    MACKEREL("Bass/Mackerel/Cod/Junk", 328, 321),
    TUNA("Swordfish/Tuna", 323, 312),
    LOBSTER("Lobster", 323, 312),
    SHRIMPS("Anchovies/Shrimps", 309, 330, 318),
    HERRING("Herring/Sardine/Pike", 309, 324, 330, 318),
    SALMON("Salmon/Trout", 313, 324),
    SHARK("Shark", 328, 321);


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
