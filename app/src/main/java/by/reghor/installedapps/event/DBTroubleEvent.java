package by.reghor.installedapps.event;

/**
 * Created by reghor on 1/17/16.
 */
public class DBTroubleEvent {
    private String message;

    public DBTroubleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
