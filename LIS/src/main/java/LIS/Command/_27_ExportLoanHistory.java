package LIS.Command;

import LIS.Controller.CommandAction;
import LIS.Model.DAO.LoanDAO;
import LIS.Model.VO.LoanBook;
import LIS.Model.VO.UserVO;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.List;


public class _27_ExportLoanHistory implements CommandAction {
	LoanDAO loanDAO = LoanDAO.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "/06_login.jsp";
		}

		UserVO user = (UserVO) session.getAttribute("loginUser");

		List<LoanBook> loanList = loanDAO.getMyLoanHistory(user);
		request.setAttribute("loanList", loanList);

		// Excel 파일로 독서이력 내보내기

		XSSFWorkbook xssfWb = null;
		XSSFSheet xssfSheet = null;
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;

		try {
			int rowNo = 0; // 행의 갯수
			xssfWb = new XSSFWorkbook(); //XSSFWorkbook 객체 생성
			xssfSheet = xssfWb.createSheet("워크 시트1"); // 워크시트 이름 설정

			// 폰트 스타일
			XSSFFont font = xssfWb.createFont();
			font.setFontName("맑은 고딕");// 폰트 스타일
			font.setFontHeightInPoints((short) 20);// 폰트 크기
			font.setBold(true);// Bold 설정

			// 테이블 셀 스타일
			CellStyle cellStyle = xssfWb.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HorizontalAlignment.CENTER); // 정렬

			// 셀병합
			xssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 6));

			// 타이틀 생성
			xssfRow = xssfSheet.createRow(rowNo++); // 행 객체 추가
			xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
			xssfCell.setCellStyle(cellStyle); // 셀에 스타일 지정
			xssfCell.setCellValue(user.getUserId()+"님의 독서이력");
			xssfSheet.createRow(rowNo++);
			xssfRow = xssfSheet.createRow(rowNo++); // 빈행 추가

			CellStyle style = xssfWb.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setBorderBottom(BorderStyle.THICK);
			style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			xssfRow = xssfSheet.createRow(rowNo++);
			xssfCell = xssfRow.createCell((short) 0);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("No");
			xssfCell = xssfRow.createCell((short) 1);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("ISBN");
			xssfCell = xssfRow.createCell((short) 2);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("서명");
			xssfCell = xssfRow.createCell((short) 3);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("저자");
			xssfCell = xssfRow.createCell((short) 4);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("대출일");
			xssfCell = xssfRow.createCell((short) 5);
			xssfCell.setCellStyle(style);
			xssfCell.setCellValue("반납입");

			for(int i=0; i<loanList.size(); i++){
				xssfRow = xssfSheet.createRow(rowNo++);

				xssfCell = xssfRow.createCell((short) 0);
				xssfCell.setCellValue(i+1);

				xssfCell = xssfRow.createCell((short) 1);
				xssfCell.setCellValue(loanList.get(i).getBook().getISBN());

				xssfCell = xssfRow.createCell((short) 2);
				xssfCell.setCellValue(loanList.get(i).getBook().getBookName());

				xssfCell = xssfRow.createCell((short) 3);
				xssfCell.setCellValue(loanList.get(i).getBook().getAuthors());

				xssfCell = xssfRow.createCell((short) 4);
				xssfCell.setCellValue(loanList.get(i).getLoan().getLoanDate());

				xssfCell = xssfRow.createCell((short) 5);
				xssfCell.setCellValue(loanList.get(i).getLoan().getReturnDate());

				xssfSheet.autoSizeColumn(i+1);
			}

			String localFile = "C:\\Users\\aserv\\Desktop\\도경윤\\" + "excelDownTest" + ".xlsx";

			FileOutputStream fos = null;
			fos = new FileOutputStream(localFile);
			xssfWb.write(fos);

			response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(user.getUserId()+"_LoanHistory.xls", "UTF-8"));
			response.setContentType("application/vnd.ms.excel");

			xssfWb.write(response.getOutputStream());
			xssfWb.close();

			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/29_showLoanHistory.jsp";
	}
}
