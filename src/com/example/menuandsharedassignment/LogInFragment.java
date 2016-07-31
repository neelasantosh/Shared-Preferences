package com.example.menuandsharedassignment;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInFragment  extends Fragment{
	EditText editEmail, editPassword;
	Button buttonSignin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View fragView = inflater.inflate(R.layout.activity_main, container,false);
		
		editEmail = (EditText) fragView.findViewById(R.id.editText1);
		editPassword = (EditText) fragView.findViewById(R.id.editText2);
		buttonSignin = (Button) fragView.findViewById(R.id.button1);

		buttonSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// we read stored email and password
				SharedPreferences sp = getActivity().getSharedPreferences("user data",
						getActivity().MODE_PRIVATE);
				String oldEmail = sp.getString("email", null);// returns null
																// value if no
																// previous
																// emails and
																// passwords are
																// stored
				String oldPass = sp.getString("password", null);
				if (oldEmail == null || oldPass == null) {
					editEmail.setError("Please Register");
					return;
				}
				// validate edit text value with store value
				String email = editEmail.getText().toString();
				String password = editPassword.getText().toString();

				if (email.equals(oldEmail) && password.equals(password)) {
					
					editEmail.setText("");
					editPassword.setText("");
					MainActivity main = (MainActivity)getActivity();
					main.loadHomeFragment();
					
				} else {
					Toast.makeText(getActivity(),
							"InCorrect Email/password", Toast.LENGTH_LONG).show();
				}
			}
		});

	
		return fragView;
	}

}
