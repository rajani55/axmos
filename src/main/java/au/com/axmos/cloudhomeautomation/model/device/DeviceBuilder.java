package au.com.axmos.cloudhomeautomation.model.device;

import java.lang.reflect.InvocationTargetException;

/**
 *  DeviceBuilder facilitates a build method which returns a Device object based on deviceType and deviceId
 *
 */
public class DeviceBuilder {
    String deviceType;
    String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceBuilder setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public DeviceBuilder setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public Device build() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Device device = null;
        String deviceDriver = DeviceType.getDriver(this.deviceType);
        device = (Device) Class.forName(deviceDriver).getConstructor(String.class, String.class).newInstance(this.deviceId, this.deviceType);

        return device;
    }
}
