package com.anotherdev.android.robospice;

import com.octo.android.robospice.SpiceManager;

public class RoboSpice {

    public static RequestCreator with(SpiceManager manager) {
        return new RequestCreator(manager);
    }


    private RoboSpice() {}
}
