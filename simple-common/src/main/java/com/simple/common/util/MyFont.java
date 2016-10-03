package com.simple.common.util;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.pdf.BaseFont;

public class MyFont extends FontFactoryImp { 
	@Override 
	public Font getFont(String fontName, String encoding, boolean embedded, 
			float size, int style, BaseColor color, boolean cached) { 
		//可用Arial或标楷体，自己选一个 
		BaseFont baseFont = null; 
		try { 
			baseFont = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); 
			//baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		} catch (DocumentException e) {
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return new Font(baseFont, size, style, color); 
	} 
}