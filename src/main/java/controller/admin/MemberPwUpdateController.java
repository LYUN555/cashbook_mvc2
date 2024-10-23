package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Member;

import java.io.IOException;
import java.sql.SQLException;

import dao.AdminDAO;
import dao.MemberDAO;


@WebServlet("/memberPwUpdate")
public class MemberPwUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String email= request.getParameter("email");
		request.setAttribute("email", email);
		request.getRequestDispatcher("/WEB-INF/view/admin/memberPwUpdate.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String email= request.getParameter("email");
		String pw = request.getParameter("pw");
		if(pw==null || pw.equals("")) {
			String msg = "변경 실패";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/admin/memberPwUpdate.jsp").forward(request, response);
			return;
		}
		Member m = new Member();
		m.setEmail(email);
		m.setPw(pw);
		
		AdminDAO adminDAO = new AdminDAO();
		try {
			int row = adminDAO.memberPwUpdate(m);
			if(row == 1) {
				response.sendRedirect(request.getContextPath()+"/memberList");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
