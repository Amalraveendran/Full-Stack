package com.cakehub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DeliveryPartnerDao")
public class DeliveryPartnerDao extends HttpServlet {
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String pwd=request.getParameter("pwd");
		String cpwd=request.getParameter("cpwd");
		
		String d_email=request.getParameter("d_email");
		String d_phone=request.getParameter("d_phone");
		String d_uname=request.getParameter("d_uname");
		
		
		 try
			{int i=1;
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cakeorders", "root", "root");
				
				java.sql.PreparedStatement st=con.prepareStatement("insert into deliverypartner value(?,?,?,?,?,?)");
				st.setString(1, fname);
				st.setString(2, lname);
				st.setString(3, pwd);
				st.setString(4, d_email);
				st.setString(5, d_phone);
				st.setString(6, d_uname);
				if(pwd.equals(cpwd))
					{st.executeUpdate();
					
					}
				else
				{
				System.out.println("password must be equal");	
				}
				HttpSession session =request.getSession();
				ResultSet rs=st.executeQuery("select * from orders");
				while (rs.next())
				{	rs.absolute(i);
					session.setAttribute("name",rs.getString(1));
					session.setAttribute("lname",rs.getString(2));
					//session.setAttribute("pwd",rs.getString(3));
					session.setAttribute("email",rs.getString(4));
					session.setAttribute("phone",rs.getString(5));
					session.setAttribute("d_uname",rs.getString(6));
					
					i++;
				
				}
				
				
				LoginDao dao=new LoginDao();
				if(dao.check(d_uname,pwd))
					{response.sendRedirect("deliveryPartner.jsp");
					}
				
				
				con.close();
				
				
				
				
				
				
			}
			catch(Exception e){
				System.out.println("E:"+e.getMessage());
				
			}
		
		
		
	}

	
}
