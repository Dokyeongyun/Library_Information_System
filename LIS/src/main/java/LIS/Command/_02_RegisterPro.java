package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class _02_RegisterPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone_pre = request.getParameter("phone_pre");
		String phone = request.getParameter("phone");
		String stuNum = request.getParameter("stuNum");
		String dept = request.getParameter("dept");

		UserVO registerUser = new UserVO();
		registerUser.setUserId(userId);
		registerUser.setPassword(password);
		registerUser.setName(name);
		registerUser.setEmail(email);
		registerUser.setPhone(phone_pre+phone);
		registerUser.setStuNum(Integer.parseInt(stuNum));
		registerUser.setDept(dept);

		UserDAO userDAO = UserDAO.getInstance();
		int result = userDAO.register(registerUser);

		request.setAttribute("result", result);

		return "/05_registerPro.jsp";
	}
}
