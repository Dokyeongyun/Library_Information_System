package LIS.Command;

import LIS.Controller.CommandAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class _02_Login implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/05_login.jsp";
	}
}
