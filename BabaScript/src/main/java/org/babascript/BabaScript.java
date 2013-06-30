package org.babascript;

import android.util.Log;

import com.takumibaba.lindaclient.Linda;
import com.takumibaba.lindaclient.LindaCallback;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by s09704tb on 2013/07/01.
 */
public class BabaScript implements LindaCallback {

    private Linda linda;
    private Linda.TupleSpace ts;

    public BabaScript(){
        linda = new Linda();
        ts = linda.ts;
        linda.callback = this;
        linda.connect();

    }

    @Override
    public void connect(JSONObject json) {
        Log.d("BB:onConnect", json.toString());
        JSONArray tuple = new JSONArray();
        tuple.put("babascript");
        ts.watch(tuple);
    }

    @Override
    public void watch(JSONObject json) {
        Log.d("BB:onWatch", json.toString());
    }

    @Override
    public void read(JSONObject json) {
        Log.d("BB:onRead", json.toString());
    }

    @Override
    public void write(JSONObject json) {
        Log.d("BB:onWrite", json.toString());
    }

    @Override
    public void take(JSONObject json) {
        Log.d("BB:onTake", json.toString());
    }
}
