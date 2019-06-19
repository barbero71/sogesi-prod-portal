package it.sogesispa.prod.web.controllers;

import it.sogesispa.prod.web.dto.AttivitaDTO;
import it.sogesispa.prod.web.dto.ImpiantoDTO;
import it.sogesispa.prod.web.dto.MacchinaImpiantoInterventoDTO;
import it.sogesispa.prod.web.dto.LineaDTO;
import it.sogesispa.prod.web.dto.MacchinaDTO;
import it.sogesispa.prod.web.dto.OperatoreDTO;
import it.sogesispa.prod.web.dto.PianificazioneAttivitaDTO;
import it.sogesispa.prod.web.dto.PeriodicitaDTO;
import it.sogesispa.prod.web.dto.RiepilogoPianificazioneDTO;
import it.sogesispa.prod.web.dto.StabilimentoDTO;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.models.jpa.TPlant;
import it.sogesispa.prod.web.reports.ReportElencoAttivita;
import it.sogesispa.prod.web.reports.ReportMacchina;
import it.sogesispa.prod.web.reports.ReportSettimanale;
import it.sogesispa.prod.web.scheduler.Scheduler;
import it.sogesispa.prod.web.services.PianificazioneAttivitaService;
import it.sogesispa.prod.web.utils.ListaAttivitaSessionFilter;
import it.sogesispa.prod.web.utils.ListaPianificazioniSessionFilter;
import it.sogesispa.prod.web.utils.Page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

@Controller
public class ManutenzioneController {
	private static final Logger logger = Logger.getLogger(ManutenzioneController.class);
	private int pagesize = 10;
	@Autowired
	private PianificazioneAttivitaService pianificazioneAttivitaService;
	@Autowired
	private Scheduler scheduler;

	@RequestMapping(value = "/lista-attivita", method = RequestMethod.GET)
	public String listaAttivita(Model model, HttpSession session,
			@RequestParam(value = "cmbMach", required = false) String cmbMach,
			@RequestParam(value = "cmbGroup", required = false) Long groupId,
			@RequestParam(value = "cmbStab", required = false) Long stabId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "sorting", required = false) String sorting,
			@RequestParam(value = "sortingValue", required = false) String sortingValue,
			@RequestParam(value = "eseguite", required = false) String eseguite,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		try {
			manageListaAttivitaSessionFilter(session, cmbMach, groupId, stabId, txtDateFrom, txtDateTo, sorting,
					sortingValue, pageNumber, eseguite);

			ListaAttivitaSessionFilter listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
					.getAttribute("listaAttivitaSessionFilter");
			/*
			 * Collection<AttivitaDTO> lista =
			 * pianificazioneAttivitaService.getListaAttivitaDTO(sMachID,
			 * groupId, stabId, txtDateFrom, txtDateTo,
			 * listaAttivitaSessionFilter );
			 */
			if(pageSize!=null&&pageSize>0)
			{
				this.pagesize = pageSize;
			}
			listaAttivitaSessionFilter.setPageSize(this.pagesize);
			Page paginaAttivita = pianificazioneAttivitaService.getPaginaAttivita(listaAttivitaSessionFilter, this.pagesize);

			String stabilimento = "Tutti";
			if (listaAttivitaSessionFilter.getStabId() != null) {
				TPlant t = pianificazioneAttivitaService.getStabilimento(listaAttivitaSessionFilter.getStabId());
				if (t != null)
					stabilimento = t.getPlantDesc();
			}

			// model.addAttribute("listaAttivita", lista);
			model.addAttribute("stabilimento", stabilimento);
			model.addAttribute("paginaAttivita", paginaAttivita);
		} catch (Exception e) {
			logger.error("Errore nel metodo listaAttivita del controller ManutenzioneController", e);
		}
		return "lista-attivita";
	}

	private void manageListaAttivitaSessionFilter(HttpSession session, String cmbMach, Long groupId, Long stabId,
			Date txtDateFrom, Date txtDateTo, String sorting, String sortingValue, Integer pageNumber, String eseguite) {
		try {
			User usr = (User) session.getAttribute("user");
			ListaAttivitaSessionFilter listaAttivitaSessionFilter = null;

			if (sorting != null) { // provengo dal sort di un campo
				listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
						.getAttribute("listaAttivitaSessionFilter");

				if (sortingValue.equals("prioritaAsc")) {
					listaAttivitaSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("prioritaDesc")) {
					listaAttivitaSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("prioritaNone")) {
					listaAttivitaSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("dataPrevistaAsc")) {
					listaAttivitaSessionFilter.setDataPrevistaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("dataPrevistaDesc")) {
					listaAttivitaSessionFilter.setDataPrevistaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("dataPrevistaNone")) {
					listaAttivitaSessionFilter.setDataPrevistaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("lineaAsc")) {
					listaAttivitaSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("lineaDesc")) {
					listaAttivitaSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("lineaNone")) {
					listaAttivitaSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("macchinaAsc")) {
					listaAttivitaSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("macchinaDesc")) {
					listaAttivitaSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("macchinaNone")) {
					listaAttivitaSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("impiantoAsc")) {
					listaAttivitaSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("impiantoDesc")) {
					listaAttivitaSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("impiantoNone")) {
					listaAttivitaSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_NONE);
				}
				if (sortingValue.equals("pianificazioneAsc")) {
					listaAttivitaSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("pianificazioneDesc")) {
					listaAttivitaSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("pianificazioneNone")) {
					listaAttivitaSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("interventoAsc")) {
					listaAttivitaSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("interventoDesc")) {
					listaAttivitaSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("interventoNone")) {
					listaAttivitaSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("eseguitaAsc")) {
					listaAttivitaSessionFilter.setEseguitaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("eseguitaDesc")) {
					listaAttivitaSessionFilter.setEseguitaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("eseguitaNone")) {
					listaAttivitaSessionFilter.setEseguitaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

			} else {
				String[] sMach = cmbMach != null ? cmbMach.split("[+]") : null;
				long[] sMachID = cmbMach != null ? new long[sMach.length] : null;
				if (sMach != null) {
					for (int i = 0; i < sMach.length; i++) {
						sMachID[i] = Long.parseLong(sMach[i]);
					}
				}
				listaAttivitaSessionFilter = new ListaAttivitaSessionFilter();
				listaAttivitaSessionFilter.setCmbMach(sMachID);
				listaAttivitaSessionFilter.setCmbMachString(cmbMach);
				listaAttivitaSessionFilter.setUserAuthLevel(usr.getAuthLevel());
				listaAttivitaSessionFilter.setGroupId(groupId);
				listaAttivitaSessionFilter.setStabId(stabId);
				listaAttivitaSessionFilter.setTxtDateFrom(txtDateFrom);
				listaAttivitaSessionFilter.setTxtDateTo(txtDateTo);
				listaAttivitaSessionFilter.setEseguite(eseguite);

				session.setAttribute("listaAttivitaSessionFilter", listaAttivitaSessionFilter);
			}

			if (pageNumber != null) {
				listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
						.getAttribute("listaAttivitaSessionFilter");
				listaAttivitaSessionFilter.setPageNumber(pageNumber);
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo manageListaAttivitaSessionFilter del controller ManutenzioneController", e);
		}
	}

	@RequestMapping(value = "/lista-pianificazioni", method = RequestMethod.GET)
	public String listaPianificazioni(Model model, HttpSession session,
			@RequestParam(value = "cmbMach", required = false) String cmbMach,
			@RequestParam(value = "cmbGroup", required = false) Long groupId,
			@RequestParam(value = "cmbStab", required = false) Long stabId,
			@RequestParam(value = "periodicita", required = false) Integer periodicita,
			@RequestParam(value = "sorting", required = false) String sorting,
			@RequestParam(value = "sortingValue", required = false) String sortingValue,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

		try {
			int pagesize = 10;

			manageListaPianificazioniSessionFilter(session, cmbMach, groupId, stabId, periodicita, sorting,
					sortingValue, pageNumber);

			ListaPianificazioniSessionFilter listaPianificazioniSessionFilter = (ListaPianificazioniSessionFilter) session
					.getAttribute("listaPianificazioniSessionFilter");

			Page paginaAttivita = pianificazioneAttivitaService
					.getListaPianificazioneAttivita(listaPianificazioniSessionFilter, pagesize);

			String stabilimento = "Tutti";
			if (listaPianificazioniSessionFilter.getStabId() != null) {
				TPlant t = pianificazioneAttivitaService.getStabilimento(listaPianificazioniSessionFilter.getStabId());
				if (t != null)
					stabilimento = t.getPlantDesc();
			}

			// model.addAttribute("listaPianificazioni", lista);
			model.addAttribute("stabilimento", stabilimento);
			model.addAttribute("paginaPianificazioni", paginaAttivita);
		} catch (Exception e) {
			logger.error("Errore nel metodo listaPianificazioni del controller ManutenzioneController", e);
		}
		return "lista-pianificazioni";
	}

	private void manageListaPianificazioniSessionFilter(HttpSession session, String cmbMach, Long groupId, Long stabId,
			Integer periodicita, String sorting, String sortingValue, Integer pageNumber) {
		try {
			User usr = (User) session.getAttribute("user");
			ListaPianificazioniSessionFilter listaPianificazioniSessionFilter = null;

			if (sorting != null) { // provengo dal sort di un campo
				listaPianificazioniSessionFilter = (ListaPianificazioniSessionFilter) session
						.getAttribute("listaPianificazioniSessionFilter");

				if (sortingValue.equals("prioritaAsc")) {
					listaPianificazioniSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("prioritaDesc")) {
					listaPianificazioniSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("prioritaNone")) {
					listaPianificazioniSessionFilter.setPrioritaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("lineaAsc")) {
					listaPianificazioniSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("lineaDesc")) {
					listaPianificazioniSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("lineaNone")) {
					listaPianificazioniSessionFilter.setLineaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("macchinaAsc")) {
					listaPianificazioniSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("macchinaDesc")) {
					listaPianificazioniSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("macchinaNone")) {
					listaPianificazioniSessionFilter.setMacchinaSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("impiantoAsc")) {
					listaPianificazioniSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("impiantoDesc")) {
					listaPianificazioniSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("impiantoNone")) {
					listaPianificazioniSessionFilter.setImpiantoSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("pianificazioneAsc")) {
					listaPianificazioniSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("pianificazioneDesc")) {
					listaPianificazioniSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("pianificazioneNone")) {
					listaPianificazioniSessionFilter.setPianificazioneSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

				if (sortingValue.equals("interventoAsc")) {
					listaPianificazioniSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_ASC);
				} else if (sortingValue.equals("interventoDesc")) {
					listaPianificazioniSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_DESC);
				} else if (sortingValue.equals("interventoNone")) {
					listaPianificazioniSessionFilter.setInterventoSort(ListaAttivitaSessionFilter.SORT_NONE);
				}

			} else {
				String[] sMach = cmbMach != null ? cmbMach.split("[+]") : null;
				long[] sMachID = cmbMach != null ? new long[sMach.length] : null;
				if (sMach != null) {
					for (int i = 0; i < sMach.length; i++) {
						sMachID[i] = Long.parseLong(sMach[i]);
					}
				}
				listaPianificazioniSessionFilter = new ListaPianificazioniSessionFilter();
				listaPianificazioniSessionFilter.setCmbMach(sMachID);
				listaPianificazioniSessionFilter.setCmbMachString(cmbMach);
				listaPianificazioniSessionFilter.setUserAuthLevel(usr.getAuthLevel());
				listaPianificazioniSessionFilter.setGroupId(groupId);
				listaPianificazioniSessionFilter.setStabId(stabId);
				listaPianificazioniSessionFilter.setPeriodicita(periodicita);
				session.setAttribute("listaPianificazioniSessionFilter", listaPianificazioniSessionFilter);
			}

			if (pageNumber != null) {
				listaPianificazioniSessionFilter = (ListaPianificazioniSessionFilter) session
						.getAttribute("listaPianificazioniSessionFilter");
				listaPianificazioniSessionFilter.setPageNumber(pageNumber);
			}
		} catch (Exception e) {
			logger.error(
					"Errore nel metodo manageListaPianificazioniSessionFilter del controller ManutenzioneController",
					e);
		}
	}

	@RequestMapping(value = "/dettaglio-pianificazione", method = RequestMethod.POST)
	public String paginaDettaglioPianificazione(Model model, HttpSession session, @RequestParam("action") String action,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "data", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date data,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "descrizione", required = false) String descrizione,
			@RequestParam(value = "stabilimento", required = false) Long stabilimento,
			@RequestParam(value = "intervento", required = false) String intervento,
			@RequestParam(value = "linea", required = false) Long linea,
			@RequestParam(value = "macchina", required = false) Long macchina,
			@RequestParam(value = "impianto", required = false) Long impianto,
			@RequestParam(value = "periodicita", required = false) Long periodicita,
			@RequestParam(value = "priorita", required = false) Long priorita) {
		try {
			if (action.equals("paginaDettaglioPianificazioneAction") && id != null) {
				PianificazioneAttivitaDTO patt = pianificazioneAttivitaService.getPianificazioneAttivitaDTO(id);
				model.addAttribute("patt", patt);
				return "dettaglio-pianificazione";
			} else if (action.equals("eliminapianificazione")) {
				return "dettaglio-pianificazione";
			} else if (action.equals("newAttPianificata")) {
				PianificazioneAttivitaDTO patt = new PianificazioneAttivitaDTO();
				model.addAttribute("patt", patt);
				Collection<LineaDTO> linee = pianificazioneAttivitaService.getLinee();
				Collection<StabilimentoDTO> stabilimenti = pianificazioneAttivitaService.getStabilimenti();
				Collection<MacchinaDTO> macchine = pianificazioneAttivitaService.getMacchine();
				Collection<ImpiantoDTO> impianti = pianificazioneAttivitaService.getImpianti();
				model.addAttribute("linee", linee);
				model.addAttribute("stabilimenti", stabilimenti);
				model.addAttribute("macchine", macchine);
				model.addAttribute("impianti", impianti);
				model.addAttribute("mode", "new");
				return "dettaglio-pianificazione";
			} else if (action.equals("forzaEsecuzionePianificazione")) {
				PianificazioneAttivitaDTO patt = new PianificazioneAttivitaDTO();
				model.addAttribute("patt", patt);
				Collection<LineaDTO> linee = pianificazioneAttivitaService.getLinee();
				Collection<StabilimentoDTO> stabilimenti = pianificazioneAttivitaService.getStabilimenti();
				Collection<MacchinaDTO> macchine = pianificazioneAttivitaService.getMacchine();
				Collection<ImpiantoDTO> impianti = pianificazioneAttivitaService.getImpianti();
				model.addAttribute("linee", linee);
				model.addAttribute("stabilimenti", stabilimenti);
				model.addAttribute("macchine", macchine);
				model.addAttribute("impianti", impianti);
				model.addAttribute("mode", "new");
				caricaListaPianificazioni(model, session);
				forzaEsecuzionePianificazione(model, session);  
				return "lista-pianificazioni";
			} else if (action.equals("newAttStraordinaria")) {
				Collection<LineaDTO> linee = pianificazioneAttivitaService.getLinee();
				Collection<StabilimentoDTO> stabilimenti = pianificazioneAttivitaService.getStabilimenti();
				model.addAttribute("linee", linee);
				model.addAttribute("stabilimenti", stabilimenti);
				Collection<MacchinaDTO> macchine = pianificazioneAttivitaService.getMacchine();
				Collection<ImpiantoDTO> impianti = pianificazioneAttivitaService.getImpianti();
				model.addAttribute("macchine", macchine);
				model.addAttribute("impianti", impianti);
				AttivitaDTO patt = new AttivitaDTO();
				patt.setDataPrevista(new Date());
				patt.setDataEsecuzione(new Date());
				model.addAttribute("patt", patt);
				model.addAttribute("mode", "new");
				return "dettaglio-attivita";
			} else {

				if (action.equals("salvaPianificazioneAction") && (id == null || id == 0)) {
					pianificazioneAttivitaService.salvaAttivitaPianificata(stabilimento, linea, macchina, impianto,
							periodicita, data, priorita, nome, descrizione);
				} else if (action.equals("salvaPianificazioneAction") && id != null && id > 0) {
					pianificazioneAttivitaService.aggiornaAttivitaPianificata(id, macchina, impianto,periodicita, data, priorita, nome,
							descrizione);
				} else if (action.equals("eliminaPianificazioneAction") && id != null && id > 0) {
					pianificazioneAttivitaService.eliminaAttivitaPianificata(id);
				}
				
				caricaListaPianificazioni(model, session);

				return "lista-pianificazioni";
				// return "redirect:lista-pianificazioni";
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo paginaDettaglioPianificazione del controller ManutenzioneController", e);
		}
		return null;
	}

	private void forzaEsecuzionePianificazione(Model model, HttpSession session) {
		scheduler.fixedDelayTask(); 
	}

	private void caricaListaPianificazioni(Model model, HttpSession session) {
		ListaPianificazioniSessionFilter listaPianificazioniSessionFilter = (ListaPianificazioniSessionFilter) session
				.getAttribute("listaPianificazioniSessionFilter");

		Page paginaAttivita = pianificazioneAttivitaService
				.getListaPianificazioneAttivita(listaPianificazioniSessionFilter, pagesize);

		String stab = "Tutti";
		if (listaPianificazioniSessionFilter.getStabId() != null) {
			TPlant t = pianificazioneAttivitaService
					.getStabilimento(listaPianificazioniSessionFilter.getStabId());
			if (t != null)
				stab = t.getPlantDesc();
		}

		model.addAttribute("stabilimento", stab);
		model.addAttribute("paginaPianificazioni", paginaAttivita);
		
	}

	@RequestMapping(value = "/lista-attivita", method = RequestMethod.POST)
	public String listaAttivita(Model model, HttpSession session, @RequestParam("action") String action,
			@RequestParam("idAttivita") long idAttivita) {
		try {
			Collection<LineaDTO> linee = pianificazioneAttivitaService.getLinee();
			Collection<StabilimentoDTO> stabilimenti = pianificazioneAttivitaService.getStabilimenti();
			Collection<MacchinaDTO> macchine = pianificazioneAttivitaService.getMacchine();
			Collection<ImpiantoDTO> impianti = pianificazioneAttivitaService.getImpianti();
			Collection<OperatoreDTO> operatori = pianificazioneAttivitaService.getOperatori();
			model.addAttribute("linee", linee);
			model.addAttribute("stabilimenti", stabilimenti);
			model.addAttribute("macchine", macchine);
			model.addAttribute("impianti", impianti);
			model.addAttribute("operatori", operatori);
			if (action.equals("paginaEseguiAction")) {

				AttivitaDTO attivita = pianificazioneAttivitaService.getAttivitaDTO(idAttivita);
				model.addAttribute("attivita", attivita);
				return "dettaglio-attivita";
			} else if (action.equals("paginaRimandaAction")) {
				AttivitaDTO attivita = pianificazioneAttivitaService.getAttivitaDTO(idAttivita);
				model.addAttribute("attivita", attivita);
				return "rimanda-attivita";
			} else if (action.equals("salvaAttivitaAction")) {
				return "lista-attivita";
			} else if (action.equals("annullaAttivitaAction")) {
				return "lista-attivita";
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo listaAttivita del controller ManutenzioneController", e);
		}
		return null;
	}

	@RequestMapping(value = "/attivita", method = RequestMethod.POST)
	public String attivita(Model model, HttpSession session, @RequestParam("action") String action,
			@RequestParam(value = "dataEsecuzione", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataEsecuzione,
			@RequestParam(value = "oreImpiegate", required = false) String oreImpiegate,
			@RequestParam(value = "costi", required = false) String costi,
			@RequestParam(value = "operatore", required = false) Long operatore,
			@RequestParam(value = "dataPrevista", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataPrevista,
			@RequestParam(value = "note", required = false) String note,
			@RequestParam(value = "idAttivita", required = true) Long idAttivita,
			@RequestParam(value = "stabilimento", required = false) Long stabilimento,
			@RequestParam(value = "intervento", required = false) String intervento,
			@RequestParam(value = "linea", required = false) Long linea,
			@RequestParam(value = "macchina", required = false) Long macchina,
			@RequestParam(value = "impianto", required = false) Long impianto, RedirectAttributes redirectAttributes) {
		try {
			if (action.equals("salvaAttivitaAction") && idAttivita != null) {

				pianificazioneAttivitaService.setAttivitaEseguita(idAttivita.longValue(), dataEsecuzione, oreImpiegate,
						costi, operatore, note, dataPrevista);

			} else if (action.equals("salvaAttivitaAction") && idAttivita == null) {

				pianificazioneAttivitaService.salvaAttivita(dataEsecuzione, oreImpiegate, costi, operatore, note,
						dataPrevista, stabilimento, intervento, linea, macchina, impianto);

			} else if (action.equals("annullaAttivitaAction")) {
				//
			}

			ListaAttivitaSessionFilter listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
					.getAttribute("listaAttivitaSessionFilter");

			Page paginaAttivita = pianificazioneAttivitaService.getPaginaAttivita(listaAttivitaSessionFilter, pagesize);

			String stab = "Tutti";
			if (listaAttivitaSessionFilter.getStabId() != null) {
				TPlant t = pianificazioneAttivitaService.getStabilimento(listaAttivitaSessionFilter.getStabId());
				if (t != null)
					stab = t.getPlantDesc();
			}

			model.addAttribute("stabilimento", stab);
			model.addAttribute("paginaAttivita", paginaAttivita);
		} catch (Exception e) {
			logger.error("Errore nel metodo attivita del controller ManutenzioneController", e);
		}
		return "lista-attivita";

	}

	@RequestMapping(value = "/getlinee", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getLinee(HttpSession session, @RequestParam("plant") String plant) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<LineaDTO> machines = new ArrayList<LineaDTO>();

			User usr = (User) session.getAttribute("user");

			machines = pianificazioneAttivitaService.getLinee(plant, usr);

			data.put("groups", machines);
		} catch (Exception e) {
			logger.error("Errore nel metodo getLinee del controller ManutenzioneController", e);
		}
		return data;

	}

	@RequestMapping(value = "/getstabilimenti", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getStabilimenti(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<StabilimentoDTO> machines = new ArrayList<StabilimentoDTO>();

			User usr = (User) session.getAttribute("user");

			machines = pianificazioneAttivitaService.getStabilimenti(usr.getAuthLevel());

			data.put("plants", machines);
		} catch (Exception e) {
			logger.error("Errore nel metodo getStabilimenti del controller ManutenzioneController", e);
		}
		return data;

	}

	@RequestMapping(value = "/gettipimacchina", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMachines(HttpSession session, @RequestParam("plant") String plant,
			@RequestParam("group") String linea) {

		Map<String, Object> data = new HashMap<String, Object>();
		try {

			List<MacchinaDTO> machines = new ArrayList<MacchinaDTO>();

			User usr = (User) session.getAttribute("user");

			machines = pianificazioneAttivitaService.getMachines(linea, plant, usr);

			data.put("machines", machines);
		} catch (Exception e) {
			logger.error("Errore nel metodo getMachines del controller ManutenzioneController", e);
		}
		return data;
	}

	@RequestMapping(value = "/getmacchine", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMacchine(HttpSession session, @RequestParam("plant") String plant,
			@RequestParam("group") String linea) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			List<MacchinaDTO> machines = new ArrayList<MacchinaDTO>();

			User usr = (User) session.getAttribute("user");

			machines = pianificazioneAttivitaService.getMacchineFisiche(linea, plant, usr);

			data.put("machines", machines);
		} catch (Exception e) {
			logger.error("Errore nel metodo getMachines del controller ManutenzioneController", e);
		}
		return data;
	}

	@RequestMapping(value = "/getimpianti", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getImpianti(HttpSession session, @RequestParam("plant") String plant,
			@RequestParam("group") String linea, @RequestParam("macchina") String macchina) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			List<ImpiantoDTO> impianti = new ArrayList<ImpiantoDTO>();

			User usr = (User) session.getAttribute("user");

			impianti = pianificazioneAttivitaService.getImpianti(macchina, usr);

			data.put("impianti", impianti);
		} catch (Exception e) {
			logger.error("Errore nel metodo getMachines del controller ManutenzioneController", e);
		}
		return data;

	}

	@RequestMapping(value = "/report/stampaElencoAttivita", method = RequestMethod.GET)
	public void stanpaElencoAttivita(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "cmbMach", required = false) String cmbMach,
			@RequestParam(value = "cmbGroup", required = false) Long groupId,
			@RequestParam(value = "cmbStab", required = false) Long stabId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "sortingReport", required = false) String sorting,
			@RequestParam(value = "eseguite", required = false) String eseguite,
			@RequestParam(value = "sortingValueReport", required = false) String sortingValue,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		try {
			manageListaAttivitaSessionFilter(session, cmbMach, groupId, stabId, txtDateFrom, txtDateTo, sorting,
					sortingValue, pageNumber, eseguite);

			ListaAttivitaSessionFilter listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
					.getAttribute("listaAttivitaSessionFilter");
			Collection<AttivitaDTO> lista = pianificazioneAttivitaService
					.getListaAttivitaDTO(listaAttivitaSessionFilter);
			String stabilimento = "Tutti";
			if (listaAttivitaSessionFilter.getStabId() != null) {
				TPlant t = pianificazioneAttivitaService.getStabilimento(listaAttivitaSessionFilter.getStabId());
				if (t != null)
					stabilimento = t.getPlantDesc();
			}
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss");
				String filename = "ElencoAttivita_" + sdf.format(new Date()) + ".pdf";
				ReportElencoAttivita report = new ReportElencoAttivita();
				User usr = (User) session.getAttribute("user");
				String operatore = usr.getFirstName() + " " + usr.getLastName();
				report.createPdf(lista, stabilimento, operatore, null, response.getOutputStream());
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=" + filename);
				response.flushBuffer();
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			} catch (DocumentException e) {
				logger.error("Errore nel metodo stanpaElencoAttivita del controller ManutenzioneController", e);
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo stanpaElencoAttivita del controller ManutenzioneController", e);
		}
	}

	@RequestMapping(value = "/report/stampaPianoSettimanale", method = RequestMethod.GET)
	public void stanpaPianoSettimanale(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "cmbMach", required = false) String cmbMach,
			@RequestParam(value = "cmbGroup", required = false) Long groupId,
			@RequestParam(value = "txtcmbStab", required = false) Long stabId,
			@RequestParam(value = "txtdateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateTo,
			@RequestParam(value = "sortingReport", required = false) String sorting,
			@RequestParam(value = "eseguite", required = false) String eseguite,
			@RequestParam(value = "sortingValueReport", required = false) String sortingValue,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		try {
			manageListaAttivitaSessionFilter(session, cmbMach, groupId, stabId, txtDateFrom, txtDateTo, sorting,
					sortingValue, pageNumber, eseguite);

			ListaAttivitaSessionFilter listaAttivitaSessionFilter = (ListaAttivitaSessionFilter) session
					.getAttribute("listaAttivitaSessionFilter");

			Calendar cal = Calendar.getInstance();
			cal.setTime(listaAttivitaSessionFilter.getTxtDateFrom());

			// "calculate" the start date of the week
			Calendar first = (Calendar) cal.clone();
			first.add(Calendar.DAY_OF_WEEK, first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

			// and add six days to the end date
			Calendar last = (Calendar) first.clone();
			last.add(Calendar.DAY_OF_YEAR, 6);

			listaAttivitaSessionFilter.setTxtDateFrom(first.getTime());
			listaAttivitaSessionFilter.setTxtDateTo(last.getTime());
			listaAttivitaSessionFilter.setDataPrevistaSort(0);
			listaAttivitaSessionFilter.setNomeIstanzaMacchinaSort(1);
			Collection<AttivitaDTO> lista = pianificazioneAttivitaService
					.getListaAttivitaDTO(listaAttivitaSessionFilter);
			String stabilimento = "Tutti";
			if (listaAttivitaSessionFilter.getStabId() != null) {
				TPlant t = pianificazioneAttivitaService.getStabilimento(listaAttivitaSessionFilter.getStabId());
				if (t != null)
					stabilimento = t.getPlantDesc();
			}
			try {
				@SuppressWarnings("unused")
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

				ReportSettimanale report = new ReportSettimanale();
				User usr = (User) session.getAttribute("user");
				String operatore = usr.getFirstName() + " " + usr.getLastName();
				String header = "Settimana dal " + sdf2.format(first.getTime()) + " al " + sdf2.format(last.getTime());
				String filename = "PianoSettimanale_" + header + ".pdf";
				report.createPdf(lista, stabilimento, operatore, header,first.getTime(),last.getTime(), response.getOutputStream());
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=" + filename);
				response.flushBuffer();
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			} catch (DocumentException e) {
				logger.error("Errore nel metodo stanpaPianoSettimanale del controller ManutenzioneController", e);
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo stanpaPianoSettimanale del controller ManutenzioneController", e);
		}
	}

	@RequestMapping(value = "/report/stampaGiornoMacchina", method = RequestMethod.GET)
	public void stanpaGiornoMacchina(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "repcmbMach", required = false) String cmbMach,
			@RequestParam(value = "repcmbStab", required = false) Long stabId,
			@RequestParam(value = "replinea", required = false) Long linea,
			@RequestParam(value = "repdateFrom", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date txtDateFrom) {

		try {
			User usr = (User) session.getAttribute("user");
			Collection<MacchinaImpiantoInterventoDTO> lista = pianificazioneAttivitaService
					.getListaImopiantiIntervento(cmbMach, stabId, linea, txtDateFrom, usr.getAuthLevel());

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss");
				@SuppressWarnings("unused")
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

				ReportMacchina report = new ReportMacchina();
				@SuppressWarnings("unused")
				String operatore = usr.getFirstName() + " " + usr.getLastName();
				String filename = "ElencoAttivita_" + sdf.format(txtDateFrom) + ".pdf";
				report.createPdf(lista, txtDateFrom, response.getOutputStream());
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=" + filename);
				response.flushBuffer();
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			} catch (DocumentException e) {
				logger.error("Errore nel metodo stanpaGiornoMacchina del controller ManutenzioneController", e);
			}
		} catch (Exception e) {
			logger.error("Errore nel metodo stanpaGiornoMacchina del controller ManutenzioneController", e);
		}
	}

	@RequestMapping(value = "/getriepilogopianificazione", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getRiepilogoPianificazione(HttpSession session,
			@RequestParam("stabilimento") String stabilimento, @RequestParam("linea") String linea,
			@RequestParam("macchina") String macchina) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			List<RiepilogoPianificazioneDTO> r = new ArrayList<RiepilogoPianificazioneDTO>();

			r = pianificazioneAttivitaService.getRiepilogoPianificazioni(stabilimento, linea, macchina);

			data.put("riepilogo", r);
		} catch (Exception e) {
			logger.error("Errore nel metodo getRiepilogoPianificazione del controller ManutenzioneController", e);
		}
		return data;
	}
	
	@RequestMapping(value = "/getperiodicita", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getPeriodicita(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			List<PeriodicitaDTO> r = new ArrayList<PeriodicitaDTO>();

			r = pianificazioneAttivitaService.getPeriodicita();

			data.put("periodicita", r);
		} catch (Exception e) {
			logger.error("Errore nel metodo getRiepilogoPianificazione del controller ManutenzioneController", e);
		}
		return data;
	}
}
