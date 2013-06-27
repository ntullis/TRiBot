package scripts.metacrafter.enums;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public enum Craftables {

    GOLD_RING("Gold Ring", 0, 1, 2357, 1592);

    private final String NAME;
    private final int ID;



    private final int TYPE;
    private final int RAW_ID;
    private final int TOOL_ID;

    private Craftables(String NAME, int ID, int TYPE, int RAW_ID, int TOOL_ID) {
        this.ID = ID;
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.RAW_ID = RAW_ID;
        this.TOOL_ID = TOOL_ID;

    }


    public String getName() {
        return NAME;
    }

    public int getID() {
        return ID;
    }

    public int getRawID() {
        return RAW_ID;
    }

    public int getToolID() {
        return TOOL_ID;
    }

    public int getType() {
        return TYPE;
    }

}
