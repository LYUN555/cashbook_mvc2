package controller.notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Comment;
import vo.Notice;
import vo.Page;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CommentDAO;
import dao.NoticeDAO;


@WebServlet("/noticeOne")
public class NoticeOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String adminId = (String) session.getAttribute("adminId");
		if(email == null && adminId == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		// 게시판 번호 검증
		String strNoticeNo = request.getParameter("noticeNo");
		if(strNoticeNo == null || strNoticeNo.equals("")){
			response.sendRedirect(request.getContextPath()+"/home.jsp");
		    return;
		}
		int noticeNo = Integer.parseInt(strNoticeNo);
		
		// 댓글 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		Page p = new Page();
		p.setCurrentPage(currentPage);
		p.setRowPerPage(rowPerPage);
		
		NoticeDAO noticeDAO = new NoticeDAO();
		Notice notice = null;
		CommentDAO commentDAO = new CommentDAO();
		List<Comment> commentList = null;
		
		int totalRow = 0;
		int lastPage = 0;
		Connection conn = null;
		try {
			// 상세정보
			notice = noticeDAO.selectNoticeOne(noticeNo);
			// 댓글 리스트
			commentList = commentDAO.selectCommentListByNotice(noticeNo,p);
			conn = DBUtil.getConnection();
			totalRow = commentDAO.totalCountComment(conn, noticeNo);
			lastPage = totalRow/p.getRowPerPage();
			
			if(totalRow % p.getRowPerPage() !=0){
				lastPage++;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("email", email);
		request.setAttribute("adminId", adminId);
		request.setAttribute("notice", notice);
		request.setAttribute("noticeNo", noticeNo);
		request.setAttribute("commentList", commentList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeOne.jsp").forward(request, response);
	}

}
