package it.polimi.ingsw.comunication.common.errors;

public class MotherNatureError {
    private String motherErrorMessage;
    private int code;

    public MotherNatureError() {
        motherErrorMessage = "Invalid MotherNature steps: please retry.\r";
        code = 400;
    }

    public String getMotherErrorMessage() {
        return motherErrorMessage;
    }

    public int getCode() {
        return code;
    }
}
