package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {
	private int testId;
	private String testName;
	@Id
	@GeneratedValue
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
}
