package com.simple.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.StringUtils;

import com.simple.common.excel.SheetCell;
import com.simple.common.excel.SheetRow;

public class SheetForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private int cellCount;
	private List<SheetRowForm> rows;
	public int getCellCount() {
		return cellCount;
	}
	public void setCellCount(int cellCount) {
		this.cellCount = cellCount;
	}
	public List<SheetRowForm> getRows() {
		return rows;
	}
	public void setRows(List<SheetRowForm> rows) {
		this.rows = rows;
	}
	
	public List<SheetRow> castToSheetRows() {
		List<SheetRow> sheetRows = new ArrayList<SheetRow>();
		if ( null != this.rows && rows.size() > 0 ) {
			for ( int i = 0 ; i < rows.size() ; i ++ ) {
				SheetRow sheeRow = new SheetRow();
				SheetRowForm srf = rows.get(i);
				if ( null != srf && null != srf.getCells() && srf.getCells().size() > 0 ) {
					for (int j = 0 ;j < srf.getCells().size() ; j ++) {
						SheetCellForm scf = srf.getCells().get(j);
						SheetCell titleCell = new SheetCell(scf.getContent(),scf.getBegin(),scf.getEnd());
						titleCell.setAlignment(SheetCell.ALIGNMENT_CENTER);
						titleCell.setFont("宋体", scf.getFontSize(), scf.getBoldweight(), new HSSFColor.BLACK());
						if (scf.getBorder()==1) {
							titleCell.setBorder(SheetCell.BORDER_HAIR, new HSSFColor.BLACK());
						}
						if (scf.getBgcolor()==1) {
							titleCell.setBgColor(new HSSFColor.GREY_80_PERCENT());
						}
						sheeRow.addSheetCell(titleCell);
					}
				}
				sheetRows.add(sheeRow);
			}
		}
		return sheetRows;
	}
}
