package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
@webServlet("/BookList");
public class BookList extends HttpServlet {
	private static final String query="SELECT EMPLOYEEID,BOOKNAME,BOOKCATEGORY,BOOKEDITION,NUMBEROFCOPIES,BOOKPRICE FROM BOOKDATA";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		try(Connection con= DriverManager.getConnection("jdbc:mysql:///bookregistration","root","Rushikesh2903");
				PreparedStatement ps= con.prepareStatement(query)){
				ResultSet rs=ps.executeQuery(query);
				out.println("<table border='1' align='center'>");
				out.println("<tr>");
				while(rs.next()) {
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getInt(5)+"</td>");
				out.println("<td>"+rs.getInt(6)+"</td>");
				out.println("</tr>");
				}
				out.println("</table>");
				
				
		
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.println("<h1>"+e.getMessage()+"</h1>");
				}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
