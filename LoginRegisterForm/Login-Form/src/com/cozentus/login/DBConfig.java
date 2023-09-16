package com.cozentus.login;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/DBConfig")
public class DBConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Connection con = null;
	static {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginform", "root", "password");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	static Connection getCon() {
		return con;
	}
}
