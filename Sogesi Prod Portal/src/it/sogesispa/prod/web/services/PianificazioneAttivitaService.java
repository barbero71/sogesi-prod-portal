package it.sogesispa.prod.web.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.sogesispa.prod.web.dto.AttivitaDTO;
import it.sogesispa.prod.web.dto.ImpiantoDTO;
import it.sogesispa.prod.web.dto.MacchinaImpiantoInterventoDTO;
import it.sogesispa.prod.web.dto.MacchinaImpiantoInterventoDTO.ImpiantoInterventoDTO;
import it.sogesispa.prod.web.dto.LineaDTO;
import it.sogesispa.prod.web.dto.MacchinaDTO;
import it.sogesispa.prod.web.dto.OperatoreDTO;
import it.sogesispa.prod.web.dto.PianificazioneAttivitaDTO;
import it.sogesispa.prod.web.dto.PeriodicitaDTO;
import it.sogesispa.prod.web.dto.RiepilogoPianificazioneDTO;
import it.sogesispa.prod.web.dto.StabilimentoDTO;
import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.models.jpa.PaAttivita;
import it.sogesispa.prod.web.models.jpa.PaImpianti;
import it.sogesispa.prod.web.models.jpa.PaLinea;
import it.sogesispa.prod.web.models.jpa.PaMacchine;
import it.sogesispa.prod.web.models.jpa.PaPeriodicita;
import it.sogesispa.prod.web.models.jpa.PaPianificazioni;
import it.sogesispa.prod.web.models.jpa.PaPriorita;
import it.sogesispa.prod.web.models.jpa.PaTipiMacchina;
import it.sogesispa.prod.web.models.jpa.TPlant;
import it.sogesispa.prod.web.models.jpa.TUser;
import it.sogesispa.prod.web.scheduler.Scheduler;
import it.sogesispa.prod.web.utils.ListaAttivitaSessionFilter;
import it.sogesispa.prod.web.utils.ListaPianificazioniSessionFilter;
import it.sogesispa.prod.web.utils.Page;
import it.sogesispa.prod.web.utils.SchedulerUtil;
import it.sogesispa.prod.web.utils.UserPlantIdsUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pianificazioneAttivitaService")
@Transactional
public class PianificazioneAttivitaService {

	@PersistenceContext
	private EntityManager emf;

	public List<PaAttivita> getListaAttivita() {
		@SuppressWarnings("unchecked")
		List<PaAttivita> ret = emf.createQuery("SELECT c FROM PaAttivita c").getResultList();
		return ret;
	}

	public PaAttivita getAttivita(Long id) {
		PaAttivita ret = emf.find(PaAttivita.class, id);
		return ret;
	}

	public Collection<AttivitaDTO> getListaAttivitaDTO() {
		List<PaAttivita> l = this.getListaAttivita();
		Collection<AttivitaDTO> ret = new ArrayList<AttivitaDTO>();
		for (PaAttivita p : l) {
			AttivitaDTO a = convert2DTO(p);
			ret.add(a);
		}
		return ret;
	}

	public AttivitaDTO getAttivitaDTO(long idAttivita) {
		PaAttivita p = getAttivita(idAttivita);
		return convert2DTO(p);
	}

	public void setAttivitaEseguita(long idAttivita, Date dataEsecuzione, String oreImpiegate, String costi,
			Long operatore, String note, Date dataPrevista) {
		PaAttivita p = getAttivita(idAttivita);
		if (p != null) {
			if (dataEsecuzione != null)
				p.setDataEsecuzione(dataEsecuzione);

			if (oreImpiegate != null)
				p.setOre(oreImpiegate);
			if (costi != null)
				p.setCosti(costi);

			if (operatore != null) {
				TUser t = emf.find(TUser.class, operatore);
				p.setOperatore(t);
			}

			if (dataPrevista != null)
				p.setDataPrevista(dataPrevista);
			if (note != null)
				p.setNote(note);
			// .....

			emf.merge(p);
		}
	}

	private AttivitaDTO convert2DTO(PaAttivita p) {
		AttivitaDTO a = new AttivitaDTO();
		if (p != null) {
			a.setDataPrevista(p.getDataPrevista());
			a.setEseguita(p.getDataEsecuzione() != null ? true : false);
			a.setIdAttivita(p.getId());
			a.setImpianto(p.getPaImpianti().getDescrizione());
			a.setIntervento(p.getDescrizioneIntt());
			a.setNomeIntervento(p.getNomeInt());
			a.setIdImpianto(p.getPaImpianti().getId());
			a.setLinea(p.getPaMacchine().getPaLinea().getDescrizione());
			a.setIdLinea(p.getPaMacchine().getPaLinea().getId());

			a.setMacchina(p.getPaMacchine().getDescrizione());
			a.setIdMacchina(p.getPaMacchine().getId());

			a.setPriorita(p.getPaPriorita().getLivello());

			if (p.getPaPianificazioni() != null) {
				a.setTipoPianificazione(p.getPaPianificazioni().getPaPeriodicita().getDescrizione());
			} else {
				a.setTipoPianificazione("-");
			}

			a.setStabilimento(p.getPaMacchine().getTPlant().getPlantDesc());
			a.setIdStabilimento(p.getPaMacchine().getTPlant().getPlantId());

			a.setDataEsecuzione(p.getDataEsecuzione());
			a.setNote(p.getNote());
			if (p.getOperatore() != null) {
				a.setOperatore(p.getOperatore().getUserId());
				a.setNomeOperatore(p.getOperatore().getFirstName() + " " + p.getOperatore().getLastName());
			}
			a.setCosti(p.getCosti());
			a.setOreImpiegate(p.getOre());
		}
		return a;
	}

	private PianificazioneAttivitaDTO convert2DTO(PaPianificazioni p) {
		PianificazioneAttivitaDTO a = new PianificazioneAttivitaDTO();
		if (p != null) {
			a.setId(p.getId());
			a.setCodPeriodicita(p.getPaPeriodicita().getId());
			a.setCodPriorita(p.getPaPriorita().getId());
			a.setDataInizio(p.getDataPrimoIntervento());
			a.setDescrizione(p.getDescrizioneInt());
			a.setImpianto(p.getPaImpianti().getDescrizione());
			a.setIntervento(p.getDescrizioneInt());
			a.setLinea(p.getPaMacchine().getPaLinea().getDescrizione());
			a.setMacchina(p.getPaMacchine().getDescrizione());
			a.setNome(p.getDescrizioneInt());
			a.setPeriodicita(p.getPaPeriodicita().getDescrizione());
			a.setPriorita(p.getPaPriorita().getLivello());
			a.setStabilimento(p.getPaMacchine().getTPlant().getPlantDesc());
			a.setTipoPianificazione(p.getPaPeriodicita().getDescrizione());
			a.setIdImpianto(p.getPaImpianti().getId());
			a.setIdLinea(p.getPaMacchine().getPaLinea().getId());
			a.setIdMacchina(p.getPaMacchine().getId());
			a.setIdStabilimento(p.getPaMacchine().getTPlant().getPlantId());
		}
		return a;
	}

	public Collection<AttivitaDTO> getListaAttivitaDTO(ListaAttivitaSessionFilter listaAttivitaSessionFilter) {
		List<PaAttivita> l = this.getListaAttivita(listaAttivitaSessionFilter);
		Collection<AttivitaDTO> ret = new ArrayList<AttivitaDTO>();
		for (PaAttivita p : l) {
			AttivitaDTO a = convert2DTO(p);
			ret.add(a);
		}
		return ret;
	}

	public Page getPaginaAttivita(ListaAttivitaSessionFilter listaAttivitaSessionFilter, int pageSize) {
		return this.getListaAttivitaPaged(listaAttivitaSessionFilter, pageSize);
	}

	public Page getListaAttivitaPaged(ListaAttivitaSessionFilter listaAttivitaSessionFilter, int pageSize) {
		Page result;

		int pageNumber = listaAttivitaSessionFilter.getPageNumber();
		// int pageSize = listaAttivitaSessionFilter.getPageSize();
		long[] cmbMach = listaAttivitaSessionFilter.getCmbMach();
		Long groupId = listaAttivitaSessionFilter.getGroupId();
		Long stabId = listaAttivitaSessionFilter.getStabId();
		Date dateFrom = listaAttivitaSessionFilter.getTxtDateFrom();
		Date dateTo = listaAttivitaSessionFilter.getTxtDateTo();

		String selectStr = " SELECT c";

		String contaStr = " select count(c)  ";

		String query = " FROM PaAttivita c where 1 = 1 ";
		if(listaAttivitaSessionFilter.getEseguite()!=null&&!listaAttivitaSessionFilter.getEseguite().equalsIgnoreCase("ALL"))
		{
			if(listaAttivitaSessionFilter.getEseguite().equalsIgnoreCase("SI"))
			{
				query = query + " and c.dataEsecuzione is not null ";
			} else {
				query = query + " and c.dataEsecuzione is null ";
			}
		}
		if (stabId != null && stabId != 999) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + stabId + ")  ";
		} else {
			List<Long> lid = UserPlantIdsUtil.getPlantIds(listaAttivitaSessionFilter.getUserAuthLevel());
			String query2add = " and (c.paMacchine.TPlant.plantId in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : lid) {
				if (l > 0) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		if (groupId != null && groupId != 999)
			query = query + " and (c.paMacchine.paLinea.id = " + groupId + ")  ";
		if (cmbMach != null) {
			String query2add = " and (c.paMacchine.paTipiMacchina.id in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : cmbMach) {
				if (l != 999) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		String txtDateFrom = dateFrom != null ? s.format(dateFrom) : null;
		String txtDateTo = dateTo != null ? s.format(dateTo) : null;
		if (txtDateFrom != null)
			query = query + " and (c.dataPrevista >= to_date('" + txtDateFrom + "','DD-MM-YYYY'))  ";
		if (txtDateTo != null)
			query = query + " and (c.dataPrevista <= to_date('" + txtDateTo + "','DD-MM-YYYY'))";

		// ordinamento
		String orderStr = "order by ";
		List<String> orderByClauseList = new ArrayList<String>();
		if (listaAttivitaSessionFilter.getDataPrevistaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.dataPrevista asc ");
		} else if (listaAttivitaSessionFilter.getDataPrevistaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.dataPrevista desc ");
		}
		if (listaAttivitaSessionFilter.getLineaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione asc ");
		} else if (listaAttivitaSessionFilter.getLineaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione desc ");
		}

		if (listaAttivitaSessionFilter.getMacchinaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id asc ");
		} else if (listaAttivitaSessionFilter.getMacchinaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id desc ");
		}
		if (listaAttivitaSessionFilter.getImpiantoSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paImpianti.id asc ");
		} else if (listaAttivitaSessionFilter.getImpiantoSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paImpianti.id desc ");
		}
		if (listaAttivitaSessionFilter.getPrioritaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPriorita.id asc ");
		} else if (listaAttivitaSessionFilter.getPrioritaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPriorita.id desc ");
		}
		if (listaAttivitaSessionFilter.getPianificazioneSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPianificazioni.id asc ");
		} else if (listaAttivitaSessionFilter.getPianificazioneSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPianificazioni.id desc ");
		}
		if (listaAttivitaSessionFilter.getInterventoSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.descrizioneIntt asc ");
		} else if (listaAttivitaSessionFilter.getInterventoSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.descrizioneIntt desc ");
		}
		if (listaAttivitaSessionFilter.getEseguitaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.dataEsecuzione asc ");
		} else if (listaAttivitaSessionFilter.getEseguitaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.dataEsecuzione desc ");
		}

		if (orderByClauseList.size() == 0) {
			orderByClauseList.add(" c.id desc ");
		}
		for (int i = 0; i < orderByClauseList.size(); i++) {
			orderStr += orderByClauseList.get(i);
			if (i < orderByClauseList.size() - 1) {
				orderStr += ", ";
			}
		}
		// fine ordinamento

		query = query + orderStr;

		selectStr += query;

		contaStr += query;
		Query contaQuery = emf.createQuery(contaStr);

		long totalRows = (Long) contaQuery.getResultList().get(0);
		int totalPages = (int) ((totalRows - 1) / pageSize + 1);
		if (totalPages < pageNumber) {
			pageNumber = totalPages;
		}

		Query selectQuery = emf.createQuery(selectStr);
		selectQuery.setFirstResult((pageNumber - 1) * pageSize);
		selectQuery.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<PaAttivita> ret = selectQuery.getResultList();

		List<AttivitaDTO> dtoList = new ArrayList<AttivitaDTO>();
		for (PaAttivita p : ret) {
			AttivitaDTO a = convert2DTO(p);
			dtoList.add(a);
		}

		result = new Page(dtoList, pageNumber, pageSize, totalRows);

		return result;
	}

	public List<PaAttivita> getListaAttivita(ListaAttivitaSessionFilter listaAttivitaSessionFilter) {
		String query = "SELECT c FROM PaAttivita c where 1 = 1 ";
		if (listaAttivitaSessionFilter.getStabId() != null && listaAttivitaSessionFilter.getStabId() != 999) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + listaAttivitaSessionFilter.getStabId() + ")  ";
		} else {
			List<Long> lid = UserPlantIdsUtil.getPlantIds(listaAttivitaSessionFilter.getUserAuthLevel());
			String query2add = " and (c.paMacchine.TPlant.plantId in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : lid) {
				if (l > 0) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		if (listaAttivitaSessionFilter.getGroupId() != null && listaAttivitaSessionFilter.getGroupId() != 999)
			query = query + " and (c.paMacchine.paLinea.id = " + listaAttivitaSessionFilter.getGroupId()
					+ ")  ";
		if (listaAttivitaSessionFilter.getCmbMach() != null) {
			String query2add = " and (c.paMacchine.paTipiMacchina.id in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : listaAttivitaSessionFilter.getCmbMach()) {
				if (l != 999) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		String txtDateFrom = listaAttivitaSessionFilter.getTxtDateFrom() != null
				? s.format(listaAttivitaSessionFilter.getTxtDateFrom()) : null;
		String txtDateTo = listaAttivitaSessionFilter.getTxtDateTo() != null
				? s.format(listaAttivitaSessionFilter.getTxtDateTo()) : null;
		if (txtDateFrom != null)
			query = query + " and (c.dataPrevista >= to_date('" + txtDateFrom + "','DD-MM-YYYY'))  ";
		if (txtDateTo != null)
			query = query + " and (c.dataPrevista <= to_date('" + txtDateTo + "','DD-MM-YYYY'))";

		// ordinamento
		String orderStr = "order by ";
		List<String> orderByClauseList = new ArrayList<String>();
		if (listaAttivitaSessionFilter.getDataPrevistaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.dataPrevista asc ");
		} else if (listaAttivitaSessionFilter.getDataPrevistaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.dataPrevista desc ");
		}
		if (listaAttivitaSessionFilter.getLineaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione asc ");
		} else if (listaAttivitaSessionFilter.getLineaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione desc ");
		}
		if (listaAttivitaSessionFilter.getMacchinaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id asc ");
		} else if (listaAttivitaSessionFilter.getMacchinaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id desc ");
		}
		if (listaAttivitaSessionFilter.getImpiantoSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paImpianti.id asc ");
		} else if (listaAttivitaSessionFilter.getImpiantoSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paImpianti.id desc ");
		}
		if (listaAttivitaSessionFilter.getPrioritaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPriorita.id asc ");
		} else if (listaAttivitaSessionFilter.getPrioritaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPriorita.id desc ");
		}
		if (listaAttivitaSessionFilter.getPianificazioneSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPianificazioni.id asc ");
		} else if (listaAttivitaSessionFilter.getPianificazioneSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPianificazioni.id desc ");
		}
		if (listaAttivitaSessionFilter.getInterventoSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.descrizioneIntt.id asc ");
		} else if (listaAttivitaSessionFilter.getInterventoSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.descrizioneIntt.id desc ");
		}
		if (listaAttivitaSessionFilter.getEseguitaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.dataEsecuzione asc ");
		} else if (listaAttivitaSessionFilter.getEseguitaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.dataEsecuzione desc ");
		}
		if (listaAttivitaSessionFilter.getNomeIstanzaMacchinaSort() == ListaAttivitaSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.id asc ");
		} else if (listaAttivitaSessionFilter.getNomeIstanzaMacchinaSort() == ListaAttivitaSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.id desc ");
		}

		if (orderByClauseList.size() == 0) {
			orderByClauseList.add(" c.id desc ");
		}
		for (int i = 0; i < orderByClauseList.size(); i++) {
			orderStr += orderByClauseList.get(i);
			if (i < orderByClauseList.size() - 1) {
				orderStr += ", ";
			}
		}
		// fine ordinamento

		query = query + orderStr;
		Query q = emf.createQuery(query);
		@SuppressWarnings("unchecked")
		List<PaAttivita> ret = q.getResultList();
		return ret;
	}

	public TPlant getStabilimento(Long stabId) {
		return emf.find(TPlant.class, stabId);
	}

	public Collection<PianificazioneAttivitaDTO> getListaPianificazioneAttivitaDTO(long[] cmbMach, Long groupId,
			Long stabId, Integer periodicita, int userAuthLevel) {
		List<PaPianificazioni> l = this.getListaPianificazioneAttivita(cmbMach, groupId, stabId, periodicita,
				userAuthLevel);
		Collection<PianificazioneAttivitaDTO> ret = new ArrayList<PianificazioneAttivitaDTO>();
		for (PaPianificazioni p : l) {
			PianificazioneAttivitaDTO a = convert2DTO(p);
			ret.add(a);
		}
		return ret;
	}

	public List<PaPianificazioni> getListaPianificazioneAttivita(long[] cmbMach, Long groupId, Long stabId,
			Integer periodicita, int userAuthLevel) {
		String query = "SELECT c FROM PaPianificazioni c where 1 = 1 and c.deleted = 0 ";
		if (stabId != null && stabId != 999) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + stabId + ")  ";
		} else {
			List<Long> lid = UserPlantIdsUtil.getPlantIds(userAuthLevel);
			String query2add = " and (c.paMacchine.TPlant.plantId in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : lid) {
				if (l > 0) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		if (groupId != null && groupId != 999)
			query = query + " and (c.paMacchine.paLinea.id = " + groupId + ")  ";
		if (cmbMach != null) {
			String query2add = " and (c.paMacchine.paTipiMacchina.id in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : cmbMach) {
				if (l != 999) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}

		if (periodicita != null && periodicita > 0)
			query = query + " and (c.paPeriodicita.id = " + periodicita + ")  ";

		query = query + " order by c.id desc";
		Query q = emf.createQuery(query);
		@SuppressWarnings("unchecked")
		List<PaPianificazioni> ret = q.getResultList();
		return ret;
	}

	public PianificazioneAttivitaDTO getPianificazioneAttivitaDTO(long id) {
		PaPianificazioni p = emf.find(PaPianificazioni.class, id);
		return convert2DTO(p);
	}

	public void creaAttivita() {
		// TODO Auto-generated method stub

	}

	public Collection<LineaDTO> getLinee() {
		@SuppressWarnings("unchecked")
		List<PaLinea> linee = emf.createQuery("SELECT c FROM PaLinea c").getResultList();
		Collection<LineaDTO> ret = new ArrayList<>();
		for (PaLinea p : linee) {
			LineaDTO l = new LineaDTO();
			l.setId(p.getId());
			l.setName(p.getDescrizione());
			ret.add(l);
		}
		return ret;
	}

	public Collection<StabilimentoDTO> getStabilimenti() {
		@SuppressWarnings("unchecked")
		List<TPlant> stabilimenti = emf.createQuery("SELECT c FROM TPlant c").getResultList();
		Collection<StabilimentoDTO> ret = new ArrayList<>();
		for (TPlant p : stabilimenti) {
			StabilimentoDTO l = new StabilimentoDTO();
			l.setId(p.getPlantId());
			l.setName(p.getPlantDesc());
			l.setCdc(p.getPlantCdc());
			ret.add(l);
		}
		return ret;
	}

	public Collection<MacchinaDTO> getMacchine() {
		@SuppressWarnings("unchecked")
		List<PaTipiMacchina> list = emf.createQuery("SELECT c FROM PaTipiMacchina c").getResultList();
		Collection<MacchinaDTO> ret = new ArrayList<>();
		for (PaTipiMacchina p : list) {
			MacchinaDTO l = new MacchinaDTO();
			l.setId(p.getId());
			l.setName(p.getDescrizione());
			ret.add(l);
		}
		return ret;
	}

	public Collection<ImpiantoDTO> getImpianti() {
		@SuppressWarnings("unchecked")
		List<PaImpianti> list = emf.createQuery("SELECT c FROM PaImpianti c").getResultList();
		Collection<ImpiantoDTO> ret = new ArrayList<>();
		for (PaImpianti p : list) {
			ImpiantoDTO l = new ImpiantoDTO();
			l.setId(p.getId());
			l.setName(p.getDescrizione());
			ret.add(l);
		}
		return ret;
	}

	public void salvaAttivita(Date dataEsecuzione, String oreImpiegate, String costi, Long operatore, String note,
			Date dataPrevista, Long stabilimento, String intervento, Long linea, Long macchina, Long impianto) {
		PaAttivita p = new PaAttivita();
		p.setDataEsecuzione(dataEsecuzione);
		p.setDataPrevista(dataPrevista);
		p.setDescrizioneIntt(intervento);
		p.setNomeInt(intervento);
		p.setNote(note);
		try {
			PaImpianti paImpianti = emf.find(PaImpianti.class, impianto);
			p.setPaImpianti(paImpianti);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			PaMacchine paMacchine = emf.find(PaMacchine.class, macchina);
			p.setPaMacchine(paMacchine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (oreImpiegate != null)
			p.setOre(oreImpiegate);
		if (costi != null)
			p.setCosti(costi);

		if (operatore != null) {
			TUser t = emf.find(TUser.class, operatore);
			p.setOperatore(t);
		}

		emf.persist(p);

	}

	public List<StabilimentoDTO> getStabilimenti(int plantAccess) {
		List<StabilimentoDTO> ret = new ArrayList<>();
		if (plantAccess > 0) {
			String query = "select  c from TPlant c where c.plantId in :lista ";

			List<Long> lid = UserPlantIdsUtil.getPlantIds(plantAccess);
			@SuppressWarnings("unchecked")
			List<TPlant> linee = emf.createQuery(query).setParameter("lista", lid).getResultList();

			for (TPlant p : linee) {
				StabilimentoDTO l = new StabilimentoDTO();
				l.setId(p.getPlantId());
				l.setName(p.getPlantDesc());
				ret.add(l);
			}
		}
		return ret;
	}

	public List<LineaDTO> getLinee(String plant, User usr) {
		List<LineaDTO> ret = new ArrayList<>();
		if (plant != null && plant.trim().length() > 0 && !plant.equalsIgnoreCase("999")) {
			String query = "select distinct c from PaLinea c  JOIN c.paMacchinas e where e.TPlant.plantId = "
					+ plant;
			@SuppressWarnings("unchecked")
			List<PaLinea> linee = emf.createQuery(query).getResultList();

			for (PaLinea p : linee) {
				LineaDTO l = new LineaDTO();
				l.setId(p.getId());
				l.setName(p.getDescrizione());
				ret.add(l);
			}
		}
		return ret;
	}

	public List<MacchinaDTO> getMachines(String linea, String plant, User usr) {
		List<MacchinaDTO> ret = new ArrayList<>();
		if (plant != null && plant.trim().length() > 0 && !plant.equalsIgnoreCase("999") && linea != null
				&& linea.trim().length() > 0 && !linea.equalsIgnoreCase("999")) {
			String query = "select distinct d from PaTipiMacchina d JOIN d.paMacchines e where e.TPlant.plantId = "
					+ plant + " and e.paLinea.id=" + linea;
			@SuppressWarnings("unchecked")
			List<PaTipiMacchina> linee = emf.createQuery(query).getResultList();

			for (PaTipiMacchina p : linee) {
				MacchinaDTO l = new MacchinaDTO();
				l.setId(p.getId());
				l.setName(p.getDescrizione());
				ret.add(l);
			}
		}
		return ret;
	}

	public List<ImpiantoDTO> getImpianti(String macchina, User usr) {
		List<ImpiantoDTO> ret = new ArrayList<>();
		if (macchina != null && macchina.trim().length() > 0 && !macchina.equalsIgnoreCase("999")) {
//			String query = "select d from PaImpianti d JOIN d.paImpiantoTMacchinas e where e.paTipiMacchina.id = "
//					+ macchina;
			
//BUG FIX 2017-03-09
			String query = "select d from PaImpianti d "
					+ "JOIN d.paImpiantoTMacchinas e "
					+ " JOIN e.paTipiMacchina tm"
					+ " JOIN tm.paMacchines m   "
					+ " where m.id = "
					+ macchina;
			
			@SuppressWarnings("unchecked")
			List<PaImpianti> im = emf.createQuery(query).getResultList();

			for (PaImpianti p : im) {
				ImpiantoDTO l = new ImpiantoDTO();
				l.setId(p.getId());
				l.setName(p.getDescrizione());
				ret.add(l);
			}
		}
		return ret;
	}

	public List<MacchinaDTO> getMacchineFisiche(String linea, String plant, User usr) {
		List<MacchinaDTO> ret = new ArrayList<>();
		if (plant != null && plant.trim().length() > 0 && !plant.equalsIgnoreCase("999") && linea != null
				&& linea.trim().length() > 0 && !linea.equalsIgnoreCase("999")) {
			String query = "select d from PaMacchine d where d.TPlant.plantId = " + plant
					+ " and d.paLinea.id=" + linea;
			@SuppressWarnings("unchecked")
			List<PaMacchine> linee = emf.createQuery(query).getResultList();

			for (PaMacchine p : linee) {
				MacchinaDTO l = new MacchinaDTO();
				l.setId(p.getId());
				l.setName(p.getDescrizione());
				ret.add(l);
			}
		}
		return ret;
	}

	public void salvaAttivitaPianificata(Long stabilimento, Long linea, Long macchina, Long impianto, Long periodicita,
			Date data, Long priorita, String nome, String descrizione) {
		PaPianificazioni p = new PaPianificazioni();
		p.setDataPrimoIntervento(data);
		p.setDescrizioneInt(descrizione);
		p.setNomeInt(nome);
		p.setDeleted(new BigDecimal(0));
		PaImpianti paImpianti = emf.find(PaImpianti.class, impianto);
		p.setPaImpianti(paImpianti);

		PaMacchine paMacchine = emf.find(PaMacchine.class, macchina);
		p.setPaMacchine(paMacchine);

		PaPeriodicita paPeriodicita = emf.find(PaPeriodicita.class, periodicita);
		p.setPaPeriodicita(paPeriodicita);

		PaPriorita paPriorita = emf.find(PaPriorita.class, priorita);
		p.setPaPriorita(paPriorita);

		emf.persist(p);
	}

	public void aggiornaAttivitaPianificata(long id, Long macchina, Long impianto, Long periodicita, Date data,
			Long priorita, String nome, String descrizione) {
		PaPianificazioni p = emf.find(PaPianificazioni.class, id);
		if (p != null) {
			p.setDataPrimoIntervento(data);
			p.setDescrizioneInt(descrizione);
			p.setNomeInt(nome);

			PaPeriodicita paPeriodicita = emf.find(PaPeriodicita.class, periodicita);
			if (paPeriodicita != null)
				p.setPaPeriodicita(paPeriodicita);

			PaPriorita paPriorita = emf.find(PaPriorita.class, priorita);
			if (paPriorita != null)
				p.setPaPriorita(paPriorita);
			PaImpianti paImpianti = emf.find(PaImpianti.class, impianto);
			if (paImpianti != null)
				p.setPaImpianti(paImpianti);

			PaMacchine paMacchine = emf.find(PaMacchine.class, macchina);
			if (paMacchine != null)
				p.setPaMacchine(paMacchine);

			emf.merge(p);
		}

	}

	public void eliminaAttivitaPianificata(long id) {
		PaPianificazioni p = emf.find(PaPianificazioni.class, id);
		if (p != null) {
			p.setDeleted(new BigDecimal(1));
			emf.merge(p);
		}
	}

	public void aggiornaPianificazioni(int giorniDaPianificare, int giorniDaOggi, String[] excludedDate) {

		LocalDate ll = LocalDate.now().plusDays(giorniDaOggi);
		Date oggi = java.sql.Date.valueOf(ll);
		LocalDate ll2 = LocalDate.now().plusDays(giorniDaOggi + giorniDaPianificare);
		Date dataMax = java.sql.Date.valueOf(ll2);
		@SuppressWarnings("unchecked")
		List<PaPianificazioni> pianlist = emf
				.createQuery("SELECT c FROM PaPianificazioni c where c.deleted=0"
						// + " and c.dataPrimoIntervento <= :dt"
						+ " ")
				// .setParameter("dt", oggi)
				.getResultList();
		emf.createQuery(
				"Delete from PaAttivita as c where c.paPianificazioni is not null and c.dataPrevista >= :dt and c.dataEsecuzione is null")
				.setParameter("dt", oggi).executeUpdate();

		for (PaPianificazioni p : pianlist) {
			System.out.println(p.getDescrizioneInt());
			
			int periodicita = (int) p.getPaPeriodicita().getId();
			Date dataInizio = p.getDataPrimoIntervento();
			// ------------------------
			int gg = 0 ;
			if (periodicita == Scheduler.PERIODICITA_GIORNALIERA) {
				for (int i = 0; i < giorniDaPianificare; i++) {
					LocalDate l = LocalDate.now().plusDays(gg + giorniDaOggi);
					gg++;
					Date d = java.sql.Date.valueOf(l);
					if (SchedulerUtil.isDayOK(d, excludedDate)) {
						if (!d.after(dataMax) && !d.before(dataInizio)) {
							insertPaAttivita(d, p);
						}
					} else {
						i--;
						continue;
					}
				}
			}
			// ------------------------
//			else if (periodicita == Scheduler.PERIODICITA_OGNI2GG) {
//				gg=0;
//				for (int i = 0; i < (giorniDaPianificare / 2); i++) {
//					LocalDate l = LocalDate.now().plusDays((gg + 1) + giorniDaOggi);
//					gg+=2;
//					Date d = java.sql.Date.valueOf(l);
//					if (SchedulerUtil.isDayOK(d, excludedDate)) {
//						if (!d.after(dataMax) && !d.before(dataInizio)) {
//							insertPaAttivita(d, p);
//						}
//					} else {
//						i--;
//						continue;
//					}
//				}
//			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_SETTIMANALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.DAY_OF_YEAR, 7);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < giorniDaPianificare / 7; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.DAY_OF_YEAR, 7);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
//			else if (periodicita == Scheduler.PERIODICITA_QUINDICINALE) {
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(dataInizio);
//				Date datex = calendar.getTime();
//
//				while (datex.before(oggi)) {
//					calendar.setTime(datex);
//					calendar.add(Calendar.DAY_OF_YEAR, 15);
//					datex = calendar.getTime();
//				}
//				if (!datex.before(oggi)) {
//					for (int i = 0; i < giorniDaPianificare / 15; i++) {
//						if (!datex.after(dataMax)) {
//							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
//								insertPaAttivita(datex, p);
//							} else {
//								calendar.setTime(datex);
//								calendar.add(Calendar.DAY_OF_YEAR, 1);
//								datex = calendar.getTime();
//								i--;
//								continue;
//							}
//						}
//						calendar.setTime(datex);
//						calendar.add(Calendar.DAY_OF_YEAR, 15);
//						datex = calendar.getTime();
//					}
//				}
//			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_MENSILE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.MONTH, 1);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < (int) (giorniDaPianificare / 28); i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.MONTH, 1);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_BIMESTRALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.MONTH, 2);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < (int) (giorniDaPianificare / 28) / 2; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.MONTH, 2);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_TRIMESTRALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.MONTH, 3);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < (int) (giorniDaPianificare / 28) / 3; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.MONTH, 3);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_SEMESTRALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.MONTH, 6);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < (int) (giorniDaPianificare / 28) / 6; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.MONTH, 6);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_QUADRIMESTRALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.MONTH, 4);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < (int) (giorniDaPianificare / 28) / 6; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.MONTH, 6);
						datex = calendar.getTime();
					}
				}
			}
			// ------------------------
			else if (periodicita == Scheduler.PERIODICITA_ANNUALE) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.YEAR, 1);
					datex = calendar.getTime();
				}
				if (!datex.after(dataMax)) {
					for (int i = 0; i < 1; i++) {
						if (SchedulerUtil.isDayOK(datex, excludedDate)) {
							insertPaAttivita(datex, p);
						} else {
							calendar.setTime(datex);
							calendar.add(Calendar.DAY_OF_YEAR, 1);
							datex = calendar.getTime();
							i--;
							continue;
						}
					}
				}
			} else{
				Integer giorni = p.getPaPeriodicita().getIntGiorni();
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataInizio);
				Date datex = calendar.getTime();

				while (datex.before(oggi)) {
					calendar.setTime(datex);
					calendar.add(Calendar.DAY_OF_YEAR, giorni);
					datex = calendar.getTime();
				}
				if (!datex.before(oggi)) {
					for (int i = 0; i < giorniDaPianificare / giorni; i++) {
						if (!datex.after(dataMax)) {
							if (SchedulerUtil.isDayOK(datex, excludedDate)) {
								insertPaAttivita(datex, p);
							} else {
								calendar.setTime(datex);
								calendar.add(Calendar.DAY_OF_YEAR, 1);
								datex = calendar.getTime();
								i--;
								continue;
							}
						}
						calendar.setTime(datex);
						calendar.add(Calendar.DAY_OF_YEAR, giorni);
						datex = calendar.getTime();
					}
				}
			
				
			}
		}
	}

	private void insertPaAttivita(Date d, PaPianificazioni p) {

		Query query = emf.createQuery("select count(*) from PaAttivita as c where " + " c.dataPrevista = :date and "
				+ " c.paPianificazioni = :p ").setParameter("date", d).setParameter("p", p);
		long count = (long) query.getSingleResult();
		if (count == 0) {
			PaAttivita a = new PaAttivita();
			a.setDataPrevista(d);
			a.setDescrizioneIntt(p.getDescrizioneInt());
			a.setNomeInt(p.getNomeInt());
			a.setPaImpianti(p.getPaImpianti());
			a.setPaMacchine(p.getPaMacchine());
			a.setPaPianificazioni(p);
			a.setPaPriorita(p.getPaPriorita());
			emf.persist(a);
		}
	}

	public Page getListaPianificazioneAttivita(ListaPianificazioniSessionFilter listaPianificazioniSessionFilter,
			int pageSize) {
		return this.getListaPianificazioneAttivitaPaged(listaPianificazioniSessionFilter, pageSize);
	}

	public Page getListaPianificazioneAttivitaPaged(ListaPianificazioniSessionFilter listaPianificazioniSessionFilter,
			int pageSize) {
		Page result;

		int pageNumber = listaPianificazioniSessionFilter.getPageNumber();
		// int pageSize = listaAttivitaSessionFilter.getPageSize();
		long[] cmbMach = listaPianificazioniSessionFilter.getCmbMach();
		Long groupId = listaPianificazioniSessionFilter.getGroupId();
		Long stabId = listaPianificazioniSessionFilter.getStabId();

		String selectStr = " SELECT c";

		String contaStr = " select count(c)  ";

		String query = " FROM PaPianificazioni c where 1 = 1 and c.deleted = 0 ";
		if (stabId != null && stabId != 999) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + stabId + ")  ";
		} else {
			List<Long> lid = UserPlantIdsUtil.getPlantIds(listaPianificazioniSessionFilter.getUserAuthLevel());
			String query2add = " and (c.paMacchine.TPlant.plantId in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : lid) {
				if (l > 0) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}
		if (groupId != null && groupId != 999)
			query = query + " and (c.paMacchine.paLinea.id = " + groupId + ")  ";
		if (cmbMach != null) {
			String query2add = " and (c.paMacchine.paTipiMacchina.id in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : cmbMach) {
				if (l != 999) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}

		if (listaPianificazioniSessionFilter.getPeriodicita() != null
				&& listaPianificazioniSessionFilter.getPeriodicita() > 0)
			query = query + " and (c.paPeriodicita.id = " + listaPianificazioniSessionFilter.getPeriodicita() + ")  ";

		// ordinamento
		String orderStr = "order by ";
		List<String> orderByClauseList = new ArrayList<String>();

		if (listaPianificazioniSessionFilter.getLineaSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione asc ");
		} else if (listaPianificazioniSessionFilter.getLineaSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paLinea.descrizione desc ");
		}
		if (listaPianificazioniSessionFilter.getMacchinaSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id asc ");
		} else if (listaPianificazioniSessionFilter.getMacchinaSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paMacchine.paTipiMacchina.id desc ");
		}
		if (listaPianificazioniSessionFilter.getImpiantoSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paImpianti.id asc ");
		} else if (listaPianificazioniSessionFilter.getImpiantoSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paImpianti.id desc ");
		}
		if (listaPianificazioniSessionFilter.getPrioritaSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPriorita.id asc ");
		} else if (listaPianificazioniSessionFilter.getPrioritaSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPriorita.id desc ");
		}
		if (listaPianificazioniSessionFilter.getPianificazioneSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.paPianificazioni.id asc ");
		} else if (listaPianificazioniSessionFilter
				.getPianificazioneSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.paPianificazioni.id desc ");
		}
		if (listaPianificazioniSessionFilter.getInterventoSort() == ListaPianificazioniSessionFilter.SORT_ASC) {
			orderByClauseList.add(" c.descrizioneInt asc ");
		} else if (listaPianificazioniSessionFilter.getInterventoSort() == ListaPianificazioniSessionFilter.SORT_DESC) {
			orderByClauseList.add(" c.descrizioneInt desc ");
		}

		if (orderByClauseList.size() == 0) {
			orderByClauseList.add(" c.id desc ");
		}
		for (int i = 0; i < orderByClauseList.size(); i++) {
			orderStr += orderByClauseList.get(i);
			if (i < orderByClauseList.size() - 1) {
				orderStr += ", ";
			}
		}
		// fine ordinamento

		query = query + orderStr;

		selectStr += query;

		contaStr += query;
		Query contaQuery = emf.createQuery(contaStr);

		long totalRows = (Long) contaQuery.getResultList().get(0);
		int totalPages = (int) ((totalRows - 1) / pageSize + 1);
		if (totalPages < pageNumber) {
			pageNumber = totalPages;
		}

		Query selectQuery = emf.createQuery(selectStr);
		selectQuery.setFirstResult((pageNumber - 1) * pageSize);
		selectQuery.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<PaPianificazioni> ret = selectQuery.getResultList();

		List<PianificazioneAttivitaDTO> dtoList = new ArrayList<PianificazioneAttivitaDTO>();
		for (PaPianificazioni p : ret) {
			PianificazioneAttivitaDTO a = convert2DTO(p);
			dtoList.add(a);
		}

		result = new Page(dtoList, pageNumber, pageSize, totalRows);

		return result;
	}

	public PaMacchine getMacchina(Long idMacchina) {

		return emf.find(PaMacchine.class, idMacchina);
	}

	public Collection<MacchinaImpiantoInterventoDTO> getListaImopiantiIntervento(String cmbMach, Long stabId,
			Long linea, Date txtDateFrom, Integer authLevel) {
		List<MacchinaImpiantoInterventoDTO> ret = new ArrayList<>();
		String query = "select c from PaAttivita c where c.dataPrevista = :data "
				+ (cmbMach != null && !cmbMach.equals("999") && !cmbMach.equals("999+999")
						? (" and c.paMacchine.paTipiMacchina.id = " + cmbMach) : "")
				+ (linea != null && linea > 0 && linea != 999
						? (" and c.paMacchine.paLinea.id = " + linea) : "");

		if (stabId != null && stabId != 999) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + stabId + ")  ";
		} else {
			List<Long> lid = UserPlantIdsUtil.getPlantIds(authLevel);
			String query2add = " and (c.paMacchine.TPlant.plantId in (";
			boolean first = true;
			boolean almeno1 = false;
			for (long l : lid) {
				if (l > 0) {
					almeno1 = true;
					if (!first) {
						query2add = query2add + ",";
					} else {
						first = false;
					}
					query2add = query2add + l;
				}
			}
			if (almeno1)
				query = query + query2add + "))  ";
		}

		query = query + " order by c.paMacchine.id, c.paImpianti.id";
		Query q = emf.createQuery(query).setParameter("data", txtDateFrom);
		@SuppressWarnings("unchecked")
		List<PaAttivita> list = q.getResultList();
		@SuppressWarnings("unused")
		List<AttivitaDTO> latt = new ArrayList<>();
		long idImpanto = -1;
		long idMacchina = -1;
		MacchinaImpiantoInterventoDTO i = null;
		ImpiantoInterventoDTO iid = null;
		for (PaAttivita a : list) {
			if (a.getPaMacchine().getId() != idMacchina) {
				idMacchina = a.getPaMacchine().getId();
				i = new MacchinaImpiantoInterventoDTO();
				i.setNomeMacchina(a.getPaMacchine().getDescrizione());
				i.setNomeLinea(a.getPaMacchine().getPaLinea().getDescrizione());
				i.setNomeStabilimento(a.getPaMacchine().getTPlant().getPlantDesc());
				idImpanto = -1;
				ret.add(i);
			}
			if (a.getPaImpianti().getId() != idImpanto) {
				idImpanto = a.getPaImpianti().getId();
				iid = i.new ImpiantoInterventoDTO();
				iid.setDescrizioneImpianto(a.getPaImpianti().getDescrizione());
				i.getLista().add(iid);
			}
			iid.getList().add(convert2DTO(a));
		}
		return ret;
	}

	public Collection<OperatoreDTO> getOperatori() {
		@SuppressWarnings("unchecked")
		List<TUser> utenti = emf.createQuery("select c from TUser c where c.paRuoli.id = 1 ").getResultList();
		List<OperatoreDTO> ret = new ArrayList<>();
		for (TUser t : utenti) {
			OperatoreDTO o = new OperatoreDTO();
			o.setNome(t.getFirstName() + " " + t.getLastName());
			o.setId(t.getUserId());
			ret.add(o);
		}
		return ret;
	}

	public List<RiepilogoPianificazioneDTO> getRiepilogoPianificazioni(String stabilimento, String linea,
			String macchina) {
		List<RiepilogoPianificazioneDTO> ret = new ArrayList<>();
		String query = "SELECT c FROM PaPianificazioni c where 1 = 1 and c.deleted = 0 ";
		if (stabilimento != null && stabilimento.trim().length() > 0) {
			query = query + " and (c.paMacchine.TPlant.plantId = " + stabilimento + ")  ";
		}
		if (linea != null && linea.trim().length() > 0)
			query = query + " and (c.paMacchine.paLinea.id = " + linea + ")  ";
		if (macchina != null && macchina.trim().length() > 0) {
			query = query + " and (c.paMacchine.id =" + macchina + ")";
		}

		query = query + " order by c.id desc";
		Query q = emf.createQuery(query);
		@SuppressWarnings("unchecked")
		List<PaPianificazioni> lpia = q.getResultList();

		for (PaPianificazioni p : lpia) {
			RiepilogoPianificazioneDTO ri = new RiepilogoPianificazioneDTO();
			ri.setImpianto(p.getPaImpianti().getDescrizione());
			ri.setIntervento(p.getNomeInt());
			ri.setPriorita(p.getPaPriorita().getLivello());
			ri.setTipopianificazione(p.getPaPeriodicita().getDescrizione());
			ret.add(ri);
		}

		return ret;
	}

	public List<PeriodicitaDTO> getPeriodicita() {
		List<PeriodicitaDTO> ret = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<PaPeriodicita> per = emf.createQuery("select c from PaPeriodicita c order by c.id ").getResultList();
		for (PaPeriodicita p : per) {
			PeriodicitaDTO pe = new PeriodicitaDTO();
			pe.setId(p.getId());
			pe.setName(p.getDescrizione());
			ret.add(pe);
		}
		return ret;
	}

}
