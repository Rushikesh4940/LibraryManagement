package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query="INSERT INTO BOOKDATA(EMPLOYEEID,BOOKNAME,BOOKCATEGORY,BOOKEDITION,NUMBEROFCOPIES,BOOKPRICE)VALUE(?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String id=req.getParameter("EmployeeID");
		String BookCategory=req.getParameter("BookCategory");
		String BookName=req.getParameter("BookName");
		String BookEdition=req.getParameter("BookEdition");
		int BookPrice=Integer.parseInt(req.getParameter("Bookprice"));
		int NumberpfCopies=Integer.parseInt(req.getParameter("NumbeerOfCopies"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		try(Connection con= DriverManager.getConnection("jdbc:mysql:///bookregistration","root","Rushikesh2903");
				PreparedStatement ps= con.prepareStatement(query)){
				ps.setString(1,id);
				ps.setString(2, BookName);
				ps.setString(3,BookCategory);
				ps.setString(4,BookEdition);
				ps.setInt(5, NumberpfCopies);
				ps.setInt(6, BookPrice);
				int count=ps.executeUpdate();
				if(count==1) {
					out.println("<h2> RECORD IS REGISTERED SUCESSFULLY</h2>");
				}else {
					out.println("<h2> RECORD IS NOT REGISTERED</h2>");
				}
		
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
