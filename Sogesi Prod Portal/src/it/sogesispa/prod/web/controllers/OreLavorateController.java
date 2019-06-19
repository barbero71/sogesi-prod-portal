package it.sogesispa.prod.web.controllers;

import it.sogesispa.prod.web.dto.OreChartDTO;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.OreLavorateService;
import it.sogesispa.prod.web.utils.ListaOreLavorateSessionFilter;
import it.sogesispa.prod.web.utils.OreLavorateChartSessionFilter;
import it.sogesispa.prod.web.utils.Page;

import java.util.Date;
import java.util.List;

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
public class OreLavorateController {
	private static final Logger logger = Logger.getLogger(OreLavorateController.class);
	private int pagesize = 9999;
	@Autowired
	private OreLavorateService oreLavorateAttivitaService;

	@RequestMapping(value = "/ore-lavorate", method = RequestMethod.GET)
	public String listaOreLavorate(Model model, HttpSession session,
			@RequestParam(value = "stab", required = false) String stabId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "tipoore", required = false) String tipoore,
			@RequestParam(value = "sorting", required = false) String sorting,
			@RequestParam(value = "sortingValue", required = false) String sortingValue,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		
		try {
			manageOreLavorateSessionFilter(session, stabId, txtDateFrom, txtDateTo, tipoore, sorting,
					sortingValue, pageNumber);

			ListaOreLavorateSessionFilter listaOreLavorateSessionFilter = (ListaOreLavorateSessionFilter) session
					.getAttribute("listaOreLavorateSessionFilter");
			
			Page paginaAttivita = oreLavorateAttivitaService.getPaginaOre(listaOreLavorateSessionFilter, pagesize);

			model.addAttribute("paginaOre", paginaAttivita);
			
		} catch (Exception e) {
			logger.error("Errore nel metodo lista ore lavorate del controller OreLavorateController", e);
		}
		return "ore-lavorate";
	}

	private void manageOreLavorateSessionFilter(HttpSession session, String stabId, Date txtDateFrom, Date txtDateTo, String tipoore,
			String sorting, String sortingValue, Integer pageNumber) {
		try {
			User usr = (User) session.getAttribute("user");
			ListaOreLavorateSessionFilter listaOreLavorateSessionFilter = null;

			if (sorting != null) { // provengo dal sort di un campo
				listaOreLavorateSessionFilter = (ListaOreLavorateSessionFilter) session
						.getAttribute("listaOreLavorateSessionFilter");

				if (sortingValue.equals("stabilimentoSortAsc")) {
					listaOreLavorateSessionFilter.setStabilimentoSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("stabilimentoSortDesc")) {
					listaOreLavorateSessionFilter.setStabilimentoSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("stabilimentoSortNone")) {
					listaOreLavorateSessionFilter.setStabilimentoSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("oreLavorateOrdinarieSortAsc")) {
					listaOreLavorateSessionFilter.setOreLavorateOrdinarieSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("oreLavorateOrdinarieSortDesc")) {
					listaOreLavorateSessionFilter.setOreLavorateOrdinarieSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("oreLavorateOrdinarieSortNone")) {
					listaOreLavorateSessionFilter.setOreLavorateOrdinarieSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("oreStraordinarioSortAsc")) {
					listaOreLavorateSessionFilter.setOreStraordinarioSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("oreStraordinarioSortDesc")) {
					listaOreLavorateSessionFilter.setOreStraordinarioSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("oreStraordinarioSortNone")) {
					listaOreLavorateSessionFilter.setOreStraordinarioSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("oreFerieSortAsc")) {
					listaOreLavorateSessionFilter.setOreFerieSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("oreFerieSortDesc")) {
					listaOreLavorateSessionFilter.setOreFerieSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("oreFerieSortNone")) {
					listaOreLavorateSessionFilter.setOreFerieSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("oreMaternitaSortAsc")) {
					listaOreLavorateSessionFilter.setOreMaternitaSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("oreMaternitaSortDesc")) {
					listaOreLavorateSessionFilter.setOreMaternitaSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("oreMaternitaSortNone")) {
					listaOreLavorateSessionFilter.setOreMaternitaSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("malattieSortAsc")) {
					listaOreLavorateSessionFilter.setMalattieSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("malattieSortDesc")) {
					listaOreLavorateSessionFilter.setMalattieSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("malattieSortNone")) {
					listaOreLavorateSessionFilter.setMalattieSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("infortuniSortAsc")) {
					listaOreLavorateSessionFilter.setInfortuniSort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("infortuniSortDesc")) {
					listaOreLavorateSessionFilter.setInfortuniSort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("infortuniSortNone")) {
					listaOreLavorateSessionFilter.setInfortuniSort(ListaOreLavorateSessionFilter.SORT_NONE);
				}
				
				if (sortingValue.equals("legge104SortAsc")) {
					listaOreLavorateSessionFilter.setLegge104Sort(ListaOreLavorateSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("legge104SortDesc")) {
					listaOreLavorateSessionFilter.setLegge104Sort(ListaOreLavorateSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("legge104SortNone")) {
					listaOreLavorateSessionFilter.setLegge104Sort(ListaOreLavorateSessionFilter.SORT_NONE);
				}

			} else {
				
				listaOreLavorateSessionFilter = new ListaOreLavorateSessionFilter();
				
				listaOreLavorateSessionFilter.setUserAuthLevel(usr.getAuthLevel());
				listaOreLavorateSessionFilter.setStabId(stabId);
				listaOreLavorateSessionFilter.setTxtDateFrom(txtDateFrom);
				listaOreLavorateSessionFilter.setTxtDateTo(txtDateTo);
				listaOreLavorateSessionFilter.setTipoore(tipoore);
				session.setAttribute("listaOreLavorateSessionFilter", listaOreLavorateSessionFilter);
			}

			if (pageNumber != null) {
				listaOreLavorateSessionFilter = (ListaOreLavorateSessionFilter) session
						.getAttribute("listaOreLavorateSessionFilter");
				listaOreLavorateSessionFilter.setPageNumber(pageNumber);
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo manageOreLavorateSessionFilter", e);
		}
	}
	
	@RequestMapping(value = "/ore-lavorate-charts", method = RequestMethod.GET)
	public String chartsOreLavorate(Model model, HttpSession session,
			@RequestParam(value = "stab", required = false) String stabId,
			@RequestParam(value = "tipoore", required = false) String tipoore,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "unitaMisura", required = false) Integer unitaMisura) {
		
		try {
			OreLavorateChartSessionFilter oreLavorateChartSessionFilter =  new OreLavorateChartSessionFilter();
			User usr = (User) session.getAttribute("user");
			oreLavorateChartSessionFilter.setUserAuthLevel(usr.getAuthLevel());
			oreLavorateChartSessionFilter.setStabId(stabId);
			oreLavorateChartSessionFilter.setTxtDateFrom(txtDateFrom);
			oreLavorateChartSessionFilter.setTxtDateTo(txtDateTo);
			oreLavorateChartSessionFilter.setUnitaMisura(unitaMisura);
			oreLavorateChartSessionFilter.setTipoore(tipoore);
			session.setAttribute("oreLavorateChartSessionFilter", oreLavorateChartSessionFilter);
			List<OreChartDTO> charts = oreLavorateAttivitaService.getOreCharts(oreLavorateChartSessionFilter);

			model.addAttribute("charts", charts);
			
		} catch (Exception e) {
			logger.error("Errore nel metodo lista ore lavorate del controller OreLavorateController", e);
		}
		return "ore-lavorate-charts";
	}

}
