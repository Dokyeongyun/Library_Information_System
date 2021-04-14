package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.VO.BookVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class _30_BestLoanBooks implements CommandAction {

	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		List<BookVO> newBookList = bookDAO.getNewBookList();
		request.setAttribute("newBookList", newBookList);

		return "/32_bestLoanBooks.jsp";
	}
}
