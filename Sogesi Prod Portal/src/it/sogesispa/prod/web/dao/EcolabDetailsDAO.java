package it.sogesispa.prod.web.dao;

import it.sogesispa.prod.web.models.EcolabDetails;
import it.sogesispa.prod.web.models.EcolabFilter;

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

@Component("ecolabDetailsDao")
public class EcolabDetailsDAO
{

	public List<EcolabDetails> getEcolabDetails(EcolabFilter filter,
			Integer stabId, Integer groupId)
	{
		List<EcolabDetails> det = new ArrayList<EcolabDetails>();

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

		try
		{
			Connection con = ds.getConnection();

			String storedProc = "{call ECOLAB_DETAILS(?,?,?,?,?,?)}";
			CallableStatement callableStatement = con.prepareCall(storedProc);

			callableStatement.setInt(1, stabId);
			/*
			 * if (!(filter.getMachineId()[0].equals("999") && filter
			 * .getMachineId()[1].equals("999"))) { callableStatement.setInt(2,
			 * filter.getMachineId()[0]); } else { callableStatement.setInt(2,
			 * groupId); }
			 */
			callableStatement.setInt(2, groupId);
			callableStatement.setInt(3, filter.getMachineId()[1]);
			callableStatement.setDate(4, filter.getDateFrom());
			callableStatement.setDate(5, filter.getDateTo());

			callableStatement.registerOutParameter(6,
					oracle.jdbc.OracleTypes.CURSOR);
			callableStatement.execute();

			ResultSet rs = (ResultSet) callableStatement.getObject(6);

			while (rs.next())
			{
				EcolabDetails dStab = new EcolabDetails();

				dStab.setGroupId(rs.getString("GR_Number"));
				dStab.setGroupName(rs.getString("GR_Name"));
				dStab.setMachineId(rs.getString("Ma_InterNumber"));
				dStab.setMachineName(rs.getString("MA_Name"));

				dStab.setMachineLoad(rs.getString("Load"));
				dStab.setProgramName(rs.getString("PG_Name"));
				dStab.setNomLoad(rs.getString("NomLoad"));
				dStab.setCicli(Integer.parseInt(rs.getString("CICLI")));
				
				dStab.setLoad((Integer.parseInt(rs.getString("Load")) / Integer.parseInt(rs.getString("CICLI"))));
				if (Integer.parseInt(rs.getString("NomLoad")) == 0)
				{
					dStab.setPercLoad(0f);
				}
				else
				{
					dStab.setPercLoad((Float.parseFloat(rs.getString("Load")) / Float.parseFloat(rs.getString("NomLoad"))/ Float.parseFloat(rs.getString("CICLI"))));
				}
				det.add(dStab);
			}

			rs.close();
			callableStatement.close();
			con.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return det;

	}

}
