package scripts.metacrafter.enums;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 26.6.2013
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public enum Stations {

    FURNACE("Furnace", 4, 24009);


    private String OPTION;
    private int MAX_DIST;
    private int ID;


    private Stations(String OPTION, int ID, int MAX_DIST) {
        this.OPTION = OPTION;
        this.MAX_DIST = MAX_DIST;
        this.ID = ID;


    }

    public String getOption() {
        return OPTION;
    }

    public int getID() {
        return ID;
    }

    public int getMaxDist() {
        return MAX_DIST;
    }


}
