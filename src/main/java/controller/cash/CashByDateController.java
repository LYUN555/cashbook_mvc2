package controller.cash;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Cash;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.CashDAO;

@WebServlet("/cashByDate")
public class CashByDateController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) { // 로그인 전이면
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		String email = (String) (session.getAttribute("email"));

		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		// 입력값 유효성
		if (y == null || m == null || d == null || y.equals("") || m.equals("") || d.equals("")) {
			response.sendRedirect(request.getContextPath() + "/CashByMonth");
			return;
		}
		String cashDate = y+"-"+m+"-"+d;
		
		Cash c = new Cash();
		c.setEmail(email);
		c.setCashDate(cashDate);
		
		CashDAO cashDAO = new CashDAO();
		List<Cash> list = null;
		try {
			list = cashDAO.selectCashListByDate(c);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("email", email);
		request.setAttribute("cashDate", cashDate);
		request.setAttribute("list", list);
		// 포워딩
		request.getRequestDispatcher("WEB-INF/view/cash/cashByDate.jsp").forward(request, response);
	}

}
