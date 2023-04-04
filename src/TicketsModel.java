/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anan
 */
public class TicketsModel {
    String user_id, ticket_id, flight_id, from_place, to_place, seat_type, from_date, boarding_time,departing_time, price, rating, created_date, timeString;
    TicketsModel() {}
    public TicketsModel(String user_id, String flight_id, String from_place, String to_place, String seat_type, String from_date, String boarding_time, String departing_time, String price, String rating, String created_date) {
        this.user_id = user_id;
        this.flight_id = flight_id;
        this.from_place = from_place;
        this.to_place = to_place;
        this.seat_type = seat_type;
        this.from_date = from_date;
        this.boarding_time = boarding_time;
        this.departing_time = departing_time;
        this.price = price;
        this.rating = rating;
        this.created_date = created_date;
    }
}
