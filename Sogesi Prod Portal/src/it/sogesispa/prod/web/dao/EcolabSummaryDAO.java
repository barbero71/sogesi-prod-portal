package it.sogesispa.prod.web.dao;

import it.sogesispa.prod.web.models.EcolabFilter;
import it.sogesispa.prod.web.models.EcolabSummary;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component("ecolabSummaryDao")
public class EcolabSummaryDAO
{

	public List<EcolabSummary> getEcolabSummary(EcolabFilter filter,
			Integer stabId, Integer groupId)
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

			String storedProc = "{call ECOLAB_SUMMARY(?,?,?,?,?,?)}";

			CallableStatement callableStatement = con.prepareCall(storedProc);

			callableStatement.setInt(1, stabId);
			callableStatement.setInt(2, groupId);
			callableStatement.setInt(3, filter.getMachineId()[1]);
			callableStatement.setDate(4, filter.getDateFrom());
			callableStatement.setDate(5, filter.getDateTo());

			callableStatement.registerOutParameter(6,
					oracle.jdbc.OracleTypes.CURSOR);
			callableStatement.execute();

			ResultSet rs = (ResultSet) callableStatement.getObject(6);

			List<EcolabSummary> sumStab = new ArrayList<EcolabSummary>();

			Integer totStab = 0;
			Integer totGrp = 0;

			// ---------------------------------------------------------------------------------
			// TODO: Aggiungere alla stored procedure i parametri plantId e
			// GroupId in INPUT
			// ---------------------------------------------------------------------------------

			while (rs.next())
			{

				EcolabSummary sum = new EcolabSummary();

				sum.setGroupId(Integer.parseInt(rs.getString("GR_Number")));
				sum.setGroupName(rs.getString("GR_Name"));
				sum.setMachineId(Integer.parseInt(rs
						.getString("Ma_InterNumber")));
				sum.setMachineName(rs.getString("MA_Name"));

				sum.setTotalLoad(totStab += Integer.parseInt(rs.getString("Load")));
				sum.setGroupLoad(totGrp += Integer.parseInt(rs.getString("Load")));
				sumStab.add(sum);

				totStab = 0;

			}

			rs.close();
			callableStatement.close();
			con.close();
			return sumStab;
			

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public int getEcolabTotals(EcolabFilter filter,
			Integer stabId, Integer groupId)
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

			String storedProc = "{call ECOLAB_SUMMARY(?,?,?,?,?,?)}";

			CallableStatement callableStatement = con.prepareCall(storedProc);

			// callableStatement.setInt(1, filter.getStabId());
			callableStatement.setInt(1, stabId);
			// callableStatement.setInt(2, filter.getGroupId());
			callableStatement.setInt(2, 999);
			callableStatement.setInt(3, 999);
			callableStatement.setDate(4, filter.getDateFrom());
			callableStatement.setDate(5, filter.getDateTo());

			callableStatement.registerOutParameter(6,
					oracle.jdbc.OracleTypes.CURSOR);
			callableStatement.execute();

			ResultSet rs = (ResultSet) callableStatement.getObject(6);

			List<EcolabSummary> sumStab = new ArrayList<EcolabSummary>();

			Integer totStab = 0;
			Integer totGrp = 0;

			// ---------------------------------------------------------------------------------
			// TODO: Aggiungere alla stored procedure i parametri plantId e
			// GroupId in INPUT
			// ---------------------------------------------------------------------------------

			while (rs.next())
			{

				EcolabSummary sum = new EcolabSummary();

				sum.setGroupId(Integer.parseInt(rs.getString("GR_Number")));
				sum.setGroupName(rs.getString("GR_Name"));
				sum.setMachineId(Integer.parseInt(rs
						.getString("Ma_InterNumber")));
				sum.setMachineName(rs.getString("MA_Name"));

				sum.setTotalLoad(totStab += Integer.parseInt(rs.getString("Load")));
				sum.setGroupLoad(totGrp += Integer.parseInt(rs.getString("Load")));
				sumStab.add(sum);

				//totStab = 0;

			}

			rs.close();
			callableStatement.close();
			con.close();
			return totStab;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return 0;
	}

}
