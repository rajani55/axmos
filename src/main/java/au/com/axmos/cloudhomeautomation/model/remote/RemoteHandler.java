package au.com.axmos.cloudhomeautomation.model.remote;

import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.InvalidUndoRequest;
import au.com.axmos.cloudhomeautomation.exceptions.SlotUninitialized;
import au.com.axmos.cloudhomeautomation.exceptions.UnSupportedRemote;

/**
 *  RemoteHandler delegate the request to corresponding remote class for execution
 */
public class RemoteHandler {

    public static void delegateStateChangeRequest(String remoteModel, int signal) throws DeviceUnreachable, SlotUninitialized, UnSupportedRemote {
        switch(remoteModel){
            case "A":
                RemoteA.getInstance().handleStateChangeRequest(signal);
                break;
            default:
                throw new UnSupportedRemote();
        }
    }

    public static void delegateForUndo(String remoteModel) throws DeviceUnreachable, InvalidUndoRequest, UnSupportedRemote {
        switch(remoteModel){
            case "A":
                RemoteA.getInstance().handleUndoRequest();
                break;
            default:
                throw new UnSupportedRemote();
        }
    }
}
