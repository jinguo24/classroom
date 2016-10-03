package com.simple.model;

import java.io.Serializable;

public class SheetCellForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String content;
	private int begin;
	private int end;
	private short fontSize;
	private short boldweight;
	private int border;
	private int bgcolor;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public short getFontSize() {
		return fontSize;
	}
	public void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}
	public short getBoldweight() {
		return boldweight;
	}
	public void setBoldweight(short boldweight) {
		this.boldweight = boldweight;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public int getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(int bgcolor) {
		this.bgcolor = bgcolor;
	}
}
