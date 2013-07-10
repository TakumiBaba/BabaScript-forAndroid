package org.babascript;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.takumibaba.lindaclient.Linda;
import com.takumibaba.lindaclient.LindaCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Handler;

/**
 * Created by s09704tb on 2013/07/01.
 */
public class BabaScript implements LindaCallback {
    private MainActivity mActivity;
    private Linda linda;
    public Linda.TupleSpace ts;
    public ListView listView;
    public Handler mHandler;

    public BabaScript(Activity activity){
        linda = new Linda();
        ts = linda.ts;
        linda.callback = this;
        mActivity = (MainActivity)activity;
        linda.connect();
        listView = (ListView)activity.findViewById(R.id.listView);

    }

    @Override
    public void connect(JSONObject json) {
        Log.d("BB:onConnect", json.toString());
        JSONArray tuple = new JSONArray();
        tuple.put("babascript");
        tuple.put("eval");
        ts.watch(tuple);
    }

    @Override
    public void watch(JSONObject json) {
        Log.d("BB:onWatch", json.toString());
        JSONArray tuple = null;
        try {
            tuple = json.getJSONArray("data");
            final Order order = new Order();
            order.content = tuple.getString(2);
            order.callback = tuple.getJSONObject(4).getString("callback");
            order.option = tuple.getJSONArray(3);
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mActivity.addOrder(order);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
