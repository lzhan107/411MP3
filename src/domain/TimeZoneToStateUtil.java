/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mp3utilities.MP3Utilities;

/**
 *
 * @author Lei
 */
public class TimeZoneToStateUtil {
    private static List<String> easternStateList = new ArrayList<>();
    private static List<String> centralStateList = new ArrayList<>();
    private static List<String> mountainStateList = new ArrayList<>();
    private static List<String> pacificStateList = new ArrayList<>();
    private static List<String> listOfContinentalStates = new ArrayList<>();
    private static Map<String, List> timeZoneToState = new HashMap<>();

    //Get all the state lists based on region
    public static void feedData(){
        easternStateList = MP3Utilities.populateEasternStateList();
        centralStateList = MP3Utilities.populateCentralStateList();
        mountainStateList = MP3Utilities.populateMountainStateList();
        pacificStateList = MP3Utilities.populatePacificStateList();
    }
    //Create reiong-to-state map
    public static void regionStateMap(){
        timeZoneToState.put("EasternStates", easternStateList);
        timeZoneToState.put("CentralStates", centralStateList);
        timeZoneToState.put("MountainStates", mountainStateList);
        timeZoneToState.put("PacificStates", pacificStateList);
    }
    //Merge all state lists, then convert to an array
    public static String[] mergeStateListAsArray(){
        listOfContinentalStates.addAll(easternStateList);
        listOfContinentalStates.addAll(centralStateList);
        listOfContinentalStates.addAll(mountainStateList);
        listOfContinentalStates.addAll(pacificStateList);
        return (String[]) listOfContinentalStates.toArray();
    }
}
