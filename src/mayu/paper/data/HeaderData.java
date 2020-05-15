package mayu.paper.data;

public class HeaderData {
	
	private String examName;	//考试名称
	private Integer startTime;		//开始年度
	private Integer endTime;		//结束年度
	private Integer examTerm;		//考试学期
	private String examSubject;	//考试科目
	private String examType;	//考试类型
	private Integer examTime;		//考试时间
	private Integer questionTypeNum;//题型数
	
	public HeaderData(){}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(Integer examTerm) {
		this.examTerm = examTerm;
	}

	public String getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Integer getExamTime() {
		return examTime;
	}

	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}

	public Integer getQuestionTypeNum() {
		return questionTypeNum;
	}

	public void setQuestionTypeNum(Integer questionTypeNum) {
		this.questionTypeNum = questionTypeNum;
	}
	
}
