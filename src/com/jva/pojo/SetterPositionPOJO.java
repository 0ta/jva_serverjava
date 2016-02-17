package com.jva.pojo;

import java.util.List;

public class SetterPositionPOJO {
	private List<PassPOJO> passes;
	private String position;
	private int answerHitratio_num = 0;
	private int answerHitratio_correctNum = 0;
	public int getAnswerHitratio_num() {
		return answerHitratio_num;
	}
	public void setAnswerHitratio_num(int answerHitratio_num) {
		this.answerHitratio_num = answerHitratio_num;
	}
	public int getAnswerHitratio_correctNum() {
		return answerHitratio_correctNum;
	}
	public void setAnswerHitratio_correctNum(int answerHitratio_correctNum) {
		this.answerHitratio_correctNum = answerHitratio_correctNum;
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
