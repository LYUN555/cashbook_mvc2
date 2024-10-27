package controller.notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Notice;

import java.io.IOException;
import java.sql.SQLException;

import dao.NoticeDAO;


@WebServlet("/updateNotice")
public class UpdateNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String adminId = (String)(session.getAttribute("adminId"));
		String strNoticeNo = request.getParameter("noticeNo");
		if(strNoticeNo == null || strNoticeNo.equals("")){
			response.sendRedirect(request.getContextPath()+"/home.jsp");
		    return;
		}
		int noticeNo = Integer.parseInt(strNoticeNo);
		
		// 공지번호 내용 호출
		NoticeDAO noticeDAO = new NoticeDAO();
		Notice notice = null;
		try {
			notice = noticeDAO.selectNoticeOne(noticeNo);
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/view/notice/updateNotice.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String adminId = (String)(session.getAttribute("adminId"));
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		// 유효성 검증
		if(noticeTitle==null || noticeTitle.equals("") ||
				noticeContent==null || noticeContent.equals("")){
			
			String msg="내용을 입력하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/notice/updateNotice.jsp").forward(request, response);
			return;
		}
		
		// 업데이트 파라미터값 저장
		Notice n = new Notice();
		n.setAdminId(adminId);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setNoticeNo(noticeNo);
		
		NoticeDAO noticeDAO = new NoticeDAO();
		try {
			int row = noticeDAO.updateNotice(n);
			if(row==1) {
				response.sendRedirect(request.getContextPath()+"/noticeOne?noticeNo="+noticeNo);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}

}
