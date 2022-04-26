package it.polimi.ingsw.comunication.common.errors;

public class MageError {
    private String mageError;
    private int code;

    void MageError(int i){
        switch (i){
            case 1:
                mageError = "Mage already choosen: please select another mage.\r";
                code = 103;
                break;
            case 2:
                mageError = "Invalid mage: please retry.\r";
                code = 104;
                break;
        }
    }

    public String getMageError() {
        return mageError;
    }

    public int getCode() {
        return code;
    }
}
