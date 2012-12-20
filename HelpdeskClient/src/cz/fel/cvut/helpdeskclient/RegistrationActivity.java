package cz.fel.cvut.helpdeskclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends Activity {
	
	Button back;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		back = (Button)findViewById(R.id.back);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(RegistrationActivity.this,Login.class);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();
				
			}
		});
	}
	

}
