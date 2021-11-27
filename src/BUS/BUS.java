package BUS;

public class BUS {
    public static boolean checkNullFieldsLogin(String email, String password) {
        if (email.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }

    public static int indexGender(String gender) {
        if (gender.equals("Male")) {
            return 0;
        }
        else if (gender.equals("Female")) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
