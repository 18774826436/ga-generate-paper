package mayu.paper.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mayu.paper.data.HeaderData;
import mayu.paper.utils.TemplateOutputUtils;

public class Header extends BaseModel{

	private HeaderData headerData;
	private String templatePath;
	private String outputPath;
	
	public Header() {

	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH + config.HEADER_TEMPLATE;
		outputPath = config.OUTPUT_PATH + "/temp/header_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		Map<String,Object> templateData = new HashMap<String,Object>();
		
		Class<? extends HeaderData> headerDataClz = headerData.getClass();
		Field[] fields = headerDataClz.getDeclaredFields();
		
		for(Field field : fields){
			field.setAccessible(true);
			String fieldName = field.getName();
			Object fieldValue = field.get(headerData);
			templateData.put(fieldName, fieldValue);
		}
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateData);
		
		return outputPath;
	}
	
	public void setHeaderData(HeaderData headerData) {
		this.headerData = headerData;
	}
}
