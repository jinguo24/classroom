package com.simple.enums;

public enum TClassImagesEnum {

	WELCOME("welcome","欢迎登录安全教育平台图片","b1Src"),LOGIN_BACK("login_back","登录背景图片","b2Src"),
	KQZBAN("kqzban","课前准备按钮图","b3Src"),XZKCAN("xzkcan","选择课程按钮图","b4Src"),XK_BACK("xk_back","选课页背景图","b5Src"),
	LOGO("logo","安全平台logo","b6Src"),NJBJXZAN("njbjxzan","年纪班级选择按钮","b7Src"),HOME_BACK("home_back","返回首页按钮图","b8Src"),
	XSXZAN("xsxzan","学生选择按钮图","b9Src"),CXXZBJAN("cxxzbjan","重新选择班级按钮图","b10Src"),PRE_STEP("pre_step","返回上一级","b11Src"),
	JXXKAN("jxxkan","继续选课按钮","b12Src");
	
	private TClassImagesEnum(String code, String name,String param) {
		this.code = code;
		this.name = name;
		this.param = param;
	}
	private String code;
	private String name;
	private String param;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	public static String getCodeByParam(String param) {
		for (TClassImagesEnum tie : TClassImagesEnum.values()) {
			if (tie.getParam().equals(param)) {
				return tie.getCode();
			}
		}
		return null;
	}
	
	public static String getParamByCode(String code) {
		for (TClassImagesEnum tie : TClassImagesEnum.values()) {
			if (tie.code.equals(code)) {
				return tie.getParam();
			}
		}
		return null;
	}
}
