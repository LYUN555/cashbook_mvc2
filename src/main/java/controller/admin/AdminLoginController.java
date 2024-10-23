package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Admin;

import java.io.IOException;
import java.sql.SQLException;

import dao.AdminDAO;


@WebServlet("/adminLogin")
public class AdminLoginController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 분기
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") != null) { 
			response.sendRedirect(request.getContextPath()+"/memberList");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/admin/adminLogin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") != null) { 
			response.sendRedirect(request.getContextPath()+"/memberList");
			return;
		}
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		// 유효성 검사
		String msg ="";
		if(adminId==null||adminId.equals("") || adminPw==null||adminPw.equals("")) {
			msg ="입력을 확인하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/admin/adminLogin.jsp").forward(request, response);
			return;
		}
		
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setAdminPw(adminPw);
		
		AdminDAO adminDAO = new AdminDAO();
		String loginAdmin = null;
		try {
			loginAdmin = adminDAO.Adminlogin(admin);
			if(loginAdmin == null) {
				msg ="로그인 실패";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/view/admin/adminLogin.jsp").forward(request, response);
				return;
			} else {
				session.setAttribute("adminId", adminId);
				response.sendRedirect(request.getContextPath()+"/memberList");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
