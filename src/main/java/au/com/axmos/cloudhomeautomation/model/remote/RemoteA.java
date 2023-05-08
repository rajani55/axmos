package au.com.axmos.cloudhomeautomation.model.remote;

import au.com.axmos.cloudhomeautomation.dao.Devices;
import au.com.axmos.cloudhomeautomation.dao.Slots;
import au.com.axmos.cloudhomeautomation.dao.UndoState;
import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.InvalidUndoRequest;
import au.com.axmos.cloudhomeautomation.exceptions.SlotUninitialized;
import au.com.axmos.cloudhomeautomation.model.device.Device;
import au.com.axmos.cloudhomeautomation.model.device.DeviceState;

public class RemoteA implements RemoteAdapter {
    public static RemoteA instance;

    public static synchronized RemoteA getInstance(){
        if(instance == null){
            instance = new RemoteA();
        }
        return instance;
    }

    @Override
    public void handleStateChangeRequest(int signal) throws DeviceUnreachable, SlotUninitialized {
        int slotId = signal/100;
        int state = signal%100;
        DeviceState deviceState = DeviceState.getDeviceStateByValue(state);
        Device device = Slots.getDevice(slotId);
        UndoState.setDeviceId(device.getDeviceId());
        UndoState.setState(Devices.getDeviceState(device.getDeviceId()));
        Devices.setDeviceState(device.getDeviceId(), deviceState);
    }

    public void handleUndoRequest() throws DeviceUnreachable, InvalidUndoRequest {
        Devices.setDeviceState(UndoState.getDeviceId(), UndoState.getState());
        UndoState.reset();
    }
}
