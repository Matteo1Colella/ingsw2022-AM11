package it.polimi.ingsw.comunication.common.errors;

public class CloudCardError {
    private String cloudCardError;
    private int code;

    public CloudCardError() {
        this.cloudCardError = "Invalid CloudCard choice: please retry.\r";
        this.code = 203;
    }

    public String getCloudCardError() {
        return cloudCardError;
    }

    public int getCode() {
        return code;
    }
}
