package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.BoardVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class _12_ShowArticle implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int articleId = Integer.parseInt(request.getParameter("articleId"));

		ArticleVO article = boardDAO.getArticle(articleId);
		request.setAttribute("article", article);

		return "/16_showArticle.jsp";
	}
}
