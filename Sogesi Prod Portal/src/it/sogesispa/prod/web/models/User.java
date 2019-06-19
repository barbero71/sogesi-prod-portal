package it.sogesispa.prod.web.models;

public class User
{
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String eMail;
	private Integer authLevel;
	private Integer sessionId;

	public User()
	{

	}

	public User(int userId, String userName, String firstName, String lastName,
			String password, String eMail)
	{
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.eMail = eMail;
	}

	public User(String userName, String firstName, String lastName,
			String password, String eMail)
	{

		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.eMail = eMail;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String geteMail()
	{
		return eMail;
	}

	public void seteMail(String eMail)
	{
		this.eMail = eMail;
	}

	public Integer getAuthLevel()
	{
		return authLevel;
	}

	public void setAuthLevel(Integer authLevel)
	{
		this.authLevel = authLevel;
	}

	public Integer getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Integer sessionId)
	{
		this.sessionId = sessionId;
	}

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", eMail=" + eMail
				+ ", authLevel=" + authLevel + ", sessionId=" + sessionId + "]";
	}

}
