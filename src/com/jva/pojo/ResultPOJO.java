package com.jva.pojo;

import java.util.List;

public class ResultPOJO {
	private String gameId;
	private String set;
	private String score;
	private String currentRotation;
	private int rallyNumber;
	private String uninum_p1;
	private String uninum_p2;
	private String uninum_p3;
	private String uninum_p4;
	private String uninum_p5;
	private String uninum_p6;
	private String rrate_p2;
	private String rrate_p3;
	private String rrate_p4;
	private String rrate_p5;
	private String rrate_p6;
	private String answerHitratio_total;
	private String answerHitratio_1;
	private String answerHitratio_2;
	private String answerHitratio_3;
	private String answerHitratio_4;
	private String answerHitratio_5;
	public String getAnswerHitratio_total() {
		return answerHitratio_total;
	}
	public void setAnswerHitratio_total(String answerHitratio_total) {
		this.answerHitratio_total = answerHitratio_total;
	}
	public String getAnswerHitratio_1() {
		return answerHitratio_1;
	}
	public void setAnswerHitratio_1(String answerHitratio_1) {
		this.answerHitratio_1 = answerHitratio_1;
	}
	public String getAnswerHitratio_2() {
		return answerHitratio_2;
	}
	public void setAnswerHitratio_2(String answerHitratio_2) {
		this.answerHitratio_2 = answerHitratio_2;
	}
	public String getAnswerHitratio_3() {
		return answerHitratio_3;
	}
	public void setAnswerHitratio_3(String answerHitratio_3) {
		this.answerHitratio_3 = answerHitratio_3;
	}
	public String getAnswerHitratio_4() {
		return answerHitratio_4;
	}
	public void setAnswerHitratio_4(String answerHitratio_4) {
		this.answerHitratio_4 = answerHitratio_4;
	}
	public String getAnswerHitratio_5() {
		return answerHitratio_5;
	}
	public void setAnswerHitratio_5(String answerHitratio_5) {
		this.answerHitratio_5 = answerHitratio_5;
	}
	public String getRrate_p2() {
		return rrate_p2;
	}
	public void setRrate_p2(String rrate_p2) {
		this.rrate_p2 = rrate_p2;
	}
	public String getRrate_p3() {
		return rrate_p3;
	}
	public void setRrate_p3(String rrate_p3) {
		this.rrate_p3 = rrate_p3;
	}
	public String getRrate_p4() {
		return rrate_p4;
	}
	public void setRrate_p4(String rrate_p4) {
		this.rrate_p4 = rrate_p4;
	}
	public String getRrate_p5() {
		return rrate_p5;
	}
	public void setRrate_p5(String rrate_p5) {
		this.rrate_p5 = rrate_p5;
	}
	public String getRrate_p6() {
		return rrate_p6;
	}
	public void setRrate_p6(String rrate_p6) {
		this.rrate_p6 = rrate_p6;
	}
	private List<SetterPositionPOJO> setterPositions;
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCurrentRotation() {
		return currentRotation;
	}
	public void setCurrentRotation(String currentRotation) {
		this.currentRotation = currentRotation;
	}
	public int getRallyNumber() {
		return rallyNumber;
	}
	public void setRallyNumber(int rallyNumber) {
		this.rallyNumber = rallyNumber;
	}
	public String getUninum_p1() {
		return uninum_p1;
	}
	public void setUninum_p1(String uninum_p1) {
		this.uninum_p1 = uninum_p1;
	}
	public String getUninum_p2() {
		return uninum_p2;
	}
	public void setUninum_p2(String uninum_p2) {
		this.uninum_p2 = uninum_p2;
	}
	public String getUninum_p3() {
		return uninum_p3;
	}
	public void setUninum_p3(String uninum_p3) {
		this.uninum_p3 = uninum_p3;
	}
	public String getUninum_p4() {
		return uninum_p4;
	}
	public void setUninum_p4(String uninum_p4) {
		this.uninum_p4 = uninum_p4;
	}
	public String getUninum_p5() {
		return uninum_p5;
	}
	public void setUninum_p5(String uninum_p5) {
		this.uninum_p5 = uninum_p5;
	}
	public String getUninum_p6() {
		return uninum_p6;
	}
	public void setUninum_p6(String uninum_p6) {
		this.uninum_p6 = uninum_p6;
	}
	public List<SetterPositionPOJO> getSetterPositions() {
		return setterPositions;
	}
	public void setSetterPositions(List<SetterPositionPOJO> setterPositions) {
		this.setterPositions = setterPositions;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public void sumSetterPositionsPassesActualAtackNum() {
		for (int i = 0; i < setterPositions.size(); i++) {
			setterPositions.get(i).sumPassesActualAtackNum();
		}
	}
}
