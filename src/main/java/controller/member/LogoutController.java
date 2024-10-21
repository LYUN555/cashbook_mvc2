package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/logout")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null){
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		request.getSession().invalidate();
		System.out.println("로그아웃 성공");
		response.sendRedirect(request.getContextPath()+"/home");
	}

}
