package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.VO.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class _19_CollectionDetail implements CommandAction {
	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String ISBN = request.getParameter("ISBN");
		List<BookVO> list = bookDAO.getBooksUsingISBN(ISBN);

		request.setAttribute("bookList", list);
		return "/22_collectionDetail.jsp";
	}
}
