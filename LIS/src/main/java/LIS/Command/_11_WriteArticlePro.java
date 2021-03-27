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


public class _11_WriteArticlePro implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String writer = ((UserVO) session.getAttribute("loginUser")).getUserId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		ArticleVO article = new ArticleVO();
		article.setBoardId(boardId);
		article.setWriter(writer);
		article.setTitle(title);
		article.setContent(content);

		int articleId = boardDAO.writeArticle(article);

		request.setAttribute("articleId", articleId);
		return "/15_writeArticlePro.jsp";
	}
}
