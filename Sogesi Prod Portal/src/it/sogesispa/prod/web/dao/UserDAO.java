package it.sogesispa.prod.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import it.sogesispa.prod.web.models.User;

import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDAO
{

	public User setSession(String userId, String password)
	{
		// TODO: creare le query o la call alla procedura per ottenere l'utente

		User user = new User();
		Boolean rsEmpty = true;

		try
		{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();

			ResultSet rs = sm
					.executeQuery("SELECT * FROM T_USERS WHERE USER_NAME='"
							+ userId + "' AND PASSWORD='" + password + "'");

			while (rs.next())
			{
				rsEmpty = false;
				user.setUserId(Integer.parseInt(rs.getString("USER_ID")));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setAuthLevel(Integer.parseInt(rs.getString("PLANT_ACCESS")));
				user.seteMail(rs.getString("EMAIL"));


			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();

		}
		catch (NamingException | SQLException e)
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return user;
		else
			return null;
	}
	
	public User setSession(String userName)
	{
		// TODO: creare le query o la call alla procedura per ottenere l'utente

		User user = new User();
		Boolean rsEmpty = true;
		
		//System.out.println(userName);

		try
		{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:/comp/env/jdbc/orasogesi");
			Connection con = ds.getConnection();

			Statement sm = con.createStatement();
			
			ResultSet rs = sm
					.executeQuery("SELECT * FROM T_USERS WHERE USER_NAME='"
							+ userName  + "'");

			while (rs.next())
			{
				rsEmpty = false;
				user.setUserId(Integer.parseInt(rs.getString("USER_ID")));
				user.setUserName(rs.getString("USER_NAME"));
				//user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setAuthLevel(Integer.parseInt(rs.getString("PLANT_ACCESS")));
				user.seteMail(rs.getString("EMAIL"));


			}

			rs.close();
			sm.close();
			con.close();
			ctx.close();

		}
		catch (NamingException | SQLException e)
		{
			e.printStackTrace();
		}
		
		if (rsEmpty == false)
			return user;
		else
			return null;
	}
}
