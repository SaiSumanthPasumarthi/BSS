/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lakshman
 *
 */
public class SplitterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> level1SlotList;
	
	private List<Integer> level2SlotList;
	
	private List<Integer> level3SlotList;

	public List<String> getLevel1SlotList() {
		return level1SlotList;
	}

	public void setLevel1SlotList(List<String> level1SlotList) {
		this.level1SlotList = level1SlotList;
	}

	public List<Integer> getLevel2SlotList() {
		return level2SlotList;
	}

	public void setLevel2SlotList(List<Integer> level2SlotList) {
		this.level2SlotList = level2SlotList;
	}

	public List<Integer> getLevel3SlotList() {
		return level3SlotList;
	}

	public void setLevel3SlotList(List<Integer> level3SlotList) {
		this.level3SlotList = level3SlotList;
	}
	
}
