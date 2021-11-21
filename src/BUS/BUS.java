package BUS;

public class BUS {
    public static boolean checkNullFieldsLogin(String email, String password) {
        if (email.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }
}
