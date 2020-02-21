package cn.hengxin.paper.data;

/* 单选题数据模型 */
public class SingleChoiceData {
	private Integer num;	// 题号
	private String title;	// 题目
	private String aOption;	// 选项A
	private String bOption;	// 选项B
	private String cOption;	// 选项C
	private String dOption; // 选项D

	public SingleChoiceData() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getaOption() {
		return aOption;
	}

	public void setaOption(String aOption) {
		this.aOption = aOption;
	}

	public String getbOption() {
		return bOption;
	}

	public void setbOption(String bOption) {
		this.bOption = bOption;
	}

	public String getcOption() {
		return cOption;
	}

	public void setcOption(String cOption) {
		this.cOption = cOption;
	}

	public String getdOption() {
		return dOption;
	}

	public void setdOption(String dOption) {
		this.dOption = dOption;
	}
}
