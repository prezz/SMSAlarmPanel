package com.android.smsalarmpanel.strategy;

public class StatusSmsCommandStrategy extends AbstractSmsCommandStrategy {

	private static final long serialVersionUID = -1241294679116113970L;

	
	public boolean sendSmsAlarmCommand(String code) {
		return super.sendSmsCommand("Get status", code, "200");
	}
}
