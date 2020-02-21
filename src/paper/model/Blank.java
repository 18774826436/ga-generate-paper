package paper.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hengxin.paper.model.BaseModel;
import com.deepoove.poi.data.DocxRenderData;

import cn.hengxin.paper.data.BlankData;
import cn.hengxin.paper.data.Config;
import cn.hengxin.paper.utils.OutputFormatUtils;
import cn.hengxin.paper.utils.TemplateOutputUtils;

public class Blank extends BaseModel{

	private List<BlankData> blanks;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	public Blank() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.BLANK_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.BLANK_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH + "/temp/blank_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		Map<String, Object> templateMap = new HashMap<String, Object>();
		int blankNum = blanks.size();
		
		for(int i=0; i<blankNum; i++){
			blanks.get(i).setNum(i+1);
		}
		templateMap.put("num", OutputFormatUtils.questionNumToChinese(num));
		
		DocxRenderData blankDocx = new DocxRenderData(new File(dataTemplatePath), blanks);
		templateMap.put("blankDocx", blankDocx);
		
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateMap);
		
		return outputPath;
	}
	
	public void setBlanks(List<BlankData> blanks) {
		this.blanks = blanks;
	}
	
}
