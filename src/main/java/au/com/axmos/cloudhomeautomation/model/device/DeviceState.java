package au.com.axmos.cloudhomeautomation.model.device;

public enum DeviceState {
     STATE_OFF, STATE_ON;

     public static DeviceState getDeviceStateByValue(int state){
          DeviceState deviceState;
          if(state == 0){
               deviceState = STATE_OFF;
          } else {
               deviceState = STATE_ON;
          }
          return deviceState;
     }
}
