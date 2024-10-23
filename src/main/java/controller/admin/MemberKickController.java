package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Cash;
import vo.Member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.CashDAO;
import dao.MemberDAO;


@WebServlet("/memberKick")
public class MemberKickController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String email= request.getParameter("email");
		request.setAttribute("email", email);
		request.getRequestDispatcher("/WEB-INF/view/admin/memberKick.jsp").forward(request, response);
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
			String msg = "강퇴 실패";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/admin/memberKick.jsp").forward(request, response);
			return;
		}
		Member m = new Member();
		m.setEmail(email);
		m.setPw(pw);
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			CashDAO cashDAO = new CashDAO();
			// 캐시 먼저 삭제
			int cashRow =cashDAO.deleteCashByEmail(email);
			
			MemberDAO memberDAO = new MemberDAO();
			
			if(cashRow == 1) { // 캐시삭제 성공
				// 캐시 삭제 후 멤버 삭제
				int emailRow = memberDAO.deleteMemberEmail(conn, m);
				if(emailRow==1) {// 멤버삭제성공
					int insertRow = memberDAO.insertOutid(conn, email);
					if(insertRow ==1) { // 탈퇴회원 db 추가
						response.sendRedirect(request.getContextPath()+"/memberList");
						conn.commit();
					}
				} 
			} else {
				// 캐시가 없는 멤버 삭제
				int emailRow = memberDAO.deleteMemberEmail(conn, m);
				if(emailRow==1) {// 멤버삭제성공
					int insertRow = memberDAO.insertOutid(conn, email);
					if(insertRow ==1) { // 탈퇴회원 db 추가
						response.sendRedirect(request.getContextPath()+"/memberList");
						conn.commit();
					}
				}
			}
		} catch (Exception e) {
			try {
				// 실패시 롤백
				System.out.println("롤백");
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

}
