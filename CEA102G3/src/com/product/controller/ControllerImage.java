package com.product.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;



import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ControllerImage extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		//System.out.println(productPicCol+"  "+product+"  "+productNoCol+"  "+productNo);
//		"SELECT product_Pic1 FROM guideme.product WHERE product_No =25" 
		try {
			java.sql.Statement stmt = con.createStatement();
			String picCol = req.getParameter("picCol").trim();
			String table = "product";
			String PKCol = "product_no";
			String PKNumber = req.getParameter("PKNumber").trim();

			String ForProductTable = "SELECT "+ picCol +" FROM "+ table +" WHERE "+ PKCol +" = " + PKNumber;

			ResultSet rs = stmt.executeQuery(ForProductTable);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(picCol));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/resources/img/woman-wearing-blue-straw-hat-lying-on-beach-and-looking-at-seascape.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/resources/img/woman-wearing-blue-straw-hat-lying-on-beach-and-looking-at-seascape.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			
		}
	}

	public void init() throws ServletException {
		
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
				con = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}