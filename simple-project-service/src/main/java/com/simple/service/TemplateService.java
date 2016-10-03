package com.simple.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.excel.SheetRow;
import com.simple.common.util.DateUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.dao.DictionaryDao;
import com.simple.dao.QuestionDao;
import com.simple.dao.QuestionItemDao;
import com.simple.dao.QuestionResultDao;
import com.simple.dao.SectionDao;
import com.simple.dao.TemplateDao;
import com.simple.model.Dictionary;
import com.simple.model.FormQuestion;
import com.simple.model.FormQuestionItem;
import com.simple.model.PageResult;
import com.simple.model.Question;
import com.simple.model.QuestionItem;
import com.simple.model.QuestionResult;
import com.simple.model.Section;
import com.simple.model.SheetForm;
import com.simple.model.Template;
import com.simple.model.TemplateData;
import com.simple.model.TemplateForm;
import com.simple.model.TemplateFormHelper;
@Service
public class TemplateService {

	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private QuestionItemDao questionItemDao;
	@Autowired
	private QuestionResultDao questionResultDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	public String addTemplate(TemplateForm templateForm,String leaseholderId,String userName) {
		Template template = TemplateFormHelper.castToTemplate(templateForm,userName, leaseholderId);
		templateDao.addTemplate(template);
		addQuestionByForm(templateForm,userName,leaseholderId,template.getCode());
		return template.getCode();
	}
	
	private void addQuestionByForm(TemplateForm templateForm,String userName,String leaseholderId,String templateCode) {
		List<TemplateData> datas = templateForm.getData();
		if ( null != datas ) {
			for ( int i = 0 ; i < datas.size(); i++) {
				TemplateData data = datas.get(i);
				//添加章节
				Section section = TemplateFormHelper.castToSection(data,leaseholderId, i, templateCode);
				sectionDao.addSection(section);
				//添加章节下面的问题
				List<FormQuestion> questions = datas.get(i).getTms();
				if ( null != questions ) {
					for (int j =0 ;j <questions.size() ; j++) {
						Question question = TemplateFormHelper.castQuestion(questions.get(j),leaseholderId,section.getCode(),templateCode);
						questionDao.addQuestion(question);
						
						List<FormQuestionItem> items = questions.get(j).getItems();
						if ( null != items ) {
							for (int k = 0 ; k < items.size(); k++) {
								QuestionItem qi = TemplateFormHelper.castQuestionItem(items.get(k),leaseholderId,question.getType(),
										question.getCode(),section.getCode(),templateCode);
								questionItemDao.addQuestionItem(qi);
							}
						}
					}
				}
			}
		}
	}
	
	public TemplateForm queryByCode(String leaseholderId, String code,boolean needStatistics,boolean isResultInfo) {
		List<Template> templates = templateDao.query(leaseholderId, code,null,null, null, null, null, null,1, 1);
		if ( null != templates && templates.size() > 0) {
			Template template = templates.get(0);
			TemplateForm form = TemplateFormHelper.castToTemplateForm(template);
			//查询章节
			List<Section> sections =  sectionDao.query(leaseholderId, null, code, null, 1, 1000);
			if ( null != sections ) {
				List<TemplateData> datas = new ArrayList<TemplateData>();
				for ( int i = 0 ; i < sections.size(); i++) {
					Section section = sections.get(i);
					TemplateData data = TemplateFormHelper.castToSectionForm(section);
					List<Question> questions = questionDao.query(leaseholderId, null, template.getCode(), section.getCode(), 
							1, 1000);
					if ( null != questions) {
						List<FormQuestion> fqs = new ArrayList<FormQuestion>();
						for ( int j = 0 ; j < questions.size(); j ++) {
							FormQuestion qf = null;
							Question q = questions.get(j);
							//如果是统计到题目
							if (isResultInfo) {
								qf = getQuestionResult(q,leaseholderId,template,section);
							}else {
								qf = getQuestionItemResult(q,leaseholderId,template,section,needStatistics);
							}
							fqs.add(qf);
						}
						data.setTms(fqs);
					}
					datas.add(data);
				}
				form.setData(datas);
				return form;
			}
		}
		return null;
	}
	
	private FormQuestion getQuestionResult(Question q,String leaseholderId,Template template,Section section) {
		FormQuestion qf = TemplateFormHelper.castQuestionForm(q);
		List<QuestionItem> items =  questionItemDao.query(leaseholderId, null, q.getCode(), 
				template.getCode(), section.getCode(), 1, 100);
		Map<String , String> itemMarkMap = new HashMap<String,String>();
		StringBuffer bz = new StringBuffer();
		if ( null != items) {
			List<FormQuestionItem> fis = new ArrayList<FormQuestionItem>();
			for ( int i =0; i < items.size(); i ++) {
				QuestionItem qi = items.get(i);
				itemMarkMap.put(qi.getCode(), qi.getMark());
				
				if (qi.getIsAnswer()==1) {
					bz.append(qi.getMark()).append(",");
				}
				//问答题,有items
				if ("textbox".equals(q.getType())) {
					FormQuestionItem fi = TemplateFormHelper.castQuestionItemForm(qi);
					fi.setValue(getQuestionValues(itemMarkMap, leaseholderId, template, section, q,fi.getCode()));
					fis.add(fi);
				}
			}
			if ("textbox".equals(q.getType())&& fis.size() > 0 ) {
				qf.setItems(fis);
			}
			if (bz.length() > 1) {
				qf.setBz(bz.substring(0,bz.length()-1));
			}
		}
		if (!"textbox".equals(q.getType())) {
			qf.setValue(getQuestionValues(itemMarkMap, leaseholderId, template, section, q,null));
		}
		return qf;
	}
	
	private String getQuestionValues(Map<String , String> itemMarkMap,String leaseholderId,Template template,Section section,Question q,String itemCode ) {
		QuestionResult cr = new QuestionResult();
		cr.setLeaseholderId(leaseholderId);
		cr.setTempalteCode(template.getCode());
		cr.setSectionCode(section.getCode());
		cr.setQuestionCode(q.getCode());
		cr.setQuestionItemCode(itemCode);
		List<QuestionResult> resutls = questionResultDao.query(cr, 1, 100);
		if ( null != resutls) {
			Map<String,String> personResultMap = new HashMap<String,String>();
			for ( int i = 0 ; i < resutls.size(); i++) {
				QuestionResult qr = resutls.get(i);
				if (personResultMap.containsKey(qr.getStudentId())) {
					String value = personResultMap.get(qr.getStudentId());
					if ("textbox".equals(q.getType())) {
						personResultMap.put(qr.getStudentId(), value+","+qr.getContent());
					}else {
						if (itemMarkMap.containsKey(qr.getQuestionItemCode())) {
							personResultMap.put(qr.getStudentId(), value+","+itemMarkMap.get(qr.getQuestionItemCode()));
						}
					}
				}else {
					if ("textbox".equals(q.getType())) {
						personResultMap.put(qr.getStudentId(), qr.getContent());
					}else {
						if (itemMarkMap.containsKey(qr.getQuestionItemCode())) {
							personResultMap.put(qr.getStudentId(), itemMarkMap.get(qr.getQuestionItemCode()));
						}
					}
				}
			}
			StringBuffer s = new StringBuffer();
			for (Iterator<String> it = personResultMap.keySet().iterator(); it.hasNext();) {
				String values = personResultMap.get(it.next());
				if ( null != values && values.length() > 0 ) {
					s.append(values).append("-");
				}
			}
			if (s.length() > 1) {
				return s.substring(0,s.length()-1);
			}
		}
		return null;
	}
	
	private FormQuestion getQuestionItemResult(Question q,String leaseholderId,Template template,Section section,boolean needStatistics) {
		FormQuestion qf = TemplateFormHelper.castQuestionForm(q);
		List<QuestionItem> items =  questionItemDao.query(leaseholderId, null, q.getCode(), 
				template.getCode(), section.getCode(), 1, 100);
		if ( null != items) {
			//如果需要统计
			Map<String,Integer> pMap = null;//答题人数
			int totalPcount = 0;
			if (needStatistics) {
				//计算选项的答题人数
				pMap = questionResultDao.queryResultStatistics(items);
				//获取总人数
				if ( null != pMap) {
					for (java.util.Iterator<String> it = pMap.keySet().iterator();it.hasNext();) {
						totalPcount = totalPcount + pMap.get(it.next());
					}
				}
				if (totalPcount==0) totalPcount = 1;
			}
			List<FormQuestionItem> fis = new ArrayList<FormQuestionItem>();
			for (int k = 0 ; k < items.size(); k++) {
				QuestionItem qi = items.get(k);
				FormQuestionItem fi = TemplateFormHelper.castQuestionItemForm(qi);
				//需要统计加上统计数据
				if ( needStatistics && null != pMap && pMap.containsKey(qi.getCode())) {
					int c = pMap.get(qi.getCode());
					fi.setPcount(c);
					float p = (c/(float)totalPcount)*100;
					fi.setPercent(new DecimalFormat("0.00").format(p));
				}
				fis.add(fi);
			}
			qf.setItems(fis);
		}
		return qf;
	}
	
	
	public PageResult query(String leaseholderId,String code,String begin,String end,String type,String name,
			String category,List<String> codes,int pageIndex,int pageSize) {
		List<Template> templateList = null;
		int count = 0;
		if ( null != codes && codes.size() == 0 ) {
		}else {
			templateList = this.queryTemplateList(leaseholderId, code, begin, end, 
					type, name, category, codes, pageIndex, pageSize);
			count = templateDao.queryCount(leaseholderId, code, begin, end, type, name, category,codes);
		}
		return new PageResult(count,pageSize,pageIndex,templateList);
	}
	
	private List<Template> queryTemplateList(String leaseholderId,String code,String begin,String end,String type,String name,
			String category,List<String> codes,int pageIndex,int pageSize) {
		if (null == codes || codes.size() == 0) {
			codes = null;
		}
		List<Template> templateList = queryList(leaseholderId, code,begin,end, type, name, category,codes, pageIndex, pageSize);
		if ( null != templateList && templateList.size() > 0 ) {
			for ( int i = 0 ; i < templateList.size() ; i ++) {
				Template t = templateList.get(i);
				if ( null != t.getTemplateType()) {
					Dictionary dictionary = dictionaryDao.queryByCode(t.getTemplateType());
					if ( null != dictionary) {
						t.setTemplateTypeName(dictionary.getName());
					}
				}
				if ( null != t.getCategory()) {
					Dictionary dictionaryCategory = dictionaryDao.queryByCode(t.getCategory());
					if ( null != dictionaryCategory) {
						t.setCategoryName(dictionaryCategory.getName());
					}
				}
			}
		}
		return templateList;
	}
	
	public List<Template> queryList(String leaseholderId,String code,String begin,String end,String type,String name,
			String category,List<String> codes,int pageIndex,int pageSize) {
		return templateDao.query(leaseholderId, code,begin,end, type, name, category,codes, pageIndex, pageSize);
	}
	 
	public Template queryById(int id) {
		return templateDao.queryById(id);
	}
	
	public void updateTemplate(Template template) {
		templateDao.updateTemplate(template);
	}
	
	public void deleteById(int  id) {
		Template template = queryById(id);
		templateDao.deleteById(id);
		sectionDao.deleteByTemplateCode(template.getCode());
		questionDao.deleteByTemplateCode(template.getCode());
		questionItemDao.deleteByTemplateCode(template.getCode());
	}
	
	public String update(TemplateForm templateForm,String leaseholderId,String userName) {
		List<Template> templates =  templateDao.query(leaseholderId, templateForm.getCode(),null,null, null, null, null,null, 1, 1);
		if( null != templates && templates.size() > 0 ) {
			Template t = templates.get(0);
			Template template = TemplateFormHelper.castToTemplate(templateForm,userName, leaseholderId);
			template.setId(t.getId());
			template.setCode(t.getCode());
			template.setCreateTime(t.getCreateTime());
			template.setCreator(t.getCreator());
			updateTemplate(template);
		}
		//先删除问题和选项，然后新增
		sectionDao.deleteByTemplateCode(templateForm.getCode());
		questionDao.deleteByTemplateCode(templateForm.getCode());
		questionItemDao.deleteByTemplateCode(templateForm.getCode());
		//新增
		addQuestionByForm(templateForm, userName, leaseholderId, templateForm.getCode());
		return templateForm.getCode();
	}
	
	public ResponseInfo download(String leaseholderId,String code,String begin,String end,String type,String name,
			String category,List<String> codes) {
		List<Template> templateList = this.queryTemplateList(leaseholderId, code, begin, end, type, name, category, codes, 1, 1000);
		String[] titles = new String[]{"模版分类","模版类型","模版名称","备注","更新时间"};
		return DownLoadExcel.download(templateList, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				Template log = (Template) o;
				List<String> sl = new ArrayList<String>();
				sl.add(log.getTemplateTypeName());
				sl.add(log.getCategoryName());
				sl.add(log.getName());
				sl.add(log.getRemark());
				sl.add(DateUtil.date2AllString(log.getCreateTime()));
				return sl;
			}
		});
		
	}
	
	public void download(SheetForm sf,HttpServletResponse response,String fileName) {
		DownLoadExcel.downExcel(response,fileName,sf.castToSheetRows(),sf.getCellCount());
	}
	
}
