package com.mohammadsayed.celebrities.bases;

import android.content.Context;

import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.R;

/**
 * Created by mohammad on 6/10/17.
 */

public class ErrorManager {

    private static ErrorManager mErrorManager;

    public static ErrorManager getInstance() {
        if (mErrorManager == null) {
            synchronized (ErrorManager.class) {
                if (mErrorManager == null) {
                    mErrorManager = new ErrorManager();
                }
            }
        }
        return mErrorManager;
    }

    public CoreError handleApiError(Context context, BaseResponse baseResponse) {
        CoreError coreError = new CoreError();
        coreError.setStatusCode(baseResponse.getCode());
        coreError.setMessage(baseResponse.getMessage());
        return handleApiError(context, coreError);
    }

    public CoreError handleApiError(Context context, CoreError coreError) {
        if (coreError.getMessage() != null
                && !(coreError.getMessage().contains("html") || coreError.getMessage().contains("HTML"))) {
            return coreError;
        }
        String message = context.getString(R.string.error_unknown);
        coreError.setMessage(context.getString(R.string.error_format, coreError.getStatusCode(), message));
        return coreError;
    }
}
