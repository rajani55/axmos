package au.com.axmos.cloudhomeautomation.controllers;

import au.com.axmos.cloudhomeautomation.exceptions.DeviceUnreachable;
import au.com.axmos.cloudhomeautomation.exceptions.InvalidUndoRequest;
import au.com.axmos.cloudhomeautomation.exceptions.SlotUninitialized;
import au.com.axmos.cloudhomeautomation.exceptions.UnSupportedRemote;
import au.com.axmos.cloudhomeautomation.model.remote.RemoteHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 *  RemoteRequestController is the servlet that listens requests from various remote clients
 */
@RestController
public class RemoteRequestController{
    private static final Logger LOG = LogManager.getLogger(RemoteRequestController.class);

    @PostMapping("device/state")
    public void changeState(@PathParam("signal") Integer signal, @RequestHeader("remoteModel") String remoteModel) throws DeviceUnreachable, SlotUninitialized, UnSupportedRemote {
        RemoteHandler.delegateStateChangeRequest(remoteModel, signal);
    }

    @PostMapping("action/undo")
    public void undoAction(@RequestHeader("remoteModel") String remoteModel) throws DeviceUnreachable, SlotUninitialized, InvalidUndoRequest, UnSupportedRemote {
        RemoteHandler.delegateForUndo(remoteModel);
    }

}
