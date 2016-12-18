package net.prezz.smsalarmpanel.strategy;

public class ArmFullSmsCommandStrategy extends AbstractSmsCommandStrategy {

	private static final long serialVersionUID = -5105197192983232502L;

	
	public boolean sendSmsAlarmCommand(String code) {
		return super.sendSmsCommand("Arm full", code, "121");
	}
}
