/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anan
 */
public class FlightsModel {
    String id;
    String origin;
    String[] timeString = new String[9];
    String journeyDate;
    double perDestinationCost;
    FlightsModel() {}
    public FlightsModel(String id, String origin, String journeyDate, double perDestinationCost) {
        this.id = id;
        this.origin = origin;
        this.journeyDate = journeyDate;
        this.perDestinationCost = perDestinationCost;
    }
    
}
