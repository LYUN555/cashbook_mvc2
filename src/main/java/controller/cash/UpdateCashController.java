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

/**
 * Servlet implementation class UpdateCashController
 */
@WebServlet("/updateCash")
public class UpdateCashController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		CashDAO cashDAO = new CashDAO();
		Cash cash = null;
		try {
			cash =cashDAO.selectCashOne(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("cash", cash);
		request.getRequestDispatcher("/WEB-INF/view/cash/updateCash.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		String cashDate = request.getParameter("cashDate"); 
		String strcashNo = request.getParameter("cashNo");
		String strmoney = request.getParameter("money");
		String kind = request.getParameter("kind");
		String memo = request.getParameter("memo");
		
		// 입력값 유효성 검사
		if(cashDate == null || cashDate.equals("")|| strcashNo == null || strcashNo.equals("")
	     		  ||strmoney == null || strmoney.equals("") || kind == null || kind.equals("")
															||memo == null || memo.equals("")) {
			
			String msg = "입력값을 확인하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/view/cash/updateCash.jsp").forward(request, response);
			return;												
		}
		int money = Integer.parseInt(strmoney);
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		
		Cash c = new Cash();
		c.setCashNo(cashNo);
		c.setKind(kind);
		c.setMoney(money);
		c.setMemo(memo);
		
		CashDAO cashDao = new CashDAO();
		int row = 0;
		try {
			row =cashDao.updateCash(c);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String[] arr = cashDate.split("-"); 
		response.sendRedirect(request.getContextPath()+"/cashByDate?y="+arr[0]+"&m="+arr[1]+"&d="+arr[2]);
		
	}

}
