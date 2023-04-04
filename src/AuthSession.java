import java.util.prefs.Preferences;

public class AuthSession {
    
    public static Preferences prefs =Preferences.userNodeForPackage(Main.class);
    final static String idPref = "id";
    final static String emailPref = "email";
    
    
    public static void writeAuth (String userID, String email) {
        prefs.put(idPref, userID);
        prefs.put(emailPref, email);
    }

    public static String readID() {
        return prefs.get(idPref, "@ERROR");
    }
    
    public static String readEmail() {
        return prefs.get(emailPref, "@ERROR");
    }
    
    
    
}
