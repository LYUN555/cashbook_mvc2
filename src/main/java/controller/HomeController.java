package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CommentDAO;
import dao.NoticeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Notice;
import vo.Page;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 정보 전달
		String email = (String) request.getSession().getAttribute("email");
		String adminId = (String) request.getSession().getAttribute("adminId");
		
		
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		
		Page p = new Page();
		p.setCurrentPage(currentPage);
		p.setRowPerPage(rowPerPage);
		
		NoticeDAO noticeDAO = new NoticeDAO();
		List<Notice> noticeList = null;
		CommentDAO commentDAO = new CommentDAO();
		int totalRow = 0;
		// 게시판 댓글수 구현
		Map<Integer, Integer> commentCountList = new HashMap<>();
		int commentCount = 0;
		try {
			noticeList = noticeDAO.selectNoticeListByPage(p);
			totalRow = noticeDAO.totalCount();
			for (Notice notice : noticeList) {
                commentCount = commentDAO.commentCountByNotice(notice.getNoticeNo());
                commentCountList.put(notice.getNoticeNo(), commentCount); 
            }
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		int lastPage = totalRow/p.getRowPerPage();
		
		if(totalRow % p.getRowPerPage() !=0){
			lastPage++;
		}
		
		// home.jsp 로 전달할 데이터
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("commentCount", commentCount);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("email", email);
		request.setAttribute("adminId", adminId);
		
		// 포워딩
		request.getRequestDispatcher("WEB-INF/view/home.jsp").forward(request, response);
	}

}
