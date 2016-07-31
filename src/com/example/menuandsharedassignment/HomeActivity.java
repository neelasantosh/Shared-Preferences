package com.example.menuandsharedassignment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

public class HomeActivity extends Fragment {
	TextView textName, textEmail;
	Button buttonCall;
	ImageView img;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View fragView = inflater.inflate(R.layout.home1, container,false);
		textName = (TextView) fragView.findViewById(R.id.textView2);
		textEmail = (TextView) fragView.findViewById(R.id.textView3);
		buttonCall = (Button) fragView.findViewById(R.id.button1);
		img=(ImageView) fragView.findViewById(R.id.imageView1);
		
		 final SharedPreferences sp= getActivity().getSharedPreferences("user data",getActivity().MODE_PRIVATE);
		textName.setText(sp.getString("name", null));
		textEmail.setText(sp.getString("email", null));
		
		final String number = sp.getString("mobile", null);
		
		buttonCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intentcall=new Intent();
				intentcall.setAction("android.intent.action.CALL");
				
				Uri u= Uri.parse("tel:"+number);
				intentcall.setData(u);
				
				startActivity(intentcall);
			}
		});
		
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intentimg=new Intent(Intent.ACTION_SENDTO);
				Uri u= Uri.parse("mailto:"+sp.getString("mail", null));
				intentimg.setData(u);
				intentimg.putExtra("subject", "my subject");
				intentimg.putExtra("body", "my message");
				startActivity(intentimg);
				
			}
		});
		return fragView;
	}
	
	
}
