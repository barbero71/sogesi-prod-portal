package it.sogesispa.prod.web.models;

public class ChartTable {
	String nomeStabilimento;
	String produzioneTotaleKg;
	ChartLine chartLine;
	
	public String getNomeStabilimento() {
		return nomeStabilimento;
	}
	public void setNomeStabilimento(String nomeStabilimento) {
		this.nomeStabilimento = nomeStabilimento;
	}
	public String getProduzioneTotaleKg() {
		return produzioneTotaleKg;
	}
	public void setProduzioneTotaleKg(String produzioneTotaleKg) {
		this.produzioneTotaleKg = produzioneTotaleKg;
	}
	public ChartLine getChartLine() {
		return chartLine;
	}
	public void setChartLine(ChartLine chartLine) {
		this.chartLine = chartLine;
	}
	
}
