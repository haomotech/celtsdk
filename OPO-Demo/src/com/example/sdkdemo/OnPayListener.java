package com.example.sdkdemo;

import android.content.Context;
import android.widget.Toast;

import com.celtgame.sdk.CeltListener;
import com.celtgame.sdk.CeltAgent;


public class OnPayListener implements CeltListener {
	private Context mContext;
	
	public OnPayListener(Context context) {
		this.mContext = context;
	}
	
	@Override
	public void onBillingFinish(int errcode) {
		if(errcode == CeltAgent.ORDER_OK) {
			Toast.makeText(mContext, "Order OK!", Toast.LENGTH_LONG).show();
		} else if(errcode == CeltAgent.ORDER_ALL_OK) {
			Toast.makeText(mContext, "All Order OK!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_CANCEL) {
			Toast.makeText(mContext, "User Cance!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_FAIL){
			Toast.makeText(mContext, "Order Fail!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_IN_DEBT){
			Toast.makeText(mContext, "Order In Debt!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_CLEAR_DEBT){
			Toast.makeText(mContext, "Order Clear Debt!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_FREE){
			Toast.makeText(mContext, "Order Free!", Toast.LENGTH_LONG).show();
		}else if (errcode == CeltAgent.ORDER_FORBIDDEN){
			Toast.makeText(mContext, "Order forbidden!", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_WAIT){
			Toast.makeText(mContext, "please wait", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_EXCEED){
			Toast.makeText(mContext, "Order exceed", Toast.LENGTH_LONG).show();
		} else if (errcode == CeltAgent.ORDER_NOT_EXIST){
			Toast.makeText(mContext, "Order not exist", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitFinish(int errcode) {
		if(errcode == CeltAgent.INIT_OK) {
			Toast.makeText(mContext, "Init OK!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(mContext, "Init Fail!", Toast.LENGTH_LONG).show();
		}
	}

}

