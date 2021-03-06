package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.BookDAO;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.BookVO;
import LIS.Model.VO.LoanVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class _21_LoanBookPro implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();
	BookDAO bookDAO = BookDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sDate2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 14);

		String[] bookIdArr = request.getParameterValues("bookId"); // 대출 도서 ID
		String loaner = request.getParameter("loaner"); // 대출자 ID
		String loanDate = sDate.format(new Date());
		String returnDeadLine = sDate2.format(cal.getTime());

		// bookId 이용해 대출 도서 정보 가져오기
		for(int i=0; i<bookIdArr.length; i++){
			BookVO book = bookDAO.getBookUsingBookId(Integer.parseInt(bookIdArr[i]));
			if(!book.getBookStatus().equals("이용가능")){
				request.setAttribute("result", -2);
				return "/24_loanBookPro.jsp";
			}
		}

		// LoanVO 생성 및 데이터 세팅
		List<LoanVO> list = new ArrayList<>();
		for(int i=0; i<bookIdArr.length; i++){
			LoanVO loanVO = new LoanVO();
			loanVO.setBookId(Integer.parseInt(bookIdArr[i]));
			loanVO.setLoaner(loaner);
			loanVO.setLoanDate(loanDate);
			loanVO.setReturnDeadline(returnDeadLine);
			list.add(loanVO);
		}

		// 대출작업 실행
		int result = loanDAO.insertLoan(list);

		request.setAttribute("result", result);
		request.setAttribute("loanList", list);

		return "/24_loanBookPro.jsp";
	}
}
