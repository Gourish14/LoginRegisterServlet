package com.cozentus.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	  String email = req.getParameter("email");
	  String password = req.getParameter("password");
	  try (Connection con = DBConfig.getCon();){
		  String query = "select * from login where email =? and password =?";
		  PreparedStatement ps = con.prepareStatement(query);
		  ps.setString(1, email);
		  ps.setString(2, password);
		  ResultSet rs  = ps.executeQuery();
		  if (rs.next()) {
			 RequestDispatcher rd = req.getRequestDispatcher("DisplayServlet");
			 rd.forward(req, res);
		  }
		  else {
			  res.setContentType("text/html");
			  PrintWriter pw = res.getWriter();
			  pw.print("<h1>invalid credential!!!!<h1>");
			  RequestDispatcher rd = req.getRequestDispatcher("/LoginServlet");
				 rd.forward(req, res);
		  }
	  } catch (Exception e) {
		e.printStackTrace();
	}
  }
}
