package au.com.axmos.cloudhomeautomation.dao;

import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.SlotUninitialized;
import au.com.axmos.cloudhomeautomation.model.device.Device;
import au.com.axmos.cloudhomeautomation.model.slot.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slots {
    static List<Slot> slotsList = new ArrayList<>();
    static Map<Integer, Device> slotsMap = new HashMap<>();
    static {
        initSlots();
        initRegisterDevicesToSlots();
    }

    private static void initSlots() {
        addSlot(1);
        addSlot(2);
        addSlot(3);
        addSlot(4);
        addSlot(5);
        addSlot(6);
    }

    public static void addSlot(int slotId) {
        Slot slot = new Slot(slotId);
        slotsList.add(slot);
    }

    public static Device getDevice(int slotId) throws SlotUninitialized {
        Device device = slotsMap.get(slotId);
        if(device == null)
            throw new SlotUninitialized();
       return device;
    }

    public static void registerDevice(int slotId, String deviceId) throws DeviceUnreachable {
        Device device = Devices.getDevice(deviceId);
        slotsMap.put(slotId, device);
    }

    private static void initRegisterDevicesToSlots() {
        try {
            registerDevice(1, "garagedoor1");
            registerDevice(2, "garagedoor2");
            registerDevice(3, "dishwasher1");
            registerDevice(4, "livingroom1");
            registerDevice(5, "livingroom2");
            //registerDevice(6, "livingroom3");
        } catch (Exception e){
            System.out.println("Ignoring exceptions");
        }
    }


}
