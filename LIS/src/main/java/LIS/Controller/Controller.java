package LIS.Controller;

import LIS.Command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"*.do"})
public class Controller extends HttpServlet {

	private final Map<String, Object> commandMap = new HashMap<>();

	public void init() {
		commandMap.put("/main.do", new _00_Main());
		commandMap.put("/register.do", new _01_Register());
		commandMap.put("/registerPro.do", new _02_RegisterPro());
		commandMap.put("/login.do", new _03_Login());
		commandMap.put("/loginPro.do", new _04_LoginPro());
		commandMap.put("/logout.do", new _05_Logout());
		commandMap.put("/insertBookUsingExcel.do", new _06_InsertBookPro());
		commandMap.put("/myInfo.do", new _07_MyInfo());
		commandMap.put("/modifyUserInfo.do", new _08_ModifyUserInfo());
		commandMap.put("/boardMain.do", new _09_BoardMain());
		commandMap.put("/writeArticle.do", new _10_WriteArticle());
		commandMap.put("/writeArticlePro.do", new _11_WriteArticlePro());
		commandMap.put("/showArticle.do", new _12_ShowArticle());
		commandMap.put("/writeComment.do", new _13_WriteComment());
		commandMap.put("/modifyArticle.do", new _14_ModifyArticle());
		commandMap.put("/modifyArticlePro.do", new _15_ModifyArticlePro());
		commandMap.put("/deleteArticle.do", new _16_DeleteArticle());
		commandMap.put("/collectionSearch.do", new _17_CollectionSearch());
		commandMap.put("/collectionSearchPro.do", new _18_CollectionSearchPro());
		commandMap.put("/collectionDetail.do", new _19_CollectionDetail());
		commandMap.put("/loanBookForm.do", new _20_loanBookForm());
		commandMap.put("/loanBookPro.do", new _21_LoanBookPro());
		commandMap.put("/manageMyLoanInfo.do", new _22_ManageLoanInfo());
		commandMap.put("/extendLoanPeriod.do", new _23_ExtendLoanPeriod());
		commandMap.put("/returnBookForm.do", new _24_returnBookForm());
		commandMap.put("/returnBookPro.do", new _25_returnBookPro());
		commandMap.put("/showLoanHistory.do", new _26_ShowLoanHistory());
		commandMap.put("/exportLoanHistory.do", new _27_ExportLoanHistory());
		commandMap.put("/collectionSearchHistory.do", new _28_CollectionSearchHistory());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = "";
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("content", view);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/00_index.jsp");
		dispatcher.forward(request, response);
	}
}
