package scripts.metafisher.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public enum FishTools {
    SMALL_FISHING_NET(303, "Net", -1),
    BIG_FISHING_NET(305, "Net", -1),
    HARPOON(311, "Harpoon", -1),
    LOBSTER_POT(301, "Cage", -1),
    FISHING_ROD(307, "Bait", 313),
    FLY_FISHING_ROD(309, "Lure", 314);


    private final int TOOL_ID;
    private final String OPTION;
    private final int INGREDIENT_ID;

    private FishTools(int TOOL_ID, String OPTION, int INGREDIENT_ID) {
        this.TOOL_ID = TOOL_ID;
        this.OPTION = OPTION;
        this.INGREDIENT_ID = INGREDIENT_ID;


    }

    public int getID() {
        return TOOL_ID;
    }

    public String getOption() {
        return OPTION;
    }

    public int getIngredientID() {
        return INGREDIENT_ID;
    }

}
