package controller.cash;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import vo.Cash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.CashDAO;


@WebServlet("/deleteCash")
public class DeleteCashController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		String cashDate = request.getParameter("cashDate");
		
		// 캐시넘버 입력
		Cash c = new Cash();
		c.setCashNo(cashNo);
		
		// 삭제
		CashDAO cashDAO = new CashDAO();
		String[] arr = cashDate.split("-");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int row = cashDAO.deleteCash(conn,c);
			if(row == 0) {
				String msg = "삭제실패";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/view/cash/cashByDate.jsp?y="+arr[0]+"&m="+arr[1]+"&d="+arr[2]).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/cashByDate?y="+arr[0]+"&m="+arr[1]+"&d="+arr[2]);
			}						
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
