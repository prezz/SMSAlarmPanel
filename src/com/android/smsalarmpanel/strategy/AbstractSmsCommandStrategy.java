package com.android.smsalarmpanel.strategy;

import java.io.Serializable;

import android.telephony.SmsManager;

public abstract class AbstractSmsCommandStrategy implements Serializable {

	private static final long serialVersionUID = 7985826920712745681L;

	private String phoneNumber;
	private String description;
	
		
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public abstract boolean sendSmsAlarmCommand(String code);
	
	protected boolean sendSmsCommand(String descriptor, String code, String command) {
		if (phoneNumber == null) {
			return false;
		}
		
		if (descriptor == null) {
			return false;
		}
		
		if (code == null || code.length() != 4) {
			return false;
		}
		
		if (command == null || command.length() != 3) {
			return false;
		}

		String message = String.format("%s#%s%s", descriptor, code, command);
		
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, null, null);
		
		return true;
	}
}
