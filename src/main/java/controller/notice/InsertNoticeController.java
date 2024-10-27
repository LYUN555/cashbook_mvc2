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


@WebServlet("/insertNotice")
public class InsertNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String adminId = (String)(session.getAttribute("adminId"));
		
		request.setAttribute("adminId", adminId);
		request.getRequestDispatcher("/WEB-INF/view/notice/insertNotice.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		String adminId = (String)(session.getAttribute("adminId"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");	
		// 유효성 검사
		if(noticeTitle==null||noticeTitle.equals("")||
				noticeContent==null||noticeContent.equals("")){
			String msg="입력을 확인하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/notice/insertNotice.jsp").forward(request, response);
			return;
		}
		
		// NoticeDAO.insertNotice(Notice)
		Notice n = new Notice();
		n.setAdminId(adminId);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		NoticeDAO noticeDAO = new NoticeDAO();
		try {
			int row = noticeDAO.insertNotice(n);
			if(row == 1) {
				response.sendRedirect(request.getContextPath()+"/home");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
