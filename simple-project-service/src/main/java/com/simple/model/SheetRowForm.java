package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class SheetRowForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<SheetCellForm> cells;

	public List<SheetCellForm> getCells() {
		return cells;
	}

	public void setCells(List<SheetCellForm> cells) {
		this.cells = cells;
	}
}
