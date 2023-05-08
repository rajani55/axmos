# aXmos - HUB API documentation
 This API is used to notify the Hub controller to turn on/off a device or undo the previous action.
 
### Prerequisities
* JDK (version: >= 1.8)

### Getting Started 
* This application is developed using spring boot with embedded Tomcat server and Maven
* Maven Command to start the application on localhost:8080 - 
<br><code> mvnw spring-boot:start </code>

###Assumptions
1. There is no API implemented to map a particular device to a slot, but there are util functions written to initalize slots with a device.
    <br> Number of slots on remote: 6; devices mapped to slots (1-5) 
    <table>
    <th>Slot No</th>
    <th>Device ID</th>
    <tr><td>1</td><td>garagedoor1</td></tr>
    <tr><td>2</td><td>garagedoor2</td></tr>
    <tr><td>3</td><td>dishwasher1</td></tr>
    <tr><td>4</td><td>livingroom1</td></tr>
    <tr><td>5</td><td>livingroom2</td></tr>
    <tr><td>6</td><td></td></tr>
    </table>

2. Undo request will be honured after a state change request only. Undo cannot be done after a Undo request.

3. The remote sends a signal value based on slot number and request on/off state of device. Named this remote as RemoteA
    <code> <br> formula to calculate signal:
    <br> to turn off, signal = 100 * slot no + 0 
    <br> to turn on, signal = 100 * slot no + 1
    </code>

4. By adding different Remote classed that implement RemoteAdapter, signal value can be customized as per hardware.
 

### Reference Documentation

#### State change request: Success response is 200 OK
* Request method: <code>POST</code>
* URL for state change of a device (if any) associated to a slot: 
    <br>
     <code> http://localhost:8080/api/hub/device/state?signal={signal} </code>
* Request Header:
    <br> <code> remoteModel: A </code>
    
<br> e.g., CURL request: slot no - 4, turn on device : (100 * 4 + 1)  
<br>      curl --location --request POST 'http://localhost:8080/api/hub/device/state?signal=401' \
        --header 'remoteModel: A'


#### Undo request: Success response is 200 OK, Invalid undo response is 400 Bad Request
* Request method: <code>POST</code>
* URL for state change of a device (if any) associated to a slot: 
    <br>
     <code> http://localhost:8080/api/hub/action/undo </code>
* Request Header:
    <br> <code> remoteModel: A </code>

<br> e.g., CURL request: <br>
curl --location --request POST 'http://localhost:8080/api/hub/action/undo' \
--header 'remoteModel: A'


### Coding overview:
* Spring boot application entry point: CloudHomeAutomationApplication
* The main servlet controller for API: <code>RemoteRequestController</code>, the base URI is defined in application.properties
* Configuration of slots, devices, and their mapping are placed in 'dao' sub-package. Same with persistent state to execute undo request.
* All exceptions are defined in 'exceptions' sub-package.
* Rest all are placed in 'model' sub-package.
* GarageDoor, LivingRoomLight, DishWasher are subclasses of Device
* RemoteA implements RemoteAdapter
* State change responses will be written to console log.
