package LIS.Model.DAO;

import LIS.Model.VO.*;
import org.apache.poi.hssf.record.BookBoolRecord;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	private static final BookDAO instance = new BookDAO();

	public static BookDAO getInstance() {
		return instance;
	}

	private BookDAO() { }

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() {
		String dbURL = "jdbc:mysql://localhost:3306/LIS?serverTimezone=UTC";
		String dbID = "root";
		String dbPassword = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 도서정보 추가
	public int insertBooks(List<BookVO> list) {
		int check = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO " +
					"book(bookName, authors, publisher, publicationYear, ISBN, bookImageURL, vol, category, storageLocation) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int size = list.size();
			for(int i=0; i<size; i++){
				BookVO book = list.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book.getBookName());
				pstmt.setString(2, book.getAuthors());
				pstmt.setString(3, book.getPublisher());
				pstmt.setInt(4, book.getPublicationYear());
				pstmt.setString(5, book.getISBN());
				pstmt.setString(6, book.getBookImageURL());
				pstmt.setInt(7, book.getVol());
				pstmt.setString(8, book.getCategory());
				pstmt.setString(9, book.getStorageLocation());

				pstmt.executeUpdate();

				double processed = ((double) i/size) *100;
				System.out.println("[도서정보 입력 진행중("+i+"/"+size+") 진행률: "+processed+"%]");

				check++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[도서정보 입력 오류!] "+check+" 개의 도서 정보가 추가되었습니다.");
			return check;
		}
		System.out.println("[도서정보 입력 완료] "+check+" 개의 도서 정보가 추가되었습니다.");
		return check;
	}

	// Excel 파일에서 도서 정보 읽은 후 리턴
	public List<BookVO> getBookListFromExcel(String filePath){
		List<BookVO> books = new ArrayList<>();

		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\aserv\\Desktop\\도경윤\\개발\\도서정보시스템\\도서목록.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수
			int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
			for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex); // 각 행을 읽어온다
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					BookVO book = new BookVO();
					for (int columnIndex = 0; columnIndex <= cells; columnIndex++) {
						XSSFCell cell = row.getCell(columnIndex); // 셀에 담겨있는 값을 읽는다.
						String value = "";
						if(cell != null){
							CellType cellType = cell.getCellType();
							if(cellType == CellType.NUMERIC){
								value = cell.getNumericCellValue() +"";
							}else if(cellType == CellType.STRING){
								value = cell.getStringCellValue() + "";
							}
						}
						switch (columnIndex){
							case 0:
								book.setBookId(Integer.parseInt(value));
								break;
							case 1:
								book.setBookName(value);
								break;
							case 2:
								book.setAuthors(value);
								break;
							case 3:
								book.setPublisher(value);
								break;
							case 4:
								if(value.equals("null") || value.equals("")){
									value="9999";
								}
								book.setPublicationYear((int)Double.parseDouble(value));
								break;
							case 5:
								book.setISBN(value);
								break;
							case 6:
								book.setBookImageURL(value);
								break;
							case 7:
								int num = 0;
								if(value.equals("")){ value = "0"; }
								try{ num = Integer.parseInt(value);
								}catch (NumberFormatException ignored){ }
								book.setVol(num);
								break;
							case 8:
								book.setCategory(value);
								break;
							case 9:
								book.setStorageLocation(value);
								break;
						}
					}
					books.add(book);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return books;
	}

	// 도서 검색 후 검색히스토리 추가
	public List<BookVO> searchBook(BookSearchForm bookSearchForm, SearchHistoryVO sh) {
		List<BookVO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String prefix = "SELECT * FROM book WHERE (";
			String middle = makeQuery1(bookSearchForm.getSearchFormParts());
			String suffix = makeQuery2(bookSearchForm);

			String sql = prefix+middle+suffix;
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				BookVO book = new BookVO();
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthors(rs.getString("authors"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationYear(rs.getInt("publicationYear"));
				book.setISBN(rs.getString("ISBN"));
				book.setBookImageURL(rs.getString("bookImageURL"));
				book.setVol(rs.getInt("vol"));
				book.setCategory(rs.getString("category"));
				book.setStorageLocation(rs.getString("storageLocation"));
				book.setBookStatus(rs.getString("bookStatus"));
				book.setRegDate(rs.getString("regDate"));
				list.add(book);
			}

			String sql2 = "INSERT INTO searchhistory(sh_keyword, sh_user, sh_query) VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, sh.getSh_keyword());
			pstmt.setString(2, sh.getSh_user());
			pstmt.setString(3, sql);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String makeQuery1(List<SearchFormPart> list){
		StringBuilder query = new StringBuilder();
		for(int i=0; i<list.size(); i++){
			String searchType = list.get(i).getSearchType();
			String keyword = list.get(i).getKeyword();
			String operator = list.get(i).getOperator();
			if(i==list.size()-1) operator = "";
			query.append(searchType).append(" LIKE ").append("'%").append(keyword).append("%' ").append(operator).append(" ");
		}
		return query.toString();
	}

	public String makeQuery2(BookSearchForm bookSearchForm){
		return ") AND storageLocation = '" + bookSearchForm.getStorageLocation() + "' AND publicationYear >= " +
				bookSearchForm.getPublicationYear1() + " AND publicationYear <= " + bookSearchForm.getPublicationYear2();
	}
}
