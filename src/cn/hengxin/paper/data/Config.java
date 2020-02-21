package cn.hengxin.paper.data;

/* 装载配置文件  字段名需与配置文件相同*/
public class Config {

	public String TEMPLATE_PATH = "/";	// 模板所在的根路径
	public String OUTPUT_PATH = "/";		// 输出路径
	public String HEADER_TEMPLATE;		// 头部模板的文件名
	public String SCORETABLE_TEMPLATE;	// 得分表模板的文件名
	public String SINGLECHOICE_TEMPLATE;	// 单选题总体模板的文件名
	public String SINGLECHOICE_DATA_TEMPLATE;	// 单个单选题模板的文件名
	public String SHORTANSWER_TEMPLATE;		// 简答题总体模板的文件名
	public String SHORTANSWER_DATA_TEMPLATE;	// 单个简单题模板的文件名
	public String MULTICHOICE_TEMPLATE;	// 多选题总体模板的文件名
	public String MULTICHOICE_DATA_TEMPLATE;	// 单个多选题模板的文件名
	public String JUDGEMENT_TEMPLATE;	// 判断题总体模板的文件名
	public String JUDGEMENT_DATA_TEMPLATE;	// 单个判断题模板的文件名
	public String COMPREHENSIVE_TEMPLATE;	// 综合题总体模板的文件名
	public String COMPREHENSIVE_DATA_TEMPLATE;	// 单个综合题模板的文件名
	public String BLANK_TEMPLATE;	// 填空题总体模板的文件名
	public String BLANK_DATA_TEMPLATE;	// 单个填空题模板的文件名
	
	public Config(){}
	
	/* 
	private static class Holder{
		private static final Config INSTANCE = new Config();
	}
	
	public static Config getInstance(){
		return Holder.INSTANCE;
	}
	*/
}
