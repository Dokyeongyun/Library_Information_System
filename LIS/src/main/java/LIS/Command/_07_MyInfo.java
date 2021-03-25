package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _07_MyInfo implements CommandAction {
	UserDAO userDAO = UserDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");

		if(user==null){
			request.setAttribute("isLogin", "false");
			return "/11_myInfo.jsp";
		}

		user = userDAO.getUser(user);

		request.setAttribute("userInfo", user);
		return "/11_myInfo.jsp";
	}
}
