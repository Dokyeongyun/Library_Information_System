package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.DAO.SearchHistoryDAO;
import LIS.Model.VO.LoanBook;
import LIS.Model.VO.SearchHistoryVO;
import LIS.Model.VO.UserVO;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.List;


public class _28_CollectionSearchHistory implements CommandAction {
	SearchHistoryDAO searchHistoryDAO = SearchHistoryDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String userId = "";
		UserVO user = new UserVO();
		if(request.getSession().getAttribute("loginUser") == null){
			userId = request.getSession().getId();
			user.setUserId(userId);
		}else{
			user = (UserVO) request.getSession().getAttribute("loginUser");
		}

		List<SearchHistoryVO> list = searchHistoryDAO.getSearchHistory(user);
		request.setAttribute("shList", list);

		return "/30_showSearchHistory.jsp";
	}
}
