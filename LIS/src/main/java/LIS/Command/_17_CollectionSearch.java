package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _17_CollectionSearch implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/20_collectionSearch.jsp";
	}
}
