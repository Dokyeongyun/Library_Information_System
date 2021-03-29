package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.VO.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


public class _18_CollectionSearchPro implements CommandAction {
	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SearchFormPart> list = new ArrayList<>();
		String[] search_type = request.getParameterValues("search_type");
		String[] keyword = request.getParameterValues("keyword");
		String[] operator = request.getParameterValues("operator");
		String storageLocation = request.getParameter("storageLocation");
		String publicationYear1 = request.getParameter("publicationYear1");
		String publicationYear2 = request.getParameter("publicationYear2");

		for(int i=0; i<search_type.length; i++){
			SearchFormPart part = new SearchFormPart();
			part.setSearchType(search_type[i]);
			part.setKeyword(keyword[i]);
			part.setOperator(operator[i]);
			list.add(part);
		}

		// BookSearchForm
		BookSearchForm bookSearchForm = new BookSearchForm();
		bookSearchForm.setSearchFormParts(list);
		bookSearchForm.setStorageLocation(storageLocation);
		bookSearchForm.setPublicationYear1(publicationYear1);
		bookSearchForm.setPublicationYear2(publicationYear2);

		// SearchHistoryVO
		SearchHistoryVO sh = new SearchHistoryVO();
		HttpSession session = request.getSession();
		String userId = session.getId()+"_"+new Date().getTime();
		if(session.getAttribute("loginUser") != null){
			userId = ((UserVO) session.getAttribute("loginUser")).getUserId();
		}
		sh.setSh_user(userId);
		sh.setSh_keyword(Arrays.toString(keyword));

		List<BookVO> resultList = bookDAO.searchBook(bookSearchForm, sh);
		request.setAttribute("resultList", resultList);

		System.out.println(bookSearchForm);
		System.out.println(resultList);
		return "/21_collectionSearchResult.jsp";
	}
}
