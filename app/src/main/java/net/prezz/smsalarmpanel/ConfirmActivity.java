package net.prezz.smsalarmpanel;

import net.prezz.smsalarmpanel.strategy.AbstractSmsCommandStrategy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends Activity {

	private AbstractSmsCommandStrategy smsCommandStrategy;
	private String code;


	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);        
        
        smsCommandStrategy = (AbstractSmsCommandStrategy)getIntent().getExtras().getSerializable(AbstractSmsCommandStrategy.class.getName());
        
        code = "";
        
        setDescriptionText(smsCommandStrategy.getDescription());
        
        final Button button0 = (Button)findViewById(R.id.button0);
        button0.setOnClickListener(new ButtonDigitClickListener(button0));
        
        final Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new ButtonDigitClickListener(button1));
        
        final Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new ButtonDigitClickListener(button2));
        
        final Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new ButtonDigitClickListener(button3));
        
        final Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new ButtonDigitClickListener(button4));
        
        final Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new ButtonDigitClickListener(button5));
        
        final Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new ButtonDigitClickListener(button6));
        
        final Button button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(new ButtonDigitClickListener(button7));
        
        final Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(new ButtonDigitClickListener(button8));
        
        final Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(new ButtonDigitClickListener(button9));
	}

    private void setDescriptionText(String text) {
		TextView textViewStatus = (TextView)findViewById(R.id.textDescription);
		textViewStatus.setText(text);
    }

    private void setCodeText(String text) {
		TextView textViewStatus = (TextView)findViewById(R.id.textCode);
		textViewStatus.setText(text);
    }
    
    private void appendCodeText(String text) {
		TextView textViewStatus = (TextView)findViewById(R.id.textCode);
		textViewStatus.append(text);
    }
    
    private final class ButtonDigitClickListener implements OnClickListener {
    	Button button;
    	
    	public ButtonDigitClickListener(Button button) {
    		this.button = button;
    	}
    	
		public void onClick(View v) {
			code += button.getText();
			if (code.length() == 1) {
				setCodeText("*");
			} else {
				appendCodeText("*");
			}
			
			if (code.length() == 4) {
				smsCommandStrategy.sendSmsAlarmCommand(code);
				finish();
			}
		}
    }	
}
