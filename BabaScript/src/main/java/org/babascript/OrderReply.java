package org.babascript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by s09704tb on 2013/07/11.
 */
public class OrderReply extends Activity implements View.OnClickListener{

    public TextView orderText;
    public EditText replyText;
    public Button replyButton;
    private String callback;

    public OrderReply(){

    }

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.order_reply);

        setResult(RESULT_CANCELED);
        Bundle extras = getIntent().getExtras();
        callback = extras.getString("callback");
        orderText = (TextView)findViewById(R.id.orderText);
        orderText.setText(extras.getString("content"));

        replyText = (EditText)findViewById(R.id.replyText);

        replyButton = (Button)findViewById(R.id.replyButton);
        replyButton.setTag("reply");
        replyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("callback", callback);
        intent.putExtra("content", replyText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
