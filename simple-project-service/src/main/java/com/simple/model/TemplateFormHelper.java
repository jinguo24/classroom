package com.simple.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.simple.common.util.PrimaryKeyUtil;

public class TemplateFormHelper{

	public static TemplateForm castToTemplateForm(Template template) {
		TemplateForm tf = new TemplateForm();
		tf.setContent(template.getRemark());
		tf.setRandom(template.getRandom());
		tf.setTemplateCategory(template.getCategory());
		tf.setTemplateType(template.getTemplateType());
		tf.setCode(template.getCode());
		tf.setTitle(template.getName());
		return tf;
	}
	
	public static Template castToTemplate(TemplateForm templateForm,String userName,String leaseholderId) {
		Template template = new Template();
		template.setCategory(templateForm.getTemplateCategory());
		template.setCode(PrimaryKeyUtil.getUUID());
		template.setCreateTime(new Date());
		template.setCreator(userName);
		template.setLeaseholderId(leaseholderId);
		template.setName(StringUtils.trimToNull(templateForm.getTitle()));
		template.setRandom(templateForm.getRandom());
		template.setRemark(StringUtils.trimToNull(templateForm.getContent()));
		template.setTemplateType(templateForm.getTemplateType());
		return template;
	}
	
	public static Section castToSection(TemplateData data,String leaseholderId,int index,String templateCode) {
		Section section = new Section();
		section.setCode(PrimaryKeyUtil.getUUID());
		section.setLeaseholderId(leaseholderId);
		section.setName(StringUtils.trimToNull(data.getTitle()));
		section.setSort(data.getSort());
		section.setTemplateCode(templateCode);
		return section;
	}
	
	public static TemplateData castToSectionForm(Section section) {
		TemplateData td = new TemplateData();
		td.setTitle(StringUtils.trimToNull(section.getName()));
		td.setCode(section.getCode());
		td.setSort(section.getSort());
		return td;
	}
	
	public static Question castQuestion(FormQuestion formQuestion,String leaseholderId,String sectionCode,String templateCode) {
		Question question = new Question();
		question.setCode(PrimaryKeyUtil.getUUID());
		if (formQuestion.isWtjtt()) {
			question.setIgnore(1);
		}
		if (formQuestion.isBida()) {
			question.setRequire(1);
		}
		question.setLeaseholderId(leaseholderId);
		question.setScore(formQuestion.getScores());
		question.setTitle(StringUtils.trimToNull(formQuestion.getName()));
		question.setSectionCode(sectionCode);
		question.setSort(formQuestion.getSort());
		question.setTempalteCode(templateCode);
		question.setType(formQuestion.getCate());
		return question;
	}
	
	public static FormQuestion castQuestionForm(Question question) {
		FormQuestion fq = new FormQuestion();
		fq.setBida(question.getRequire()==1?true:false);
		fq.setCate(question.getType());
		fq.setName(StringUtils.trimToNull(question.getTitle()));
		fq.setScores(question.getScore());
		fq.setSort(question.getSort());
		fq.setWtjtt(question.getIgnore()==1?true:false);
		fq.setCode(question.getCode());
		return fq;
	}
	
	private static final String[] MARKS = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
		"O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public static QuestionItem castQuestionItem(FormQuestionItem formQuestionItem,String leaseholderId,String cate,String questionCode,String sectionCode,String templateCode) {
		QuestionItem qi = new QuestionItem();
		qi.setCode(PrimaryKeyUtil.getUUID());
		if (formQuestionItem.isBz()) {
			qi.setIsAnswer(1);
		}
		qi.setLeaseholderId(leaseholderId);
		qi.setMark(MARKS[formQuestionItem.getSort()-1]);
		qi.setQuestionCode(questionCode);
		qi.setSectionCode(sectionCode);
		qi.setScore(formQuestionItem.getNumber());
		qi.setSort(formQuestionItem.getSort());
		qi.setTempalteCode(templateCode);
		qi.setTip(null);
		qi.setContent(StringUtils.trimToNull(formQuestionItem.getTitle()));
		qi.setDescription(StringUtils.trimToNull(formQuestionItem.getName()));
		qi.setImage(null);
		return qi;
	}
	
	public static FormQuestionItem castQuestionItemForm(QuestionItem item) {
		FormQuestionItem fi = new FormQuestionItem();
		fi.setBz(item.getIsAnswer()==1?true:false);
		fi.setName(item.getDescription());
		fi.setNumber(item.getScore());
		fi.setSort(item.getSort());
		fi.setTitle(item.getContent());//只有填空题会有数据
		fi.setCode(item.getCode());
		return fi;
	}
}
