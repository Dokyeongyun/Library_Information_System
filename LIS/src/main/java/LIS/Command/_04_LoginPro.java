package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _04_LoginPro implements CommandAction {
	UserDAO userDAO = UserDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		UserVO loginUser = new UserVO();
		loginUser.setUserId(userId);
		loginUser.setPassword(password);

		int result = userDAO.login(loginUser);

		if(result == 1){
			UserVO sessionUser = userDAO.getUser(loginUser);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", sessionUser);
		}

		request.setAttribute("result", result);

		return "/07_loginPro.jsp";
	}
}
