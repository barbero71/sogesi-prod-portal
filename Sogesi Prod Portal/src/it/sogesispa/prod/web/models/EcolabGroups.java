package it.sogesispa.prod.web.models;

public class EcolabGroups
{

	private int stabId;
	private int groupId;
	private String groupName;

	public int getStabId()
	{
		return stabId;
	}

	public void setStabId(int stabId)
	{
		this.stabId = stabId;
	}

	public int getGroupId()
	{
		return groupId;
	}

	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	@Override
	public String toString()
	{
		return "EcolabGroups [stabId=" + stabId + ", groupId=" + groupId
				+ ", groupName=" + groupName + "]";
	}

}
