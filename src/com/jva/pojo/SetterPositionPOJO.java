package com.jva.pojo;

import java.util.List;

public class SetterPositionPOJO {
	private List<PassPOJO> passes;
	private String position;
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
}
