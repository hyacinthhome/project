package com.zc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String stunum=request.getParameter("stunum");
		String gender=request.getParameter("gender");
		String phonenumber=request.getParameter("phonenumber");
		String email=request.getParameter("email");
		
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
		
		List<String> usernameList=new ArrayList<String>();
		List<String> stunumList=new ArrayList<String>();
		java.sql.Connection conn=null;
		try {
			conn=new GetConnection().getConnection();
			String selectusername="select username from tb_user";
			String selectstunum="select stunum from tb_user";
			st=conn.createStatement();
			rs=st.executeQuery(selectusername);
			while(rs.next()) {
				usernameList.add(rs.getString(1));
			}
			rs=st.executeQuery(selectstunum);
			while(rs.next()) {
				stunumList.add(rs.getString(1));
			}
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}

			if(usernameList.contains(username)) {
				out.print("3");
			}else if(stunumList.contains(stunum)) {
				out.print("2");
			}else {
				String insert="insert into tb_user(stunum,username,password,gender,phonenumber,email)"
						+ "values(?,?,?,?,?,?)";
				try {
					pst=conn.prepareStatement(insert);
					pst.setString(1, stunum);
					pst.setString(2, username);
					pst.setString(3, password);
					pst.setString(4, gender);
					pst.setString(5, phonenumber);
					pst.setString(6, email);
					pst.execute();
					out.print("1");
					pst.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
