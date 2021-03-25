package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.DAO.UserDAO;
import LIS.Model.VO.BookVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class _06_InsertBookPro implements CommandAction {
	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<BookVO> list = bookDAO.getBookListFromExcel("");
		int check = bookDAO.insertBooks(list);

		System.out.println(check);

		request.setAttribute("result", check);

		return "/10_insertBookPro.jsp";
	}
}
