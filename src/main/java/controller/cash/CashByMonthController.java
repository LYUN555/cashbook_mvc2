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
import java.util.Calendar;
import java.util.List;

import dao.CashDAO;


@WebServlet("/cashByMonth")
public class CashByMonthController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 분기
		HttpSession session = request.getSession();
		if(session.getAttribute("email") == null) { 
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		String email = (String) session.getAttribute("email");
		
		Calendar target = Calendar.getInstance();
		
		// 날짜 1일로 세팅
		target.set(Calendar.DATE, 1);
		
		// 요청받은 년도와 월 저장
		if(request.getParameter("targetYear")!= null && request.getParameter("targetMonth")!=null) {
			target.set(Calendar.YEAR, Integer.parseInt(request.getParameter("targetYear")));
			target.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth"))-1);
		}
		int targetYear = target.get(Calendar.YEAR);
		int targetMonth = target.get(Calendar.MONTH)+1;
		
		int day = target.get(Calendar.DAY_OF_WEEK);
		int beginBlank = day - 1;
		int lastDay = target.getActualMaximum(Calendar.DATE);
		int afterBlank = 0;
		int totalCell = beginBlank+lastDay+afterBlank;
		
		if(totalCell % 7 != 0) {
			afterBlank = 7 - (totalCell %7);
			totalCell += afterBlank;
		}
		
		// 모델 호출
		CashDAO cashDAO = new CashDAO();
		List<Cash> list = null;
		try {
			list = cashDAO.selectCashListByMonth(email, targetYear, targetMonth);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// 이번달 총 수익/지출 구하기
		int totalIncome = 0;
		int totalExpense = 0;
		for(Cash c: list){
			if(c.getKind().equals("수입")){
				totalIncome += c.getMoney();
			}else if(c.getKind().equals("지출")){
				totalExpense += c.getMoney();
			}
		}
		// 포워딩
		request.setAttribute("email", email);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("list", list);
        request.setAttribute("totalIncome", totalIncome);
        request.setAttribute("totalExpense", totalExpense);
        request.setAttribute("beginBlank", beginBlank);
        request.setAttribute("totalCell", totalCell);
		
        request.getRequestDispatcher("WEB-INF/view/cash/cashByMonth.jsp").forward(request, response);
	}

}
