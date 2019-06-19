package it.sogesispa.prod.web.services;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.sogesispa.prod.web.dto.OreChartDTO;
import it.sogesispa.prod.web.dto.OreLavorateDTO;
import it.sogesispa.prod.web.dto.StabilimentoDTO;
import it.sogesispa.prod.web.models.jpa.TPlant;
import it.sogesispa.prod.web.utils.ListaOreLavorateSessionFilter;
import it.sogesispa.prod.web.utils.OreLavorateChartSessionFilter;
import it.sogesispa.prod.web.utils.Page;
import it.sogesispa.prod.web.utils.SpEx297CdcRs;
import it.sogesispa.prod.web.utils.UserPlantIdsUtil;

@Service("oreLavorateService")
@Transactional
public class OreLavorateService {

	@PersistenceContext
	private EntityManager emf;

	@SuppressWarnings("unchecked")
	public List<StabilimentoDTO> getStabilimenti(int plantAccess, String lista) {
		List<StabilimentoDTO> ret = new ArrayList<>();
		List<TPlant> stabilimenti = new ArrayList<>();
		if (lista != null && !lista.equals("all")) {
			String select = "SELECT c FROM TPlant c ";

			select += "where c.plantId in (" + lista + ")";

			stabilimenti = emf.createQuery(select).getResultList();
		} else if (plantAccess > 0) {
			String query = "select  c from TPlant c where c.plantId in :lista ";

			List<Long> lid = UserPlantIdsUtil.getPlantIds(plantAccess);
			stabilimenti = emf.createQuery(query).setParameter("lista", lid).getResultList();
		}
		for (TPlant p : stabilimenti) {
			StabilimentoDTO l = new StabilimentoDTO();
			l.setId(p.getPlantId());
			l.setName(p.getPlantDesc());
			l.setCdc(p.getPlantCdc());
			ret.add(l);
		}
		return ret;
	}

	public Page getPaginaOre(ListaOreLavorateSessionFilter listaOreSessionFilter, int pageSize) throws SQLException {

		Page result = null;

		List<SpEx297CdcRs> lsore = getOre(listaOreSessionFilter.getTxtDateFrom(), listaOreSessionFilter.getTxtDateTo());

		List<String> cdcl = new ArrayList<>();
		List<StabilimentoDTO> stabilimenti = getStabilimenti(listaOreSessionFilter.getUserAuthLevel(),
				listaOreSessionFilter.getStabId());
		for (StabilimentoDTO sst : stabilimenti) {
			cdcl.add(sst.getCdc());
		}
		List<OreLavorateDTO> l = new ArrayList<>();
		Map<String, String> stabilimentiM = new HashMap<>();
		Map<String, BigDecimal> oreLavorateOrdinarieM = new HashMap<>();
		Map<String, BigDecimal> oreStraordinarioM = new HashMap<>();
		Map<String, BigDecimal> oreFerieM = new HashMap<>();
		Map<String, BigDecimal> oreMaternitaM = new HashMap<>();
		Map<String, BigDecimal> malattieM = new HashMap<>();
		Map<String, BigDecimal> infortuniM = new HashMap<>();
		Map<String, BigDecimal> legge104M = new HashMap<>();
		for (SpEx297CdcRs a : lsore) {
			String cdc = a.getIdCDC();
			if (cdc != null && cdcl.contains(cdc.substring(0, 5))) {
				String cdck = cdc.substring(0, 5);
				stabilimentiM.put(cdck, a.getStabilimento());

				BigDecimal ss6 = oreLavorateOrdinarieM.get(cdck);
				ss6 = (ss6 != null ? (ss6.add(a.getOreLavorate().subtract(a.getStraordinari())))
						: (a.getOreLavorate().subtract(a.getStraordinari())));
				oreLavorateOrdinarieM.put(cdck, ss6);

				BigDecimal s2 = oreStraordinarioM.get(cdck);
				s2 = (s2 != null ? (s2.add(a.getStraordinari())) : (a.getStraordinari()));
				oreStraordinarioM.put(cdck, s2);

				BigDecimal s3 = oreFerieM.get(cdck);
				s3 = (s3 != null ? (s3.add(a.getFerie())) : (a.getFerie()));
				oreFerieM.put(cdck, s3);

				BigDecimal s4 = oreMaternitaM.get(cdck);
				s4 = (s4 != null ? (s4.add(a.getMaternita())) : (a.getMaternita()));
				oreMaternitaM.put(cdck, s4);

				BigDecimal s5 = malattieM.get(cdck);
				s5 = (s5 != null ? (s5.add(a.getMalattia())) : (a.getMalattia()));
				malattieM.put(cdck, s5);

				BigDecimal s6 = infortuniM.get(cdck);
				s6 = (s6 != null ? (s6.add(a.getInfortunio())) : (a.getInfortunio()));
				infortuniM.put(cdck, s6);

				BigDecimal s7 = legge104M.get(cdck);
				s7 = (s7 != null ? (s7.add(a.getL104())) : (a.getL104()));
				legge104M.put(cdck, s7);

			}
		}

		for (String cdc : stabilimentiM.keySet()) {
			OreLavorateDTO o = new OreLavorateDTO();
			o.setCdcStabilimento(cdc);
			o.setInfortuni(infortuniM.get(cdc).floatValue());
			o.setLegge104(legge104M.get(cdc).floatValue());
			o.setMalattie(malattieM.get(cdc).floatValue());
			o.setOreFerie(oreFerieM.get(cdc).floatValue());
			o.setOreLavorateOrdinarie(oreLavorateOrdinarieM.get(cdc).floatValue());
			o.setOreMaternita(oreMaternitaM.get(cdc).floatValue());
			o.setOreStraordinario(oreStraordinarioM.get(cdc).floatValue());
			o.setStabilimento(stabilimentiM.get(cdc));
			l.add(o);
		}

		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getLegge104Sort() == listaOreSessionFilter.SORT_ASC) {
					return a.getLegge104() < b.getLegge104() ? -1 : a.getLegge104() == b.getLegge104() ? 0 : 1;
				} else if (listaOreSessionFilter.getLegge104Sort() == listaOreSessionFilter.SORT_DESC) {
					return a.getLegge104() < b.getLegge104() ? 1 : a.getLegge104() == b.getLegge104() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getInfortuniSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getInfortuni() < b.getInfortuni() ? -1 : a.getInfortuni() == b.getInfortuni() ? 0 : 1;
				} else if (listaOreSessionFilter.getInfortuniSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getInfortuni() < b.getInfortuni() ? 1 : a.getInfortuni() == b.getInfortuni() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getMalattieSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getMalattie() < b.getMalattie() ? -1 : a.getMalattie() == b.getMalattie() ? 0 : 1;
				} else if (listaOreSessionFilter.getMalattieSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getMalattie() < b.getMalattie() ? 1 : a.getMalattie() == b.getMalattie() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getOreMaternitaSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getOreMaternita() < b.getOreMaternita() ? -1
							: a.getOreMaternita() == b.getOreMaternita() ? 0 : 1;
				} else if (listaOreSessionFilter.getOreMaternitaSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getOreMaternita() < b.getOreMaternita() ? 1
							: a.getOreMaternita() == b.getOreMaternita() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getOreFerieSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getOreFerie() < b.getOreFerie() ? -1 : a.getOreFerie() == b.getOreFerie() ? 0 : 1;
				} else if (listaOreSessionFilter.getOreFerieSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getOreFerie() < b.getOreFerie() ? 1 : a.getOreFerie() == b.getOreFerie() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getOreStraordinarioSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getOreStraordinario() < b.getOreStraordinario() ? -1
							: a.getOreStraordinario() == b.getOreStraordinario() ? 0 : 1;
				} else if (listaOreSessionFilter.getOreStraordinarioSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getOreStraordinario() < b.getOreStraordinario() ? 1
							: a.getOreStraordinario() == b.getOreStraordinario() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getOreLavorateOrdinarieSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getOreLavorateOrdinarie() < b.getOreLavorateOrdinarie() ? -1
							: a.getOreLavorateOrdinarie() == b.getOreLavorateOrdinarie() ? 0 : 1;
				} else if (listaOreSessionFilter.getOreLavorateOrdinarieSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getOreLavorateOrdinarie() < b.getOreLavorateOrdinarie() ? 1
							: a.getOreLavorateOrdinarie() == b.getOreLavorateOrdinarie() ? 0 : -1;
				} else {
					return 0;
				}
			}
		});
		Collections.sort(l, new Comparator<OreLavorateDTO>() {
			@SuppressWarnings("static-access")
			@Override
			public int compare(OreLavorateDTO a, OreLavorateDTO b) {
				if (listaOreSessionFilter.getStabilimentoSort() == listaOreSessionFilter.SORT_ASC) {
					return a.getStabilimento().compareTo(b.getStabilimento());
				} else if (listaOreSessionFilter.getStabilimentoSort() == listaOreSessionFilter.SORT_DESC) {
					return a.getStabilimento().compareTo(b.getStabilimento()) * -1;
				} else {
					return 0;
				}
			}
		});

		result = new Page(l, 1, pageSize, l.size());

		return result;
	}

	public List<SpEx297CdcRs> getOre(Date dataInizio, Date dataFine) throws SQLException {
		DataSource ds = null;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		List<SpEx297CdcRs> res = new ArrayList<>();
		try {

			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
			} catch (NamingException e) {
				e.printStackTrace();
			}

			con = ds.getConnection();

			//String storedProc = "{call SP_EX297_CDC(?,?,?)}";
			String storedProc = "{call sp_sogesi_ore_portale(?,?,?)}";

			callableStatement = con.prepareCall(storedProc);
			java.sql.Date datada = new java.sql.Date(dataInizio.getTime());
			java.sql.Date dataa = new java.sql.Date(dataFine.getTime());
			callableStatement.setDate(1, datada);
			callableStatement.setDate(2, dataa);
			callableStatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			callableStatement.execute();

			rs = (ResultSet) callableStatement.getObject(3);

			@SuppressWarnings("unused")
			int quanti = 0;
			while (rs.next()) {
				quanti++;
				SpEx297CdcRs rcl = new SpEx297CdcRs();
				rcl.setCentroCosto(rs.getString(SpEx297CdcRs.CENTRO_COSTO));
				rcl.setCognome(rs.getString(SpEx297CdcRs.COGNOME));
				rcl.setDonazione(rs.getBigDecimal(SpEx297CdcRs.DONAZIONE_SANGUE_346));
				rcl.setFerie(rs.getBigDecimal(SpEx297CdcRs.FERIE_347_367_326_302));
				rcl.setIdCDC(rs.getString(SpEx297CdcRs.ID_CDC));
				rcl.setIdCompany(rs.getString(SpEx297CdcRs.IDCOMPANY));
				rcl.setIdEmploy(rs.getString(SpEx297CdcRs.IDEMPLOY));
				rcl.setInfortunio(rs.getBigDecimal(SpEx297CdcRs.INFORTUNIO_335));
				rcl.setL104(rs.getBigDecimal(SpEx297CdcRs.L104_353_373_383_393));
				rcl.setMalattia(rs.getBigDecimal(SpEx297CdcRs.MALATTIA_331_327_324));
				rcl.setMaternita(rs.getBigDecimal(SpEx297CdcRs.MATERNITA_332_333_325));
				rcl.setMesGg(rs.getString(SpEx297CdcRs.MES_GG));
				rcl.setMesMmAaaa(rs.getString(SpEx297CdcRs.MES_MM_AAAA));
				rcl.setNome(rs.getString(SpEx297CdcRs.NOME));
				rcl.setOreLavorate(rs.getBigDecimal(SpEx297CdcRs.ORE_LAVORATE));
				rcl.setPartTime(rs.getString(SpEx297CdcRs.PART_TIME));
				rcl.setPercPartTime(rs.getBigDecimal(SpEx297CdcRs.PERC_PART_TIME));
				rcl.setPermNonRetrib(rs.getBigDecimal(SpEx297CdcRs.PERM_NON_RETRIB_354_341_329));
				rcl.setPermRetribuit(rs.getBigDecimal(SpEx297CdcRs.PERM_RETRIBUITO_355));
				rcl.setPermSindacale(rs.getBigDecimal(SpEx297CdcRs.PERM_SINDACALE_358_351_342));
				rcl.setQualDescr(rs.getString(SpEx297CdcRs.QUAL_DESCRI));
				rcl.setQualifica(rs.getString(SpEx297CdcRs.QUALIFICA));
				rcl.setStabilimento(rs.getString(SpEx297CdcRs.STABILIMENTO));
				rcl.setStraordinari(rs.getBigDecimal(SpEx297CdcRs.STAORDINARI));
				rcl.setData(rs.getDate(SpEx297CdcRs.DATA));
				res.add(rcl);
			}
		} catch (Exception e) {
		} finally {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (callableStatement != null && !callableStatement.isClosed())
				callableStatement.close();
			if (con != null && !con.isClosed())
				con.close();
		}
		return res;
	}

	public List<OreChartDTO> getOreCharts(OreLavorateChartSessionFilter oreLavorateChartSessionFilter)
			throws SQLException {

		List<SpEx297CdcRs> lsore = getOre(oreLavorateChartSessionFilter.getTxtDateFrom(),
				oreLavorateChartSessionFilter.getTxtDateTo());

		List<String> cdcl = new ArrayList<>();
		List<StabilimentoDTO> stabilimenti = getStabilimenti(oreLavorateChartSessionFilter.getUserAuthLevel(),
				oreLavorateChartSessionFilter.getStabId());

		for (StabilimentoDTO sst : stabilimenti) {
			cdcl.add(sst.getCdc());
		}
		@SuppressWarnings("unused")
		List<OreLavorateDTO> l = new ArrayList<>();
		Map<String, List<Map<String, Object>>> stabil = new HashMap<>();
		Map<String, Object> orderbyk = null;
		Map<String, Object> oreLavorateOrdinarieM = null;
		Map<String, Object> oreStraordinarioM = null;
		Map<String, Object> oreFerieM = null;
		Map<String, Object> oreMaternitaM = null;
		Map<String, Object> malattieM = null;
		Map<String, Object> infortuniM = null;
		Map<String, Object> legge104M = null;
		SimpleDateFormat sdf = null;
		SimpleDateFormat sdfKey = null;
		switch (oreLavorateChartSessionFilter.getUnitaMisura()) {
		case OreLavorateChartSessionFilter.UNITA_MISURA_GIORNI:
			sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ITALIAN);
			sdfKey = new SimpleDateFormat("yyyyMMdd",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_SETTIMANE:
			sdf = new SimpleDateFormat("MMM-yyyy 'Settimana' W",Locale.ITALIAN);
			sdfKey = new SimpleDateFormat("yyyyMMW",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_MESI:
			sdf = new SimpleDateFormat("MMM-yyyy",Locale.ITALIAN);
			sdfKey = new SimpleDateFormat("yyyyMM",Locale.ITALIAN);
			break;
		case OreLavorateChartSessionFilter.UNITA_MISURA_ANNI:
			sdf = new SimpleDateFormat("yyyy",Locale.ITALIAN);
			sdfKey = new SimpleDateFormat("yyyy",Locale.ITALIAN);
			break;
		default:
			sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.ITALIAN);
			sdfKey = new SimpleDateFormat("yyyyMMdd",Locale.ITALIAN);
			break;
		}

		for (SpEx297CdcRs a : lsore) {
			String cdc = a.getIdCDC();
			if (cdc != null && cdcl.contains(cdc.substring(0, 5))) {
				String cdck = sdf.format(a.getData());
				String cdckeyordered = sdfKey.format(a.getData());
				List<Map<String, Object>> ee = stabil.get(a.getStabilimento());
				if (ee == null) {
					ee = new ArrayList<Map<String, Object>>();


						oreLavorateOrdinarieM = new HashMap<>();
						ee.add(oreLavorateOrdinarieM);
						oreStraordinarioM = new HashMap<>();
						ee.add(oreStraordinarioM);
						oreFerieM = new HashMap<>();
						ee.add(oreFerieM);
						oreMaternitaM = new HashMap<>();
						ee.add(oreMaternitaM);
						malattieM = new HashMap<>();
						ee.add(malattieM);
						infortuniM = new HashMap<>();
						ee.add(infortuniM);
						legge104M = new HashMap<>();
						ee.add(legge104M);
						
						orderbyk= new HashMap<>();
						ee.add(orderbyk);
				}
				stabil.put(a.getStabilimento(), ee);

				BigDecimal ss6 = (BigDecimal) oreLavorateOrdinarieM.get(cdck);
				ss6 = (ss6 != null ? (ss6.add(a.getOreLavorate().subtract(a.getStraordinari())))
						: (a.getOreLavorate().subtract(a.getStraordinari())));
				oreLavorateOrdinarieM.put(cdck, ss6);

				BigDecimal s2 = (BigDecimal) oreStraordinarioM.get(cdck);
				s2 = (s2 != null ? (s2.add(a.getStraordinari())) : (a.getStraordinari()));
				oreStraordinarioM.put(cdck, s2);

				BigDecimal s3 = (BigDecimal) oreFerieM.get(cdck);
				s3 = (s3 != null ? (s3.add(a.getFerie())) : (a.getFerie()));
				oreFerieM.put(cdck, s3);

				BigDecimal s4 = (BigDecimal) oreMaternitaM.get(cdck);
				s4 = (s4 != null ? (s4.add(a.getMaternita())) : (a.getMaternita()));
				oreMaternitaM.put(cdck, s4);

				BigDecimal s5 = (BigDecimal) malattieM.get(cdck);
				s5 = (s5 != null ? (s5.add(a.getMalattia())) : (a.getMalattia()));
				malattieM.put(cdck, s5);

				BigDecimal s6 = (BigDecimal) infortuniM.get(cdck);
				s6 = (s6 != null ? (s6.add(a.getInfortunio())) : (a.getInfortunio()));
				infortuniM.put(cdck, s6);

				BigDecimal s7 = (BigDecimal) legge104M.get(cdck);
				s7 = (s7 != null ? (s7.add(a.getL104())) : (a.getL104()));
				legge104M.put(cdck, s7);
				
				orderbyk.put(cdckeyordered, cdck);

			}
		}

		List<OreChartDTO> ret = new ArrayList<>();
		int ids = 0;
		for (String strStabil : stabil.keySet()) {
			List<Map<String, Object>> ee = stabil.get(strStabil);
			// .......

			Map<String, Object> m1 = ee.get(0);
			Map<String, Object> m2 = ee.get(1);
			Map<String, Object> m3 = ee.get(2);
			Map<String, Object> m4 = ee.get(3);
			Map<String, Object> m5 = ee.get(4);
			Map<String, Object> m6 = ee.get(5);
			Map<String, Object> m7 = ee.get(6);
			Map<String, Object> mOrder = ee.get(7);
			OreChartDTO o = new OreChartDTO();
			o.setIdStabilimento("strStabil_" + ids++);
			o.setStabilimento(strStabil);

			List<List<String[]>> datap = new ArrayList<>();
			Map<String, Object> orderedkey = new TreeMap<String, Object>(mOrder);
			for (String xi : orderedkey.keySet()) {
				String i = (String) orderedkey.get(xi);
				List<String[]> inlist = new ArrayList<>();
				String[] s = new String[2];

				s[0] = "'X'";
				s[1] = "'"+i+"'";
				inlist.add(s);

				if(oreLavorateChartSessionFilter.getTipoore().contains("lavorate")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'A'";
				s[1] = m1.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("straordinario")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'B'";
				s[1] = m2.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("ferie")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'C'";
				s[1] = m3.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("maternita")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'D'";
				s[1] = m4.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("malattie")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'E'";
				s[1] = m5.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("infortuni")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'F'";
				s[1] = m6.get(i).toString();
				inlist.add(s);
				}
				if(oreLavorateChartSessionFilter.getTipoore().contains("l104")||oreLavorateChartSessionFilter.getTipoore().equals("all"))
				{
				s = new String[2];
				s[0] = "'G'";
				s[1] = m7.get(i).toString();
				inlist.add(s);
				}
				datap.add(inlist);
			}

			o.setDatap(datap);
			StringBuffer labels = new StringBuffer();
			StringBuffer ykeys = new StringBuffer();
			boolean first = true;
			if(oreLavorateChartSessionFilter.getTipoore().contains("lavorate")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Ordinarie'");
				ykeys.append("'A'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("straordinario")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Straordinario'");
				ykeys.append("'B'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("ferie")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Ferie'");
				ykeys.append("'C'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("maternota")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Maternita'");
				ykeys.append("'D'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("malattie")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Malattie'");
				ykeys.append("'E'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("infortuni")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Infrotuni'");
				ykeys.append("'F'");
				first=false;
			}
			if(oreLavorateChartSessionFilter.getTipoore().contains("l104")||oreLavorateChartSessionFilter.getTipoore().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Legge 104'");
				ykeys.append("'G'");
				first=false;
			}
			o.setLabels(
					"["+labels.toString()+"]");
			o.setLineColors("['#FE2EF7','#8000FF','#2EFEC8','#2E9AFE','#FFBF00','#FA5858','#298A08']");
			o.setXkey("'X'");
			o.setYkeys("["+ykeys.toString()+"]");

			ret.add(o);
		}

		return ret;
	}

}
