package mayu.paper.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepoove.poi.data.DocxRenderData;

import mayu.paper.data.JudgementData;
import mayu.paper.utils.OutputFormatUtils;
import mayu.paper.utils.TemplateOutputUtils;

public class Judgement extends BaseModel{
	
	private List<JudgementData> judgements;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	public Judgement() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.JUDGEMENT_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.JUDGEMENT_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH+"/temp/judgement_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		Map<String, Object> templateMap = new HashMap<String, Object>();
		int judgementNum = judgements.size();
		templateMap.put("num", OutputFormatUtils.questionNumToChinese(num));
		for(int i=0; i<judgementNum; i++){
			judgements.get(i).setNum(i+1);
		}
		
		DocxRenderData judgementDocx = new DocxRenderData(new File(dataTemplatePath), judgements);
		templateMap.put("judgementDocx", judgementDocx);
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateMap);
		
		return outputPath;
	}
	
	public void setJudgements(List<JudgementData> judgements) {
		this.judgements = judgements;
	}
	
}
