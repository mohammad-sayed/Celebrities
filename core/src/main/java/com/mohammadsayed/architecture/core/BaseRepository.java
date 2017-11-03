package com.mohammadsayed.architecture.core;

/**
 * Created by mohammad on 1/16/17.
 */

public interface BaseRepository<PCB extends BasePresenterCallback> {

    PCB getPresenterCallback();

    void setPresenterCallback(PCB presenterCallback);
}
