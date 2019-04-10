package com.jessica.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dept {
	private int depNo;
	private String depName;
	private String loc;

	public int getDepNo() {
		return depNo;
	}

	public void setDepNo(int depNo) {
		this.depNo = depNo;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
