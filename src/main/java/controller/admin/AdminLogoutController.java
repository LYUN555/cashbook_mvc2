package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/adminLogout")
public class AdminLogoutController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String adminId = (String)session.getAttribute("adminId");
		request.getSession().invalidate();
		System.out.println("로그아웃 성공");
		response.sendRedirect(request.getContextPath()+"/adminLogin");
	}

}
