package cn.hengxin.paper.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import com.deepoove.poi.XWPFTemplate;

public class TemplateOutputUtils {

	public static void templateOutput(String templatePath, String outputPath, Map templateData) throws IOException{
		XWPFTemplate template = XWPFTemplate.compile(templatePath).render(templateData);
		FileOutputStream fos;
	
		fos = new FileOutputStream(outputPath); 
		template.write(fos);
		fos.flush();
		fos.close();
		template.close();
	}
}
