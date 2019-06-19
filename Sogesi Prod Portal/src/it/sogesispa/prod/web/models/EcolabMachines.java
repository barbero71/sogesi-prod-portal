package it.sogesispa.prod.web.models;

public class EcolabMachines
{

	private int stabId;
	private String groupId;
	private String machineId;
	private String machineName;

	public int getStabId()
	{
		return stabId;
	}

	public void setStabId(int stabId)
	{
		this.stabId = stabId;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getMachineId()
	{
		return machineId;
	}

	public void setMachineId(String machineId)
	{
		this.machineId = machineId;
	}

	public String getMachineName()
	{
		return machineName;
	}

	public void setMachineName(String machineName)
	{
		this.machineName = machineName;
	}

	@Override
	public String toString()
	{
		return "EcolabMachines [stabId=" + stabId + ", groupId=" + groupId
				+ ", machineId=" + machineId + ", machineName=" + machineName
				+ "]";
	}

}
