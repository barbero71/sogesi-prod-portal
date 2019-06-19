package it.sogesispa.prod.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.Date;
//import java.text.SimpleDateFormat;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.sogesispa.prod.web.models.ChartTable;
import it.sogesispa.prod.web.models.Charts;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.ChartsService;
import it.sogesispa.prod.web.utils.ChartSessionFilter;

@Controller
public class ChartsController {

	private ChartsService chartsService;

	@Autowired
	private void setChartsService(ChartsService chartsService) {
		this.chartsService = chartsService;
	}

	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	public String openCharts(Model model, HttpSession session,
			@RequestParam(value = "stab", required = false) String stabId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "unitaMisura", required = false) Integer unitaMisura,
			@RequestParam(value = "externalstabid", required = false) Integer externalstabid) {
		String retpage = "";
		if (unitaMisura!=null && unitaMisura>0) {	//giorni, settimane o mesi

			retpage = notHourlyCharts(
					model, 
					session,
					stabId,
					txtDateFrom,
					txtDateTo,
					unitaMisura,
					externalstabid );
			 
			
		} else{ //orario
 
			txtDateTo = txtDateFrom;	//lavoro su un solo giorno
			stabId = stabId==null?"2,1,4,16,32":stabId;
			txtDateFrom = txtDateFrom==null?new Date():txtDateFrom;
			txtDateTo = txtDateTo==null?new Date():txtDateTo;
			unitaMisura = unitaMisura==null?0:unitaMisura;
			retpage = hourlyCharts(
					model, 
					session,
					stabId,
					txtDateFrom,
					txtDateTo,
					unitaMisura,
					externalstabid );
			 
		}
		

		return retpage;

	}

	private String notHourlyCharts(Model model, HttpSession session,
			String stabId, Date txtDateFrom, Date txtDateTo,
			Integer unitaMisura, Integer externalstabid) {
		User usr = (User) session.getAttribute("user");
		String retpage = "charts";
		
		ChartSessionFilter chartSessionFilter = new ChartSessionFilter();
		if (externalstabid == null || (externalstabid != 1 && externalstabid != 2 && externalstabid != 4 && externalstabid != 16 && externalstabid != 32)) {
			if (usr == null)
				return "login";

			chartSessionFilter.setUserAuthLevel(usr.getAuthLevel());
			chartSessionFilter.setStabId(stabId);
		} else {
			retpage = "singlecharts";		//FIXME ???
			chartSessionFilter.setStabId(externalstabid.toString());
		}
		

		chartSessionFilter.setTxtDateFrom(txtDateFrom);
		chartSessionFilter.setTxtDateTo(txtDateTo);
		chartSessionFilter.setUnitaMisura(unitaMisura);
		session.setAttribute("chartSessionFilter", chartSessionFilter); 

		
		List<ChartTable> chartTableList = new ArrayList<ChartTable>();
		
		
		if ((usr != null && (usr.getAuthLevel() & 1) > 0) || (externalstabid != null && externalstabid == 1)) {  // PONSACCO 

			ChartTable chartTable = chartsService.getChartTable(
										1, 
										unitaMisura,
										chartSessionFilter.getTxtDateFrom(),
										chartSessionFilter.getTxtDateTo()
										); 
			
			chartTableList.add(chartTable);
		} 

		

		if ((usr != null && (usr.getAuthLevel() & 2) > 0) || (externalstabid != null && externalstabid == 2))  {  // PERUGIA

			ChartTable chartTable = chartsService.getChartTable(
										2, 
										unitaMisura,
										chartSessionFilter.getTxtDateFrom(),
										chartSessionFilter.getTxtDateTo()
										); 
			
			chartTableList.add(chartTable);
			
		} 
			
		if ((usr != null && (usr.getAuthLevel() & 4) > 0) || (externalstabid != null && externalstabid == 4)) { // STRONCONE

			ChartTable chartTable = chartsService.getChartTable(
										4, 
										unitaMisura,
										chartSessionFilter.getTxtDateFrom(),
										chartSessionFilter.getTxtDateTo()
										); 
			
			chartTableList.add(chartTable);
		} 
		
		/* INIZIO CANNARA */
		
		if ((usr != null && (usr.getAuthLevel() & 16) > 0) || (externalstabid != null && externalstabid == 16)) { 

			ChartTable chartTable = chartsService.getChartTable(
										16, 
										unitaMisura,
										chartSessionFilter.getTxtDateFrom(),
										chartSessionFilter.getTxtDateTo()
										); 
			
			chartTableList.add(chartTable);
		} 
		
		/* FINE CANNARA */
		
		/* INIZIO ERBUSCO */
		
		if ((usr != null && (usr.getAuthLevel() & 32) > 0) || (externalstabid != null && externalstabid == 16)) { 

			ChartTable chartTable = chartsService.getChartTable(
										32, 
										unitaMisura,
										chartSessionFilter.getTxtDateFrom(),
										chartSessionFilter.getTxtDateTo()
										); 
			
			chartTableList.add(chartTable);
		} 
		
		/* FINE ERBUSCO */

		model.addAttribute("chartTableList", chartTableList);
		
		return retpage;
	}

	private String hourlyCharts(
			Model model, 
			HttpSession session,
			String stabId,
			Date txtDateFrom,
			Date txtDateTo,
			Integer unitaMisura,
			 Integer externalstabid
			 ) {
		User usr = (User) session.getAttribute("user");
		String retpage;
		retpage = "charts_hourly";

		ChartSessionFilter chartSessionFilter = new ChartSessionFilter();
		if (externalstabid == null || (externalstabid != 1 && externalstabid != 2 && externalstabid != 4 && externalstabid != 16 && externalstabid != 32)) {
			if (usr == null)
				return "login";

			chartSessionFilter.setUserAuthLevel(usr.getAuthLevel());
			chartSessionFilter.setStabId(stabId);
		} else {
			retpage = "singlecharts";
			chartSessionFilter.setStabId(externalstabid.toString());
		}

		chartSessionFilter.setTxtDateFrom(txtDateFrom);
		chartSessionFilter.setTxtDateTo(txtDateTo);
		chartSessionFilter.setUnitaMisura(unitaMisura);
		session.setAttribute("chartSessionFilter", chartSessionFilter); 

		if ((usr != null && (usr.getAuthLevel() & 1) > 0) || (externalstabid != null && externalstabid == 1)) // PONSACCO
		{
			model.addAttribute("stabPon", 1);

			List<Charts> datiPon = new ArrayList<Charts>();

			datiPon = chartsService.getTotals(1, chartSessionFilter.getTxtDateFrom(),
					chartSessionFilter.getTxtDateTo());

			model.addAttribute("pon06", "eff: 0, ");
			model.addAttribute("ponTot", "0");

			if (datiPon.size() > 0) {
				for (int i = 0; i < datiPon.size(); i++) {
					if (datiPon.get(i).getOre() == 7) {
						model.addAttribute("pon07", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 8) {
						model.addAttribute("pon08", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 9) {
						model.addAttribute("pon09", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 10) {
						model.addAttribute("pon10", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 11) {
						model.addAttribute("pon11", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 12) {
						model.addAttribute("pon12", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 13) {
						model.addAttribute("pon13", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 14) {
						model.addAttribute("pon14", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 15) {
						model.addAttribute("pon15", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 16) {
						model.addAttribute("pon16", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 17) {
						model.addAttribute("pon17", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 18) {
						model.addAttribute("pon18", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 19) {
						model.addAttribute("pon19", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 20) {
						model.addAttribute("pon20", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 21) {
						model.addAttribute("pon21", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					} else if (datiPon.get(i).getOre() == 22) {
						model.addAttribute("pon22", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					}
					else if (datiPon.get(i).getOre() == 23) {
						model.addAttribute("pon23", "eff: " + datiPon.get(i).getProgr() + ", ");
						model.addAttribute("ponTot", datiPon.get(i).getProgr());
					}
				}
			}

		} else
			model.addAttribute("stabPon", 0);

		if ((usr != null && (usr.getAuthLevel() & 2) > 0) || (externalstabid != null && externalstabid == 2)) // PERUGIA
		{
			model.addAttribute("stabPsg", 1);

			List<Charts> datiPsg = new ArrayList<Charts>();

			datiPsg = chartsService.getTotals(2, chartSessionFilter.getTxtDateFrom(),
					chartSessionFilter.getTxtDateTo());

			model.addAttribute("psg06", "eff: 0, ");
			model.addAttribute("psgTot", "0");

			for (int i = 0; i < datiPsg.size(); i++) {
				if (datiPsg.size() > 0) {
					if (datiPsg.get(i).getOre() == 7) {
						model.addAttribute("psg07", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 8) {
						model.addAttribute("psg08", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 9) {
						model.addAttribute("psg09", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 10) {
						model.addAttribute("psg10", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 11) {
						model.addAttribute("psg11", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 12) {
						model.addAttribute("psg12", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 13) {
						model.addAttribute("psg13", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 14) {
						model.addAttribute("psg14", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 15) {
						model.addAttribute("psg15", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 16) {
						model.addAttribute("psg16", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 17) {
						model.addAttribute("psg17", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 18) {
						model.addAttribute("psg18", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 19) {
						model.addAttribute("psg19", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 20) {
						model.addAttribute("psg20", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 21) {
						model.addAttribute("psg21", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					} else if (datiPsg.get(i).getOre() == 22) {
						model.addAttribute("psg22", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					}
					else if (datiPsg.get(i).getOre() == 23) {
						model.addAttribute("psg23", "eff: " + datiPsg.get(i).getProgr() + ", ");
						model.addAttribute("psgTot", datiPsg.get(i).getProgr());
					}
				}
			}
		}
		
		else
			model.addAttribute("stabPsg", 0);
		
		/* INIZIO CANNARA */
		
		if ((usr != null && (usr.getAuthLevel() & 16) > 0) || (externalstabid != null && externalstabid == 16)) // CANNARA
		{
			model.addAttribute("stabCan", 1);

			List<Charts> datiCan = new ArrayList<Charts>();

			datiCan = chartsService.getTotals(16, chartSessionFilter.getTxtDateFrom(),
					chartSessionFilter.getTxtDateTo());

			model.addAttribute("can06", "eff: 0, ");
			model.addAttribute("canTot", "0");

			for (int i = 0; i < datiCan.size(); i++) {
				if (datiCan.size() > 0) {
					if (datiCan.get(i).getOre() == 7) {
						model.addAttribute("can07", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 8) {
						model.addAttribute("can08", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 9) {
						model.addAttribute("can09", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 10) {
						model.addAttribute("can10", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 11) {
						model.addAttribute("can11", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 12) {
						model.addAttribute("can12", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 13) {
						model.addAttribute("can13", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 14) {
						model.addAttribute("can14", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 15) {
						model.addAttribute("can15", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 16) {
						model.addAttribute("can16", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 17) {
						model.addAttribute("can17", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 18) {
						model.addAttribute("can18", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 19) {
						model.addAttribute("can19", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 20) {
						model.addAttribute("can20", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 21) {
						model.addAttribute("can21", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					} else if (datiCan.get(i).getOre() == 22) {
						model.addAttribute("can22", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					}else if (datiCan.get(i).getOre() == 23) {
						model.addAttribute("can23", "eff: " + datiCan.get(i).getProgr() + ", ");
						model.addAttribute("canTot", datiCan.get(i).getProgr());
					}
				}
			}
		}
		
		else
			model.addAttribute("stabCan", 0);
		
		/* FINE CANNARA */
		
		/* INIZIO ERBUSCO */
		
		if ((usr != null && (usr.getAuthLevel() & 32) > 0) || (externalstabid != null && externalstabid == 32)) // ERBUSCO
		{
			model.addAttribute("stabErb", 1);

			List<Charts> datiErb = new ArrayList<Charts>();

			datiErb = chartsService.getTotals(32, chartSessionFilter.getTxtDateFrom(),
					chartSessionFilter.getTxtDateTo());

			model.addAttribute("erb06", "eff: 0, ");
			model.addAttribute("erbTot", "0");

			for (int i = 0; i < datiErb.size(); i++) {
				if (datiErb.size() > 0) {
					if (datiErb.get(i).getOre() == 7) {
						model.addAttribute("erb07", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 8) {
						model.addAttribute("erb08", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 9) {
						model.addAttribute("erb09", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 10) {
						model.addAttribute("erb10", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 11) {
						model.addAttribute("erb11", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 12) {
						model.addAttribute("erb12", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 13) {
						model.addAttribute("erb13", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 14) {
						model.addAttribute("erb14", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 15) {
						model.addAttribute("erb15", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 16) {
						model.addAttribute("erb16", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 17) {
						model.addAttribute("erb17", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 18) {
						model.addAttribute("erb18", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 19) {
						model.addAttribute("erb19", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 20) {
						model.addAttribute("erb20", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 21) {
						model.addAttribute("erb21", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					} else if (datiErb.get(i).getOre() == 22) {
						model.addAttribute("erb22", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					}else if (datiErb.get(i).getOre() == 23) {
						model.addAttribute("erb23", "eff: " + datiErb.get(i).getProgr() + ", ");
						model.addAttribute("erbTot", datiErb.get(i).getProgr());
					}
				}
			}
		}
		
		else
			model.addAttribute("stabErb", 0);
		
		/* FINE ERBUSCO */

		if ((usr != null && (usr.getAuthLevel() & 4) > 0) || (externalstabid != null && externalstabid == 4)) // STRONCONE
		{
			model.addAttribute("stabTer", 1);

			List<Charts> datiTer = new ArrayList<Charts>();

			datiTer = chartsService.getTotals(4, chartSessionFilter.getTxtDateFrom(),
					chartSessionFilter.getTxtDateTo());

			model.addAttribute("ter06", "eff: 0, ");
			model.addAttribute("terTot", "0");

			for (int i = 0; i < datiTer.size(); i++) {
				if (datiTer.size() > 0) {
					if (datiTer.get(i).getOre() == 7) {
						model.addAttribute("ter07", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					}
					if (datiTer.get(i).getOre() == 8) {
						model.addAttribute("ter08", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 9) {
						model.addAttribute("ter09", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 10) {
						model.addAttribute("ter10", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 11) {
						model.addAttribute("ter11", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 12) {
						model.addAttribute("ter12", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 13) {
						model.addAttribute("ter13", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 14) {
						model.addAttribute("ter14", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 15) {
						model.addAttribute("ter15", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 16) {
						model.addAttribute("ter16", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 17) {
						model.addAttribute("ter17", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 18) {
						model.addAttribute("ter18", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 19) {
						model.addAttribute("ter19", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 20) {
						model.addAttribute("ter20", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 21) {
						model.addAttribute("ter21", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					} else if (datiTer.get(i).getOre() == 22) {
						model.addAttribute("ter22", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					}else if (datiTer.get(i).getOre() == 23) {
						model.addAttribute("ter23", "eff: " + datiTer.get(i).getProgr() + ", ");
						model.addAttribute("terTot", datiTer.get(i).getProgr());
					}
				}
			}
		} else {
			model.addAttribute("stabTer", 0);	
		}
		return retpage;
	}

}
