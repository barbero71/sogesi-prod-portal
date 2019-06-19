package it.sogesispa.prod.web.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Locale;
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

import it.sogesispa.prod.web.dto.ConsumoDetergenteDTO;
import it.sogesispa.prod.web.dto.DetergentiChartDTO;
//import it.sogesispa.prod.web.dto.OreChartDTO;
import it.sogesispa.prod.web.utils.ConsumoDetergentiChartSessionFilter;
import it.sogesispa.prod.web.utils.ConsumoDetergentiSessionFilter;
//import it.sogesispa.prod.web.utils.OreLavorateChartSessionFilter;

@Service("detergentiService")
@Transactional
public class DetergentiService {

	@PersistenceContext
	private EntityManager emf;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public Map<String, List<ConsumoDetergenteDTO>> getConsumi(
			ConsumoDetergentiSessionFilter consumoDetergentiSessionFilter) throws SQLException {

		Map<String, List<ConsumoDetergenteDTO>> ret = new TreeMap<>();

		if (consumoDetergentiSessionFilter.getStabId() != null
				&& (consumoDetergentiSessionFilter.getStabId().equals("all")
						|| consumoDetergentiSessionFilter.getStabId().contains("1"))) {
			String nomeStabilimento = "Perugia";
			String nomeTabella = "PERUGIA_INDATA";
			List<ConsumoDetergenteDTO> l = getListConsumeDetergenteDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiSessionFilter.getTxtDateFrom(), consumoDetergentiSessionFilter.getTxtDateTo());
			ret.put(nomeStabilimento, l);
		}
		if (consumoDetergentiSessionFilter.getStabId() != null
				&& (consumoDetergentiSessionFilter.getStabId().equals("all")
						|| consumoDetergentiSessionFilter.getStabId().contains("2"))) {
			String nomeStabilimento = "Ponsacco";
			String nomeTabella = "PONSACCO_INDATA";
			List<ConsumoDetergenteDTO> l = getListConsumeDetergenteDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiSessionFilter.getTxtDateFrom(), consumoDetergentiSessionFilter.getTxtDateTo());
			ret.put(nomeStabilimento, l);
		}
		if (consumoDetergentiSessionFilter.getStabId() != null
				&& (consumoDetergentiSessionFilter.getStabId().equals("all")
						|| consumoDetergentiSessionFilter.getStabId().contains("3"))) {
			String nomeStabilimento = "Stroncone";
			String nomeTabella = "STRONCONE_INDATA";
			List<ConsumoDetergenteDTO> l = getListConsumeDetergenteDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiSessionFilter.getTxtDateFrom(), consumoDetergentiSessionFilter.getTxtDateTo());
			ret.put(nomeStabilimento, l);
		}
		if (consumoDetergentiSessionFilter.getStabId() != null
				&& (consumoDetergentiSessionFilter.getStabId().equals("all")
						|| consumoDetergentiSessionFilter.getStabId().contains("4"))) {
			String nomeStabilimento = "Cannara";
			String nomeTabella = "CANNARA_INDATA";
			List<ConsumoDetergenteDTO> l = getListConsumeDetergenteDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiSessionFilter.getTxtDateFrom(), consumoDetergentiSessionFilter.getTxtDateTo());
			ret.put(nomeStabilimento, l);
		}
		if (consumoDetergentiSessionFilter.getStabId() != null
				&& (consumoDetergentiSessionFilter.getStabId().equals("all")
						|| consumoDetergentiSessionFilter.getStabId().contains("5"))) {
			String nomeStabilimento = "Erbusco";
			String nomeTabella = "ERBUSCO_INDATA";
			List<ConsumoDetergenteDTO> l = getListConsumeDetergenteDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiSessionFilter.getTxtDateFrom(), consumoDetergentiSessionFilter.getTxtDateTo());
			ret.put(nomeStabilimento, l);
		}
		return ret;
	}

	private List<ConsumoDetergenteDTO> getListConsumeDetergenteDTO(String nomeStabilimento, String nomeTabella,
			Date datada, Date dataa) throws SQLException {
		DataSource ds = null;
		Statement stmt = null;
		Connection con = null;
		List<ConsumoDetergenteDTO> l = new ArrayList<>();

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			String query = "select " + "c.PD_Name as nome, " + "c.PD_Price as prezzo, "
					+ "sum(b.IP_QUANTITY) as quantita, " + "sum(b.IP_PRICE) as quantita_prezzo, "
					+ "sum(b.IP_DESIREDQUANTITY) as quantita_desiderata "
					+ "from "+nomeTabella+" a, "
							+ nomeStabilimento + "_INPRODUCT b, "
							+ nomeStabilimento + "_SETUPPRODUCTS c  "
					+ "where a.ID_REFERENCE = b.ID_REFERENCE and b.PD_NUMBER = c.PD_NUMBER "
					+ " and (a.ID_START_DATE >= TO_DATE('" + sdf.format(datada) + "', 'yyyy/mm/dd') "
					+ "and a.ID_START_DATE <= TO_DATE('" + sdf.format(dataa) + "', 'yyyy/mm/dd'))"
					+ "group by c.PD_Name, c.PD_Price";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ConsumoDetergenteDTO o = new ConsumoDetergenteDTO();
				o.setCostokg(rs.getFloat("prezzo"));
				o.setCostoquantitakg(rs.getFloat("quantita_prezzo"));
				o.setIdStabilimento(1L);
				o.setNomeProdotto(rs.getString("nome"));
				o.setQuantita(rs.getFloat("quantita"));
				o.setQuantitaDesiderata(rs.getFloat("quantita_desiderata"));

				o.setStabilimento(nomeStabilimento);

				l.add(o);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}

		}
		return l;
	}

	public List<DetergentiChartDTO> getCharts(ConsumoDetergentiChartSessionFilter consumoDetergentiChartSessionFilter) throws SQLException {
		List<DetergentiChartDTO> ret = new ArrayList<>();
		if (consumoDetergentiChartSessionFilter.getStabId() != null
				&& (consumoDetergentiChartSessionFilter.getStabId().equals("all")
						|| consumoDetergentiChartSessionFilter.getStabId().contains("1"))) {
			String nomeStabilimento = "Perugia";
			String nomeTabella = "PERUGIA_INDATA";
			DetergentiChartDTO l = getDetergentiChartDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiChartSessionFilter);
			ret.add(l);
		}
		if (consumoDetergentiChartSessionFilter.getStabId() != null
				&& (consumoDetergentiChartSessionFilter.getStabId().equals("all")
						|| consumoDetergentiChartSessionFilter.getStabId().contains("2"))) {
			String nomeStabilimento = "Ponsacco";
			String nomeTabella = "PONSACCO_INDATA";
			DetergentiChartDTO l = getDetergentiChartDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiChartSessionFilter);
			ret.add(l);
		}
		if (consumoDetergentiChartSessionFilter.getStabId() != null
				&& (consumoDetergentiChartSessionFilter.getStabId().equals("all")
						|| consumoDetergentiChartSessionFilter.getStabId().contains("3"))) {
			String nomeStabilimento = "Stroncone";
			String nomeTabella = "STRONCONE_INDATA";
			DetergentiChartDTO l = getDetergentiChartDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiChartSessionFilter);
			ret.add(l);
		}
		if (consumoDetergentiChartSessionFilter.getStabId() != null
				&& (consumoDetergentiChartSessionFilter.getStabId().equals("all")
						|| consumoDetergentiChartSessionFilter.getStabId().contains("4"))) {
			String nomeStabilimento = "Cannara";
			String nomeTabella = "CANNARA_INDATA";
			DetergentiChartDTO l = getDetergentiChartDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiChartSessionFilter);
			ret.add(l);
		}
		if (consumoDetergentiChartSessionFilter.getStabId() != null
				&& (consumoDetergentiChartSessionFilter.getStabId().equals("all")
						|| consumoDetergentiChartSessionFilter.getStabId().contains("5"))) {
			String nomeStabilimento = "Erbusco";
			String nomeTabella = "ERBUSCO_INDATA";
			DetergentiChartDTO l = getDetergentiChartDTO(nomeStabilimento, nomeTabella,
					consumoDetergentiChartSessionFilter);
			ret.add(l);
		}
		return ret;
	}

	private DetergentiChartDTO getDetergentiChartDTO(String nomeStabilimento, String nomeTabella, ConsumoDetergentiChartSessionFilter consumoDetergentiChartSessionFilter) throws SQLException {
		DataSource ds = null;
		Statement stmt = null;
		Connection con = null;
		DetergentiChartDTO ret = new DetergentiChartDTO();

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		String pattern = null;
		String patternkey = null;
		switch (consumoDetergentiChartSessionFilter.getUnitaMisura()) {
		case ConsumoDetergentiChartSessionFilter.UNITA_MISURA_GIORNI:
			patternkey = "yyyymmdd";
			pattern = "Dy dd-mm-yyyy";
			break;
		case ConsumoDetergentiChartSessionFilter.UNITA_MISURA_SETTIMANE:
			patternkey ="yyyymmw";
			pattern ="\"Settimana\" w mm-yyyy";
			break;
		case ConsumoDetergentiChartSessionFilter.UNITA_MISURA_MESI:
			patternkey = "yyyymm";
			pattern = "mm-yyyy";
			break;
		case ConsumoDetergentiChartSessionFilter.UNITA_MISURA_ANNI:
			patternkey = "yyyy";
			pattern = "yyyy";
			break;
		default:
			patternkey = "yyyymmdd";
			pattern = "dd-mm-yyyy";
			break;
		}
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			String query = "select TO_CHAR(a.ID_START_DATE,'"+patternkey+"') as ide, TO_CHAR(a.ID_START_DATE,'"+pattern+"') as nome, "
					+ " sum(b.IP_QUANTITY) as quantita, "
					+ "sum(b.IP_PRICE) as quantita_prezzo "
					+ "from "+nomeTabella+" a, "
							+ nomeStabilimento + "_INPRODUCT b, "
							+ nomeStabilimento + "_SETUPPRODUCTS c  "
					+ "where a.ID_REFERENCE = b.ID_REFERENCE and b.PD_NUMBER = c.PD_NUMBER "
					+ "and (a.ID_START_DATE >= TO_DATE('" + sdf.format(consumoDetergentiChartSessionFilter.getTxtDateFrom()) + "', 'yyyy/mm/dd') "
					+ "and a.ID_START_DATE <= TO_DATE('" + sdf.format(consumoDetergentiChartSessionFilter.getTxtDateTo()) + "', 'yyyy/mm/dd')) "
					+ "group by TO_CHAR(a.ID_START_DATE,'"+patternkey+"'), TO_CHAR(a.ID_START_DATE,'"+pattern+"')"
							+ "order by 1";

			ResultSet rs = stmt.executeQuery(query);
			ret.setIdStabilimento("strStabil_" + nomeTabella);
			ret.setStabilimento(nomeStabilimento);
			List<List<String[]>> datap = new ArrayList<>();

			while (rs.next()) {
				String i = rs.getString("nome");
				List<String[]> inlist = new ArrayList<>();
				String[] s = new String[2];

				s[0] = "'X'";
				s[1] = "'"+i+"'";
				inlist.add(s);

				if(consumoDetergentiChartSessionFilter.getTipo().contains("consumi")||consumoDetergentiChartSessionFilter.getTipo().equals("all"))
				{
				s = new String[2];
				s[0] = "'A'";
				s[1] = rs.getString("quantita");
				inlist.add(s);
				}
				if(consumoDetergentiChartSessionFilter.getTipo().contains("costi")||consumoDetergentiChartSessionFilter.getTipo().equals("all"))
				{
				s = new String[2];
				s[0] = "'B'";
				s[1] = rs.getString("quantita_prezzo");
				inlist.add(s);
				}
				datap.add(inlist);
			}
			if(datap.size()<1)
			{
				String i = "0";
				List<String[]> inlist = new ArrayList<>();
				String[] s = new String[2];

				s[0] = "'X'";
				s[1] = "'"+i+"'";
				inlist.add(s);

				s = new String[2];
				s[0] = "'A'";
				s[1] = "0";
				inlist.add(s);
				
				s = new String[2];
				s[0] = "'B'";
				s[1] = "0";
				inlist.add(s);
				datap.add(inlist);
			}
			ret.setDatap(datap);
			StringBuffer labels = new StringBuffer();
			StringBuffer ykeys = new StringBuffer();
			boolean first = true;
			if(consumoDetergentiChartSessionFilter.getTipo().contains("consumi")||consumoDetergentiChartSessionFilter.getTipo().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Consumi'");
				ykeys.append("'A'");
				first=false;
			}
			if(consumoDetergentiChartSessionFilter.getTipo().contains("costi")||consumoDetergentiChartSessionFilter.getTipo().equals("all")){
				if(!first){
					labels.append(",");
					ykeys.append(",");
				}
				labels.append("'Costi'");
				ykeys.append("'B'");
				first=false;
			}
			ret.setLabels(
					"["+labels.toString()+"]");
			ret.setLineColors("['#FE2EF7','#8000FF','#2EFEC8','#2E9AFE','#FFBF00','#FA5858','#298A08']");
			ret.setXkey("'X'");
			ret.setYkeys("["+ykeys.toString()+"]");

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}

		}
		return ret;
	}


}
