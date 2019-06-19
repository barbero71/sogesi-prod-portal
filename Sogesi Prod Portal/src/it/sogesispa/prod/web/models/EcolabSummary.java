package it.sogesispa.prod.web.models;

public class EcolabSummary
{
	private Integer stabId;
	private String stabName;
	private Integer groupId;
	private String groupName;
	private Integer machineId;
	private String machineName;
	private java.sql.Date dateFrom;
	private java.sql.Date dateTo;
	private Integer totalLoad;
	private Integer groupLoad;
	private Integer stabTotals;

	public Integer getGroupLoad()
	{
		return groupLoad;
	}

	public void setGroupLoad(Integer groupLoad)
	{
		this.groupLoad = groupLoad;
	}

	public Integer getTotalLoad()
	{
		return totalLoad;
	}

	public void setTotalLoad(Integer totalLoad)
	{
		this.totalLoad = totalLoad;
	}

	public EcolabSummary()
	{

	}

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

	public Integer getMachineId()
	{
		return machineId;
	}

	public void setMachineId(Integer machineId)
	{
		this.machineId = machineId;
	}

	public java.sql.Date getDateFrom()
	{
		return dateFrom;
	}

	public void setDateFrom(java.sql.Date dateFrom)
	{
		this.dateFrom = dateFrom;
	}

	public java.sql.Date getDateTo()
	{
		return dateTo;
	}

	public void setDateTo(java.sql.Date dateTo)
	{
		this.dateTo = dateTo;
	}
	
	public Integer getStabTotals() 
	{
		return stabTotals;
	}

	public void setStabTotals(Integer stabTotals) 
	{
		this.stabTotals = stabTotals;
	}

	@Override
	public String toString()
	{
		return "EcolabSummary [stabId=" + stabId + ", stabName=" + stabName
				+ ", groupId=" + groupId + ", groupName=" + groupName
				+ ", machineId=" + machineId + ", machineName=" + machineName
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", totalLoad=" + totalLoad + ", groupLoad=" + groupLoad + "]";
	}

}
