package com.mohammadsayed.architecture.network.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mohammadsayed.architecture.utils.CLog;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by mohammad on 1/19/17.
 */

public class GsonJsonRequest<T> extends JsonRequest<T> {

    private final Gson gson = new Gson();
    private final Class<T> responseClazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private final int REQUEST_TIMEOUT_MS = 60 * 1000;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param responseClass Relevant class object, for Gson's reflection
     * @param headers       Map of request headers
     */
    public GsonJsonRequest(int method, String url, Class<T> responseClass, Map<String, String> headers,
                           String body, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, body, listener, errorListener);
        this.responseClazz = responseClass;
        this.headers = headers;
        this.listener = listener;
        setRetryPolicy(new DefaultRetryPolicy(
                REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            CLog.i("Test-Response", json);
            return Response.success(responseClazz == null ? null : gson.fromJson(json, responseClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public String toString() {
        String string = super.toString();
        string += "\nHeaders: " + getHeadersString();
        return string;
    }

    private String getHeadersString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(",");
        }
        return stringBuilder.toString();
    }
}
