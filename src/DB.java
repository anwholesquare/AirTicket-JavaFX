import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String DATABASE_URL = "jdbc:mysql://134.122.10.205:3306/airticket";
    private static final String DATABASE_USERNAME = "airticket123";
    private static final String DATABASE_PASSWORD = "airticket@123";
    private static final String QUERY = "SELECT * FROM Admin where";
    private static final String CREATE_USER_QUERY = "INSERT INTO User(email, password) VALUES (?, ?)";
    private static final String CREATE_TICKET = "INSERT INTO `TicketRequest`(`user_id`, `flight_id`, `from_place`, `to_place`, `seat_type`, `from_date`, `boarding_time`, `departing_time`, `price`, `rating`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String CREATE_FLIGHT = "INSERT INTO `Flight`(`origin`, `time_string`, `journey_date`, `per_destination_cost`) VALUES (?, ?, ?, ? )";
    private static final String CREATE_QUESTION_QUERY = "INSERT INTO `QuestionList`(`user_id`, `question`) VALUES (?, ?)";
    public Connection connection;
    
    DB () {
        try{
        connection = DriverManager
            .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch (SQLException e) {
            
        }
    }
    
    
    public boolean checkadmin (String email, String pass) {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT id FROM Admin where email = '" + email + "' and password = '" + pass + "'" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            int id = -1;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
             System.out.println(id);
            if (id != -1) {
                AuthSession.writeAuth(Integer.toString(id), email);
                return true;
            }
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return false;
                 
    }
    
    
    
    
    public boolean checkUser (String email, String pass) {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT id FROM User where email = '" + email + "' and password = '" + pass + "'" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            int id = -1;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
             System.out.println(id);
            if (id != -1) {
                AuthSession.writeAuth(Integer.toString(id), email);
                return true;
            }
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return false;
                 
    }
    
    
    public void insertUser(String emailId, String password) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public List<FlightsModel> checkAllFlights () {
        List<FlightsModel> ls  = new ArrayList<>();
        try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT * FROM `Flight` WHERE 1" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                FlightsModel fm = new FlightsModel();
                fm.id = resultSet.getString("id");
                fm.origin = resultSet.getString("origin");
                fm.journeyDate=resultSet.getString("journey_date");
                fm.perDestinationCost = resultSet.getDouble("per_destination_cost");
                String ts = resultSet.getString("time_string");
                String[] arrOfStr = ts.split("@@", -1);
                for(int i =0; i<arrOfStr.length; i++) {
                    fm.timeString[i] = arrOfStr[i];
                }
                ls.add(fm);
            }
            return ls;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }

        return ls;
    }
    
    
    public List<TicketsModel> checkAllFlightsToBuy (String JourneyDate, String seat_type, String From, String To) {
        List<TicketsModel> ls  = new ArrayList<>();
        
        
        TicketsModel tm = new TicketsModel ();
        tm.from_place = From;
        tm.to_place = To;
        tm.boarding_time = "9:50";
        tm.departing_time = "11:20";
        tm.from_date = JourneyDate;
        tm.price = Integer.toString(2400);
        tm.seat_type = seat_type;
        tm.flight_id = Integer.toString(123);
        
        
        
        ls.add(tm);
        return ls;
    }
    
    
    public void insertTicket(String fid, String from_place, String to_place, String seat_type, String from_date, String boarding_time, String departing_time, String price) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TICKET)) {
            preparedStatement.setString(1, AuthSession.readID());
            preparedStatement.setString(2, fid);
            preparedStatement.setString(3, from_place);
            preparedStatement.setString(4, to_place);
            preparedStatement.setString(5, seat_type);
            preparedStatement.setString(6, from_date);
            preparedStatement.setString(7, boarding_time);
            preparedStatement.setString(8, departing_time);
            preparedStatement.setString(9, price);
            preparedStatement.setString(10, "5");
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
    public List<TicketsModel> checkAllTickets () {
        List<TicketsModel> ls  = new ArrayList<>();
        try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT `id`, `user_id`, `flight_id`, `from_place`, `to_place`, `seat_type`, `from_date`, `boarding_time`, `departing_time`, `price`, `rating`, `created_date` FROM TicketRequest where user_id = '" + AuthSession.readID() + "'" ;
            System.out.println(q);
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                TicketsModel tm = new TicketsModel ();
                tm.ticket_id = resultSet.getString("id");
                tm.user_id = resultSet.getString("user_id");
                tm.flight_id = resultSet.getString("flight_id");
                tm.from_place = resultSet.getString("from_place");
                tm.to_place = resultSet.getString("to_place");
                tm.seat_type = resultSet.getString("seat_type");
                tm.from_date = resultSet.getString("from_date");
                tm.boarding_time = resultSet.getString("boarding_time");
                tm.departing_time = resultSet.getString("departing_time");
                tm.price = resultSet.getString("price");
                ls.add(tm);
            }
            
            
        }catch (SQLException e) {
             System.out.println(e.toString());
        }
         
         return ls;
                 
    }
    
    
    public List<TicketsModel> checkAllTicketsAdmin () {
        List<TicketsModel> ls  = new ArrayList<>();
        try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT TicketRequest.`id` as TID, `user_id`, `flight_id`, `from_place`, `to_place`, `seat_type`, `from_date`, `boarding_time`, `departing_time`, `price`, `rating`, TicketRequest.`created_date` AS CD, `email` FROM TicketRequest JOIN User ON User.id = TicketRequest.user_id" ;
            System.out.println(q);
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                TicketsModel tm = new TicketsModel ();
                tm.ticket_id = resultSet.getString("TID");
                tm.user_id = resultSet.getString("email");
                tm.flight_id = resultSet.getString("flight_id");
                tm.from_place = resultSet.getString("from_place");
                tm.to_place = resultSet.getString("to_place");
                tm.seat_type = resultSet.getString("seat_type");
                tm.from_date = resultSet.getString("from_date");
                tm.boarding_time = resultSet.getString("boarding_time");
                tm.departing_time = resultSet.getString("departing_time");
                tm.price = resultSet.getString("price");
                tm.rating = resultSet.getString("rating");
                ls.add(tm);
            }
            
            
        }catch (SQLException e) {
             System.out.println(e.toString());
        }
         
         return ls;
                 
    }
    
    
    public void insertFlight(String origin, String timeString, String jDate, String perCost) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FLIGHT)) {
            preparedStatement.setString(1, origin);
            preparedStatement.setString(2, timeString);
            preparedStatement.setString(3, jDate);
            preparedStatement.setString(4, perCost);
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
     public List<UserModel> checkAllUsers () {
        List<UserModel> ls  = new ArrayList<>();
        try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT * FROM `User` WHERE 1" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                UserModel um = new UserModel(resultSet.getString("id"), resultSet.getString("email"));
                ls.add(um);
            }
            return ls;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }

        return ls;
    }
    
            
    public void deleteUser (String id) {
         try  {
            Statement statement = connection.createStatement();
            String q = "DELETE FROM `User` WHERE id = " + id; 
            statement.execute(q);              
         }catch (SQLException e) {
             System.out.println(e.toString());
         }       
    }
    
    
    
    

    public void rateTicket (String tid, String rating) {
         try  {
            Statement statement = connection.createStatement();
            String q = "UPDATE `TicketRequest` SET `rating` = '" + rating  + "' WHERE id = '" +   tid + "'";
            statement.execute(q);              
         }catch (SQLException e) {
             System.out.println(e.toString());
         }       
    }
    
     public void deleteTicket (String id) {
         try  {
            Statement statement = connection.createStatement();
            String q = "DELETE FROM `TicketRequest` WHERE id = " + id; 
            statement.execute(q);              
         }catch (SQLException e) {
             System.out.println(e.toString());
         }       
    }
     
     public List<QuestionModel> checkAllQuestions () {
        List<QuestionModel> ls  = new ArrayList<>();
        try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT `email`, `question`, `QuestionList`.created_date as CD FROM `QuestionList` JOIN User ON User.id = QuestionList.user_id" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                QuestionModel um = new QuestionModel(resultSet.getString("question"), resultSet.getString("CD"), resultSet.getString("email"));
                ls.add(um);
            }
            return ls;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
        return ls;
     }

     
     public void insertQuestion(String question) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUESTION_QUERY)) {
            preparedStatement.setString(1, AuthSession.readID());
            preparedStatement.setString(2, question);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
     
     
     
     
     
     public String checkTotalUsers () {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT COUNT(id) as ok FROM `User`" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            String res = "";
            while (resultSet.next()) {
                res = resultSet.getString("ok");
            }
            return res;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return "5";
                 
    }
     
     public String checkTotalRev () {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT sum(price) as ok FROM `TicketRequest`" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            String res = "";
            while (resultSet.next()) {
                res = resultSet.getString("ok");
            }
            return res + "BDT";
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return "1200 BDT";
                 
    }
     
     
     
     public String checkTotalQuestions () {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT COUNT(id) FROM `QuestionList`" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            String res = "";
            while (resultSet.next()) {
                res = resultSet.getString("ok");
            }
            return res;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return "5";
                 
    }
     
     
     public String checkTotalTickets () {
         try  {
            
            Statement statement = connection.createStatement();
            String q = "SELECT COUNT(id) as ok FROM `TicketRequest` WHERE 1" ;
            
            ResultSet resultSet = statement.executeQuery(q);
            String res = "";
            while (resultSet.next()) {
                res = resultSet.getString("ok");
            }
            return res;
         }catch (SQLException e) {
             System.out.println(e.toString());
         }
         
         return "5";
                 
    }
}