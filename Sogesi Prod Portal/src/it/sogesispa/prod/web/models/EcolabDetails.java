package it.sogesispa.prod.web.models;

public class EcolabDetails
{
	private String GroupId;
	private String GroupName;
	private String MachineId;
	private String MachineName;
	private String MachineLoad;
	private String ProgramName;
	private String ProgramId;
	private String nomLoad;
	private Float percLoad;
	private Integer load;
	private Integer cicli;

	public EcolabDetails()
	{

	}

	public String getGroupId()
	{
		return GroupId;
	}

	public void setGroupId(String groupId)
	{
		GroupId = groupId;
	}

	public String getGroupName()
	{
		return GroupName;
	}

	public void setGroupName(String groupName)
	{
		GroupName = groupName;
	}

	public String getMachineId()
	{
		return MachineId;
	}

	public void setMachineId(String machineId)
	{
		MachineId = machineId;
	}

	public String getMachineName()
	{
		return MachineName;
	}

	public void setMachineName(String machineName)
	{
		MachineName = machineName;
	}

	public String getMachineLoad()
	{
		return MachineLoad;
	}

	public void setMachineLoad(String machineLoad)
	{
		MachineLoad = machineLoad;
	}

	public String getProgramName()
	{
		return ProgramName;
	}

	public void setProgramName(String programName)
	{
		ProgramName = programName;
	}

	public String getProgramId()
	{
		return ProgramId;
	}

	public void setProgramId(String programId)
	{
		ProgramId = programId;
	}

	public String getNomLoad()
	{
		return nomLoad;
	}

	public void setNomLoad(String nomLoad)
	{
		this.nomLoad = nomLoad;
	}

	public Float getPercLoad()
	{
		return percLoad;
	}

	public void setPercLoad(Float percLoad)
	{
		this.percLoad = percLoad;
	}

	public Integer getLoad()
	{
		return load;
	}

	public void setLoad(Integer load)
	{
		this.load = load;
	}

	public Integer getCicli()
	{
		return cicli;
	}

	public void setCicli(Integer cicli)
	{
		this.cicli = cicli;
	}

	@Override
	public String toString()
	{
		return "EcolabDetails [GroupId=" + GroupId + ", GroupName=" + GroupName
				+ ", MachineId=" + MachineId + ", MachineName=" + MachineName
				+ ", MachineLoad=" + MachineLoad + ", ProgramName="
				+ ProgramName + ", ProgramId=" + ProgramId + ", nomLoad="
				+ nomLoad + ", percLoad=" + percLoad + ", load=" + load + "]";
	}

	

}
