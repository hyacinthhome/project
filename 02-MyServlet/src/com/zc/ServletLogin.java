package com.zc;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		/*if(username.equals("abc")&&password.equals("123")) {
			out.print("1");
		}
		else
			out.print("2");*/
	    java.sql.Connection conn=null;
	    java.sql.Statement st=null;
	    ResultSet rs=null;
	    PreparedStatement pst=null;
	    
	    String selectUsername="select username from tb_user";
	    String selectPassword="select password from tb_user where username = ?";
	    
	    
	    //获取数据库连接
	    try {
			conn=new GetConnection().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(selectUsername);
			List<String> usernameList =new ArrayList<String>();
			while(rs.next()){
				usernameList.add(rs.getString(1));
			}
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			if(usernameList.contains(username)) {
				List<String> passwordList=new ArrayList<String>();
				pst=conn.prepareStatement(selectPassword);
				pst.setString(1, username);
				rs=pst.executeQuery();
				while(rs.next()) {
					passwordList.add(rs.getString(1));
				}
				if(passwordList.get(0).equals(password)) {
					out.print("1");   //成功登录
				}
				else {
					out.print("2");   //密码错误
				}
				if(rs!=null) {
					rs.close();
				}
				if(pst!=null) {
					pst.close();
				}
			}
			else {
				out.print("3");   //用户名不存在
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	    out.flush();
	    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
