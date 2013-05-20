package com.android.smsalarmpanel;

import com.android.smsalarmpanel.strategy.AbstractSmsCommandStrategy;
import com.android.smsalarmpanel.strategy.ArmFullSmsCommandStrategy;
import com.android.smsalarmpanel.strategy.ArmPartialSmsCommandStrategy;
import com.android.smsalarmpanel.strategy.ArmPerimeterSmsCommandStrategy;
import com.android.smsalarmpanel.strategy.DisarmSmsCommandStrategy;
import com.android.smsalarmpanel.strategy.StatusSmsCommandStrategy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * TODO
 * read sms from alarm phone number and present text to user in app
 * Sms send and delivery feedback
 *
 */

public class MainActivity extends Activity {
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        final Button buttonFull = (Button)findViewById(R.id.buttonFull);
        buttonFull.setOnClickListener(new ButtonCommandClickListener(buttonFull, new ArmFullSmsCommandStrategy()));
        
        final Button buttonPartial = (Button)findViewById(R.id.buttonPartial);
        buttonPartial.setOnClickListener(new ButtonCommandClickListener(buttonPartial, new ArmPartialSmsCommandStrategy()));
        
        final Button buttonPerimeter = (Button)findViewById(R.id.buttonPerimeter);
        buttonPerimeter.setOnClickListener(new ButtonCommandClickListener(buttonPerimeter, new ArmPerimeterSmsCommandStrategy()));

        final Button buttonDisarm = (Button)findViewById(R.id.buttonDisarm);
        buttonDisarm.setOnClickListener(new ButtonCommandClickListener(buttonDisarm, new DisarmSmsCommandStrategy()));

        final Button buttonStatus = (Button)findViewById(R.id.buttonStatus);
        buttonStatus.setOnClickListener(new ButtonCommandClickListener(buttonStatus, new StatusSmsCommandStrategy()));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuSettings:
            	startPreferenceActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
            
    private String getPhoneNumber() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String phoneNumber = sharedPreferences.getString("phoneNumber", null);
		return phoneNumber;
    }
    
    private void startPreferenceActivity() {
		startActivity(new Intent(this, PreferencesActivity.class));
    }
    
    private void startConfirmActivity(AbstractSmsCommandStrategy smsCommandStrategy) {
		Intent intent = new Intent(MainActivity.this, ConfirmActivity.class);
		intent.putExtra(AbstractSmsCommandStrategy.class.getName(), smsCommandStrategy);
		startActivity(intent);
    }
    
    private final class ButtonCommandClickListener implements OnClickListener {
    	
    	private Button button;
		private AbstractSmsCommandStrategy commandStrategy;


		public ButtonCommandClickListener(Button button, AbstractSmsCommandStrategy commandStrategy) {
    		this.button = button;
    		this.commandStrategy = commandStrategy;
    	}
    	
		public void onClick(View v) {
			String phoneNumber = getPhoneNumber();
			if (phoneNumber == null || phoneNumber.isEmpty()) {
				startPreferenceActivity();
			} else {
				commandStrategy.setPhoneNumber(phoneNumber);
				commandStrategy.setDescription(button.getText().toString());
				startConfirmActivity(commandStrategy);
			}
		}
    }
}