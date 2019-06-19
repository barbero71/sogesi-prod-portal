package it.sogesispa.prod.web.models;

//import java.util.List;

public class ChartData {
	private String xkey;
	private String ykey;
	
	public ChartData(String xkey, String ykey) {
		super();
		this.xkey = xkey;
		this.ykey = ykey;
	}
	
	public String getXkey() {
		return xkey;
	}
	public void setXkey(String xkey) {
		this.xkey = xkey;
	}
	public String getYkey() {
		return ykey;
	}
	public void setYkey(String ykey) {
		this.ykey = ykey;
	}
	
	
}
