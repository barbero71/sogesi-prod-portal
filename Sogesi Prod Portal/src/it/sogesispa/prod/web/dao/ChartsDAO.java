package it.sogesispa.prod.web.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import it.sogesispa.prod.web.models.Charts;


@Component("chartsDao")
public class ChartsDAO {
	
	public List<Charts> getChartsData(Integer stabId, Date dataInizio, Date dataFine)
	{

		try
		{
			DataSource ds = null;
			try
			{
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orasogesi");
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}

			// Mi ricavo i dati di produzione generali
			Connection con = ds.getConnection();

			String storedProc = "{call CHARTS_DATA(?,?,?,?)}";

			CallableStatement callableStatement = con.prepareCall(storedProc);
			java.sql.Date datada = new java.sql.Date(dataInizio.getTime());
			java.sql.Date dataa = new java.sql.Date(dataFine.getTime());
			callableStatement.setInt(1, stabId);
			callableStatement.setDate(2, datada);
			callableStatement.setDate(3, dataa);

			callableStatement.registerOutParameter(4,
					oracle.jdbc.OracleTypes.CURSOR);
			callableStatement.execute();

			ResultSet rs = (ResultSet) callableStatement.getObject(4);

			List<Charts> chartData = new ArrayList<Charts>();


			while (rs.next())
			{

				Charts charts = new Charts();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Calendar calendar = GregorianCalendar.getInstance();
	
				try {
					calendar.setTime(dateFormat.parse(rs.getString("ID_END_TIME")));
					if (calendar.get(Calendar.MINUTE) >= 20)
						calendar.add(Calendar.HOUR_OF_DAY, 1);
					
					charts.setOre(calendar.get(Calendar.HOUR_OF_DAY));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				charts.setProgr(Integer.parseInt(rs.getString("PROGRESSIVO")));
				charts.setSomma(Integer.parseInt(rs.getString("SOMMA")));
				charts.setData(  new java.util.Date(rs.getDate("ID_START_DATE").getTime() ) );
				chartData.add(charts);
				
				System.out.println(charts.getOre());


			}

			rs.close();
			callableStatement.close();
			con.close();
			return chartData;
			

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

}

