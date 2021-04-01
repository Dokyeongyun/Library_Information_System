package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.LoanBook;
import LIS.Model.VO.LoanVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _23_ExtendLoanPeriod implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		String[] loanIdArr = request.getParameterValues("loanId");

		List<LoanVO> loanList = new ArrayList<>();
		for (String s : loanIdArr) {
			LoanVO loan = new LoanVO();
			loan.setLoanId(Integer.parseInt(s));
			loanList.add(loan);
		}

		int result = loanDAO.extendLoanPeriod(loanList);
		request.setAttribute("result", result);

		return "/26_extendLoanPeriodPro.jsp";
	}
}
