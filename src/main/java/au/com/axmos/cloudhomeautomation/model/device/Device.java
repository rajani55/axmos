package au.com.axmos.cloudhomeautomation.model.device;

import au.com.axmos.cloudhomeautomation.dao.Devices;
import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;

public abstract class Device {
    String deviceId;
    String deviceType;

    public Device(String deviceId, String deviceType) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceState getState() throws DeviceUnreachable {
        return Devices.getDeviceState(deviceId);
    }

    public void changeState(DeviceState state) throws DeviceUnreachable{
        Devices.setDeviceState(deviceId, state);
    }
}
