package it.sogesispa.prod.web.dao;

import it.sogesispa.prod.web.models.EcolabGroups;
import it.sogesispa.prod.web.models.EcolabMachines;
import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("ecolabFilterDao")
public class EcolabFilterDAO
{
	
	private JdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc)
	{
		this.jdbc = new JdbcTemplate(jdbc);
	}

	public List<Plants> getPlantList(User usr) throws Exception
	{
		
			return jdbc.query("SELECT * FROM T_PLANTS WHERE ECOLAB = 1", new RowMapper<Plants>() {

				public Plants mapRow(ResultSet rs, int rowNum)
						throws SQLException
				{
					Plants plant = new Plants();
					
					plant.setStabId(rs.getInt("PLANT_ID"));
					plant.setStabName(rs.getString("PLANT_DESC"));
					plant.setTiNumber(rs.getInt("TI_NUMBER"));
					
					return plant;
				}
				
			});

	}

	public List<EcolabGroups> getGroupList(String plant, User usr)
			throws Exception
	{
		String sSql = "";
		if (plant != null)
		{
			if (plant.equals("999"))
			{
				sSql = "SELECT * FROM T_GROUPS";
			} else
			{
				sSql = "SELECT * FROM T_GROUPS WHERE TI_NUMBER = " + plant;
			}
		}

		else
		{
			sSql = "SELECT * FROM T_GROUPS";
		}
		
		return jdbc.query(sSql, new RowMapper<EcolabGroups>() {

			public EcolabGroups mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				EcolabGroups group = new EcolabGroups();
				
				group.setStabId(rs.getInt("PLANT_ID"));
				group.setGroupId(rs.getInt("GR_NUMBER"));
				group.setGroupName(rs.getString("GR_NAME"));
				
				return group;
			}
			
		});

	}
	
	public List<EcolabMachines> getMachineList(String group, String plant, User usr) throws Exception
	{
		String sSql = "";
		
		if(group.equals("999") || plant.equals("999"))
 		{
  			if(plant.equals("999") && !group.equals("999"))
  			{
  				sSql = "SELECT * FROM VW_MACHINES WHERE GR_Number = " + group;	  				
  			}
  			else if (group.equals("999") && !plant.equals("999"))
  			{
  				sSql = "SELECT * FROM VW_MACHINES WHERE TI_Number = " + plant;
  			}
  			else
  			{
  				sSql = "SELECT * FROM VW_MACHINES";
  			}
  		}
  		else if (group.equals("999") && plant.equals("999"))
  		{
  			sSql = "SELECT * FROM VW_MACHINES";
  		}
  		else
  		{
  			sSql = "SELECT * FROM VW_MACHINES WHERE TI_Number = " + plant.toString() + " AND GR_Number = " + group.toString();
  		}
		
		return jdbc.query(sSql, new RowMapper<EcolabMachines>() {

			public EcolabMachines mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				EcolabMachines machine = new EcolabMachines();
				
				machine.setStabId(rs.getInt("PLANT_ID"));
				machine.setGroupId(rs.getString("GR_Number"));
				machine.setMachineId(rs.getString("Ma_InterNumber"));
				machine.setMachineName(rs.getString("MA_Name"));
				
				
				return machine;
			}
			
		});

	}

}
