package au.com.axmos.cloudhomeautomation.model.device;

/**
 *  DeviceType is a enum of known devices with the corresponding device driver class
 */
public enum DeviceType {
    GARAGE_DOOR("garagedoor", "au.com.axmos.cloudhomeautomation.model.device.GarageDoor"),
    DISH_WASHER("dishwasher", "au.com.axmos.cloudhomeautomation.model.device.DishWasher"),
    LIVING_ROOM_LIGHT("livingroomlight", "au.com.axmos.cloudhomeautomation.model.device.LivingRoomLight");

    String value;
    String driver;

    DeviceType(String value, String driver) {
        this.value = value;
        this.driver = driver;
    }

    public static String getDriver(String type){
        String driver = null;

        for(DeviceType dType: DeviceType.values()){
            if(dType.getValue().equals(type)){
                driver = dType.getDriver();
            }
        }

        return driver;
    }

    public String getValue() {
        return value;
    }

    public String getDriver() {
        return driver;
    }
}
