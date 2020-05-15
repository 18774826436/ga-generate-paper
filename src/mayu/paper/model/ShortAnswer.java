package mayu.paper.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepoove.poi.data.DocxRenderData;

import mayu.paper.data.ShortAnswerData;
import mayu.paper.utils.OutputFormatUtils;
import mayu.paper.utils.TemplateOutputUtils;

public class ShortAnswer extends BaseModel{

	private List<ShortAnswerData> shortAnswers;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	public ShortAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH + config.SHORTANSWER_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.SHORTANSWER_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH+"temp/shortAnswer_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		Map<String, Object> templateData = new HashMap<String, Object>();
	
		for(int i=0; i<shortAnswers.size(); i++){
			shortAnswers.get(i).setNum(i+1);
		}
		
		templateData.put("num", OutputFormatUtils.questionNumToChinese(num));
		DocxRenderData shortAnswerDocx = new DocxRenderData(new File(dataTemplatePath), shortAnswers);
		templateData.put("shortAnswerDocx", shortAnswerDocx);
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateData);
		
		return outputPath;
	}
		
	public void setShortAnswers(List<ShortAnswerData> shortAnswers) {
		this.shortAnswers = shortAnswers;
	}
	
}
