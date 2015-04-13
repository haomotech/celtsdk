package com.example.sdkdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.celtgame.sdk.Bulletin;
import com.celtgame.sdk.CeltAgent;
import com.celtgame.sdk.CustomConfig;
import com.example.sdkdemo.OnPayListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity{
	
	private static final String TAG = "CELTSER";
		
	private OnPayListener mListener;	
	private CeltAgent mAgent;
	
	private String[] charges = new String[]{
			"P1 Order","P1 Begin","P2 Completed","P3 Failed","Buy","Use","RewardVirtual",
			"RewardItem","Event","EventString","EventJson", "setInfo",
			"order10", "order11", "order12", "order13", "order14", "order15"
			};		

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		ListView listView = new ListView(this);
		listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charges);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch(arg2){
				case 0:
					new Thread(new Runnable(){

						@Override
						public void run() {
							mAgent.order(1, 500);
						}
						
					}).start();					
					//mAgent.order(1, 500);
					break;
				case 1:
					if (mAgent.needPrompt(1)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}
					mAgent.order(1, 500);
					mAgent.onBegin("testMission");
					break;
				case 2:
					if (mAgent.needPrompt(2)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(2, 500);
					mAgent.onCompleted("testMission");
					break;
				case 3:
					if (mAgent.needPrompt(3)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(3, 500);
					mAgent.onFailed("testMission", "dead");
					break;
				case 4:
					if (mAgent.needPrompt(4)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(4, 500);
					mAgent.onBuy("shield", 1, 100);
					break;
				case 5:
					if (mAgent.needPrompt(5)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(5, 500);
					mAgent.onUse("shield", 1);
					break;
				case 6:
					if (mAgent.needPrompt(6)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(6, 500);
					mAgent.onReward("coin",50, "login");
					break;
				case 7:
					if (mAgent.needPrompt(7)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}					
					mAgent.order(7, 500);
					mAgent.onReward("shield", 1, "collect");
					break;				
				case 8:
					if (mAgent.needPrompt(8)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}	
					mAgent.order(8, 500);
					mAgent.onEvent("share");
					break;
				case 9:
					if (mAgent.needPrompt(9)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}						
					mAgent.order(9, 500);
					mAgent.onEvent("click", "button1");
					break;
				case 10:
					if (mAgent.needPrompt(10)){
						Toast.makeText(MainActivity.this, "need prompt", Toast.LENGTH_LONG).show();
					}						
					mAgent.order(10, 500);
					String json = "{\"key1\": \"value1\", \"key2\": 222}";
					try {
						mAgent.onEvent("complexEvent", new JSONObject(json));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 11:
					printCustomConfig();
					JSONObject data = new JSONObject();
					try {
						data.put("name", "player1");
						data.put("level", 1);
						data.put("age", 15);
						data.put("gender", 1);
					} catch (JSONException e) {
					}
					mAgent.setInfo("player", data);
					break;
				case 12:
					mAgent.order(10, 0);
					break;
				case 13:
					mAgent.order(11, 0);
					break;
				case 14:
					mAgent.order(12, 0);
					break;
				case 15:
					mAgent.order(13, 0);
					break;
				case 16:
					mAgent.order(14, 0);
					break;
				case 17:
					mAgent.order(15, 0);
					break;								
				}
				
			}
		});    
		
        setContentView(listView);
        
        mListener = new OnPayListener(this);
        mAgent = CeltAgent.getInstance();
        mAgent.init(this, mListener);    
        
        printCustomConfig();
       
      
    }
    
    private void printCustomConfig(){
    	
        CustomConfig custom = CustomConfig.getInstance();
        int RANK_PRIZE_NUM = custom.getInt("RANK_PRIZE_NUM", 1000);
        int POP_PROMO_CD = custom.getInt("POP_PROMO_CD", 0);
        JSONArray array[] = new JSONArray[8];
        try {
			array[0] = custom.getArray("PROMO_NO_FIRST_RATIO", new JSONArray("[1,2,3,4,5,6,7,8]"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        array[1] = custom.getArray("PROMO_NO_FISH_RATIO", null);
        array[2] = custom.getArray("PROMO_DEFAULT", null);
        array[3] = custom.getArray("PROMO_COIN_RATIO", null);
        array[4] = custom.getArray("DAILY_RATIO", null);
        
       Log.v(TAG, "" +RANK_PRIZE_NUM + " " + POP_PROMO_CD);
       
       for (int i=0; i<array.length; i++){
    	   JSONArray a = array[i];
    	   if (a == null){
    		   Log.v(TAG, "array "+i + " = null");
    		   continue;
    	   }
           for (int j=0; j<8; j++){
       			try {
       				int value = a.getInt(j);
       				Log.v(TAG, "array" + i + " " + j + " " + value);
       			} catch (JSONException e) {
       			}
           }
       }
       

        
        Bulletin bulletin = custom.getBulletin();
        if (bulletin != null){
            String bb = bulletin.id + ' ' + bulletin.title + ' ' + bulletin.text;
            Toast.makeText(this, bulletin.text, Toast.LENGTH_LONG).show();
            Log.v(TAG, "bulletin: " + bb);                	
        }
        else{
        	Log.v(TAG, "No bulletin");  
        }

            	
    }    
	
	@Override
	protected void onResume(){
		mAgent.onResume(this);
		super.onResume();
	}
	
	@Override
	protected void onPause(){
		mAgent.onPause(this);
		super.onPause();
	}
	
    @Override
	protected void onDestroy() {
    	mAgent.onExit();
		super.onDestroy();
	}	
	
}