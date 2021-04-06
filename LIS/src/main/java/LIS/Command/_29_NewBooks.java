package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.SearchHistoryDAO;
import LIS.Model.VO.SearchHistoryVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class _29_NewBooks implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		return "/31_newBooks.jsp";
	}
}
