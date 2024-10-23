package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Member;
import vo.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;


@WebServlet("/memberList")
public class MemberListController extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("adminId") == null) { 
			response.sendRedirect(request.getContextPath()+"/adminLogin");
			return;
		}
		MemberDAO memberDAO = new MemberDAO();
		Page p = new Page();
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null){
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		p.setCurrentPage(currentPage);
		p.setRowPerPage(rowPerPage);
		
		// 멤버리스트 가져오기
		List<Member> list = null;
		try {
			list = memberDAO.selectMemberList(p);
			// 페이지 갯수 구하기
			int totalRow = memberDAO.memberCount();
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage !=0){
				lastPage++;
			}
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lastPage", lastPage);
			request.getRequestDispatcher("/WEB-INF/view/admin/memberList.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
