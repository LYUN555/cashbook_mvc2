package controller.notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Comment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDAO;


@WebServlet("/deleteComment")
public class DeleteCommentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String adminId = (String) session.getAttribute("adminId");
		if(email == null && adminId == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		// 유효성 검사
		String strNoticeNo = request.getParameter("noticeNo");
		String strCommentNo = request.getParameter("commentNo");
		if(strNoticeNo == null || strNoticeNo.equals("") ||
				strCommentNo == null || strCommentNo.equals("") ){
			
			response.sendRedirect(request.getContextPath()+"/home.jsp");
		    return;
		}
		int noticeNo = Integer.parseInt(strNoticeNo);
		int commentNo = Integer.parseInt(strCommentNo);
		
		Comment c= new Comment();
		c.setNoticeNo(noticeNo);
		c.setCommentNo(commentNo);
		
		CommentDAO commentDAO = new CommentDAO();
		try {
			int row = commentDAO.deleteComment(c);
			if(row == 1) {
				response.sendRedirect(request.getContextPath()+"/noticeOne?noticeNo="+noticeNo);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		 
		}
	}

}
