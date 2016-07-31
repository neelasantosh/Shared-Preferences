package com.example.menuandsharedassignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends Fragment{
	EditText editName, editEmail, editMobile, editPassword;
	Button buttonSubmit;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View fragView = inflater.inflate(R.layout.fragment_register, container,false);
		editName = (EditText) fragView.findViewById(R.id.editText1);
		editEmail = (EditText) fragView.findViewById(R.id.editText2);
		editMobile = (EditText) fragView.findViewById(R.id.editText3);
		editPassword = (EditText) fragView.findViewById(R.id.editText4);
		buttonSubmit = (Button) fragView.findViewById(R.id.button1);

		buttonSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = editName.getText().toString();
				if (name.equals("")) {
					editName.setError("Name cannot be Blank");
					return;

				}
				String email = editEmail.getText().toString();
				String emailPattern = "[a-zA-Z0-9@.]{2,}";
				Pattern p1 = Pattern.compile(emailPattern);
				Matcher matcher = p1.matcher(email);
				if (!matcher.matches()) {
					editEmail.setError("Invalid Email");
					return;
				}
				// validate mobile number
				String number = editMobile.getText().toString();
				String mobilePattern = "\\d{10}";
				Pattern p2 = Pattern.compile(mobilePattern);
				Matcher matcher2 = p2.matcher(number);
				if (!matcher2.matches()) {
					editMobile.setError("Invalid Mobile Number");
					return;
				}
				String password = editPassword.getText().toString();
				if (password.length() < 6) {
					editPassword.setError("Invalid Password");
					return;
				}

				// save data in Shared Preferences
				// 1.open preference file
				SharedPreferences sp = getActivity().getSharedPreferences("user data",
						getActivity().MODE_PRIVATE);
				//2.Get Editor Object Preference
				SharedPreferences.Editor editor = sp.edit();
				//3.add Data to file using editor
				editor.putString("name", name);
				editor.putString("email", email);
				editor.putString("mobile", number);
				editor.putString("password", password);
				editor.commit();
				//clear all edit box
				editEmail.setText("");
				editName.setText("");
				editMobile.setText("");
				editPassword.setText("");
				MainActivity main=(MainActivity)getActivity();
				main.LoadInFragment();
			}
		});
		
		return fragView;
	}
}
