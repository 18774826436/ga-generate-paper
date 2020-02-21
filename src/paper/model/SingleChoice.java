package paper.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hengxin.paper.model.BaseModel;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;

import cn.hengxin.paper.data.Config;
import cn.hengxin.paper.data.SingleChoiceData;
import cn.hengxin.paper.utils.OutputFormatUtils;
import cn.hengxin.paper.utils.TemplateOutputUtils;

public class SingleChoice extends BaseModel{
		
	private List<SingleChoiceData> singleChoices;
	private String templatePath;
	private String dataTemplatePath;
	private String outputPath;
	
	public SingleChoice() {
		
	}
	
	@Override
	public void init() {
		templatePath = config.TEMPLATE_PATH+config.SINGLECHOICE_TEMPLATE;
		dataTemplatePath = config.TEMPLATE_PATH+config.SINGLECHOICE_DATA_TEMPLATE;
		outputPath = config.OUTPUT_PATH+"temp/singleChoice_output.docx";
	}

	@Override
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException {
		
		Map<String, Object> templateData = new HashMap<String, Object>();
		int singleChoiceNum = singleChoices.size();//����ѡ�������Ŀ
		
		//�����ѡ��Ĵ����
		final int COUNT_PER_ROW = 10;
		int rowNum = (singleChoiceNum-1)/COUNT_PER_ROW+1;
		RowRenderData[] rowRenderDatas = new RowRenderData[rowNum];
		for(int i=0;i<rowNum;i++){
			List<TextRenderData> rowData = new ArrayList<TextRenderData>();
			for(int j=0;j<COUNT_PER_ROW;j++){
				if((i*COUNT_PER_ROW)+(j+1)<=singleChoiceNum){
					rowData.add(new TextRenderData(String.valueOf((i*COUNT_PER_ROW)+(j+1))));
				}
				
			}
			rowRenderDatas[i] = RowRenderData.build("");
			rowRenderDatas[i].setRowData(rowData);
		}
		templateData.put("num", OutputFormatUtils.questionNumToChinese(num));
		templateData.put("singleChoiceTable", new MiniTableRenderData(Arrays.asList(rowRenderDatas)));
		
		//���ÿһ����
		for(int i=0; i<singleChoiceNum; i++){
			singleChoices.get(i).setNum(i+1);
		}
		DocxRenderData singleChoiceDocx = new DocxRenderData(new File(dataTemplatePath), singleChoices);
		templateData.put("singleChoiceDocx", singleChoiceDocx);
		
		TemplateOutputUtils.templateOutput(templatePath, outputPath, templateData);
		
		return outputPath;
	}
	
	public void setSingleChoices(List<SingleChoiceData> singleChoices) {
		this.singleChoices = singleChoices;
	}
}
