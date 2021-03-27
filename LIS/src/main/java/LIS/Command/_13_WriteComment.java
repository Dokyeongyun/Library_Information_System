package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.CommentVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class _13_WriteComment implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		int articleId = Integer.parseInt(request.getParameter("articleId"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		CommentVO comment = new CommentVO();
		comment.setArticleId(articleId);
		comment.setWriter(writer);
		comment.setContent(content);

		int result = boardDAO.writeComment(comment);

		request.setAttribute("result", result);
		request.setAttribute("articleId", articleId);

		return "17_writeCommentPro.jsp";
	}
}
