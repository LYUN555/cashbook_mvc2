package controller.cash;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import dao.CashDAO;

/**
 * Servlet implementation class CashByYearListController
 */
@WebServlet("/cashByYearList")
public class CashByYearListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 분기
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		String email = (String) session.getAttribute("email");
		
		// 년월 수입 지출 목록
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		if (request.getParameter("year") != null) {
			year = Integer.parseInt(request.getParameter("year"));
		}	
		CashDAO cashDAO = new CashDAO();
		// 페이징
		int max = 0;
		int min = 0;
		List<Map<String,Object>> list = null;
		Map<String, Integer> maxMin = null;
		try {
			list = cashDAO.selectCashYearList(email, year);
			// 페이징
			maxMin = cashDAO.selectCashMinMaxYear();
			max = maxMin.get("max");
			min = maxMin.get("min");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("email", email);
		request.setAttribute("year", year);
		request.setAttribute("list", list);
		request.setAttribute("max", max);
		request.setAttribute("min", min);
		request.getRequestDispatcher("WEB-INF/view/cash/cashByYearList.jsp").forward(request, response);
	}

}
