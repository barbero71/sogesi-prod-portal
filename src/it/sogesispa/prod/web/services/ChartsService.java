
package it.sogesispa.prod.web.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sogesispa.prod.web.dao.ChartsDAO;
import it.sogesispa.prod.web.models.ChartData;
import it.sogesispa.prod.web.models.ChartLine;
import it.sogesispa.prod.web.models.ChartTable;
import it.sogesispa.prod.web.models.Charts;
import it.sogesispa.prod.web.models.DataModelConstants;
//import it.sogesispa.prod.web.utils.ChartSessionFilter;
//import it.sogesispa.prod.web.utils.ConsumoDetergentiChartSessionFilter;
import it.sogesispa.prod.web.utils.OreLavorateChartSessionFilter;

@Service("chartsService")
public class ChartsService 
{
	private ChartsDAO chartsDao;
	
	@Autowired
	public void setChartsDao(ChartsDAO chartsDao)
	{
		this.chartsDao = chartsDao;
	}
	
	public List<Charts> getTotals(Integer stabId, Date datada, Date dataa)
	{
		return chartsDao.getChartsData(stabId, datada, dataa);
	}
	
	public ChartTable getChartTable(Integer stabId, Integer unitaMisura, Date dataDa, Date dataA) {
		ChartTable result = new ChartTable();
		
		String s = "Stabilimento di ";
		
		switch (stabId) {
			case DataModelConstants.Stabilimenti.ID_PONSACCO:
				result.setNomeStabilimento(s + DataModelConstants.Stabilimenti.DESCRIZIONE_PONSACCO);
				break;
			case DataModelConstants.Stabilimenti.ID_PERUGIA:
				result.setNomeStabilimento(s + DataModelConstants.Stabilimenti.DESCRIZIONE_PERUGIA);
				break;
			case DataModelConstants.Stabilimenti.ID_STRONCONE:
				result.setNomeStabilimento(s + DataModelConstants.Stabilimenti.DESCRIZIONE_STRONCONE);
				break;
			case DataModelConstants.Stabilimenti.ID_CANNARA:
				result.setNomeStabilimento(s + DataModelConstants.Stabilimenti.DESCRIZIONE_CANNARA);
				break;
		}
		
		//dati raggruppati per data/ora
		List<Charts> chartList = chartsDao.getChartsData(stabId, dataDa, dataA);
		
		
		ChartLine chartLine = new ChartLine();  
		chartLine.setXkeyName("period");
		chartLine.setYkeyName("eff");
		
		chartLine.setChartDataList(createChartDataList(chartList, unitaMisura, dataDa, dataA));
		
		
		result.setProduzioneTotaleKg( calculateProduzioneTotaleKg(chartList) );
		
		result.setChartLine(chartLine); 
		
		return result;
		
	}

	private String calculateProduzioneTotaleKg(List<Charts> chartList) {
		long somma = 0l;
		for(Charts c: chartList)
		{
			somma += c.getSomma();
		}
		return ""+somma;
	}

	//crea una lista di dati raggruppati per giorni, settimane o mesi
	private List<ChartData> createChartDataList(List<Charts> chartList, Integer unitaMisura, Date dataDa, Date dataA) {
		List<ChartData> result = new ArrayList<ChartData>();
		SimpleDateFormat pattern = null;
		SimpleDateFormat patternkey = null;
		switch (unitaMisura) {
		case OreLavorateChartSessionFilter.UNITA_MISURA_GIORNI:
			pattern = new SimpleDateFormat("dd-MMM-yyyy",Locale.ITALIAN);
			patternkey = new SimpleDateFormat("yyyyMMdd",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_SETTIMANE:
			pattern = new SimpleDateFormat("MMM-yyyy 'Settimana' W",Locale.ITALIAN);
			patternkey = new SimpleDateFormat("yyyyMMW",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_MESI:
			pattern = new SimpleDateFormat("MMM-yyyy",Locale.ITALIAN);
			patternkey = new SimpleDateFormat("yyyyMM",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_ANNI:
			pattern = new SimpleDateFormat("yyyy",Locale.ITALIAN);
			patternkey = new SimpleDateFormat("yyyy",Locale.ITALIAN);
			break;
		default:
			pattern = new SimpleDateFormat("dd-MM-yyyy",Locale.ITALIAN);
			patternkey = new SimpleDateFormat("yyyyMMdd",Locale.ITALIAN);
			break;
		}
		
		Map<String, String> orderore = new TreeMap<String, String>();
		  
		  Map<String, BigDecimal> ore = new HashMap<>();
		  for(Charts c: chartList)  {
			   String k = patternkey.format(c.getData());
			   String k2 = pattern.format(c.getData());
			   
			   orderore.put(k, k2);
			   BigDecimal s2 = ore.get(k);
			   float ss2 = (s2 != null ? (s2.floatValue() + c.getSomma()) : (c.getSomma()));
			   s2 = new BigDecimal(ss2);
			   ore.put(k, s2);
		  
		 }
		  
		  for(String s: orderore.keySet())  {
		   BigDecimal b = ore.get(s);
		   String label = orderore.get(s);
		   result.add( new ChartData(label, b.toString()) );
		  }
		
		
		return result;
	}
	
	
	
}
