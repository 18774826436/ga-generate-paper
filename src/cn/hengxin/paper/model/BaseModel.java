package cn.hengxin.paper.model;

import java.io.IOException;

import cn.hengxin.paper.data.Config;

public class BaseModel {
	
	protected Integer num;
	protected Config config;
	
	public BaseModel() {
		// TODO Auto-generated constructor stub
	}
	
	/* 初始化方法，读取配置文件，设置路径 */
	public void init(){}
	/* 输出模块   返回输出的路径 */
	public String output() throws IllegalArgumentException, IllegalAccessException, IOException{
		
		return null;
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}
	
	public void setNum(Integer num) {
		this.num = num;
	}
}
