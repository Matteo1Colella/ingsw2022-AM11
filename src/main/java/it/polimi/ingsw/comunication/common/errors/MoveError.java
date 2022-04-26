package it.polimi.ingsw.comunication.common.errors;

public class MoveError {
    private String moveError;
    private int code;

    public MoveError(int i) {
        switch (i){
            case 1:
                moveError = "Not existing island: please retry.\r";
                code = 300;
                break;
            case 2:
                moveError = "Invalid parameter: please inserta a SchoolBoard or an island.\r";
                code = 301;
                break;
        }
    }
}
