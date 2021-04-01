package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _24_returnBookForm implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		UserVO userVO = ((UserVO) session.getAttribute("loginUser"));
		if(userVO.getIsManager() != 1){
			return "/06_login.jsp";
		}

		return "/27_returnBookForm.jsp";
	}
}
