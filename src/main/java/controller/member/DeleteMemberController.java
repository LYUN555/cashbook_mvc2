package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;


@WebServlet("/deleteMember")
public class DeleteMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 분기
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		// 삭제 페이지로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/member/deleteMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 분기
        HttpSession session = request.getSession();
        if(session.getAttribute("email") == null) { 
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        String email = (String) session.getAttribute("email");
        String pw = request.getParameter("pw");
        
        // 모델 호출
        Member m = new Member();
        m.setEmail(email);
        m.setPw(pw);
        
        Connection conn = null;
        try {
			conn = DBUtil.getConnection();
			// 코드가 자동 커밋 되지 않도록 설정
			conn.setAutoCommit(false);
			// 트랜잭션처리
			MemberDAO memberDAO = new MemberDAO();
			int insertRow = memberDAO.insertOutid(conn, email);
			if(insertRow == 1) {
				int deleteRow = memberDAO.deleteMemberEmail(conn, m);
				if(deleteRow==1) {
					System.out.println("회원탈퇴 성공");
					session.invalidate();
					response.sendRedirect(request.getContextPath()+"/home");
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				System.out.println("롤백 발생");
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
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
