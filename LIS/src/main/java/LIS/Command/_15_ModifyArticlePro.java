package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class _15_ModifyArticlePro implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		String writer = ((UserVO) session.getAttribute("loginUser")).getUserId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		ArticleVO article = new ArticleVO();
		article.setBoardId(boardId);
		article.setArticleId(articleId);
		article.setWriter(writer);
		article.setTitle(title);
		article.setContent(content);

		int result = boardDAO.modifyArticle(article);
		if(result==1){
			request.setAttribute("articleId", articleId);
		}else{
			request.setAttribute("articleId", -1);
		}
		return "/18_modifyArticlePro.jsp";
	}
}
