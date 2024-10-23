package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Member;

import java.io.IOException;

import dao.MemberDAO;


@WebServlet("/insertMember")
public class InsertMemberController extends HttpServlet {
	// 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) { // 로그인 중인상태면 
			response.sendRedirect(request.getContextPath()+"/memberOne");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/member/insertMember.jsp").forward(request, response);
	}

	//2) 폼 액션 : 회원가입
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) { // 로그인 중인상태면 
			response.sendRedirect(request.getContextPath()+"/memberOne");
			return;
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String rePw = request.getParameter("repw");
		String birth = request.getParameter("birth");
		System.out.println(id+pw+rePw+birth);
		
		if(id==null || id.equals("") || pw==null ||pw.equals("") || 
				rePw==null ||!rePw.equals(pw) || birth==null ||birth.equals("")) {
			request.setAttribute("msg", "입력을 확인하세요.");
			request.getRequestDispatcher("/WEB-INF/view/member/insertMember.jsp").forward(request, response);
			return;
		}
		
		Member m = new Member();
		m.setEmail(id);
		m.setPw(pw);
		m.setBirth(birth);
		
		try {
			MemberDAO memberDAO = new MemberDAO();
			int row = memberDAO.insertMember(m);
			if(row ==0) {
				request.setAttribute("msg", "회원가입에 실패했습니다.");
				request.getRequestDispatcher("/WEB-INF/view/member/insertMember.jsp").forward(request, response);
				return;
			} else {
				System.out.println("가입 성공");
				response.sendRedirect(request.getContextPath()+"/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
