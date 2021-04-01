package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.LoanBook;
import LIS.Model.VO.LoanVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class _26_ShowLoanHistory implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		UserVO user = (UserVO) session.getAttribute("loginUser");

		List<LoanBook> loanList = loanDAO.getMyLoanHistory(user);
		request.setAttribute("loanList", loanList);

		return "/29_showLoanHistory.jsp";
	}
}
