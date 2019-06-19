package it.sogesispa.prod.web.controllers;

import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.Summary;
import it.sogesispa.prod.web.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.sogesispa.prod.web.services.ProductionService;
//import it.sogesispa.prod.web.services.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductionController {
	
	private ProductionService productionService;
	
	@Autowired
	public void setProductionService(ProductionService productionService) {
		this.productionService = productionService;
	}

	@RequestMapping(value = "/productivity")
	public String getData(Model model, HttpSession session) 
	{
		/*
		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		session.setAttribute("user", user);

		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	
		int month = cal.get(Calendar.MONTH);		
		int year = cal.get(Calendar.YEAR);
		
		if (month == 0)
		{
			month = 12; // I MESI PARTONO DA 0: PER AVERE IL MESE PRECEDENTE A GENNAIO CORREGGO CON QUESTO ALGORITMO
		}

		User usr = (User) session.getAttribute("user");

		if ((usr.getAuthLevel() & 1) > 0) // PONSACCO
		{
			model.addAttribute("stabPon", 1);

			Summary orePon = new Summary();

			orePon.setOreTotali(productionService.getOre("10005", year, month));

			model.addAttribute("orePon", orePon);
		}

		if ((usr.getAuthLevel() & 2) > 0) // PERUGIA
		{
			model.addAttribute("stabPsg", 1);

			Summary orePsg = new Summary();

			orePsg.setOreTotali(productionService.getOre("10004", year, month));

			model.addAttribute("orePsg", orePsg);
		}

		if ((usr.getAuthLevel() & 4) > 0) // STRONCONE
		{
			model.addAttribute("stabTer", 1);

			Summary oreTer = new Summary();

			oreTer.setOreTotali(productionService.getOre("10002", year, month));

			model.addAttribute("oreTer", oreTer);
		}
		
		if ((usr.getAuthLevel() & 8) > 0) // BOLOGNA
		{
			model.addAttribute("stabBol", 1);

			Summary oreBol = new Summary();

			oreBol.setOreTotali(productionService.getOre("10003", year, month));

			model.addAttribute("oreBol", oreBol);
		}
		
		if ((usr.getAuthLevel() & 16) > 0) // CANNARA
		{
			model.addAttribute("stabCan", 1);

			Summary oreCan = new Summary();

			oreCan.setOreTotali(productionService.getOre("10001", year, month));

			model.addAttribute("oreCan", oreCan);
		}
		
		return "productivity";
		*/
		return null;
	}

	@RequestMapping(value = "/productivity", method = RequestMethod.POST)
	public String getDatabyFilter(Model model, HttpSession session,
			@RequestParam("cmbStab") String stabCdc,
			@RequestParam("dateFrom") String txtDateFrom,
			@RequestParam("dateTo") String txtDateTo) {
		// ----------------------------------------
		// Imposto il filtro di visualizzazione:
		// ----------------------------------------
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		User usr = (User) session.getAttribute("user");
		
		Date dateFrom = null;
		Date dateTo = null;
		try 
		{
			dateFrom = new Date(dateFormat.parse(txtDateFrom).getTime());
			dateTo = new Date(dateFormat.parse(txtDateTo).getTime());
			
			System.out.println(dateFrom);
			System.out.println(dateTo);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(dateFrom);
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTime(dateTo);
		
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
		
		//System.out.println(dFrom);
		
		String dTo = String.valueOf(yearTo);
		dTo += "-";
		dTo += String.valueOf(dayTo);
		dTo += "-";
		dTo += String.valueOf(monthTo);
		
		//System.out.println(dTo);
		
		Summary orePon = new Summary();
		Summary orePsg = new Summary();
		Summary oreTer = new Summary();
		Summary oreBol = new Summary();
		Summary oreCan = new Summary();
		
		switch(stabCdc)
		{
		case "10005": //PONSACCO
			if ((usr.getAuthLevel() & 1) > 0) // PONSACCO
				model.addAttribute("stabPon", 1);
			else
				model.addAttribute("stabPon", 0);
			
			orePon.setOreTotali(productionService.getOreGroup("10005", dFrom, dTo, 999, "999"));
			orePon.setOreTotaliDip(productionService.getOreGroup("10005", dFrom, dTo, 999, "002"));
			orePon.setOreTotaliSom(productionService.getOreGroup("10005", dFrom, dTo, 999, "202"));
			
			orePon.setKgLavorati(productionService.getKgLavorati("075", dFrom, dTo));
			orePon.setKgPiana(productionService.getKgPiana("075", dFrom, dTo));
			orePon.setKgCotone(productionService.getKgCotone("075", dFrom, dTo));
			orePon.setKgPolicotone(productionService.getKgPolicotone("075", dFrom, dTo));
			orePon.setKgHv(productionService.getKgHv("075", dFrom, dTo));
			orePon.setKgMaterassi(productionService.getKgMaterassi("075", dFrom, dTo));
			orePon.setKgTtr(productionService.getKgTtr("075", dFrom, dTo));
			orePon.setPercPiana(productionService.getPercCat(orePon.getKgPiana(),orePon.getKgLavorati()));
			orePon.setPercCotone(productionService.getPercCat(orePon.getKgCotone(),orePon.getKgLavorati()));
			orePon.setPercPolicotone(productionService.getPercCat(orePon.getKgPolicotone(),orePon.getKgLavorati()));
			orePon.setPercHv(productionService.getPercCat(orePon.getKgHv(),orePon.getKgLavorati()));
			orePon.setPercMaterassi(productionService.getPercCat(orePon.getKgMaterassi(),orePon.getKgLavorati()));
			orePon.setPercTtr(productionService.getPercCat(orePon.getKgTtr(),orePon.getKgLavorati()));
			orePon.setProdIndex(productionService.getProdIndex(orePon.getKgLavorati(), orePon.getOreTotali()));
			
			orePon.setOrePiana(productionService.getOreGroup("10005", dFrom, dTo, 1, "999"));
			orePon.setOreCotone(productionService.getOreGroup("10005", dFrom, dTo, 2, "999"));
			orePon.setOreHv(productionService.getOreGroup("10005", dFrom, dTo, 3,"999"));
			orePon.setOreMaterassi(productionService.getOreGroup("10005", dFrom, dTo, 4, "999"));
			orePon.setOreTtr(productionService.getOreGroup("10005", dFrom, dTo, 5, "999"));
			orePon.setOreGen(productionService.getOreGroup("10005", dFrom, dTo, 99, "999"));
			
			orePon.setOrePianaDip(productionService.getOreGroup("10005", dFrom, dTo, 1,"002"));
			orePon.setOreCotoneDip(productionService.getOreGroup("10005", dFrom, dTo, 2,"002"));
			orePon.setOreHvDip(productionService.getOreGroup("10005", dFrom, dTo, 3, "002"));
			orePon.setOreMaterassiDip(productionService.getOreGroup("10005", dFrom, dTo, 4, "002"));
			orePon.setOreTtrDip(productionService.getOreGroup("10005", dFrom, dTo, 5, "002"));
			orePon.setOreGenDip(productionService.getOreGroup("10005", dFrom, dTo, 99, "002"));
			
			orePon.setOrePianaSom(productionService.getOreGroup("10005", dFrom, dTo, 1,"202"));
			orePon.setOreCotoneSom(productionService.getOreGroup("10005", dFrom, dTo, 2,"202"));
			orePon.setOreHvSom(productionService.getOreGroup("10005", dFrom, dTo, 3, "202"));
			orePon.setOreMaterassiSom(productionService.getOreGroup("10005", dFrom, dTo, 4, "202"));
			orePon.setOreTtrSom(productionService.getOreGroup("10005", dFrom, dTo, 5, "202"));
			orePon.setOreGenSom(productionService.getOreGroup("10005", dFrom, dTo, 99, "202"));
			
			orePon.setOreDipCdc0001(productionService.getOre("100050001", dFrom, dTo, "002"));
			orePon.setOreDipCdc0007(productionService.getOre("100050007", dFrom, dTo, "002"));
			orePon.setOreDipCdc0014(productionService.getOre("100050014", dFrom, dTo, "002"));
			orePon.setOreDipCdc0015(productionService.getOre("100050015", dFrom, dTo, "002"));
			orePon.setOreDipCdc0016(productionService.getOre("100050016", dFrom, dTo, "002"));
			orePon.setOreSomCdc0001(productionService.getOre("100050001", dFrom, dTo, "202"));
			orePon.setOreSomCdc0007(productionService.getOre("100050007", dFrom, dTo, "202"));
			orePon.setOreSomCdc0014(productionService.getOre("100050014", dFrom, dTo, "202"));
			orePon.setOreSomCdc0015(productionService.getOre("100050015", dFrom, dTo, "202"));
			orePon.setOreSomCdc0016(productionService.getOre("100050016", dFrom, dTo, "202"));
			
			model.addAttribute("orePon", orePon);
			break;
			
		case "10004": //PONTE SAN GIOVANNI
			if ((usr.getAuthLevel() & 2) > 0) // PERUGIA
				model.addAttribute("stabPsg", 1);
			else
				model.addAttribute("stabPsg", 0);
			
			
			orePsg.setOreTotali(productionService.getOreGroup("10004", dFrom, dTo, 999, "999"));
			orePsg.setOreTotaliDip(productionService.getOreGroup("10004", dFrom, dTo, 999, "002"));
			orePsg.setOreTotaliSom(productionService.getOreGroup("10004", dFrom, dTo, 999, "202"));
			
			orePsg.setKgLavorati(productionService.getKgLavorati("801", dFrom, dTo));
			orePsg.setKgPiana(productionService.getKgPiana("801", dFrom, dTo));
			orePsg.setKgCotone(productionService.getKgCotone("801", dFrom, dTo));
			orePsg.setKgPolicotone(productionService.getKgPolicotone("801", dFrom, dTo));
			orePsg.setKgHv(productionService.getKgHv("801", dFrom, dTo));
			orePsg.setKgMaterassi(productionService.getKgMaterassi("801", dFrom, dTo));
			orePsg.setKgTtr(productionService.getKgTtr("801", dFrom, dTo));
			orePsg.setPercPiana(productionService.getPercCat(orePsg.getKgPiana(),orePsg.getKgLavorati()));
			orePsg.setPercCotone(productionService.getPercCat(orePsg.getKgCotone(),orePsg.getKgLavorati()));
			orePsg.setPercPolicotone(productionService.getPercCat(orePsg.getKgPolicotone(),orePsg.getKgLavorati()));
			orePsg.setPercHv(productionService.getPercCat(orePsg.getKgHv(),orePsg.getKgLavorati()));
			orePsg.setPercMaterassi(productionService.getPercCat(orePsg.getKgMaterassi(),orePsg.getKgLavorati()));
			orePsg.setPercTtr(productionService.getPercCat(orePsg.getKgTtr(),orePsg.getKgLavorati()));
			orePsg.setProdIndex(productionService.getProdIndex(orePsg.getKgLavorati(), orePsg.getOreTotali()));
			
			orePsg.setOrePiana(productionService.getOreGroup("10004", dFrom, dTo, 1, "999"));
			orePsg.setOreCotone(productionService.getOreGroup("10004", dFrom, dTo, 2, "999"));
			orePsg.setOreHv(productionService.getOreGroup("10004", dFrom, dTo, 3, "999"));
			orePsg.setOreMaterassi(productionService.getOreGroup("10004", dFrom, dTo, 4, "999"));
			orePsg.setOreTtr(productionService.getOreGroup("10004", dFrom, dTo, 5, "999"));
			orePsg.setOreGen(productionService.getOreGroup("10004", dFrom, dTo, 99, "999"));
			
			orePsg.setOrePianaDip(productionService.getOreGroup("10004", dFrom, dTo, 1, "002"));
			orePsg.setOreCotoneDip(productionService.getOreGroup("10004", dFrom, dTo, 2, "002"));
			orePsg.setOreHvDip(productionService.getOreGroup("10004", dFrom, dTo, 3, "002"));
			orePsg.setOreMaterassiDip(productionService.getOreGroup("10004", dFrom, dTo, 4, "002"));
			orePsg.setOreTtrDip(productionService.getOreGroup("10004", dFrom, dTo, 5, "002"));
			orePsg.setOreGenDip(productionService.getOreGroup("10004", dFrom, dTo, 99, "002"));
			
			orePsg.setOrePianaSom(productionService.getOreGroup("10004", dFrom, dTo, 1, "202"));
			orePsg.setOreCotoneSom(productionService.getOreGroup("10004", dFrom, dTo, 2, "202"));
			orePsg.setOreHvSom(productionService.getOreGroup("10004", dFrom, dTo, 3, "202"));
			orePsg.setOreMaterassiSom(productionService.getOreGroup("10004", dFrom, dTo, 4, "202"));
			orePsg.setOreTtrSom(productionService.getOreGroup("10004", dFrom, dTo, 5, "202"));
			orePsg.setOreGenSom(productionService.getOreGroup("10004", dFrom, dTo, 99, "202"));
			
			orePsg.setOreDipCdc0001(productionService.getOre("100040001", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0007(productionService.getOre("100040007", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0008(productionService.getOre("100040008", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0013(productionService.getOre("100040013", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0014(productionService.getOre("100040014", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0015(productionService.getOre("100040015", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0016(productionService.getOre("100040016", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0027(productionService.getOre("100040027", dFrom, dTo, "002"));
			orePsg.setOreSomCdc0001(productionService.getOre("100040001", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0007(productionService.getOre("100040007", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0008(productionService.getOre("100040008", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0013(productionService.getOre("100040013", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0014(productionService.getOre("100040014", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0015(productionService.getOre("100040015", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0016(productionService.getOre("100040016", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0027(productionService.getOre("100040027", dFrom, dTo, "202"));
			
			model.addAttribute("orePsg", orePsg);
			break;
			
		case "10001":
			if ((usr.getAuthLevel() & 16) > 0) // CANNARA
				model.addAttribute("stabCan", 1);
			else
				model.addAttribute("stabCan", 0);
			
			oreCan.setOreTotali(productionService.getOreGroup("10001", dFrom, dTo, 999, "999"));
			oreCan.setOreTotaliDip(productionService.getOreGroup("10001", dFrom, dTo, 999, "002"));
			oreCan.setOreTotaliSom(productionService.getOreGroup("10001", dFrom, dTo, 999, "202"));
			
			oreCan.setKgLavorati(productionService.getKgLavorati("031", dFrom, dTo));
			oreCan.setKgPiana(productionService.getKgPiana("031", dFrom, dTo));
			oreCan.setKgCotone(productionService.getKgCotone("031", dFrom, dTo));
			oreCan.setKgPolicotone(productionService.getKgPolicotone("031", dFrom, dTo));
			oreCan.setKgHv(productionService.getKgHv("031", dFrom, dTo));
			oreCan.setKgMaterassi(productionService.getKgMaterassi("031", dFrom, dTo));
			oreCan.setKgTtr(productionService.getKgTtr("031", dFrom, dTo));
			oreCan.setPercPiana(productionService.getPercCat(oreCan.getKgPiana(),oreCan.getKgLavorati()));
			oreCan.setPercCotone(productionService.getPercCat(oreCan.getKgCotone(),oreCan.getKgLavorati()));
			oreCan.setPercPolicotone(productionService.getPercCat(oreCan.getKgPolicotone(),oreCan.getKgLavorati()));
			oreCan.setPercHv(productionService.getPercCat(oreCan.getKgHv(),oreCan.getKgLavorati()));
			oreCan.setPercMaterassi(productionService.getPercCat(oreCan.getKgMaterassi(),oreCan.getKgLavorati()));
			oreCan.setPercTtr(productionService.getPercCat(oreCan.getKgTtr(),oreCan.getKgLavorati()));
			oreCan.setProdIndex(productionService.getProdIndex(oreCan.getKgLavorati(), oreCan.getOreTotali()));
			
			oreCan.setOrePiana(productionService.getOreGroup("10001", dFrom, dTo, 1, "999"));
			oreCan.setOreCotone(productionService.getOreGroup("10001", dFrom, dTo, 2, "999"));
			oreCan.setOreHv(productionService.getOreGroup("10001", dFrom, dTo, 3, "999"));
			oreCan.setOreMaterassi(productionService.getOreGroup("10001", dFrom, dTo, 4, "999"));
			oreCan.setOreTtr(productionService.getOreGroup("10001", dFrom, dTo, 5, "999"));
			oreCan.setOreGen(productionService.getOreGroup("10001", dFrom, dTo, 99, "999"));
			
			oreCan.setOrePianaDip(productionService.getOreGroup("10001", dFrom, dTo, 1, "002"));
			oreCan.setOreCotoneDip(productionService.getOreGroup("10001", dFrom, dTo, 2, "002"));
			oreCan.setOreHvDip(productionService.getOreGroup("10001", dFrom, dTo, 3, "002"));
			oreCan.setOreMaterassiDip(productionService.getOreGroup("10001", dFrom, dTo, 4, "002"));
			oreCan.setOreTtrDip(productionService.getOreGroup("10001", dFrom, dTo, 5, "002"));
			oreCan.setOreGenDip(productionService.getOreGroup("10001", dFrom, dTo, 99, "002"));
			
			oreCan.setOrePianaSom(productionService.getOreGroup("10001", dFrom, dTo, 1, "202"));
			oreCan.setOreCotoneSom(productionService.getOreGroup("10001", dFrom, dTo, 2, "202"));
			oreCan.setOreHvSom(productionService.getOreGroup("10001", dFrom, dTo, 3, "202"));
			oreCan.setOreMaterassiSom(productionService.getOreGroup("10001", dFrom, dTo, 4, "202"));
			oreCan.setOreTtrSom(productionService.getOreGroup("10001", dFrom, dTo, 5, "202"));
			oreCan.setOreGenSom(productionService.getOreGroup("10001", dFrom, dTo, 99, "202"));
			
			oreCan.setOreDipCdc0001(productionService.getOre("100010001", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0007(productionService.getOre("100010007", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0008(productionService.getOre("100010008", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0014(productionService.getOre("100010014", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0015(productionService.getOre("100010015", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0016(productionService.getOre("100010016", dFrom, dTo, "002"));
			oreCan.setOreSomCdc0001(productionService.getOre("100010001", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0007(productionService.getOre("100010007", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0008(productionService.getOre("100010008", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0014(productionService.getOre("100010014", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0015(productionService.getOre("100010015", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0016(productionService.getOre("100010016", dFrom, dTo, "202"));
			
			model.addAttribute("oreCan", oreCan);
			break;
			
		case "10003":
			if ((usr.getAuthLevel() & 8) > 0) // BOLOGNA
				model.addAttribute("stabBol", 1);
			else
				model.addAttribute("stabBol", 0);
			
			oreBol.setOreTotali(productionService.getOreGroup("10003", dFrom, dTo, 999, "999"));
			oreBol.setOreTotaliDip(productionService.getOreGroup("10003", dFrom, dTo, 999, "002"));
			oreBol.setOreTotaliSom(productionService.getOreGroup("10003", dFrom, dTo, 999, "202"));
			
			oreBol.setKgLavorati(productionService.getKgLavorati("498", dFrom, dTo));
			oreBol.setKgPiana(productionService.getKgPiana("498", dFrom, dTo));
			oreBol.setKgCotone(productionService.getKgCotone("498", dFrom, dTo));
			oreBol.setKgPolicotone(productionService.getKgPolicotone("498", dFrom, dTo));
			oreBol.setKgHv(productionService.getKgHv("498", dFrom, dTo));
			oreBol.setKgMaterassi(productionService.getKgMaterassi("498", dFrom, dTo));
			oreBol.setKgTtr(productionService.getKgTtr("498", dFrom, dTo));
			oreBol.setPercPiana(productionService.getPercCat(oreBol.getKgPiana(),oreBol.getKgLavorati()));
			oreBol.setPercCotone(productionService.getPercCat(oreBol.getKgCotone(),oreBol.getKgLavorati()));
			oreBol.setPercPolicotone(productionService.getPercCat(oreBol.getKgPolicotone(),oreBol.getKgLavorati()));
			oreBol.setPercHv(productionService.getPercCat(oreBol.getKgHv(),oreBol.getKgLavorati()));
			oreBol.setPercMaterassi(productionService.getPercCat(oreBol.getKgMaterassi(),oreBol.getKgLavorati()));
			oreBol.setPercTtr(productionService.getPercCat(oreBol.getKgTtr(),oreBol.getKgLavorati()));
			oreBol.setProdIndex(productionService.getProdIndex(oreBol.getKgLavorati(), oreBol.getOreTotali()));
			
			oreBol.setOrePiana(productionService.getOreGroup("10003", dFrom, dTo, 1, "999"));
			oreBol.setOreCotone(productionService.getOreGroup("10003", dFrom, dTo, 2, "999"));
			oreBol.setOreHv(productionService.getOreGroup("10003", dFrom, dTo, 3, "999"));
			oreBol.setOreMaterassi(productionService.getOreGroup("10003", dFrom, dTo, 4, "999"));
			oreBol.setOreTtr(productionService.getOreGroup("10003", dFrom, dTo, 5, "999"));
			oreBol.setOreGen(productionService.getOreGroup("10003", dFrom, dTo, 99, "999"));
			
			oreBol.setOrePianaDip(productionService.getOreGroup("10003", dFrom, dTo, 1, "002"));
			oreBol.setOreCotoneDip(productionService.getOreGroup("10003", dFrom, dTo, 2, "002"));
			oreBol.setOreHvDip(productionService.getOreGroup("10003", dFrom, dTo, 3, "002"));
			oreBol.setOreMaterassiDip(productionService.getOreGroup("10003", dFrom, dTo, 4, "002"));
			oreBol.setOreTtrDip(productionService.getOreGroup("10003", dFrom, dTo, 5, "002"));
			oreBol.setOreGenDip(productionService.getOreGroup("10003", dFrom, dTo, 99, "002"));
			
			oreBol.setOrePianaSom(productionService.getOreGroup("10003", dFrom, dTo, 1, "202"));
			oreBol.setOreCotoneSom(productionService.getOreGroup("10003", dFrom, dTo, 2, "202"));
			oreBol.setOreHvSom(productionService.getOreGroup("10003", dFrom, dTo, 3, "202"));
			oreBol.setOreMaterassiSom(productionService.getOreGroup("10003", dFrom, dTo, 4, "202"));
			oreBol.setOreTtrSom(productionService.getOreGroup("10003", dFrom, dTo, 5, "202"));
			oreBol.setOreGenSom(productionService.getOreGroup("10003", dFrom, dTo, 99, "202"));
			
			oreBol.setOreDipCdc0014(productionService.getOre("100030014", dFrom, dTo, "002"));
			oreBol.setOreDipCdc0015(productionService.getOre("100030015", dFrom, dTo, "002"));
			oreBol.setOreDipCdc0016(productionService.getOre("100030016", dFrom, dTo, "002"));
			oreBol.setOreSomCdc0014(productionService.getOre("100030014", dFrom, dTo, "202"));
			oreBol.setOreSomCdc0015(productionService.getOre("100030015", dFrom, dTo, "202"));
			oreBol.setOreSomCdc0016(productionService.getOre("100030016", dFrom, dTo, "202"));
			
			model.addAttribute("oreBol", oreBol);
			break;
			
		case "10002":
			if ((usr.getAuthLevel() & 4) > 0) // STRONCONE
				model.addAttribute("stabTer", 1);
			else
				model.addAttribute("stabTer", 0);
			
			oreTer.setOreTotali(productionService.getOreGroup("10002", dFrom, dTo, 999, "999"));
			oreTer.setOreTotaliDip(productionService.getOreGroup("10002", dFrom, dTo, 999, "002"));
			oreTer.setOreTotaliSom(productionService.getOreGroup("10002", dFrom, dTo, 999, "202"));
			
			oreTer.setKgLavorati(productionService.getKgLavorati("058", dFrom, dTo));
			oreTer.setKgPiana(productionService.getKgPiana("058", dFrom, dTo));
			oreTer.setKgCotone(productionService.getKgCotone("058", dFrom, dTo));
			oreTer.setKgPolicotone(productionService.getKgPolicotone("058", dFrom, dTo));
			oreTer.setKgHv(productionService.getKgHv("058", dFrom, dTo));
			oreTer.setKgMaterassi(productionService.getKgMaterassi("058", dFrom, dTo));
			oreTer.setKgTtr(productionService.getKgTtr("058", dFrom, dTo));
			oreTer.setPercPiana(productionService.getPercCat(oreTer.getKgPiana(),oreTer.getKgLavorati()));
			oreTer.setPercCotone(productionService.getPercCat(oreTer.getKgCotone(),oreTer.getKgLavorati()));
			oreTer.setPercPolicotone(productionService.getPercCat(oreTer.getKgPolicotone(),oreTer.getKgLavorati()));
			oreTer.setPercHv(productionService.getPercCat(oreTer.getKgHv(),oreTer.getKgLavorati()));
			oreTer.setPercMaterassi(productionService.getPercCat(oreTer.getKgMaterassi(),oreTer.getKgLavorati()));
			oreTer.setPercTtr(productionService.getPercCat(oreTer.getKgTtr(),oreTer.getKgLavorati()));
			oreTer.setProdIndex(productionService.getProdIndex(oreTer.getKgLavorati(), oreTer.getOreTotali()));
			
			oreTer.setOrePiana(productionService.getOreGroup("10002", dFrom, dTo, 1, "999"));
			oreTer.setOreCotone(productionService.getOreGroup("10002", dFrom, dTo, 2,"999"));
			oreTer.setOreHv(productionService.getOreGroup("10002", dFrom, dTo, 3," 999"));
			oreTer.setOreMaterassi(productionService.getOreGroup("10002", dFrom, dTo, 4, "999"));
			oreTer.setOreTtr(productionService.getOreGroup("10002", dFrom, dTo, 5, "999"));
			oreTer.setOreGen(productionService.getOreGroup("10002", dFrom, dTo, 99, "999"));
			
			oreTer.setOrePianaDip(productionService.getOreGroup("10002", dFrom, dTo, 1, "002"));
			oreTer.setOreCotoneDip(productionService.getOreGroup("10002", dFrom, dTo, 2, "002"));
			oreTer.setOreHvDip(productionService.getOreGroup("10002", dFrom, dTo, 3, "002"));
			oreTer.setOreMaterassiDip(productionService.getOreGroup("10002", dFrom, dTo, 4, "002"));
			oreTer.setOreTtrDip(productionService.getOreGroup("10002", dFrom, dTo, 5, "002"));
			oreTer.setOreGenDip(productionService.getOreGroup("10002", dFrom, dTo, 99, "002"));
			
			oreTer.setOrePianaSom(productionService.getOreGroup("10002", dFrom, dTo, 1, "202"));
			oreTer.setOreCotoneSom(productionService.getOreGroup("10002", dFrom, dTo, 2, "202"));
			oreTer.setOreHvSom(productionService.getOreGroup("10002", dFrom, dTo, 3, "202"));
			oreTer.setOreMaterassiSom(productionService.getOreGroup("10002", dFrom, dTo, 4, "202"));
			oreTer.setOreTtrSom(productionService.getOreGroup("10002", dFrom, dTo, 5, "202"));
			oreTer.setOreGenSom(productionService.getOreGroup("10002", dFrom, dTo, 99, "202"));
			
			oreTer.setOreDipCdc0001(productionService.getOre("100020001", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0007(productionService.getOre("100020007", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0008(productionService.getOre("100020008", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0014(productionService.getOre("100020014", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0015(productionService.getOre("100020015", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0016(productionService.getOre("100020016", dFrom, dTo, "002"));
			oreTer.setOreSomCdc0001(productionService.getOre("100020001", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0007(productionService.getOre("100020007", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0008(productionService.getOre("100020008", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0014(productionService.getOre("100020014", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0015(productionService.getOre("100020015", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0016(productionService.getOre("100020016", dFrom, dTo, "202"));
			
			model.addAttribute("oreTer", oreTer);
			break;
			
		default:
			if ((usr.getAuthLevel() & 1) > 0) // PONSACCO
				model.addAttribute("stabPon", 1);
			else
				model.addAttribute("stabPon", 0);
			if ((usr.getAuthLevel() & 2) > 0) // PERUGIA
				model.addAttribute("stabPsg", 1);
			else
				model.addAttribute("stabPsg", 0);
			if ((usr.getAuthLevel() & 4) > 0) // STRONCONE
				model. addAttribute("stabTer", 1);
			else
				model.addAttribute("stabTer", 0);
			if ((usr.getAuthLevel() & 8) > 0) // BOLOGNA
				model. addAttribute("stabBol", 1);
			else
				model.addAttribute("stabBol", 0);
			if ((usr.getAuthLevel() & 16) > 0) // CANNARA
				model. addAttribute("stabCan", 1);
			else
				model.addAttribute("stabCan", 0);

			orePon.setOreTotali(productionService.getOreGroup("10005", dFrom, dTo, 999, "999"));
			orePon.setOreTotaliDip(productionService.getOreGroup("10005", dFrom, dTo, 999, "002"));
			orePon.setOreTotaliSom(productionService.getOreGroup("10005", dFrom, dTo, 999, "202"));
			orePon.setKgLavorati(productionService.getKgLavorati("075", dFrom, dTo));
			orePon.setKgPiana(productionService.getKgPiana("075", dFrom, dTo));
			orePon.setKgCotone(productionService.getKgCotone("075", dFrom, dTo));
			orePon.setKgPolicotone(productionService.getKgPolicotone("075", dFrom, dTo));
			orePon.setKgHv(productionService.getKgHv("075", dFrom, dTo));
			orePon.setKgMaterassi(productionService.getKgMaterassi("075", dFrom, dTo));
			orePon.setKgTtr(productionService.getKgTtr("075", dFrom, dTo));
			orePon.setPercPiana(productionService.getPercCat(orePon.getKgPiana(),orePon.getKgLavorati()));
			orePon.setPercCotone(productionService.getPercCat(orePon.getKgCotone(),orePon.getKgLavorati()));
			orePon.setPercPolicotone(productionService.getPercCat(orePon.getKgPolicotone(),orePon.getKgLavorati()));
			orePon.setPercHv(productionService.getPercCat(orePon.getKgHv(),orePon.getKgLavorati()));
			orePon.setPercMaterassi(productionService.getPercCat(orePon.getKgMaterassi(),orePon.getKgLavorati()));
			orePon.setPercTtr(productionService.getPercCat(orePon.getKgTtr(),orePon.getKgLavorati()));
			orePon.setProdIndex(productionService.getProdIndex(orePon.getKgLavorati(), orePon.getOreTotali()));
			
			orePon.setOrePiana(productionService.getOreGroup("10005", dFrom, dTo, 1,"999"));
			orePon.setOreCotone(productionService.getOreGroup("10005", dFrom, dTo, 2,"999"));
			orePon.setOreHv(productionService.getOreGroup("10005", dFrom, dTo, 3, "999"));
			orePon.setOreMaterassi(productionService.getOreGroup("10005", dFrom, dTo, 4, "999"));
			orePon.setOreTtr(productionService.getOreGroup("10005", dFrom, dTo, 5, "999"));
			orePon.setOreGen(productionService.getOreGroup("10005", dFrom, dTo, 99, "999"));
			
			orePon.setOrePianaDip(productionService.getOreGroup("10005", dFrom, dTo, 1,"002"));
			orePon.setOreCotoneDip(productionService.getOreGroup("10005", dFrom, dTo, 2,"002"));
			orePon.setOreHvDip(productionService.getOreGroup("10005", dFrom, dTo, 3, "002"));
			orePon.setOreMaterassiDip(productionService.getOreGroup("10005", dFrom, dTo, 4, "002"));
			orePon.setOreTtrDip(productionService.getOreGroup("10005", dFrom, dTo, 5, "002"));
			orePon.setOreGenDip(productionService.getOreGroup("10005", dFrom, dTo, 99, "002"));
			
			orePon.setOrePianaSom(productionService.getOreGroup("10005", dFrom, dTo, 1,"202"));
			orePon.setOreCotoneSom(productionService.getOreGroup("10005", dFrom, dTo, 2,"202"));
			orePon.setOreHvSom(productionService.getOreGroup("10005", dFrom, dTo, 3, "202"));
			orePon.setOreMaterassiSom(productionService.getOreGroup("10005", dFrom, dTo, 4, "202"));
			orePon.setOreTtrSom(productionService.getOreGroup("10005", dFrom, dTo, 5, "202"));
			orePon.setOreGenSom(productionService.getOreGroup("10005", dFrom, dTo, 99, "202"));
			
			orePon.setOreDipCdc0001(productionService.getOre("100050001", dFrom, dTo, "002"));
			orePon.setOreDipCdc0007(productionService.getOre("100050007", dFrom, dTo, "002"));
			orePon.setOreDipCdc0014(productionService.getOre("100050014", dFrom, dTo, "002"));
			orePon.setOreDipCdc0015(productionService.getOre("100050015", dFrom, dTo, "002"));
			orePon.setOreDipCdc0016(productionService.getOre("100050016", dFrom, dTo, "002"));
			orePon.setOreSomCdc0001(productionService.getOre("100050001", dFrom, dTo, "202"));
			orePon.setOreSomCdc0007(productionService.getOre("100050007", dFrom, dTo, "202"));
			orePon.setOreSomCdc0014(productionService.getOre("100050014", dFrom, dTo, "202"));
			orePon.setOreSomCdc0015(productionService.getOre("100050015", dFrom, dTo, "202"));
			orePon.setOreSomCdc0016(productionService.getOre("100050016", dFrom, dTo, "202"));
			
			model.addAttribute("orePon", orePon);
			
			orePsg.setOreTotali(productionService.getOreGroup("10004", dFrom, dTo, 999, "999"));
			orePsg.setOreTotaliDip(productionService.getOreGroup("10004", dFrom, dTo, 999, "002"));
			orePsg.setOreTotaliSom(productionService.getOreGroup("10004", dFrom, dTo, 999, "202"));
			orePsg.setKgLavorati(productionService.getKgLavorati("801", dFrom, dTo));
			orePsg.setKgPiana(productionService.getKgPiana("801", dFrom, dTo));
			orePsg.setKgCotone(productionService.getKgCotone("801", dFrom, dTo));
			orePsg.setKgPolicotone(productionService.getKgPolicotone("801", dFrom, dTo));
			orePsg.setKgHv(productionService.getKgHv("801", dFrom, dTo));
			orePsg.setKgMaterassi(productionService.getKgMaterassi("801", dFrom, dTo));
			orePsg.setKgTtr(productionService.getKgTtr("801", dFrom, dTo));
			orePsg.setPercPiana(productionService.getPercCat(orePsg.getKgPiana(),orePsg.getKgLavorati()));
			orePsg.setPercCotone(productionService.getPercCat(orePsg.getKgCotone(),orePsg.getKgLavorati()));
			orePsg.setPercPolicotone(productionService.getPercCat(orePsg.getKgPolicotone(),orePsg.getKgLavorati()));
			orePsg.setPercHv(productionService.getPercCat(orePsg.getKgHv(),orePsg.getKgLavorati()));
			orePsg.setPercMaterassi(productionService.getPercCat(orePsg.getKgMaterassi(),orePsg.getKgLavorati()));
			orePsg.setPercTtr(productionService.getPercCat(orePsg.getKgTtr(),orePsg.getKgLavorati()));
			orePsg.setProdIndex(productionService.getProdIndex(orePsg.getKgLavorati(), orePsg.getOreTotali()));
			
			orePsg.setOrePiana(productionService.getOreGroup("10004", dFrom, dTo, 1, "999"));
			orePsg.setOreCotone(productionService.getOreGroup("10004", dFrom, dTo, 2, "999"));
			orePsg.setOreHv(productionService.getOreGroup("10004", dFrom, dTo, 3, "999"));
			orePsg.setOreMaterassi(productionService.getOreGroup("10004", dFrom, dTo, 4, "999"));
			orePsg.setOreTtr(productionService.getOreGroup("10004", dFrom, dTo, 5, "999"));
			orePsg.setOreGen(productionService.getOreGroup("10004", dFrom, dTo, 99, "999"));
			
			orePsg.setOrePianaDip(productionService.getOreGroup("10004", dFrom, dTo, 1, "002"));
			orePsg.setOreCotoneDip(productionService.getOreGroup("10004", dFrom, dTo, 2, "002"));
			orePsg.setOreHvDip(productionService.getOreGroup("10004", dFrom, dTo, 3, "002"));
			orePsg.setOreMaterassiDip(productionService.getOreGroup("10004", dFrom, dTo, 4, "002"));
			orePsg.setOreTtrDip(productionService.getOreGroup("10004", dFrom, dTo, 5, "002"));
			orePsg.setOreGenDip(productionService.getOreGroup("10004", dFrom, dTo, 99, "002"));
			
			orePsg.setOrePianaSom(productionService.getOreGroup("10004", dFrom, dTo, 1, "202"));
			orePsg.setOreCotoneSom(productionService.getOreGroup("10004", dFrom, dTo, 2, "202"));
			orePsg.setOreHvSom(productionService.getOreGroup("10004", dFrom, dTo, 3, "202"));
			orePsg.setOreMaterassiSom(productionService.getOreGroup("10004", dFrom, dTo, 4, "202"));
			orePsg.setOreTtrSom(productionService.getOreGroup("10004", dFrom, dTo, 5, "202"));
			orePsg.setOreGenSom(productionService.getOreGroup("10004", dFrom, dTo, 99, "202"));
			
			orePsg.setOreDipCdc0001(productionService.getOre("100040001", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0007(productionService.getOre("100040007", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0008(productionService.getOre("100040008", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0013(productionService.getOre("100040013", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0014(productionService.getOre("100040014", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0015(productionService.getOre("100040015", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0016(productionService.getOre("100040016", dFrom, dTo, "002"));
			orePsg.setOreDipCdc0027(productionService.getOre("100040027", dFrom, dTo, "002"));
			orePsg.setOreSomCdc0001(productionService.getOre("100040001", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0007(productionService.getOre("100040007", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0008(productionService.getOre("100040008", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0013(productionService.getOre("100040013", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0014(productionService.getOre("100040014", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0015(productionService.getOre("100040015", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0016(productionService.getOre("100040016", dFrom, dTo, "202"));
			orePsg.setOreSomCdc0027(productionService.getOre("100040027", dFrom, dTo, "202"));
			
			model.addAttribute("orePsg", orePsg);
			
			oreTer.setOreTotali(productionService.getOreGroup("10002", dFrom, dTo, 999, "999"));
			oreTer.setOreTotaliDip(productionService.getOreGroup("10002", dFrom, dTo, 999, "002"));
			oreTer.setOreTotaliSom(productionService.getOreGroup("10002", dFrom, dTo, 999, "202"));
			oreTer.setKgLavorati(productionService.getKgLavorati("058", dFrom, dTo));
			oreTer.setKgPiana(productionService.getKgPiana("058", dFrom, dTo));
			oreTer.setKgCotone(productionService.getKgCotone("058", dFrom, dTo));
			oreTer.setKgPolicotone(productionService.getKgPolicotone("058", dFrom, dTo));
			oreTer.setKgHv(productionService.getKgHv("058", dFrom, dTo));
			oreTer.setKgMaterassi(productionService.getKgMaterassi("058", dFrom, dTo));
			oreTer.setKgTtr(productionService.getKgTtr("058", dFrom, dTo));
			oreTer.setPercPiana(productionService.getPercCat(oreTer.getKgPiana(),oreTer.getKgLavorati()));
			oreTer.setPercCotone(productionService.getPercCat(oreTer.getKgCotone(),oreTer.getKgLavorati()));
			oreTer.setPercPolicotone(productionService.getPercCat(oreTer.getKgPolicotone(),oreTer.getKgLavorati()));
			oreTer.setPercHv(productionService.getPercCat(oreTer.getKgHv(),oreTer.getKgLavorati()));
			oreTer.setPercMaterassi(productionService.getPercCat(oreTer.getKgMaterassi(),oreTer.getKgLavorati()));
			oreTer.setPercTtr(productionService.getPercCat(oreTer.getKgTtr(),oreTer.getKgLavorati()));
			oreTer.setProdIndex(productionService.getProdIndex(oreTer.getKgLavorati(), oreTer.getOreTotali()));
			
			oreTer.setOrePiana(productionService.getOreGroup("10002", dFrom, dTo, 1, "999"));
			oreTer.setOreCotone(productionService.getOreGroup("10002", dFrom, dTo, 2, "999"));
			oreTer.setOreHv(productionService.getOreGroup("10002", dFrom, dTo, 3, "999"));
			oreTer.setOreMaterassi(productionService.getOreGroup("10002", dFrom, dTo, 4, "999"));
			oreTer.setOreTtr(productionService.getOreGroup("10002", dFrom, dTo, 5, "999"));
			oreTer.setOreGen(productionService.getOreGroup("10002", dFrom, dTo, 99, "999"));
			
			oreTer.setOrePianaDip(productionService.getOreGroup("10002", dFrom, dTo, 1, "002"));
			oreTer.setOreCotoneDip(productionService.getOreGroup("10002", dFrom, dTo, 2, "002"));
			oreTer.setOreHvDip(productionService.getOreGroup("10002", dFrom, dTo, 3, "002"));
			oreTer.setOreMaterassiDip(productionService.getOreGroup("10002", dFrom, dTo, 4, "002"));
			oreTer.setOreTtrDip(productionService.getOreGroup("10002", dFrom, dTo, 5, "002"));
			oreTer.setOreGenDip(productionService.getOreGroup("10002", dFrom, dTo, 99, "002"));
			
			oreTer.setOrePianaSom(productionService.getOreGroup("10002", dFrom, dTo, 1, "202"));
			oreTer.setOreCotoneSom(productionService.getOreGroup("10002", dFrom, dTo, 2, "202"));
			oreTer.setOreHvSom(productionService.getOreGroup("10002", dFrom, dTo, 3, "202"));
			oreTer.setOreMaterassiSom(productionService.getOreGroup("10002", dFrom, dTo, 4, "202"));
			oreTer.setOreTtrSom(productionService.getOreGroup("10002", dFrom, dTo, 5, "202"));
			oreTer.setOreGenSom(productionService.getOreGroup("10002", dFrom, dTo, 99, "202"));
			
			oreTer.setOreDipCdc0001(productionService.getOre("100020001", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0007(productionService.getOre("100020007", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0008(productionService.getOre("100020008", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0014(productionService.getOre("100020014", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0015(productionService.getOre("100020015", dFrom, dTo, "002"));
			oreTer.setOreDipCdc0016(productionService.getOre("100020016", dFrom, dTo, "002"));
			oreTer.setOreSomCdc0001(productionService.getOre("100020001", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0007(productionService.getOre("100020007", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0008(productionService.getOre("100020008", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0014(productionService.getOre("100020014", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0015(productionService.getOre("100020015", dFrom, dTo, "202"));
			oreTer.setOreSomCdc0016(productionService.getOre("100020016", dFrom, dTo, "202"));
			
			model.addAttribute("oreTer", oreTer);
			
			oreBol.setOreTotali(productionService.getOreGroup("10003", dFrom, dTo, 999, "999"));
			oreBol.setOreTotaliDip(productionService.getOreGroup("10003", dFrom, dTo, 999, "002"));
			oreBol.setOreTotaliSom(productionService.getOreGroup("10003", dFrom, dTo, 999, "202"));
			oreBol.setKgLavorati(productionService.getKgLavorati("498", dFrom, dTo));
			oreBol.setKgPiana(productionService.getKgPiana("498", dFrom, dTo));
			oreBol.setKgCotone(productionService.getKgCotone("498", dFrom, dTo));
			oreBol.setKgPolicotone(productionService.getKgPolicotone("498", dFrom, dTo));
			oreBol.setKgHv(productionService.getKgHv("498", dFrom, dTo));
			oreBol.setKgMaterassi(productionService.getKgMaterassi("498", dFrom, dTo));
			oreBol.setKgTtr(productionService.getKgTtr("498", dFrom, dTo));
			oreBol.setPercPiana(productionService.getPercCat(oreBol.getKgPiana(),oreBol.getKgLavorati()));
			oreBol.setPercCotone(productionService.getPercCat(oreBol.getKgCotone(),oreBol.getKgLavorati()));
			oreBol.setPercPolicotone(productionService.getPercCat(oreBol.getKgPolicotone(),oreBol.getKgLavorati()));
			oreBol.setPercHv(productionService.getPercCat(oreBol.getKgHv(),oreBol.getKgLavorati()));
			oreBol.setPercMaterassi(productionService.getPercCat(oreBol.getKgMaterassi(),oreBol.getKgLavorati()));
			oreBol.setPercTtr(productionService.getPercCat(oreBol.getKgTtr(),oreBol.getKgLavorati()));
			oreBol.setProdIndex(productionService.getProdIndex(oreBol.getKgLavorati(), oreBol.getOreTotali()));
			
			oreBol.setOrePiana(productionService.getOreGroup("10003", dFrom, dTo, 1, "999"));
			oreBol.setOreCotone(productionService.getOreGroup("10003", dFrom, dTo, 2, "999"));
			oreBol.setOreHv(productionService.getOreGroup("10003", dFrom, dTo, 3, "999"));
			oreBol.setOreMaterassi(productionService.getOreGroup("10003", dFrom, dTo, 4, "999"));
			oreBol.setOreTtr(productionService.getOreGroup("10003", dFrom, dTo, 5, "999"));
			oreBol.setOreGen(productionService.getOreGroup("10003", dFrom, dTo, 99, "999"));
			
			oreBol.setOrePianaDip(productionService.getOreGroup("10003", dFrom, dTo, 1, "002"));
			oreBol.setOreCotoneDip(productionService.getOreGroup("10003", dFrom, dTo, 2, "002"));
			oreBol.setOreHvDip(productionService.getOreGroup("10003", dFrom, dTo, 3, "002"));
			oreBol.setOreMaterassiDip(productionService.getOreGroup("10003", dFrom, dTo, 4, "002"));
			oreBol.setOreTtrDip(productionService.getOreGroup("10003", dFrom, dTo, 5, "002"));
			oreBol.setOreGenDip(productionService.getOreGroup("10003", dFrom, dTo, 99, "002"));
			
			oreBol.setOrePianaSom(productionService.getOreGroup("10003", dFrom, dTo, 1, "202"));
			oreBol.setOreCotoneSom(productionService.getOreGroup("10003", dFrom, dTo, 2, "202"));
			oreBol.setOreHvSom(productionService.getOreGroup("10003", dFrom, dTo, 3, "202"));
			oreBol.setOreMaterassiSom(productionService.getOreGroup("10003", dFrom, dTo, 4, "202"));
			oreBol.setOreTtrSom(productionService.getOreGroup("10003", dFrom, dTo, 5, "202"));
			oreBol.setOreGenSom(productionService.getOreGroup("10003", dFrom, dTo, 99, "202"));
			
			oreBol.setOreDipCdc0014(productionService.getOre("100030014", dFrom, dTo, "002"));
			oreBol.setOreDipCdc0015(productionService.getOre("100030015", dFrom, dTo, "002"));
			oreBol.setOreDipCdc0016(productionService.getOre("100030016", dFrom, dTo, "002"));
			oreBol.setOreSomCdc0014(productionService.getOre("100030014", dFrom, dTo, "202"));
			oreBol.setOreSomCdc0015(productionService.getOre("100030015", dFrom, dTo, "202"));
			oreBol.setOreSomCdc0016(productionService.getOre("100030016", dFrom, dTo, "202"));
			
			model.addAttribute("oreBol", oreBol);
			
			oreCan.setOreTotali(productionService.getOreGroup("10001", dFrom, dTo, 999, "999"));
			oreCan.setOreTotaliDip(productionService.getOreGroup("10001", dFrom, dTo, 999, "002"));
			oreCan.setOreTotaliSom(productionService.getOreGroup("10001", dFrom, dTo, 999, "202"));
			oreCan.setKgLavorati(productionService.getKgLavorati("031", dFrom, dTo));
			oreCan.setKgPiana(productionService.getKgPiana("031", dFrom, dTo));
			oreCan.setKgCotone(productionService.getKgCotone("031", dFrom, dTo));
			oreCan.setKgPolicotone(productionService.getKgPolicotone("031", dFrom, dTo));
			oreCan.setKgHv(productionService.getKgHv("031", dFrom, dTo));
			oreCan.setKgMaterassi(productionService.getKgMaterassi("031", dFrom, dTo));
			oreCan.setKgTtr(productionService.getKgTtr("031", dFrom, dTo));
			oreCan.setPercPiana(productionService.getPercCat(oreCan.getKgPiana(),oreCan.getKgLavorati()));
			oreCan.setPercCotone(productionService.getPercCat(oreCan.getKgCotone(),oreCan.getKgLavorati()));
			oreCan.setPercPolicotone(productionService.getPercCat(oreCan.getKgPolicotone(),oreCan.getKgLavorati()));
			oreCan.setPercHv(productionService.getPercCat(oreCan.getKgHv(),oreCan.getKgLavorati()));
			oreCan.setPercMaterassi(productionService.getPercCat(oreCan.getKgMaterassi(),oreCan.getKgLavorati()));
			oreCan.setPercTtr(productionService.getPercCat(oreCan.getKgTtr(),oreCan.getKgLavorati()));
			oreCan.setProdIndex(productionService.getProdIndex(oreCan.getKgLavorati(), oreCan.getOreTotali()));
			
			oreCan.setOrePiana(productionService.getOreGroup("10001", dFrom, dTo, 1, "999"));
			oreCan.setOreCotone(productionService.getOreGroup("10001", dFrom, dTo, 2, "999"));
			oreCan.setOreHv(productionService.getOreGroup("10001", dFrom, dTo, 3, "999"));
			oreCan.setOreMaterassi(productionService.getOreGroup("10001", dFrom, dTo, 4, "999"));
			oreCan.setOreTtr(productionService.getOreGroup("10001", dFrom, dTo, 5, "999"));
			oreCan.setOreGen(productionService.getOreGroup("10001", dFrom, dTo, 99, "999"));
			
			oreCan.setOrePianaDip(productionService.getOreGroup("10001", dFrom, dTo, 1, "002"));
			oreCan.setOreCotoneDip(productionService.getOreGroup("10001", dFrom, dTo, 2, "002"));
			oreCan.setOreHvDip(productionService.getOreGroup("10001", dFrom, dTo, 3, "002"));
			oreCan.setOreMaterassiDip(productionService.getOreGroup("10001", dFrom, dTo, 4, "002"));
			oreCan.setOreTtrDip(productionService.getOreGroup("10001", dFrom, dTo, 5, "002"));
			oreCan.setOreGenDip(productionService.getOreGroup("10001", dFrom, dTo, 99, "002"));
			oreCan.setOreDipCdc0001(productionService.getOre("100010001", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0007(productionService.getOre("100010007", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0008(productionService.getOre("100010008", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0014(productionService.getOre("100010014", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0015(productionService.getOre("100010015", dFrom, dTo, "002"));
			oreCan.setOreDipCdc0016(productionService.getOre("100010016", dFrom, dTo, "002"));
			oreCan.setOreSomCdc0001(productionService.getOre("100010001", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0007(productionService.getOre("100010007", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0008(productionService.getOre("100010008", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0014(productionService.getOre("100010014", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0015(productionService.getOre("100010015", dFrom, dTo, "202"));
			oreCan.setOreSomCdc0016(productionService.getOre("100010016", dFrom, dTo, "202"));
			
			oreCan.setOrePianaSom(productionService.getOreGroup("10001", dFrom, dTo, 1, "202"));
			oreCan.setOreCotoneSom(productionService.getOreGroup("10001", dFrom, dTo, 2, "202"));
			oreCan.setOreHvSom(productionService.getOreGroup("10001", dFrom, dTo, 3, "202"));
			oreCan.setOreMaterassiSom(productionService.getOreGroup("10001", dFrom, dTo, 4, "202"));
			oreCan.setOreTtrSom(productionService.getOreGroup("10001", dFrom, dTo, 5, "202"));
			oreCan.setOreGenSom(productionService.getOreGroup("10001", dFrom, dTo, 99, "202"));
			
			model.addAttribute("oreCan", oreCan);
		}		

		return "productivity";
	}
	
	@RequestMapping(value="/getprodstabs", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getProdStabs(HttpSession session) 
	{
		
		List<Plants> plants = new ArrayList<Plants>();
		
		User usr = (User) session.getAttribute("user");
		
		plants = productionService.getPlants(usr);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("plants", plants);
		
		return data;

	}
}
