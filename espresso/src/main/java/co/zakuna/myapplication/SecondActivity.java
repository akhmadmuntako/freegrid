package co.zakuna.myapplication;

/**
 * Created by Lenovo on 14/07/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView viewById = (TextView) findViewById(R.id.resultView);
//        Bundle inputData = getIntent().getExtras();
//        String input = inputData.getString("NewText");
        viewById.setText("input");
    }
}
