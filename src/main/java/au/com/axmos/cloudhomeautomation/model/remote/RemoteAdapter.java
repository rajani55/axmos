package au.com.axmos.cloudhomeautomation.model.remote;

import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.InvalidUndoRequest;
import au.com.axmos.cloudhomeautomation.exceptions.SlotUninitialized;

public interface RemoteAdapter {

     public void handleStateChangeRequest(int signal) throws DeviceUnreachable, SlotUninitialized;

     public void handleUndoRequest() throws DeviceUnreachable, InvalidUndoRequest;
}
