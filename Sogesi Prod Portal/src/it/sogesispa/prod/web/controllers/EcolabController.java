package it.sogesispa.prod.web.controllers;

//import it.sogesispa.prod.web.dto.LineaDTO;
import it.sogesispa.prod.web.models.EcolabFilter;
import it.sogesispa.prod.web.models.EcolabGroups;
import it.sogesispa.prod.web.models.EcolabMachines;
import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.EcolabService;
import it.sogesispa.prod.web.services.ProductionService;
//import it.sogesispa.prod.web.utils.ConsumoDetergentiSessionFilter;
import it.sogesispa.prod.web.utils.EcolabSessionFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EcolabController {
	private EcolabService ecolabService;
	private static Logger logger = Logger.getLogger(EcolabController.class);
	private ProductionService productionService;

	@Autowired
	public void setEcolabService(EcolabService ecolabService) {
		this.ecolabService = ecolabService;
	}

	@Autowired
	public void setProductionService(ProductionService productionService) {
		this.productionService = productionService;
	}

	@RequestMapping(value = "/ecolab", method = RequestMethod.POST)
	public String getData(Model model, HttpSession session, @RequestParam("cmbMach") String cmbMach,
			@RequestParam("cmbGroup") Integer groupId, @RequestParam(value = "cmbStab", required = false) String stabId,
			@RequestParam("stab") String liststabId, @RequestParam("dateFrom") String txtDateFrom,
			@RequestParam("dateTo") String txtDateTo) {
		// ---------------------------------------------------------------------
		// Controllo se esiste la sessione utente, altrimenti rimando al login
		// ---------------------------------------------------------------------

		logger.info("Checking User Session Parameters..");

		/*
		 * if (session.getAttribute("user") == null) return "login_prod";
		 */
		User usr = (User) session.getAttribute("user");
		System.out.println("liststabId: " + liststabId);

		// ----------------------------------------
		// Imposto il filtro di visualizzazione:
		// ----------------------------------------
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		java.sql.Date dateFrom = null;
		java.sql.Date dateTo = null;

		String[] sMach = cmbMach.split("[+]");

		try {
			dateFrom = new java.sql.Date(dateFormat.parse(txtDateFrom).getTime());
			dateTo = new java.sql.Date(dateFormat.parse(txtDateTo).getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		EcolabSessionFilter ecolabSessionFilter = new EcolabSessionFilter();
		ecolabSessionFilter.setUserAuthLevel(usr.getAuthLevel());
		ecolabSessionFilter.setStabId(liststabId);

		ecolabSessionFilter.setTxtDateFrom(dateFrom);
		ecolabSessionFilter.setTxtDateTo(dateTo);
		session.setAttribute("ecolabSessionFilter", ecolabSessionFilter);

		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(ecolabSessionFilter.getTxtDateFrom());

		Calendar calTo = Calendar.getInstance();
		calTo.setTime(ecolabSessionFilter.getTxtDateTo());

		int yearFrom = calFrom.get(Calendar.YEAR);
		int monthFrom = calFrom.get(Calendar.MONTH) + 1;
		int dayFrom = calFrom.get(Calendar.DAY_OF_MONTH);

		int yearTo = calTo.get(Calendar.YEAR);
		int monthTo = calTo.get(Calendar.MONTH) + 1;
		int dayTo = calTo.get(Calendar.DAY_OF_MONTH);

		String dFrom = String.valueOf(yearFrom);
		dFrom += "-";
		dFrom += String.valueOf(dayFrom);
		dFrom += "-";
		dFrom += String.valueOf(monthFrom);

		// System.out.println(dFrom);

		String dTo = String.valueOf(yearTo);
		dTo += "-";
		dTo += String.valueOf(dayTo);
		dTo += "-";
		dTo += String.valueOf(monthTo);

		EcolabFilter filter = new EcolabFilter();

		filter.setDateFrom(dateFrom);
		filter.setDateTo(dateTo);
		if (!cmbMach.equals("999+999")) {
			filter.setGroupId(Integer.parseInt(sMach[0]));
		} else {
			filter.setGroupId(groupId);
		}
		Integer[] iMach = { Integer.parseInt(sMach[0]), Integer.parseInt(sMach[1]) };
		filter.setMachineId(iMach);
		// filter.setStabId(stabId);
		filter.setListStabId(liststabId);
		// filter.setStabName(this.getStabId(stabId));
		filter.setStabsName(this.getStabId(liststabId));
		filter.setGroupName(this.getGroupId(groupId));
		filter.setMachineName(getMachineId(iMach));

		model.addAttribute("filter", filter);

		// --------------------------------------------------------------------
		// Ricavo i dati di produzione relativi ai permessi dell'utente
		// --------------------------------------------------------------------

		if ((usr.getAuthLevel() & 1) > 0
				&& (liststabId != null && (liststabId.equals("999") || liststabId.equals("1") || liststabId.contains(",1")|| liststabId.contains("1,")))) 	// PONSACCO:
																																							// gruppi
																																							// 2-3
		{
			model.addAttribute("PonTot", ecolabService.getTotals(filter, 1, 999));
			model.addAttribute("stabPon", 1);
			model.addAttribute("PonLco", ecolabService.getSummary(filter, 1, 2));
			model.addAttribute("dPonLco", ecolabService.getDetails(filter, 1, 2));

			model.addAttribute("PonLce", ecolabService.getSummary(filter, 1, 3));
			model.addAttribute("dPonLce", ecolabService.getDetails(filter, 1, 3));
			// model.addAttribute("PonOreTot",
			// productionService.getOreGroup("10005", dFrom, dTo, 999, "999"));
			double ponKg = ecolabService.getTotals(filter, 1, 999);
			double ponOre = productionService.getOreGroup("10005", dFrom, dTo, 999, "999");

			if (ponOre != 0) {
				model.addAttribute("PonProdutt", (ponKg / ponOre));
				model.addAttribute("PonOreTot", ponOre);
			} else {
				model.addAttribute("PonProdutt", 0);
			}
		}

		if ((usr.getAuthLevel() & 2) > 0
				&& (liststabId != null && (liststabId.equals("999") || liststabId.contains("2")))) // PERUGIA:
																									// gruppi
																									// 6-7-8
		{
			model.addAttribute("PsgTot", ecolabService.getTotals(filter, 2, 999));
			model.addAttribute("stabPsg", 1);
			model.addAttribute("PsgLco", ecolabService.getSummary(filter, 2, 6));
			model.addAttribute("dPsgLco", ecolabService.getDetails(filter, 2, 6));

			model.addAttribute("PsgLce", ecolabService.getSummary(filter, 2, 7));
			model.addAttribute("dPsgLce", ecolabService.getDetails(filter, 2, 7));

			model.addAttribute("PsgVos", ecolabService.getSummary(filter, 2, 8));
			model.addAttribute("dPsgVos", ecolabService.getDetails(filter, 2, 8));

			// model.addAttribute("PsgOreTot",
			// productionService.getOreGroup("10004", dFrom, dTo, 999, "999"));
			double psgKg = ecolabService.getTotals(filter, 2, 999);
			double psgOre = productionService.getOreGroup("10004", dFrom, dTo, 999, "999");

			if (psgOre != 0) {
				model.addAttribute("PsgProdutt", (psgKg / psgOre));
				model.addAttribute("PsgOreTot", psgOre);
			} else {
				model.addAttribute("PsgProdutt", 0);
			}

		}

		
		if ((usr.getAuthLevel() & 4) > 0
				&& (liststabId != null && (liststabId.equals("999") || liststabId.contains("4")))) // STRONCONE:
																									// gruppi
																									// 10-11-12-13
		{
			model.addAttribute("TerTot", ecolabService.getTotals(filter, 4, 999));
			model.addAttribute("stabTer", 1);
			model.addAttribute("TerLco", ecolabService.getSummary(filter, 4, 10));
			model.addAttribute("dTerLco", ecolabService.getDetails(filter, 4, 10));

			model.addAttribute("TerLce", ecolabService.getSummary(filter, 4, 11));
			model.addAttribute("dTerLce", ecolabService.getDetails(filter, 4, 11));
			
			model.addAttribute("TerKgs10", ecolabService.getSummary(filter, 4, 12));
			model.addAttribute("dTerKgs10", ecolabService.getDetails(filter, 4, 12));

			model.addAttribute("TerSte", ecolabService.getSummary(filter, 4, 13));
			model.addAttribute("dTerSte", ecolabService.getDetails(filter, 4, 13));

			// model.addAttribute("TerOreTot",
			// productionService.getOreGroup("10002", dFrom, dTo, 999, "999"));
			double terKg = ecolabService.getTotals(filter, 4, 999);
			double terOre = productionService.getOreGroup("10002", dFrom, dTo, 999, "999");

			if (terOre != 0) {
				model.addAttribute("TerProdutt", (terKg / terOre));
				model.addAttribute("TerOreTot", terOre);
			} else {
				model.addAttribute("TerProdutt", 0);
			}
		}
		
		/* INIZIO MODIFICHE CANNARA */
		
		if ((usr.getAuthLevel() & 16) > 0
				&& (liststabId != null && (liststabId.equals("999") || liststabId.contains("16")))) // CANNARA:
																									// gruppi
																									// 102-103
		{
			model.addAttribute("CanTot", ecolabService.getTotals(filter, 16, 999));
			model.addAttribute("stabCan", 16);
			model.addAttribute("CanLco", ecolabService.getSummary(filter, 16, 102));
			model.addAttribute("dCanLco", ecolabService.getDetails(filter, 16, 102));

			model.addAttribute("CanLce", ecolabService.getSummary(filter, 16, 103));
			model.addAttribute("dCanLce", ecolabService.getDetails(filter, 16, 103));
			// model.addAttribute("PonOreTot",
			// productionService.getOreGroup("10005", dFrom, dTo, 999, "999"));
			double canKg = ecolabService.getTotals(filter, 16, 999);
			double canOre = productionService.getOreGroup("10001", dFrom, dTo, 999, "999");

			if (canOre != 0) {
				model.addAttribute("CanProdutt", (canKg / canOre));
				model.addAttribute("CanOreTot", canOre);
			} else {
				model.addAttribute("CanProdutt", 0);
			}
		}
		
		/* FINE MODIFICHE CANNARA */
		
		/* INIZIO MODIFICHE ERBUSCO */
		
		if ((usr.getAuthLevel() & 32) > 0
				&& (liststabId != null && (liststabId.equals("999") || liststabId.contains("32")))) // ERBUSCO:
																									// gruppi
																									// 201-202-204
		{
			model.addAttribute("ErbTot", ecolabService.getTotals(filter, 32, 999));
			model.addAttribute("stabErb", 32);
			model.addAttribute("ErbLco", ecolabService.getSummary(filter, 32, 201));
			model.addAttribute("dErbLco", ecolabService.getDetails(filter, 32, 201));

			model.addAttribute("ErbLce", ecolabService.getSummary(filter, 32, 202));
			model.addAttribute("dErbLce", ecolabService.getDetails(filter, 32, 202));
			
			model.addAttribute("ErbLce2", ecolabService.getSummary(filter, 32, 204));
			model.addAttribute("dErbLce2", ecolabService.getDetails(filter, 32, 204));
			// model.addAttribute("PonOreTot",
			// productionService.getOreGroup("10005", dFrom, dTo, 999, "999"));
			double erbKg = ecolabService.getTotals(filter, 32, 999);
			double erbOre = productionService.getOreGroup("10006", dFrom, dTo, 999, "999");

			if (erbOre != 0) {
				model.addAttribute("ErbProdutt", (erbKg / erbOre));
				model.addAttribute("ErbOreTot", erbOre);
			} else {
				model.addAttribute("ErbProdutt", 0);
			}
		}
		
		/* FINE MODIFICHE ERBUSCO */


		return "ecolab";
	}

	private List<String> getStabId(String liststabId) {
		String groupId = "Tutti";
		List<String> ret = new ArrayList<>();
		if (liststabId != null) {
			String sSql = "";

			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
				Connection con = ds.getConnection();

				Statement sm = con.createStatement();

				sSql = "SELECT * FROM T_PLANTS WHERE TI_NUMBER in (" + liststabId + ") ";

				ResultSet rs = sm.executeQuery(sSql);

				while (rs.next()) {
					groupId = rs.getString("PLANT_DESC");
					ret.add(groupId);
				}
				rs.close();
				sm.close();
				con.close();
				ctx.close();

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
		} else {
			ret.add(groupId);
		}
		return ret;
	}

	@SuppressWarnings("unused")
	private String getStabId(Integer filter) {
		String groupId = "Tutti";

		if (filter != 999) {
			String sSql = "";

			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
				Connection con = ds.getConnection();

				Statement sm = con.createStatement();

				sSql = "SELECT * FROM T_PLANTS WHERE TI_NUMBER = " + filter.toString();

				ResultSet rs = sm.executeQuery(sSql);

				while (rs.next()) {
					groupId = rs.getString("PLANT_DESC");
				}
				rs.close();
				sm.close();
				con.close();
				ctx.close();

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
		}
		return groupId;
	}

	private String getGroupId(Integer filter) {
		String groupId = "Tutti";

		if (filter != 999) {
			String sSql = "";

			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
				Connection con = ds.getConnection();

				Statement sm = con.createStatement();

				sSql = "SELECT * FROM T_GROUPS WHERE GR_NUMBER = " + filter.toString();

				ResultSet rs = sm.executeQuery(sSql);

				while (rs.next()) {
					groupId = rs.getString("GR_NAME");
				}
				rs.close();
				sm.close();
				con.close();
				ctx.close();

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
		}
		return groupId;
	}

	private String getMachineId(Integer[] iMach) {
		String machId = "Tutte";
		Integer[] lAll = { 999, 999 };

		if (!Arrays.equals(iMach, lAll)) {
			String sSql = "";

			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
				Connection con = ds.getConnection();

				Statement sm = con.createStatement();

				sSql = "SELECT * FROM T_SetupMachines WHERE GR_Number = " + iMach[0].toString()
						+ " AND Ma_InterNumber = " + iMach[1].toString();

				ResultSet rs = sm.executeQuery(sSql);

				while (rs.next()) {
					machId = rs.getString("MA_Name");
				}
				rs.close();
				sm.close();
				con.close();
				ctx.close();

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
		}
		return machId;
	}

	@RequestMapping(value = "/getmachines", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMachines(HttpSession session, @RequestParam("plant") String plant,
			@RequestParam("group") String group) {

		// String prn = "999+999-Tutte,";

		List<EcolabMachines> machines = new ArrayList<EcolabMachines>();

		User usr = (User) session.getAttribute("user");
		if (plant != null && !plant.equals("undefined")) {

			String[] pants = plant.split(",");
			for (String p : pants) {
				if (p.trim().length() > 0)
					machines.addAll(ecolabService.getMachines(group, p, usr));
			}
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("machines", machines);

		return data;

	}

	@RequestMapping(value = "/getgroups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMachines(HttpSession session, @RequestParam("plant") String plant) {

		// String prn = "999+999-Tutte,";

		List<EcolabGroups> groups = new ArrayList<EcolabGroups>();

		User usr = (User) session.getAttribute("user");

		if (plant != null && !plant.equals("undefined")) {

			String[] pants = plant.split(",");
			for (String p : pants) {
				if (p.trim().length() > 0)
					groups.addAll(ecolabService.getGroups(p, usr));
			}
		}
			
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("groups", groups);

		return data;

	}

	@RequestMapping(value = "/getstabs", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getStabs(HttpSession session) {

		// String prn = "999+999-Tutte,";

		List<Plants> plants = new ArrayList<Plants>();

		User usr = (User) session.getAttribute("user");

		plants = ecolabService.getPlants(usr);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("plants", plants);

		return data;

	}

}
