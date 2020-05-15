package mayu.paper.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;

import mayu.paper.utils.OutputFormatUtils;
import mayu.paper.utils.TemplateOutputUtils;

/* 得分表 */
public class ScoreSheet extends BaseModel{

	private Integer questionNum;	// 问题类型数量
	private String templatePath;
	private String outputPath;
	
	public ScoreSheet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.SCORETABLE_TEMPLATE;
		outputPath = config.OUTPUT_PATH+"/temp/scoreSheet_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		
		Map<String,Object> templateMap = new HashMap<String,Object>();
		int rowNum = 3;//评分表的行数
		RowRenderData[] rowRenderDatas = new RowRenderData[rowNum];
		
		for(int row=0; row<rowNum; row++){
			List<TextRenderData> rowData = new ArrayList<TextRenderData>();
			rowData.add(new TextRenderData(OutputFormatUtils.SCORE_SHEET_COL_ITEMS[row]));
			
			if(row==0){
				
				for(int col=0; col<questionNum; col++){
					rowData.add(new TextRenderData(OutputFormatUtils.questionNumToChinese(col+1)));
				}
				
				rowData.add(new TextRenderData("总分"));
			}else{
				
				for(int col=0; col<rowNum+1; col++){
					rowData.add(new TextRenderData(" "));
				}
				
			}
			
			RowRenderData rowRenderData = RowRenderData.build("");
			rowRenderData.setRowData(rowData);
			rowRenderDatas[row] = rowRenderData;
		}
		
		templateMap.put("scoreTable", new MiniTableRenderData(Arrays.asList(rowRenderDatas)));
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateMap);
		
		return outputPath;
	}
	
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
}
