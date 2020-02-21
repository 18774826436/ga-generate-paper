package cn.hengxin.paper.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;
import cn.hengxin.paper.data.Config;
import cn.hengxin.paper.model.BaseModel;
import cn.hengxin.paper.model.Header;
import cn.hengxin.paper.model.ScoreSheet;
import cn.hengxin.paper.utils.OutputFormatUtils;
import cn.hengxin.paper.utils.POIMergeDocUtils;

public class PaperGenerator{

	private Config config = new Config();
	private Header header;
	private ScoreSheet scoreSheet;
	private List<BaseModel> questions;
	
	public PaperGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() throws IllegalArgumentException, IllegalAccessException, IOException {
		
		// 读取配置文件
		readConfig();
		
		// 创建临时目录
		String tempPath = config.OUTPUT_PATH+"/temp";
		File tempFile = new File(tempPath);
		if(!tempFile.exists()){
			tempFile.mkdirs();
		}
	}

	public void output() throws IllegalArgumentException, IllegalAccessException, IOException {
		// TODO Auto-generated method stub
		String[] docxSrc = new String[questions.size()+2];
		ScoreSheet scoreSheet = new ScoreSheet();
		scoreSheet.setQuestionNum(questions.size());
		
		for(int i=0;i<questions.size();i++){
			questions.get(i).setNum(i+1);
		}
		
		questions.add(0, header);
		questions.add(1,scoreSheet);
		
		for(int i=0;i<questions.size();i++){
			questions.get(i).setConfig(config);
			questions.get(i).init();
			docxSrc[i] = questions.get(i).output();
		}
		
		POIMergeDocUtils.mergeDoc(docxSrc, config.OUTPUT_PATH+"/output.docx");
	}
	
	/* 读取配置文件 */
	private void readConfig() throws IOException, IllegalArgumentException, IllegalAccessException{
		Properties properties = new Properties();
		InputStream in = PaperGenerator.class.getResourceAsStream("/config.properties");
		properties.load(in);
		
		/* 利用反射机制从配置文件中装配字段 */
		Class<? extends Config> configClz = config.getClass();
		Field[] fields = configClz.getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			String fieldName = field.getName();
			System.out.println(fieldName);
			String fieldValue = properties.getProperty(fieldName);
			System.out.println(fieldValue);
			field.set(config, fieldValue);
		}
		in.close();
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public void setQuestions(List<BaseModel> questions) {
		this.questions = questions;
	}
}
