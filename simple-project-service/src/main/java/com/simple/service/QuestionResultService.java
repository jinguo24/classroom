package com.simple.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.util.DateUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.dao.QuestionDao;
import com.simple.dao.QuestionResultDao;
import com.simple.dao.SectionDao;
import com.simple.dao.TeachingLogDao;
import com.simple.dao.TemplateDao;
import com.simple.model.FormQuestionResult;
import com.simple.model.FormQuestionResultItems;
import com.simple.model.FormQuestionResultTms;
import com.simple.model.PageResult;
import com.simple.model.QuestionResult;
import com.simple.model.TeachingLog;
import com.simple.model.Template;
@Service
public class QuestionResultService {

	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private QuestionResultDao questionResultDao;
	@Autowired
	private TeachingLogDao teachingLogDao;
	
	public void addResult(String templateCode,FormQuestionResult[] sections,String leaseholderId,String studentId,String skbh) {
		String activityCode = "000000";
		String activityName = "安全教室";
		if ( null != sections ) {
			for ( int i = 0 ; i < sections.length ;i ++) {
				FormQuestionResult sf = sections[i];
				List<FormQuestionResultTms> questions = sf.getTms();
				if ( null != questions ) {
					for ( int j = 0 ; j < questions.size(); j++) {
						FormQuestionResultTms qf = questions.get(j);
						List<FormQuestionResultItems> answers = qf.getItems();
						if ( null != answers) {
							for ( int k =0 ;k< answers.size() ; k++) {
								FormQuestionResultItems qif = answers.get(k);
								QuestionResult qr = new QuestionResult();
								qr.setActivityCode(activityCode);
								qr.setActivityName(activityName);
								qr.setCode(PrimaryKeyUtil.getUUID());
								qr.setContent(StringUtils.trimToNull(qif.getTitle()));
								if ("pingfen".equals(qf.getCate())) {
									qr.setScore(Integer.parseInt(qif.getTitle()));
								}
								qr.setLeaseholderId(leaseholderId);
								qr.setQuestionCode(qf.getCode());
								qr.setQuestionItemCode(qif.getChooseCode());
								qr.setSectionCode(sf.getCode());
								qr.setTempalteCode(templateCode);
								qr.setStudentId(studentId);
								//TODO通过授课编号查询信息
								TeachingLog tl = new TeachingLog();
								tl.setSkbh(skbh);
								List<TeachingLog> logs = teachingLogDao.query(tl, null, null, 1, 1,null);
								if ( null != logs && logs.size() > 0 ) {
									TeachingLog log = logs.get(0);
									qr.setSkbh(skbh);
									qr.setXxbh(log.getXxbh());
									qr.setXxmc(log.getXxmc());
									qr.setNjbh(log.getNjbh());
									qr.setNjmc(log.getNjmc());
									qr.setBjbh(log.getBjbh());
									qr.setBjmc(log.getBjmc());
									qr.setXn(log.getXn());
								}
								questionResultDao.addQuestionResult(qr);
							}
						}
					}
				}
			}
		}
	}
	
	public PageResult query(QuestionResult result,int pageIndex,int pageSize) {
		List<QuestionResult> templateList =  questionResultDao.query(result, pageIndex, pageSize);
		int count = questionResultDao.queryCount(result);
		return new PageResult(count,pageSize,pageIndex,templateList);
	}
	
	public List<String> queryTemplateCodes(QuestionResult result) {
		return questionResultDao.queryTemplateCodes(result);
	}
}
