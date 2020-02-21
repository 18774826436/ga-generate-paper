package paper.utils;

public class OutputFormatUtils {
	
	public static final String[] SCORE_SHEET_COL_ITEMS = {"���", "�÷�", "������"};
	
	public static String questionNumToChinese(Integer num){
		String[] resultStr = {"һ","��","��","��","��","��","��","��",
		                      "��","ʮ","ʮһ","ʮ��","ʮ��","ʮ��","ʮ��"};
		return resultStr[num-1];
	}
	
}
