package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;

import dao.MemberDAO;


@WebServlet("/emailCheck")
public class EmailCheckController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 세션 분기
			HttpSession session = request.getSession();
			if(session.getAttribute("email") != null) { // 로그인 중인상태면 
				response.sendRedirect(request.getContextPath()+"/memberOne");
				return;
			}
			// 요청 분석
			String ckid = request.getParameter("ckid");
			if(ckid==null ||ckid.equals("")) {
				response.sendRedirect(request.getContextPath()+"/insertMember");
				return;
			}
			
			// 모델 호출
			String msg = "사용가능한 이메일 입니다";
			try (Connection conn = DBUtil.getConnection()){
				MemberDAO memberDAO = new MemberDAO();
				String id = memberDAO.idCheck(conn, ckid); // null 이면 사용가능, 아니면 불가
				if(id != null) {
					msg = "사용 불가능한 이메일 입니다";
					ckid= "";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 모델 뷰 호출
				
			request.setAttribute("ckid", ckid);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("WEB-INF/view/member/insertMember.jsp").forward(request, response);
		
	}

}
