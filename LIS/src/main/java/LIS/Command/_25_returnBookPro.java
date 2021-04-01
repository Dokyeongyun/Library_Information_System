package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.BookVO;
import LIS.Model.VO.LoanVO;
import LIS.Model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class _25_returnBookPro implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String[] bookIdArr = request.getParameterValues("bookId"); // 반납 도서 ID
		String loaner = request.getParameter("loaner"); // 반납자 ID
		String loanDate = sDate.format(new Date());

		// LoanVO 생성 및 데이터 세팅
		List<LoanVO> list = new ArrayList<>();
		for(int i=0; i<bookIdArr.length; i++){
			LoanVO loanVO = new LoanVO();
			loanVO.setBookId(Integer.parseInt(bookIdArr[i]));
			loanVO.setLoaner(loaner);
			loanVO.setIsReturn(1);
			loanVO.setReturnDate(loanDate);
			list.add(loanVO);
		}

		// 대출작업 실행
		int result = loanDAO.returnBooks(list);
		request.setAttribute("result", result);
		request.setAttribute("returnList", list);

		return "/28_returnBookPro.jsp";
	}
}
