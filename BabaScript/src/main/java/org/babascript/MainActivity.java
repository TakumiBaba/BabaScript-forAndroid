package org.babascript;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.takumibaba.lindaclient.Linda;

public class MainActivity extends Activity {
    Linda linda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BabaScript bb = new BabaScript();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
