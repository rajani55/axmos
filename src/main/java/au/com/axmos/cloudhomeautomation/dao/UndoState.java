package au.com.axmos.cloudhomeautomation.dao;

import au.com.axmos.cloudhomeautomation.exceptions.InvalidUndoRequest;
import au.com.axmos.cloudhomeautomation.model.device.DeviceState;

public class UndoState {
    private static String deviceId;
    private static DeviceState state;

    public static String getDeviceId() throws InvalidUndoRequest {
        if(deviceId == null){
            throw new InvalidUndoRequest();
        }
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        UndoState.deviceId = deviceId;
    }

    public static DeviceState getState() throws InvalidUndoRequest {
        if(state == null){
            throw new InvalidUndoRequest();
        }
        return state;
    }

    public static void setState(DeviceState state) {
        UndoState.state = state;
    }

    public static void reset() {
        deviceId = null;
        state = null;
    }
}
