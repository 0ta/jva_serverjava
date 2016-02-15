package com.jva.pojo;

public class PassPOJO {
	private String receptionPlayer;
	private String actualAtacknumPerReceptionPlayer = "0";
	private String targetPercent_p2;
	private String targetPercent_p3;
	private String targetPercent_p4;
	private String targetPercent_p5;
	private String targetPercent_p6;
	private String actualAtacknum_p2 = "0";
	private String actualAtacknum_p3 = "0";
	private String actualAtacknum_p4 = "0";
	private String actualAtacknum_p5 = "0";
	private String actualAtacknum_p6 = "0";
	public String getActualAtacknumPerReceptionPlayer() {
		return actualAtacknumPerReceptionPlayer;
	}
	public void setActualAtacknumPerReceptionPlayer(
			String actualAtacknumPerReceptionPlayer) {
		this.actualAtacknumPerReceptionPlayer = actualAtacknumPerReceptionPlayer;
	}
	public String getActualAtacknum_p2() {
		return actualAtacknum_p2;
	}
	public void setActualAtacknum_p2(String actualAtacknum_p2) {
		this.actualAtacknum_p2 = actualAtacknum_p2;
	}
	public String getActualAtacknum_p3() {
		return actualAtacknum_p3;
	}
	public void setActualAtacknum_p3(String actualAtacknum_p3) {
		this.actualAtacknum_p3 = actualAtacknum_p3;
	}
	public String getActualAtacknum_p4() {
		return actualAtacknum_p4;
	}
	public void setActualAtacknum_p4(String actualAtacknum_p4) {
		this.actualAtacknum_p4 = actualAtacknum_p4;
	}
	public String getActualAtacknum_p5() {
		return actualAtacknum_p5;
	}
	public void setActualAtacknum_p5(String actualAtacknum_p5) {
		this.actualAtacknum_p5 = actualAtacknum_p5;
	}
	public String getActualAtacknum_p6() {
		return actualAtacknum_p6;
	}
	public void setActualAtacknum_p6(String actualAtacknum_p6) {
		this.actualAtacknum_p6 = actualAtacknum_p6;
	}
	public String getReceptionPlayer() {
		return receptionPlayer;
	}
	public void setReceptionPlayer(String receptionPlayer) {
		this.receptionPlayer = receptionPlayer;
	}
	public String getTargetPercent_p2() {
		return targetPercent_p2;
	}
	public void setTargetPercent_p2(String targetPercent_p2) {
		this.targetPercent_p2 = targetPercent_p2;
	}
	public String getTargetPercent_p3() {
		return targetPercent_p3;
	}
	public void setTargetPercent_p3(String targetPercent_p3) {
		this.targetPercent_p3 = targetPercent_p3;
	}
	public String getTargetPercent_p4() {
		return targetPercent_p4;
	}
	public void setTargetPercent_p4(String targetPercent_p4) {
		this.targetPercent_p4 = targetPercent_p4;
	}
	public String getTargetPercent_p5() {
		return targetPercent_p5;
	}
	public void setTargetPercent_p5(String targetPercent_p5) {
		this.targetPercent_p5 = targetPercent_p5;
	}
	public String getTargetPercent_p6() {
		return targetPercent_p6;
	}
	public void setTargetPercent_p6(String targetPercent_p6) {
		this.targetPercent_p6 = targetPercent_p6;
	}
	public void sumActualAtacknum() {
		int ret = Integer.parseInt(this.actualAtacknum_p2)
				+ Integer.parseInt(this.actualAtacknum_p3)
				+ Integer.parseInt(this.actualAtacknum_p4)
				+ Integer.parseInt(this.actualAtacknum_p5)
				+ Integer.parseInt(this.actualAtacknum_p6);
		this.actualAtacknumPerReceptionPlayer = String.valueOf(ret);
	}
}
