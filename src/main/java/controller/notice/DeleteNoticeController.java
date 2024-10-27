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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDAO;
import dao.NoticeDAO;


@WebServlet("/deleteNotice")
public class DeleteNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		
		String strNoticeNo = request.getParameter("noticeNo");
		if(strNoticeNo == null || strNoticeNo.equals("")){
			response.sendRedirect(request.getContextPath()+"/home");
		    return;
		}
		int noticeNo = Integer.parseInt(strNoticeNo);
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 트랜잭션 처리
			conn.setAutoCommit(false);
			
			Notice n = new Notice();
			n.setNoticeNo(noticeNo);
			NoticeDAO noticeDAO = new NoticeDAO();
			// 참조 db 코멘트부터 삭제
			Comment c = new Comment();
			c.setNoticeNo(noticeNo);
			CommentDAO commentDAO = new CommentDAO();
			int count = commentDAO.totalCountComment(conn, noticeNo);
			// 댓글 한개이상 전부 삭제
			if(count > 0) {
				int row = commentDAO.deleteCommentsByNotice(conn, c);
				System.out.println("CommentDAO row :"+row);
				if(row > 0) {
					// 댓글 삭제 후 공지글 삭제
					noticeDAO.deleteNotice(conn, n);
					response.sendRedirect(request.getContextPath()+"/home");
				} else {
					System.out.println("공지글 삭제 실패 롤백");
					conn.rollback();
					response.sendRedirect(request.getContextPath()+"/noticeOne?noticeNo="+noticeNo);
				}
			} else { // 댓글이 없는 게시물일때 바로 삭제
				noticeDAO.deleteNotice(conn, n);
				response.sendRedirect(request.getContextPath()+"/home");
			}
			System.out.println("커밋성공");
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				System.out.println("롤백발생");
				conn.rollback();
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
