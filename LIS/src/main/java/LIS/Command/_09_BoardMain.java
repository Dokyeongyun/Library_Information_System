package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BoardDAO;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.BoardVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class _09_BoardMain implements CommandAction {
	BoardDAO boardDAO = BoardDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		List<ArticleVO> articleList = boardDAO.getAllArticles(boardId);
		List<BoardVO> boardList = boardDAO.getBoardTypes();

		request.setAttribute("articleList", articleList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardId", boardId);
		return "/13_boardMain.jsp";
	}
}
