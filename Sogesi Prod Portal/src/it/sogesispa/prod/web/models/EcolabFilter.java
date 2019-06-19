package it.sogesispa.prod.web.models;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EcolabFilter
{
	private Integer stabId;
	private String stabName;
	private List<String> stabsName;
	private String listStabId;
	private Integer groupId;
	private String groupName;
	private Integer[] machineId;
	private String machineName;

	public String getStabName()
	{
		return stabName;
	}

	public void setStabName(String stabName)
	{
		this.stabName = stabName;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getMachineName()
	{
		return machineName;
	}

	public void setMachineName(String machineName)
	{
		this.machineName = machineName;
	}

	private java.sql.Date dateFrom;
	private java.sql.Date dateTo;

	public Integer getStabId()
	{
		return stabId;
	}

	public void setStabId(Integer stabId)
	{
		this.stabId = stabId;
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	public Integer[] getMachineId()
	{
		return machineId;
	}

	public void setMachineId(Integer[] machineId)
	{
		this.machineId = machineId;
	}

	public java.sql.Date getDateFrom()
	{
		if(dateFrom==null){
			setDateFrom(new java.sql.Date(new Date().getTime()));
		}
		return dateFrom;
	}

	public void setDateFrom(java.sql.Date dateFrom)
	{
		this.dateFrom = dateFrom;
	}

	public java.sql.Date getDateTo()
	{
		if(dateTo==null){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, 1);
			setDateTo(new java.sql.Date(cal.getTime().getTime()));
		}
		return dateTo;
	}

	public void setDateTo(java.sql.Date dateTo)
	{
		this.dateTo = dateTo;
	}

	@Override
	public String toString()
	{
		return "EcolabFilter [stabId=" + stabId + ", stabName=" + stabName
				+ ", groupId=" + groupId + ", groupName=" + groupName
				+ ", machineId=" + Arrays.toString(machineId)
				+ ", machineName=" + machineName + ", plantId=" + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + "]";
	}

	public String getListStabId() {
		if(listStabId==null)setListStabId("999");
		return listStabId;
	}

	public void setListStabId(String listStabId) {
		this.listStabId = listStabId;
	}

	public List<String> getStabsName() {
		return stabsName;
	}

	public void setStabsName(List<String> stabsName) {
		this.stabsName = stabsName;
	}

}
