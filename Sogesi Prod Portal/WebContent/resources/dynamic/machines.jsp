<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="it.sogesispa.prod.web.models.User" %>
<%
String group = request.getParameter("group");
String plant = request.getParameter("plant");
String prn = "999+999-Tutte,";
String sSql = "";
User usr = (User) session.getAttribute("user");
try
  	{
  		Context ctx = new InitialContext();
 		DataSource ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oradb");
 		Connection con = ds.getConnection();
 		 
 		Statement sm = con.createStatement();
 		if(group.equals("999") || plant.equals("999"))
 		{
  			if(plant.equals("999") && !group.equals("999"))
  			{
  				sSql = "SELECT * FROM \"VW_MACHINES\" WHERE \"GR_Number\" = " + group;
  			}
  			else if (group.equals("999") && !plant.equals("999"))
  			{
  				sSql = "SELECT * FROM \"VW_MACHINES\" WHERE \"TI_Number\" = " + plant;
  			}
  			else
  			{
  				sSql = "SELECT * FROM \"VW_MACHINES\"";
  			}
  		}
  		else if (group.equals("999") && plant.equals("999"))
  		{
  			sSql = "SELECT * FROM \"VW_MACHINES\"";
  		}
  		else
  		{
  			sSql = "SELECT * FROM \"VW_MACHINES\" WHERE \"TI_Number\" = " + plant + "AND \"GR_Number\" = " + group;
  		}
  		
  		ResultSet rs = sm.executeQuery(sSql);
 		 
 		while(rs.next())
 		{
 		 	if ((Integer.parseInt(rs.getString("PLANT_ID")) & usr.getAuthLevel()) > 0)
 		 	{
 		 		prn += rs.getString("GR_Number") + "+" + rs.getString("MA_InterNumber") + "-" + rs.getString("MA_Name") + ",";
 			}
 		}
 		rs.close();
 		sm.close();
 		con.close();
 		ctx.close(); 
 		
 		out.print(prn);
 	}
 	catch (SQLException e)
 	{
 		e.printStackTrace();
 	}
%>
