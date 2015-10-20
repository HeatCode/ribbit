package com.heatcode.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by HeatherGuin on 10/19/2015.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
// Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "W02sSJLKjOYpfwGvhnoQnCcEBWE9YaUpyiDo9NDk", "mZ4MosRswpsWuxQ2MWu0EOQthayTkyNixAlNS9zr");
    }


}
