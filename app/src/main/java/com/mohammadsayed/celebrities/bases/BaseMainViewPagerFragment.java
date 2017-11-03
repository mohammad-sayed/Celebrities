package com.mohammadsayed.celebrities.bases;

import com.mohammadsayed.architecture.core.BaseFragment;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.utils.StringUtil;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public abstract class BaseMainViewPagerFragment<P extends BasePresenter> extends BaseFragment<P> {

    private String mPageTitle;

    public void setPageTitle(String pageTitle) {
        this.mPageTitle = pageTitle;
    }

    public String getPageTitle() {
        return mPageTitle;
    }
}
