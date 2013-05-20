package com.android.smsalarmpanel.strategy;

public class DisarmSmsCommandStrategy extends AbstractSmsCommandStrategy {

	private static final long serialVersionUID = -96804436884010584L;


	public boolean sendSmsAlarmCommand(String code) {
		return super.sendSmsCommand("Disarm", code, "120");
	}
}
