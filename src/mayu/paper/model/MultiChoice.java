package mayu.paper.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;

import mayu.paper.data.MultiChoiceData;
import mayu.paper.utils.OutputFormatUtils;
import mayu.paper.utils.TemplateOutputUtils;

public class MultiChoice extends BaseModel{

	private List<MultiChoiceData> multiChoices;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.MULTICHOICE_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.MULTICHOICE_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH+"temp/multiChoice_output.docx";
	}
	
	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		int multiChoiceNum = multiChoices.size();//多项选择题的数目
		
		//输出多选题的答题表
		final int COUNT_PER_ROW = 10;
		int rowNum = (multiChoiceNum-1)/COUNT_PER_ROW+1;
		RowRenderData[] rowRenderDatas = new RowRenderData[rowNum];
		for(int i=0;i<rowNum;i++){
			List<TextRenderData> rowData = new ArrayList<TextRenderData>();
			for(int j=0;j<COUNT_PER_ROW;j++){
				if((i*COUNT_PER_ROW)+(j+1)<=multiChoiceNum){
					rowData.add(new TextRenderData(String.valueOf((i*COUNT_PER_ROW)+(j+1))));
				}
				
			}
			rowRenderDatas[i] = RowRenderData.build("");
			rowRenderDatas[i].setRowData(rowData);
		}
		templateMap.put("num", OutputFormatUtils.questionNumToChinese(num));
		templateMap.put("multiChoiceTable", new MiniTableRenderData(Arrays.asList(rowRenderDatas)));
		
		//输出每一道题
		for(int i=0; i<multiChoiceNum; i++){
			multiChoices.get(i).setNum(i+1);
		}
		DocxRenderData multiChoiceDocx = new DocxRenderData(new File(dataTemplatePath), multiChoices);
		templateMap.put("multiChoiceDocx", multiChoiceDocx);
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateMap);
		
		return outputPath;
	}

	public void setMultiChoices(List<MultiChoiceData> multiChoices) {
		this.multiChoices = multiChoices;
	}

}
