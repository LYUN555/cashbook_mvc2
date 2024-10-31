package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Member;

import java.io.IOException;
import java.sql.SQLException;

import dao.MemberDAO;


@WebServlet("/updateMemberPw")
public class UpdateMemberPwController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) {  
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		String email = (String)session.getAttribute("email");
		
		request.setAttribute("email", email);
		request.getRequestDispatcher("WEB-INF/view/member/updateMemberPw.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		String email = (String)session.getAttribute("email");
		String prePw = request.getParameter("prepw");
		String newPw = request.getParameter("newpw");
		
		System.out.println(email+prePw+newPw);
		String msg = "";
		if(prePw == null || prePw.length()<4 || newPw == null || newPw.length()<4 || prePw.equals(newPw)) {
			msg = "입력을 확인하세요";
			request.setAttribute("email", email);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("WEB-INF/view/member/updateMemberPw.jsp").forward(request, response);
			return;
		}
		
		MemberDAO memberDAO = new MemberDAO();
		try {
			int row = memberDAO.updateMemberPw(email, prePw, newPw);
			if(row==1) {
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/home");
			} else {
				msg = "변경 실패";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("WEB-INF/view/member/updateMemberPw.jsp").forward(request, response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
