package it.sogesispa.prod.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.Summary;
import it.sogesispa.prod.web.models.User;

@Component("productionDao")
public class ProductionDAO {

	private JdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new JdbcTemplate(jdbc);
	}
	
	private String orclDate(String date)
	{
		//String dd = null;
		
		DateFormat sqlDate = new SimpleDateFormat("yyyy-dd-MM");
		Date d = null;;
		try
		{
			d = sqlDate.parse(date);
		} 
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy", Locale.ITALIAN);
		return formatter.format(d);
	}

	
	public double getOreGroup(String plantCdc, String dateFrom, String dateTo, int group, String codAz) {
		
		//FIXME qui non ho la connessione sqlserver quindi metto un dato a cazzo 
		//return 0;
		
		@SuppressWarnings("unused")
		Summary summary = new Summary();
		String cdc = "";
		String sql = "";
		@SuppressWarnings("unused")
		Boolean rsEmpty1 = true;
		@SuppressWarnings("unused")
		Boolean rsEmpty2 = true;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();

			if (group == 999)
			{
				sql = "SELECT * FROM T_ORE_GROUPS WHERE CDC_STAB = " + Integer.parseInt(plantCdc);
			}
			else
			{
				sql = "SELECT * FROM T_ORE_GROUPS WHERE ORE_CAT = " + group 
					+ " AND CDC_STAB = " + Integer.parseInt(plantCdc);
			}
			
			ResultSet rs = sm.executeQuery(sql);
			

			while (rs.next()) {
				rsEmpty1 = false;
				cdc += "'" + rs.getString("CDC_ID") + "',";

			}
			
			 if (cdc.length() > 0 && cdc.charAt(cdc.length()-1)==',') {
			      cdc = cdc.substring(0, cdc.length()-1);
			    }

			rs.close();
			sm.close();
			con.close();
			ctx.close();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		//QUI OTTENGO LE ORE PER LA CATEGORIA

/*		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/mssql");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
/*
			sql = "SELECT SUM(MES_LAV_TIME) as TOTALE_ORE FROM "
					+ "haop_000001mes INNER JOIN hrdd_000001employee11 ON "
					+ "haop_000001mes.IDCOMPANY = hrdd_000001employee11.IDCOMPANY AND "
					+ "haop_000001mes.IDEMPLOY = hrdd_000001employee11.IDEMPLOY "
					
					+ "WHERE DTENDVL > '" + dateTo + "' AND ((haop_000001mes.MES_AAMM >= RTRIM(CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateFrom
					+ "')))" + " + RTRIM(RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateFrom
					+ "'))),2)) "
					+ "AND haop_000001mes.MES_AAMM <= RTRIM(CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateTo
					+ "')))" + " + RTRIM(RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateTo
					+ "'))),2)) "
					+ "AND haop_000001mes.MES_GG >= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateFrom
					+ "'))),2) "
					+ "AND haop_000001mes.MES_GG <= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateTo + "'))),2) ";

			if (!codAz.equals("999"))
				sql += " AND haop_000001mes.IDCOMPANY = '000" + codAz + "' ";

			if (rsEmpty1 == false)
			{
				sql +=  "AND hrdd_000001employee11.IDCOSTCNT IN (" + cdc + ")" + "))";
			}
			else
			{
				sql +=  "AND hrdd_000001employee11.IDCOSTCNT IN (-1)";
				sql += "))";
			}

			
			sql = "SELECT SUM(ORE) AS TOTALE_ORE FROM FCT_ORE WHERE "
					+ "(DATA >= '" + dateFrom + "') AND (DATA <= '" + dateTo + "') ";
					if (!codAz.equals("999"))
					{
						sql += "AND ID_AZIENDA = '000" + codAz + "' ";
					}
					if (rsEmpty1 == false)
					{
						sql += "AND  ID_CDC IN (" + cdc + ")";
					}
					else
					{
						sql += "AND  ID_CDC IN (-1)";
					}		
			System.out.println(sql);
			
			ResultSet rs = sm.executeQuery(sql);
			
			while (rs.next()) {
				rsEmpty2 = false;
				summary.setOreTotali(rs.getDouble("TOTALE_ORE"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		if (rsEmpty2 == false)
			return summary.getOreTotali();
		else
*/			return 0;
	}

	public double getOre(String plantCdc, String dateFrom, String dateTo, String codAz) {
/*		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/mssql");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();

			String sql = "SELECT SUM(MES_LAV_TIME) as TOTALE_ORE "
					+ "FROM (MES LEFT OUTER JOIN PERS ON MES.MES_CODPERS =PERS.RFPERS_CODPERS) "
					+ "WHERE ((MES.MES_AA >= CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateFrom
					+ "')) "
					+ "AND MES.MES_AA <= CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateTo
					+ "')) "
					+ "AND MES.MES_MM >= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateFrom
					+ "'))),2) "
					+ "AND MES.MES_MM <= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateTo
					+ "'))),2) "
					+ "AND MES.MES_GG >= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateFrom
					+ "'))),2) "
					+ "AND MES.MES_GG <= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateTo + "'))),2) " 
					+ "AND PERS.RFPERS_CDC LIKE '"
					+ plantCdc + "%'))";
					if (!codAz.equals("999"))
					 sql += " AND PERS.RFPERS_SOC2 = '" + codAz + "' ";

			String sql = "SELECT SUM(MES_LAV_TIME) as TOTALE_ORE FROM "
					+ "haop_000001mes INNER JOIN hrdd_000001employee11 ON "
					+ "haop_000001mes.IDCOMPANY = hrdd_000001employee11.IDCOMPANY AND "
					+ "haop_000001mes.IDEMPLOY = hrdd_000001employee11.IDEMPLOY "
					
					+ "WHERE ((haop_000001mes.MES_AAMM >= RTRIM(CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateFrom
					+ "')))" + " + RTRIM(RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateFrom
					+ "'))),2)) "
					+ "AND haop_000001mes.MES_AAMM <= RTRIM(CONVERT(CHAR,DATEDIFF(year,'1940','"
					+ dateTo
					+ "')))" + " + RTRIM(RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(MONTH, '"
					+ dateTo
					+ "'))),2)) "
					+ "AND haop_000001mes.MES_GG >= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateFrom
					+ "'))),2) "
					+ "AND haop_000001mes.MES_GG <= RIGHT('00' + RTRIM(CONVERT(CHAR,DATEPART(DAY,'"
					+ dateTo + "'))),2) "
					+ "AND hrdd_000001employee11.IDCOSTCNT LIKE '"	+ plantCdc + "%'))";
			
			if (!codAz.equals("999"))
				sql += " AND haop_000001mes.IDCOMPANY = '000" + codAz + "' ";

			String sql = "SELECT SUM(ORE) AS TOTALE_ORE FROM FCT_ORE WHERE "
					+ "(DATA >= '" + dateFrom + "') AND (DATA <= '" + dateTo + "') "
					+ " AND ID_CDC LIKE '" + plantCdc + "%' ";
				
				if (!codAz.equals("999"))
					sql += " AND ID_AZIENDA = '000" + codAz + "'";
				
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setOreTotali(rs.getDouble("TOTALE_ORE"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		if (rsEmpty == false)
			return summary.getOreTotali();
		else
*/			return 0;
	}
	
	public double getKgLavorati(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			sql += "AND TSA_CAT_MERC IN ('02','03','04','05','06','07','08','22') "; //AND (TSA_STIRO = '00' or TSA_STIRO = ' ' or TSA_STIRO IS NULL)"; 
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgPiana(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			sql += "AND TSA_CAT_MERC IN ('02','04','07') AND (TSA_STIRO = '00' or TSA_STIRO = ' ' or TSA_STIRO IS NULL)";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgCotone(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			//sql += "AND TSA_CAT_MERC IN ('02','04','07') AND (TSA_STIRO = '00' or TSA_STIRO IS NULL)";
			sql += "AND TSA_CAT_MERC IN ('03','05','07') AND (TSA_STIRO = '01')";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgPolicotone(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			//sql += "AND TSA_CAT_MERC IN ('02','04','07') AND (TSA_STIRO = '00' or TSA_STIRO IS NULL)";
			sql += "AND TSA_CAT_MERC IN ('03','05','07') AND (TSA_STIRO = '02')";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgHv(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			//sql += "AND TSA_CAT_MERC IN ('02','04','07') AND (TSA_STIRO = '00' or TSA_STIRO IS NULL)";
			sql += "AND TSA_CAT_MERC IN ('06') AND (TSA_STIRO = '02')";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgMaterassi(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			sql += "AND TSA_CAT_MERC IN ('08') AND (TSA_STIRO = '00' or TSA_STIRO = ' ' or TSA_STIRO IS NULL)";
			//sql += "AND TSA_CAT_MERC IN ('03','05','07') AND (TSA_STIRO = '02')";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getKgTtr(String plantCdc, String dateFrom, String dateTo)
	{
		Summary summary = new Summary();
		Boolean rsEmpty = true;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			String sql = "SELECT SUM(TSA_PESONETT0) AS KG_LAVORATI ";
			sql += "FROM TMP_STAT_ANAL_DD ";
			sql += "WHERE TSA_STABIL = '" + plantCdc + "' ";
			sql += "AND TSA_CAT_MERC IN ('22') AND (TSA_STIRO = '00' or TSA_STIRO = ' ' or TSA_STIRO IS NULL)";
			//sql += "AND TSA_CAT_MERC IN ('03','05','07') AND (TSA_STIRO = '02')";
			sql += "AND TSA_DATA BETWEEN '" + orclDate(dateFrom) +"' AND '" + orclDate(dateTo) + "'";
			
			ResultSet rs = sm.executeQuery(sql);

			while (rs.next()) {
				rsEmpty = false;
				summary.setKgLavorati(rs.getDouble("KG_LAVORATI"));
				summary.setStabId(1);
				summary.setStabName("Stabilimento di Ponsacco");

			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();
		}
		catch (NamingException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return summary.getKgLavorati();
		else
			return 0;
	}
	
	public double getPercCat(double kgCat, double kgTotali)
	{
		double perc = 0d;
		
		if (kgTotali != 0)
		{
			perc = kgCat/kgTotali;
		}
		
		return perc;
	}
	
	public double getProdIndex(double kg, double ore)
	{
		double perc = 0d;
		
		if (ore != 0)
		{
			perc = kg/ore;
		}
		
		return perc;
	}

	public List<Plants> getPlantList(User usr) throws Exception {

		return jdbc.query("SELECT * FROM T_PLANTS", new RowMapper<Plants>() {

			public Plants mapRow(ResultSet rs, int rowNum) throws SQLException {
				Plants plant = new Plants();

				plant.setStabId(rs.getInt("PLANT_ID"));
				plant.setStabName(rs.getString("PLANT_DESC"));
				plant.setTiNumber(rs.getInt("TI_NUMBER"));
				plant.setStabCdc(rs.getString("PLANT_CDC"));

				return plant;
			}

		});

	}
}
