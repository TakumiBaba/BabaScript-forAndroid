package org.babascript;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by s09704tb on 2013/07/11.
 */
public class OrderArrayAdapter extends ArrayAdapter {
    ArrayList<Order> orders;

    public OrderArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        orders = new ArrayList<Order>();
    }

    public void add(Order order){
        orders.add(order);
        super.add(order.content);
    }

    public void remove(int position){
        if(orders.size() < 1 || position > orders.size()){
            return;
        }
        Order order = orders.get(position);
        super.remove(order.content);
        orders.remove(position);
    }
}
