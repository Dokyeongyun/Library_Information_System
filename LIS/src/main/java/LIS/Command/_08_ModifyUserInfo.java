package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _08_ModifyUserInfo implements CommandAction {
	UserDAO userDAO = UserDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");

		if(user==null){
			request.setAttribute("isLogin", "false");
			return "/12_modifyUserInfoPro.jsp";
		}

		String userId = request.getParameter("userId");
		String phone_pre = request.getParameter("phone_pre");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		UserVO modifyUserInfo = new UserVO();
		modifyUserInfo.setUserId(userId);
		modifyUserInfo.setPhone(phone_pre+phone);
		modifyUserInfo.setEmail(email);

		int result = userDAO.modifyUser(modifyUserInfo);

		request.setAttribute("result",result);

		return "/12_modifyUserInfoPro.jsp";
	}
}
