package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public enum FishPools {
    MACKEREL("Bass/Mackerel/Cod/Junk", 4908, 313),
    TUNA("Swordfish/Tuna", 311, 312),
    LOBSTER("Lobster", 311, 312),
    SHRIMPS("Anchovies/Shrimps", 322, 330, 3019),
    HERRING("Herring/Sardine/Pike", 322, 330, 314, 3019, 329),
    SALMON("Salmon/Trout", 314, 329),
    SHARK("Shark", 4908, 313);


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
