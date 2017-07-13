package database;

import java.util.ArrayList;

/**
 * Class IDGenerator
 * is returning free ids when user inserting anime into
 * database.
 * @author George Lukas
 * @version 1.0
 */
public class IDGenerator {

    /*list with free ids*/
    private ArrayList<Integer> freeIds;

    /**
     * EMPTY Constructor
     */
    public IDGenerator() {
    }

    /**
     * Method isFreeID
     * is controlling list of ids and if some id is free returning true.
     * @return - true if id is free, false when it is not
     */
    public boolean isFreeID() {
        return  freeIds != null && !freeIds.isEmpty() ;
    }

    /**
     * Method getID
     * is returning free id in database.
     * @return - free id in database
     */
    public int getID(){
        int freeID = freeIds.get(0);
        freeIds.remove(0);
        return  freeID;
    }

    /**
     * Method setFreeIds
     * setting list of free ids to generator
     * @param freeIds - list of free ids
     */
    public void setFreeIds(ArrayList<Integer> freeIds) {
        this.freeIds = freeIds;
    }
}
