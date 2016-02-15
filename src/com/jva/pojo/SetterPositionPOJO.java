package com.jva.pojo;

import java.util.List;

public class SetterPositionPOJO {
	private List<PassPOJO> passes;
	private String position;
	private String answerHitratio;
	public String getAnswerHitratio() {
		return answerHitratio;
	}
	public void setAnswerHitratio(String answerHitratio) {
		this.answerHitratio = answerHitratio;
	}
	public List<PassPOJO> getPasses() {
		return passes;
	}
	public void setPasses(List<PassPOJO> passes) {
		this.passes = passes;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void sumPassesActualAtackNum() {
		for (int i = 0; i < passes.size(); i++) {
			passes.get(i).sumActualAtacknum();
		}
	}
}
