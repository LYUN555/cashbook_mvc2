package controller.notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Comment;

import java.io.IOException;
import java.sql.SQLException;

import dao.CommentDAO;


@WebServlet("/insertComment")
public class InsertCommentController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String adminId = (String) session.getAttribute("adminId");
		if(email == null && adminId == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		String commentWriter = request.getParameter("commentWriter");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String commentContent = request.getParameter("commentContent");
		// 유효성 검증
		if(commentContent==null || commentContent.equals("")){
			String msg="댓글을 입력하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("WEB-INF/view/notice/noticeOne.jsp?noticeNo="+noticeNo).forward(request, response);
			return;
		}
		
		Comment c = new Comment();
		c.setNoticeNo(noticeNo);
		c.setCommentContent(commentContent);
		c.setCommentWriter(commentWriter);
		
		CommentDAO commentDAO = new CommentDAO();
		try {
			int row = commentDAO.insertCommentNotice(c);
			if(row == 1) {
				response.sendRedirect(request.getContextPath()+"/noticeOne?noticeNo="+noticeNo);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
