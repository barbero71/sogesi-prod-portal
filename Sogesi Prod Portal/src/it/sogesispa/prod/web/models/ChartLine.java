package it.sogesispa.prod.web.models;

import java.util.List;

public class ChartLine {
	String xkeyName;
	String ykeyName;	
	List<ChartData> chartDataList;
 
	public String getXkeyName() {
		return xkeyName;
	}

	public void setXkeyName(String xkeyName) {
		this.xkeyName = xkeyName;
	}

	public String getYkeyName() {
		return ykeyName;
	}

	public void setYkeyName(String ykeyName) {
		this.ykeyName = ykeyName;
	}

	public List<ChartData> getChartDataList() {
		return chartDataList;
	}

	public void setChartDataList(List<ChartData> chartDataList) {
		this.chartDataList = chartDataList;
	}
	
	
	
}
