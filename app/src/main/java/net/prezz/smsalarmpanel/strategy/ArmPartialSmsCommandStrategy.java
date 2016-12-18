package net.prezz.smsalarmpanel.strategy;

public class ArmPartialSmsCommandStrategy extends AbstractSmsCommandStrategy {

	private static final long serialVersionUID = 809248794345414526L;


	public boolean sendSmsAlarmCommand(String code) {
		return super.sendSmsCommand("Arm partial", code, "122");
	}
}
