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

import dao.CashDAO;

@WebServlet("/insertCash")
public class InsertCashController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		// 입력값
		String cashDate = request.getParameter("cashDate");
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/cash/insertCash.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		String email = (String) session.getAttribute("email");

		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		String strMoney = request.getParameter("money");
		String memo = request.getParameter("memo");

		// cashDate 는"0000-0-0"
		String[] arr = cashDate.split("-");
		// 유효성 검사
		String msg = "";
		if (cashDate == null || cashDate.equals("") || kind == null || kind.equals("") || strMoney == null
				|| strMoney.equals("") || memo == null || memo.equals("")) {
			msg = "잘못된 입력입니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/cash/insertCash.jsp").forward(request, response);
			return;
		}
		int money = Integer.parseInt(strMoney);
		Cash c = new Cash();
		c.setEmail(email);
		c.setCashDate(cashDate);
		c.setKind(kind);
		c.setMoney(money);
		c.setMemo(memo);

		CashDAO cashDAO = new CashDAO();
		try {
			int row = cashDAO.insertCash(c);
			if (row == 1) {
				response.sendRedirect(request.getContextPath()+"/cashByDate?y="+arr[0]+"&m="+arr[1]+"&d="+arr[2]);return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
