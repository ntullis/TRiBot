package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public enum FishPools {
    MACKEREL("Bass/Mackerel/Cod/Junk", 2545, 2598),
    TUNA("Swordfish/Tuna", 2544, 2597),
    LOBSTER("Lobster", 2544, 2597),
    SHRIMPS("Anchovies/Shrimps", 309, 330, 318, 3883, 3886, 3877, 2603, 2606),
    HERRING("Herring/Sardine/Pike", 2591, 2596, 2603, 2606),
    SALMON("Salmon/Trout", 2591, 2596),
    SHARK("Shark", 2545, 2598);


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
