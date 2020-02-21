package paper.data;

public class HeaderData {
	
	private String examName;	//��������
	private Integer startTime;		//��ʼ���
	private Integer endTime;		//�������
	private Integer examTerm;		//����ѧ��
	private String examSubject;	//���Կ�Ŀ
	private String examType;	//��������
	private Integer examTime;		//����ʱ��
	private Integer questionTypeNum;//������
	
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
