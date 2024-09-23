package com.test.lesson03;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.MysqlService;

@WebServlet("/lesson03/insert_ex02")
public class InsertEx02 extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		// response header 생략 
		
		// request parameter 꺼내기
		String name=  request.getParameter("name");
		String yyyymmdd=  request.getParameter("yyyymmdd");
		String email=  request.getParameter("email");
		String introduce=  request.getParameter("introduce");
		
		// DB 연결
		MysqlService ms = MysqlService.getInstance();
		ms.connect();
		
		// insert 쿼리 수행
		String inserQuery = "insert into `new_user`"
				+ "(`name`, `yyyymmdd`, `email`, `introduce`)"
				+ "values"
				+ "('" + name + "', '" + yyyymmdd + "', '" + email + "', '" + introduce + "')";
		try {
			ms.update(inserQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// DB 연결 해제
		ms.disconnect();
		
		// 유저 목록 화면으로 이동 => redirect (302번으로 나옴) -> 최종 화면에서 200 ok
		response.sendRedirect("/lesson03/ex02_1.jsp");
		
	}
}
