package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _16_DeleteArticle implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		int articleId = Integer.parseInt(request.getParameter("articleId"));

		ArticleVO article = boardDAO.getArticle(articleId);

		int boardId = article.getBoardId();
		int result = boardDAO.deleteArticle(article);

		request.setAttribute("boardId", boardId);
		request.setAttribute("result", result);
		return "/19_deleteArticlePro.jsp";
	}
}
