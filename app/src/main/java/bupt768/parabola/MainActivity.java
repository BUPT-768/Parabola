package bupt768.parabola;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import bupt768.parabola.MyViews.Message;
import bupt768.parabola.MyViews.MessageType;
import bupt768.parabola.MyViews.MySurfaceView;


public class MainActivity extends ActionBarActivity {

    private Button turnLeftButton;
    private Button turnRightButton;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MySurfaceView mySurfaceView = new MySurfaceView(MainActivity.this);
        setContentView(R.layout.activity_main);
        turnRightButton=(Button)findViewById(R.id.turn_right_button);
        turnRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Integer> action=new HashMap<String, Integer>();
                action.put("offsetX",50);
                action.put("offsetY", 60);
                Message moveMessage=new Message(MessageType.Control,"ball1",action);
                mySurfaceView.manager.getMessage(moveMessage);
                Log.d(TAG,"click");
            }
        });


//        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
