package com.makemyandroidapp.broadcastexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
/****************************
 * Example that demonstrates how
 * you can dynamically create
 * and register a BroadcastReceiver
 * from within an Activity.
 ****************************/
public class MainActivity extends Activity {
	//Reference to our Background Layout
	RelativeLayout bgroundLyt;
	
	//Define our Action string note the use of package name.
	public final String ACTION_CHANGE_COLOR = 
			"com.makemyandroidapp.broadcastexample.CHANGE_COLOR";
	
	//Reference for the receiver.
	BroadcastReceiver myActivityReceiver;
	
	//Tag for logs.
	public final String TAG = "Fun With Receivers";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bgroundLyt = (RelativeLayout)findViewById(R.id.bgroundLyt);
		
		//Declare our receiver. Overriding the onReceive() method.
		myActivityReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context ctx, Intent data) {
				//Read the color out of the extras
				String hexColor = data.getStringExtra("color");
				Toast.makeText(ctx, "Changing Color", Toast.LENGTH_SHORT).show();
				Log.i(TAG, "Color Received = " + hexColor);
				if(null != hexColor){
					//Set the background color
					//TODO: validate color string
					bgroundLyt.setBackgroundColor(Color.parseColor(hexColor));
				}
			}
		};
		//Create an IntentFilter to listen for our ACTION string.
		IntentFilter changeColorFilter = new IntentFilter(ACTION_CHANGE_COLOR);
		
		//Finally, register the receiver.
		registerReceiver(myActivityReceiver, changeColorFilter);
	}
	@Override
	public void onStop(){
		super.onStop();
		//Don't forget to unregister your receiver when you are done with it.
		unregisterReceiver(myActivityReceiver);
	}
}
