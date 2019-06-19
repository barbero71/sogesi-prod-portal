package it.sogesispa.prod.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sogesispa.prod.web.dao.ProductionDAO;
import it.sogesispa.prod.web.models.Plants;
import it.sogesispa.prod.web.models.User;

@Service("productionService")
public class ProductionService
{
	private ProductionDAO productionDao;
	
	@Autowired
	public void setProductionDao(ProductionDAO productionDao)
	{
		this.productionDao = productionDao;
	}
	/*
	public double getOre(String plantCdc, int year, int month) 
	{
		return productionDao.getOre(plantCdc, year, month);
	}
	*/
	public double getOre(String plantCdc, String dateFrom, String dateTo, String codAz)
	{
		return productionDao.getOre(plantCdc, dateFrom, dateTo, codAz);
	}
	
	
	public double getOreGroup(String plantCdc, String dateFrom, String dateTo, int group, String codAz)
	{
		return productionDao.getOreGroup(plantCdc, dateFrom, dateTo, group, codAz);
	}
	
	
	public double getKgLavorati(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgLavorati(plantCdc, dateFrom, dateTo);
	}
	
	
	public double getKgPiana(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgPiana(plantCdc, dateFrom, dateTo);
	}
	
	
	public double getKgCotone(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgCotone(plantCdc, dateFrom, dateTo);
	}
	
	public double getKgPolicotone(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgPolicotone(plantCdc, dateFrom, dateTo);
	}
	
	
	public double getKgHv(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgHv(plantCdc, dateFrom, dateTo);
	}
	
	public double getKgMaterassi(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgMaterassi(plantCdc, dateFrom, dateTo);
	}
	
	
	public double getKgTtr(String plantCdc, String dateFrom, String dateTo)
	{
		return productionDao.getKgTtr(plantCdc, dateFrom, dateTo);
	}
	
	public double getPercCat(double kgCat, double kgTotali)
	{
		return productionDao.getPercCat(kgCat, kgTotali);
	}
	
	
	public double getProdIndex(double kg, double ore)
	{
		return productionDao.getProdIndex(kg, ore);
	}

	
	public List<Plants> getPlants(User usr)
	{
		try
		{
			return productionDao.getPlantList(usr);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
