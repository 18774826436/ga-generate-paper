package cn.hengxin.paper.utils;

public class OutputFormatUtils {
	
	public static final String[] SCORE_SHEET_COL_ITEMS = {"题号", "得分", "评阅人"};
	
	public static String questionNumToChinese(Integer num){
		String[] resultStr = {"一","二","三","四","五","六","七","八",
		                      "九","十","十一","十二","十三","十四","十五"};
		return resultStr[num-1];
	}
	
}
