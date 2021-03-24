package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _05_Logout implements CommandAction {
	UserDAO userDAO = UserDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();

		return "/08_logoutPro.jsp";
	}
}
