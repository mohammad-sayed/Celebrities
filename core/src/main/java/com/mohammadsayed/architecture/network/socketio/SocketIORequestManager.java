package com.mohammadsayed.architecture.network.socketio;

import android.content.Context;

import com.google.gson.Gson;
import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.utils.CLog;
import com.mohammadsayed.architecture.utils.Constants;
import com.mohammadsayed.architecture.utils.DeviceUtil;
import com.mohammadsayed.architecture.utils.StringUtil;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by mohammad on 5/8/17.
 */

public class SocketIORequestManager {

    private static SocketIORequestManager mSocketIORequestManager;
    private Socket mSocket;
    private Gson mGson;
    private Map<String, Emitter.Listener> mEventListeners;
    private boolean mSocketConnecting = false;


    private SocketIORequestManager() {
        mGson = new Gson();
        mEventListeners = new HashMap<>();
    }

    /**
     * Get the singleton object instance of this class
     *
     * @return
     */
    public static SocketIORequestManager getInstance() {
        if (mSocketIORequestManager == null) {
            synchronized (SocketIORequestManager.class) {
                if (mSocketIORequestManager == null) {
                    mSocketIORequestManager = new SocketIORequestManager();
                }
            }
        }
        return mSocketIORequestManager;
    }

    /**
     * initialize socket url before calling {@link SocketIORequestManager#connectSocketIO(Context, OnSocketIOConnectionListener)}
     *
     * @param url socket url to be connected with
     */
    public void initializeSocketIO(String url) {
        if (mSocket != null) {
            return;
        }
        try {
            mSocket = IO.socket(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect socket IO called after {@link SocketIORequestManager#initializeSocketIO(String)}
     * this method call assign a callbacks to know connection states [Connecting, Connected, Disconnected]
     * and call {@link OnSocketIOConnectionListener} corresponding methods
     *
     * @param context
     * @param onSocketIOConnectionListener
     */
    public synchronized void connectSocketIO(Context context, final OnSocketIOConnectionListener onSocketIOConnectionListener) {
        if (mSocket == null || mSocketConnecting || mSocket.connected()) {
            return;
        }
        if (!DeviceUtil.isOnline(context)) {
            if (onSocketIOConnectionListener != null) {
                onSocketIOConnectionListener.onSocketIOConnectionError(createInternetConnectionError(context));
            }
            return;
        }
        mSocketConnecting = true;
        List<Emitter.Listener> listeners = mSocket.listeners(Socket.EVENT_CONNECTING);
        //listeners contains a default size 1
        if (listeners.size() < 2) {
            mSocket.on(Socket.EVENT_CONNECTING, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    mSocketConnecting = true;
                    CLog.i("Test-Socket Request Manager", "Connecting");
                    if (onSocketIOConnectionListener != null) {
                        onSocketIOConnectionListener.onSocketIOConnected();
                    }
                }
            });
        }
        listeners = mSocket.listeners(Socket.EVENT_CONNECT);
        if (listeners.size() < 2) {
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    CLog.i("Test-Socket Request Manager", "Connected");
                    mSocketConnecting = false;
                    if (onSocketIOConnectionListener != null) {
                        onSocketIOConnectionListener.onSocketIOConnected();
                    }
                }
            });
        }
        if (!mSocket.hasListeners(Socket.EVENT_DISCONNECT)) {
            mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    mSocketConnecting = false;
                    if (onSocketIOConnectionListener != null) {
                        onSocketIOConnectionListener.onSocketIODisconnected();
                    }
                    CLog.i("Test-Socket Request Manager", "Disconnected");
                }
            });
        }
        mSocket.connect();
    }

    private CoreError createInternetConnectionError(Context context) {
        CoreError coreError = new CoreError();
        coreError.setStatusCode(Constants.ErrorCodes.NO_INTERNET_CONNECTION);
        coreError.setMessage(context.getString(R.string.error_no_internet_connection));
        return coreError;
    }

    public void disconnectSocketIO() {
        if (mSocket != null && mSocket.connected()) {
            mSocket.disconnect();
        }
    }

    public void resetSocketIO() {
        if (mSocket == null) {
            return;
        }
        disconnectSocketIO();
        mSocket.off(Socket.EVENT_CONNECT);
        mSocket.off(Socket.EVENT_DISCONNECT);
        mEventListeners.clear();
        mSocketConnecting = false;
    }


    public void sendMessage(Context context, String callerTag, final String eventName, Object object, final OnSocketIOMessageListener onSocketIOMessageListener, final OnSocketIOErrorListener onSocketIOErrorListener) {
        if (mSocket == null) {
            return;
        }
        if (!DeviceUtil.isOnline(context)) {
            if (onSocketIOErrorListener != null) {
                onSocketIOErrorListener.onSocketMessageError(createInternetConnectionError(context));
            }
            return;
        }

        if (!isSocketConnected()) {
            if (onSocketIOErrorListener != null) {
                CoreError coreError = new CoreError();
                coreError.setStatusCode(Constants.ErrorCodes.NO_SOCKET_CONNECTION);
                coreError.setMessage(context.getString(R.string.error_no_socket_connection));
                onSocketIOErrorListener.onSocketMessageError(coreError);
            }
            return;
        }

        if (onSocketIOMessageListener != null) {
            registerOnEvent(callerTag, eventName, onSocketIOMessageListener);
        }
        String message = mGson.toJson(object);
        CLog.i("Test-Request_" + eventName, message);
        mSocket.emit(eventName, message);
    }

    public synchronized void registerOnEvent(String callerTag, final String eventName, final OnSocketIOMessageListener onSocketIOMessageListener) {
        if (mSocket == null || mEventListeners.containsKey(eventName.concat(callerTag)) || onSocketIOMessageListener == null) {
            return;
        }

        Emitter.Listener listener = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                String jsonString = obj.toString();
                if (!StringUtil.isEmpty(jsonString, true)) {
                    onSocketIOMessageListener.onSocketMessage(eventName, jsonString);
                } else {
                    onSocketIOMessageListener.onSocketMessage(eventName, null);
                }
            }
        };
        mSocket.on(eventName, listener);
        mEventListeners.put(eventName.concat(callerTag), listener);
    }

    public void unregisterOnEvent(String callerTag, String eventName) {
        if (mSocket == null) {
            return;
        }
        Emitter.Listener listener = mEventListeners.get(eventName.concat(callerTag));
        if (listener != null) {
            mSocket.off(eventName, listener);
        }
        mEventListeners.remove(eventName.concat(callerTag));
    }

    public Socket getSocket() {
        return mSocket;
    }

    public interface OnSocketIOConnectionListener {
        void onSocketIOConnecting();

        void onSocketIOConnected();

        void onSocketIOConnectionError(CoreError coreError);

        void onSocketIODisconnected();

    }

    public interface OnSocketIOMessageListener {
        void onSocketMessage(String eventName, String jsonMessage);
    }

    public interface OnSocketIOErrorListener {
        void onSocketMessageError(CoreError coreError);
    }

    public boolean isSocketConnecting() {
        return mSocketConnecting;
    }

    public boolean isSocketConnected() {
        return mSocket != null && mSocket.connected();
    }
}
