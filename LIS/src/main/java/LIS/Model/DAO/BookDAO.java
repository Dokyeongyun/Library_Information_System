package LIS.Model.DAO;

import LIS.Model.VO.*;
import LIS.Utils.DatabaseConnection;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
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

	Connection conn = DatabaseConnection.getInstance().conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 도서정보 추가
	public int insertBooks(List<BookVO> list) {
		int check = 0;
		try {
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

	// ISBN를 이용해 도서정보 리스트 가져오기
	public List<BookVO> getBooksUsingISBN(String ISBN){
		List<BookVO> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book WHERE ISBN = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ISBN);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// bookId를 이용해 도서정보 가져오기
	public BookVO getBookUsingBookId(int bookId){
		BookVO book = new BookVO();
		try {
			String sql = "SELECT * FROM book WHERE bookId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);

			rs = pstmt.executeQuery();

			if(rs.next()){
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// 도서상태 업데이트
	public int updateBookStatus(List<LoanVO> list, String status){
		int check = -1;
		int[] batchCheck;

		try {
			conn.setAutoCommit(false);
			String sql = "UPDATE book SET bookStatus = ? WHERE bookId = ?";
			pstmt = conn.prepareStatement(sql);

			for(int i=0; i<list.size(); i++){
				pstmt.setString(1, status);
				pstmt.setInt(2, list.get(i).getBookId());
				pstmt.addBatch();
			}
			batchCheck = pstmt.executeBatch();
			for (int j : batchCheck) { if (j == 0) { throw new Exception("도서 상태 업데이트 실패"); } }
			check = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
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

			String sql2 = "INSERT INTO searchhistory(sh_keyword, sh_keyword2, sh_user, sh_query, sh_resultSize) VALUES (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, sh.getSh_keyword());
			pstmt.setString(2, bookSearchForm.toString());
			pstmt.setString(3, sh.getSh_user());
			pstmt.setString(4, sql);
			pstmt.setInt(5, list.size());

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
		StringBuilder query = new StringBuilder();
		if(bookSearchForm.getStorageLocation().equals("")){
			query.append(")")
					.append("AND publicationYear >= ").append(bookSearchForm.getPublicationYear1())
					.append(" AND publicationYear <= ").append(bookSearchForm.getPublicationYear2());
		}else{
			query.append(")")
					.append(" AND storageLocation = '").append(bookSearchForm.getStorageLocation()).append("' ")
					.append("AND publicationYear >= ").append(bookSearchForm.getPublicationYear1())
					.append(" AND publicationYear <= ").append(bookSearchForm.getPublicationYear2());
		}
		return query.toString();
	}

	// Query를 직접 이용한 도서 검색
	public List<BookVO> searchBookUsingQuery(String query) {
		List<BookVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 신착도서 (최근 한달이내 입수된 도서) 목록 가져오기
	public List<BookVO> getNewBookList(){
		List<BookVO> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book WHERE regDate > DATE_SUB(NOW(), INTERVAL 1 MONTH)";

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 베스트 대출 (최근 30일간 최다 대출 TOP 100)
	public List<BookVO> getBestLoanBookList(){
		List<BookVO> list = new ArrayList<>();
		try {
			String sql = "SELECT b.*" +
					"FROM" +
					"(SELECT bookId, COUNT(*) AS 'loanCount' " +
					"FROM loan\n " +
					"WHERE loanDate > DATE_SUB(NOW(), INTERVAL 1 MONTH) " +
					"    GROUP BY bookId) l, book b " +
					"WHERE l.bookId = b.bookId " +
					"ORDER BY loanCount DESC;";

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
