package au.com.axmos.cloudhomeautomation.dao;

import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnsupported;
import au.com.axmos.cloudhomeautomation.model.device.DeviceBuilder;
import au.com.axmos.cloudhomeautomation.model.device.DeviceState;
import au.com.axmos.cloudhomeautomation.model.device.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Devices {
    private static final Logger LOG = LogManager.getLogger(Devices.class);

    static List<Device> devicesList = new ArrayList<>();
    static Map<String, DeviceState> deviceStateMap = new HashMap<>();

    static {
        initDevicesList();
        initDeviceState();
    }

    private static void addDevice(String deviceId, String deviceType) throws DeviceUnsupported {
        try {
            Device device = (new DeviceBuilder()).setDeviceId(deviceId).setDeviceType(deviceType).build();
            devicesList.add(device);
        } catch (Exception e){
            throw new DeviceUnsupported();
        }
    }

    public static DeviceState getDeviceState(String deviceId) throws DeviceUnreachable {
        DeviceState deviceState = null;
        try {
            deviceState = deviceStateMap.get(deviceId);
        } catch(Exception e){
            throw new DeviceUnreachable();
        }
        return deviceState;
    }

    public static void setDeviceState(String deviceId, DeviceState state) throws DeviceUnreachable {
        LOG.info("Changed state of {}: {}", deviceId, state.name());
        deviceStateMap.put(deviceId, state);
    }

    public static Device getDevice(String deviceId) throws DeviceUnreachable {
        Optional<Device> first = devicesList.stream().filter(device -> deviceId.equalsIgnoreCase(device.getDeviceId())).findFirst();
        return first.orElseThrow(DeviceUnreachable::new);
    }

    private static void initDevicesList() {
        try {
            addDevice("garagedoor1", "garagedoor");
            addDevice("dishwasher1", "dishwasher");
            addDevice("livingroom1", "livingroomlight");
            addDevice("garagedoor2", "garagedoor");
            addDevice("livingroom3", "livingroomlight");
            addDevice("livingroom2", "livingroomlight");
        } catch (Exception e){
            System.out.println("Ignore the exceptions");
        }
    }

    private static void initDeviceState(){
        deviceStateMap.put("garagedoor1", DeviceState.STATE_OFF);
        deviceStateMap.put("dishwasher1", DeviceState.STATE_OFF);
        deviceStateMap.put("livingroom1", DeviceState.STATE_OFF);
        deviceStateMap.put("garagedoor2", DeviceState.STATE_OFF);
        deviceStateMap.put("livingroom3", DeviceState.STATE_OFF);
        deviceStateMap.put("livingroom2", DeviceState.STATE_OFF);
    }

    public static List<Device> getDevicesList(){
        return devicesList;
    }
}
