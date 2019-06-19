<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="it.sogesispa.prod.web.models.User" %>
<%

// dynamic.jsp source
// result returned as comma separated string  which is parsed by Javascript and added in the combo box 
//  This page can be changed to query the database or read data from file depending on the input parameter
/*
String input= request.getParameter("params");
if(input.equals("google"))
 out.print("http://www.gmail.com,http://www.orkut.com,http://www.google.com");
else if(input.equals("yahoo"))
 out.print("http://www.yahoo.com,http://mail.yahoo.com");
else if(input.equals("microsoft"))
 out.print("http://hotmail.com,http://www.microsoft.com");
else
 out.print("No data found");
*/

String input= request.getParameter("params");
String prn = "999-Tutti,";
String sSql = "";
User usr = (User) session.getAttribute("user");
try
  	{
  		Context ctx = new InitialContext();
 		DataSource ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oradb");
 		Connection con = ds.getConnection();
 		 
 		Statement sm = con.createStatement();
 		if(!input.equals("999"))
 		{
  			sSql = "SELECT * FROM T_GROUPS WHERE TI_NUMBER = " + input;
  		}
  		else
  		{
  			sSql = "SELECT * FROM T_GROUPS";
  		}  		
 		ResultSet rs = sm.executeQuery(sSql);
 		while(rs.next())
 		{
 		 	if ((Integer.parseInt(rs.getString("PLANT_ID")) & usr.getAuthLevel()) > 0)
 		 	{
 		 		prn += rs.getString("GR_NUMBER") + "-" + rs.getString("GR_NAME") + ",";
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