package it.polimi.ingsw.comunication.common.errors;

public class LoginError {
    private String loginError;
    private int code;

    void LoginError(int i){
        switch (i){
            case 1:
                loginError = "Already existing name: please retry\r";
                code = 100;
                break;
            case 2:
                loginError = "Invalid number of players: please retry\r";
                code = 101;
                break;
        }
    }

    public String getLoginError() {
        return loginError;
    }

    public int getCode() {
        return code;
    }
}
