package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.BoardVO;
import LIS.Model.VO.CommentVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class _14_ModifyArticle implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleId = Integer.parseInt(request.getParameter("articleId"));

		ArticleVO article = boardDAO.getArticle(articleId);
		List<BoardVO> list = boardDAO.getBoardTypes();

		request.setAttribute("boardList", list);
		request.setAttribute("article", article);
		request.setAttribute("isModify", true);

		return "/14_writeArticle.jsp";
	}
}
