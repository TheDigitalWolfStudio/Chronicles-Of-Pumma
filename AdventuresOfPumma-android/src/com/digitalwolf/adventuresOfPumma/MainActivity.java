


package com.digitalwolf.adventuresOfPumma;

/*
This is the main class that runs on Android Devices. This class calls AdventuresOfPumma.java in the main libgdx project. 
 */


import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//import com.apperhand.device.android.AndroidSDKProvider;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
     // Define StartApp Banner
        initialize(new AdventuresOfPumma(), cfg);
    }
}