package org.babascript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.takumibaba.lindaclient.Linda;

import org.json.JSONArray;

public class MainActivity extends Activity {
    Linda linda;
    public ListView listView;
    public OrderArrayAdapter adapter;
    private BabaScript bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new OrderArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = adapter.orders.get(i);
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "org.babascript.OrderReply");
                intent.putExtra("callback", order.callback);
                intent.putExtra("content", order.content);
                startActivityForResult(intent, 0);
            }
        });
        bb = new BabaScript(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void addOrder(Order order){
        adapter.add(order);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK){
            Bundle extras = intent.getExtras();
            String callback = "";
            String content = "";
            if(extras != null){
                callback = extras.getString("callback");
                content = extras.getString("content");
            }
            for(int i=0;i<adapter.orders.size();i++){
                if(adapter.orders.get(i).callback.equals(callback)){
                    Log.d("RESULT", "delete!");
                    adapter.remove(i);
                    adapter.notifyDataSetChanged();
                    JSONArray tuple = new JSONArray();
                    tuple.put("babascript");
                    tuple.put("return");
                    tuple.put(callback);
                    tuple.put(content);
                    tuple.put(new JSONArray());
                    bb.ts.write(tuple, new JSONArray());
                }
            }
        }
    }

}