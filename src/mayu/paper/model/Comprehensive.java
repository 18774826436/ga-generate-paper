package mayu.paper.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepoove.poi.data.DocxRenderData;

import mayu.paper.data.ComprehensiveData;
import mayu.paper.utils.OutputFormatUtils;
import mayu.paper.utils.TemplateOutputUtils;

public class Comprehensive extends BaseModel{
	private List<ComprehensiveData> comprehensives;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	public Comprehensive() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.COMPREHENSIVE_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.COMPREHENSIVE_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH + "/temp/comprehensive_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		Map<String, Object> templateMap = new HashMap<String, Object>();
		int comprehensiveNum = comprehensives.size();
		templateMap.put("num", OutputFormatUtils.questionNumToChinese(num));
		
		for(int i=0; i<comprehensiveNum; i++){
			comprehensives.get(i).setNum(i+1);
		}
		
		
		DocxRenderData comprehensiveDocx = new DocxRenderData(new File(dataTemplatePath), comprehensives);
		templateMap.put("comprehensiveDocx", comprehensiveDocx);
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateMap);
		
		return outputPath;
	}
	
	public void setComprehensives(List<ComprehensiveData> comprehensives) {
		this.comprehensives = comprehensives;
	}
	
}
