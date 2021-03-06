package mage.server;

/**
 *
 * @author LevelX2
 */

public enum DisconnectReason {
    LostConnection(" has lost connection"),
    Disconnected(" has left XMage"),
    CleaningUp(" [cleaning up]"),
    ConnectingOtherInstance(" reconnected and replaced still active old session"),
    AdminDisconnect(" was disconnected by the Admin"),
    SessionExpired(" session expired"),
    Undefined("");

    String message;
    DisconnectReason(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
