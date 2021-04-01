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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoanDAO {

	private static final LoanDAO instance = new LoanDAO();

	public static LoanDAO getInstance() {
		return instance;
	}

	private LoanDAO() { }

	Connection conn = DatabaseConnection.getInstance().conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 도서 대출 (도서 상태 변경 + 대출 작업 수행 트랜잭션처리)
	public int insertLoan(List<LoanVO> list) {
		int check = -1;
		int[] batchCheck;
		try {
			conn.setAutoCommit(false);

			int updateStatus = BookDAO.getInstance().updateBookStatus(list, "대출중");
			if(updateStatus != 1){
				check = -2;
				throw new Exception("대출불가능 도서");
			}

			String sql = "INSERT INTO loan(loaner, bookId, loanDate, returnDeadline) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			for(int i=0; i<list.size(); i++){
				pstmt.setString(1, list.get(i).getLoaner());
				pstmt.setInt(2, list.get(i).getBookId());
				pstmt.setString(3, list.get(i).getLoanDate());
				pstmt.setString(4, list.get(i).getReturnDeadline());
				pstmt.addBatch();
			}
			batchCheck = pstmt.executeBatch();
			for (int j : batchCheck) { if (j == 0) { throw new Exception("대출처리 실패"); } }
			check = 1;
			conn.commit();

		} catch (Exception e) {
			try{ conn.rollback(); }catch (Exception ignored){ }
			e.printStackTrace();
		} finally {
			try { conn.setAutoCommit(true); } catch (Exception e2) { e2.printStackTrace(); }
		}
		return check;
	}

	// 도서 대출기한 연장
	public int extendLoanPeriod(List<LoanVO> list) {
		int check = -1;
		int[] batchCheck;
		try {
			conn.setAutoCommit(false);
			String sql = "UPDATE loan SET returnDeadline = DATE_ADD(returnDeadline, INTERVAL 7 DAY), isExtended = 1 WHERE loanId = ?;";
			pstmt = conn.prepareStatement(sql);

			for(int i=0; i<list.size(); i++){
				pstmt.setInt(1, list.get(i).getLoanId());
				pstmt.addBatch();
			}
			batchCheck = pstmt.executeBatch();
			for (int j : batchCheck) { if (j == 0) { throw new Exception("연장처리 실패"); } }
			check = 1;
			conn.commit();

		} catch (Exception e) {
			try{ conn.rollback(); }catch (Exception ignored){ }
			e.printStackTrace();
		} finally {
			try { conn.setAutoCommit(true); } catch (Exception e2) { e2.printStackTrace(); }
		}
		return check;
	}

	// 도서 반납 (도서 상태 변경 + 반납 작업 수행 트랜잭션처리)
	public int returnBooks(List<LoanVO> list) {
		int check = -1;
		int[] batchCheck;
		try {
			conn.setAutoCommit(false);

			int updateStatus = BookDAO.getInstance().updateBookStatus(list, "이용가능");
			if(updateStatus != 1){
				check = -2;
				throw new Exception("반납 실패");
			}

			String sql = "UPDATE loan SET isReturn = 1, returnDate = ? WHERE loaner = ? AND bookId = ?";

			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<list.size(); i++){
				pstmt.setString(1, list.get(i).getReturnDate());
				pstmt.setString(2, list.get(i).getLoaner());
				pstmt.setInt(3, list.get(i).getBookId());
				pstmt.addBatch();
			}
			batchCheck = pstmt.executeBatch();
			for (int j : batchCheck) { if (j == 0) { throw new Exception("대출처리 실패"); } }
			check = 1;
			conn.commit();
		} catch (Exception e) {
			try{ conn.rollback(); }catch (Exception ignored){ }
			e.printStackTrace();
		} finally {
			try { conn.setAutoCommit(true); } catch (Exception e2) { e2.printStackTrace(); }
		}
		return check;
	}

	// 유저별 대출중인 도서정보 가져오기
	public List<LoanBook> getMyLoanBook(UserVO user) {
		List<LoanBook> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM loan l NATURAL JOIN book b WHERE l.bookId = b.bookId AND isReturn = 0 AND loaner = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());

			rs = pstmt.executeQuery();

			while(rs.next()){
				LoanBook lb = new LoanBook();
				LoanVO l = new LoanVO();
				BookVO b = new BookVO();

				l.setBookId(rs.getInt("bookId"));
				l.setLoanId(rs.getInt("loanId"));
				l.setLoaner(rs.getString("loaner"));
				l.setLoanDate(rs.getString("loanDate"));
				l.setReturnDeadline(rs.getString("returnDeadline"));
				l.setIsExtended(rs.getInt("isExtended"));
				l.setIsReturn(rs.getInt("isReturn"));

				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthors(rs.getString("authors"));
				b.setPublisher(rs.getString("publisher"));
				b.setPublicationYear(rs.getInt("publicationYear"));
				b.setISBN(rs.getString("ISBN"));
				b.setBookImageURL(rs.getString("bookImageURL"));
				b.setVol(rs.getInt("vol"));
				b.setCategory(rs.getString("category"));
				b.setStorageLocation(rs.getString("storageLocation"));
				b.setBookStatus(rs.getString("bookStatus"));

				lb.setBook(b);
				lb.setLoan(l);
				list.add(lb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
