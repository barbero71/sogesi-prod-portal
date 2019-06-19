package it.sogesispa.prod.web.services;

import java.util.List;

import it.sogesispa.prod.web.dao.EcolabDetailsDAO;
import it.sogesispa.prod.web.dao.EcolabFilterDAO;
import it.sogesispa.prod.web.dao.EcolabSummaryDAO;
import it.sogesispa.prod.web.models.EcolabDetails;
import it.sogesispa.prod.web.models.EcolabFilter;
import it.sogesispa.prod.web.models.EcolabGroups;
import it.sogesispa.prod.web.models.EcolabMachines;
import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.EcolabSummary;
import it.sogesispa.prod.web.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ecolabService")
public class EcolabService
{

	private EcolabSummaryDAO ecolabSummaryDao;
	private EcolabDetailsDAO ecolabDetailsDao;
	private EcolabFilterDAO ecolabFilterDao;
	
	
	@Autowired
	public void setEcolabSummaryDao(EcolabSummaryDAO ecolabSummaryDao)
	{
		this.ecolabSummaryDao = ecolabSummaryDao;
	}
	
	@Autowired
	public void setEcolabDetailsDao(EcolabDetailsDAO ecolabDetailsDao)
	{
		this.ecolabDetailsDao = ecolabDetailsDao;
	}
	
	@Autowired
	public void setEcolabFilterDao(EcolabFilterDAO ecolabFilterDao)
	{
		this.ecolabFilterDao = ecolabFilterDao;
	}
	
	public List<EcolabSummary> getSummary(EcolabFilter filter, Integer stabId, Integer groupId)
	{
		return ecolabSummaryDao.getEcolabSummary(filter, stabId, groupId);

	}
	
	public List<EcolabDetails> getDetails(EcolabFilter filter, Integer stabId, Integer groupId)
	{
		return ecolabDetailsDao.getEcolabDetails(filter, stabId, groupId);
	}

	public List<EcolabMachines> getMachines(String group, String plant, User usr)
	{
		try
		{
			return ecolabFilterDao.getMachineList(group, plant, usr);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<EcolabGroups> getGroups(String plant, User usr)
	{
		try
		{
			return ecolabFilterDao.getGroupList(plant, usr);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Plants> getPlants(User usr)
	{
		try
		{
			return ecolabFilterDao.getPlantList(usr);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int getTotals(EcolabFilter filter, Integer stabId, Integer groupId)
	{
		return ecolabSummaryDao.getEcolabTotals(filter, stabId, groupId);
	}
	
}
