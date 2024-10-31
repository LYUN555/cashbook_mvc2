package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Member;
import vo.Notice;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;
import dao.NoticeDAO;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) { 
			request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) { 
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		String email = request.getParameter("email");
		String pw = request.getParameter("memberPw");
		
		if(email ==null || email.equals("") || email ==null || email.equals("") ) {
			response.sendRedirect(request.getContextPath()+"/home?msg=");
			return;
		}
		
		Member member = new Member();
		member.setEmail(email);
		member.setPw(pw);
		
		MemberDAO memberDAO = new MemberDAO();
		
		// 자동닫기
		try (Connection conn = DBUtil.getConnection()){
			email = memberDAO.login(conn,member);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// 로그인 실패
		if(email == null){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} else {
			// 로그인 성공
			session.setAttribute("email", email); // 세션에 이메일 저장
			// 홈 컨트롤러로 전송
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
	}

}
