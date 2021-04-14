package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.DAO.SearchHistoryDAO;
import LIS.Model.VO.BookVO;
import LIS.Model.VO.SearchHistoryVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class _29_NewBooks implements CommandAction {

	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		List<BookVO> newBookList = bookDAO.getNewBookList();

		request.setAttribute("newBookList", newBookList);

		return "/31_newBooks.jsp";
	}
}
