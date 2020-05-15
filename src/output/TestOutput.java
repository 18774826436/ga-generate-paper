package output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mayu.paper.data.BlankData;
import mayu.paper.data.HeaderData;
import mayu.paper.data.JudgementData;
import mayu.paper.data.MultiChoiceData;
import mayu.paper.data.SingleChoiceData;
import mayu.paper.generator.PaperGenerator;
import mayu.paper.model.BaseModel;
import mayu.paper.model.Blank;
import mayu.paper.model.Header;
import mayu.paper.model.Judgement;
import mayu.paper.model.MultiChoice;
import mayu.paper.model.SingleChoice;

public class TestOutput {
	public static void output(){
		PaperGenerator pg = new PaperGenerator();
		pg.setHeader(getHeader());
		List<BaseModel> questions = new ArrayList<BaseModel>();
		questions.add(getSingleChoice());
		questions.add(getMultiChoice());
		questions.add(getBlank());
		questions.add(getJudgement());
		pg.setQuestions(questions);
		
		try {
			pg.init();
			pg.output();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BaseModel getMultiChoice() {
		List<MultiChoiceData> multiChoices = new ArrayList<MultiChoiceData>();
		for(int i=0;i<5;i++){
			MultiChoiceData mcd = new MultiChoiceData();
			mcd.setTitle("我是多选题");
			mcd.setaOption("我是选项a");
			mcd.setbOption("我是选项b");
			mcd.setcOption("我是选项c");
			mcd.setdOption("我是选项d");
			multiChoices.add(mcd);
		}
		MultiChoice multiChoice = new MultiChoice();
		multiChoice.setMultiChoices(multiChoices);
		
		return multiChoice;
	}

	private static BaseModel getJudgement() {
		List<JudgementData> judgements = new ArrayList<JudgementData>();
		
		for(int i=0;i<5;i++){
			JudgementData jd = new JudgementData();
			jd.setTitle("我是判断题,请打√或者×(  )");
			judgements.add(jd);
		}
		
		Judgement judgement = new Judgement();
		judgement.setJudgements(judgements);
		
		return judgement;

	}

	private static BaseModel getBlank() {
		List<BlankData> blanks = new ArrayList<BlankData>();
		
		for(int i=0;i<5;i++){
			BlankData bd = new BlankData();
			bd.setTitle("我是填空题，请填空______");
			blanks.add(bd);
		}
		
		Blank blank = new Blank();
		blank.setBlanks(blanks);
		
		return blank;

	}

	private static BaseModel getSingleChoice() {
		List<SingleChoiceData> singleChoices = new ArrayList<SingleChoiceData>();
		for(int i=0;i<15;i++){
			SingleChoiceData scd = new SingleChoiceData();
			scd.setTitle("我是选择题");
			scd.setaOption("我是选项a");
			scd.setbOption("我是选项b");
			scd.setcOption("我是选项c");
			scd.setdOption("我是选项d");
			singleChoices.add(scd);
		}
		SingleChoice singleChoice = new SingleChoice();
		singleChoice.setSingleChoices(singleChoices);
		return singleChoice;
	}

	private static Header getHeader() {
		HeaderData hd = new HeaderData();
		hd.setStartTime(2017);
		hd.setEndTime(2018);
		hd.setExamTerm(1);
		hd.setExamSubject("数据库");
		hd.setExamType("开卷");
		hd.setExamTime(120);
		Header header = new Header();
		header.setHeaderData(hd);
		return header;
	}
	
	public static void main(String[] args) {
		output();
	}
}
