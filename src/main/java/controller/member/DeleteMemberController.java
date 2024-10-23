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

import dao.CashDAO;
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
            conn.setAutoCommit(false);
            
            CashDAO cashDAO = new CashDAO();
            MemberDAO memberDAO = new MemberDAO();
            
            // 캐시 먼저 삭제
            int cashRow = cashDAO.deleteCashByEmail(email);
            int emailRow = memberDAO.deleteMemberEmail(conn, m);
            if (emailRow == 1) {
                // 멤버 삭제 성공 시 탈퇴 회원 DB 추가
                int insertRow = memberDAO.insertOutid(conn, email);
                if (insertRow == 1) {
                    // 모든 작업이 성공하면 커밋
                	conn.commit();
                    response.sendRedirect(request.getContextPath() + "/memberList");
                } else {
                    conn.rollback(); // 탈퇴 회원 DB 추가 실패 시 롤백
                }
            } else {
                conn.rollback(); // 멤버 삭제 실패 시 롤백
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback(); // 실패 시 롤백
                    System.out.println("롤백 발생");
                }
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
