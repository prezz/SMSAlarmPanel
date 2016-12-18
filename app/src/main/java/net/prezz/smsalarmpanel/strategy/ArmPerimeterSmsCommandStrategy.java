package net.prezz.smsalarmpanel.strategy;

public class ArmPerimeterSmsCommandStrategy extends AbstractSmsCommandStrategy {

	private static final long serialVersionUID = 1478540598775302598L;

	
	public boolean sendSmsAlarmCommand(String code) {
		return super.sendSmsCommand("Arm perimeter", code, "123");
	}
}
