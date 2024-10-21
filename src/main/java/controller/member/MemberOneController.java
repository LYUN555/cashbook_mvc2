package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/memberOne")
public class MemberOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) { // 로그인 전이면
			response.sendRedirect(request.getContextPath()+"/home");
            return;
		}
		String email = request.getParameter("email");
		
		request.setAttribute("email", email);
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
	}

}
