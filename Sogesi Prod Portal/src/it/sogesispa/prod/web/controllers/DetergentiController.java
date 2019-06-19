package it.sogesispa.prod.web.controllers;

import it.sogesispa.prod.web.dto.ConsumoDetergenteDTO;
import it.sogesispa.prod.web.dto.DetergentiChartDTO;
//import it.sogesispa.prod.web.dto.OreChartDTO;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.DetergentiService;
import it.sogesispa.prod.web.utils.ConsumoDetergentiChartSessionFilter;
import it.sogesispa.prod.web.utils.ConsumoDetergentiSessionFilter;
//import it.sogesispa.prod.web.utils.OreLavorateChartSessionFilter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetergentiController {
	private static final Logger logger = Logger.getLogger(DetergentiController.class);
	@Autowired
	private DetergentiService detergentiService;

	
	@RequestMapping(value = "/consumo-detergenti", method = RequestMethod.GET)
	public String consumoDetergenti(Model model, HttpSession session,
			@RequestParam(value = "stab", required = false) String stabId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo) {
		
		try {
			ConsumoDetergentiSessionFilter consumoDetergentiSessionFilter =  new ConsumoDetergentiSessionFilter();
			User usr = (User) session.getAttribute("user");
			consumoDetergentiSessionFilter.setUserAuthLevel(usr.getAuthLevel());
			consumoDetergentiSessionFilter.setStabId(stabId);
			consumoDetergentiSessionFilter.setTxtDateFrom(txtDateFrom);
			consumoDetergentiSessionFilter.setTxtDateTo(txtDateTo);
			session.setAttribute("consumoDetergentiSessionFilter", consumoDetergentiSessionFilter);
		
			Map<String,List<ConsumoDetergenteDTO>> list = detergentiService.getConsumi(consumoDetergentiSessionFilter);

			model.addAttribute("listaDetergenti", list);
			
		} catch (Exception e) {
			logger.error("Errore nel metodo lista ore lavorate del controller OreLavorateController", e);
		}
		return "consumo-detergenti";
	}
	@RequestMapping(value = "/consumo-detergenti-charts", method = RequestMethod.GET)
	public String chartsOreLavorate(Model model, HttpSession session,
			@RequestParam(value = "stab", required = false) String stabId,
			@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "unitaMisura", required = false) Integer unitaMisura) {
		
		try {
			ConsumoDetergentiChartSessionFilter consumoDetergentiChartSessionFilter =  new ConsumoDetergentiChartSessionFilter();
			User usr = (User) session.getAttribute("user");
			consumoDetergentiChartSessionFilter.setUserAuthLevel(usr.getAuthLevel());
			consumoDetergentiChartSessionFilter.setStabId(stabId);
			consumoDetergentiChartSessionFilter.setTxtDateFrom(txtDateFrom);
			consumoDetergentiChartSessionFilter.setTxtDateTo(txtDateTo);
			consumoDetergentiChartSessionFilter.setUnitaMisura(unitaMisura);
			consumoDetergentiChartSessionFilter.setTipo(tipo);
			session.setAttribute("consumoDetergentiChartSessionFilter", consumoDetergentiChartSessionFilter);
			List<DetergentiChartDTO> charts = detergentiService.getCharts(consumoDetergentiChartSessionFilter);

			model.addAttribute("charts", charts);
			
		} catch (Exception e) {
			logger.error("Errore nel metodo lista ore lavorate del controller DetergentiController", e);
		}
		return "consumo-detergenti-charts";
	}

}
