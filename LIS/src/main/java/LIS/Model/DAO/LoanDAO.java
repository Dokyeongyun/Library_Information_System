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
	public int insertLoan(LoanVO loan) {
		int check = -1;
		try {
			conn.setAutoCommit(false);

			int updateStatus = BookDAO.getInstance().updateBookStatus(loan.getBookId(), "대출중");
			if(updateStatus != 1){
				check = -2;
				throw new Exception("대출불가능 도서");
			}

			String sql = "INSERT INTO loan(loaner, bookId, loanDate, returnDeadline) VALUES(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loan.getLoaner());
			pstmt.setInt(2, loan.getBookId());
			pstmt.setString(3, loan.getLoanDate());
			pstmt.setString(4, loan.getReturnDeadline());

			check = pstmt.executeUpdate();

			if(check == 1){
				conn.commit();
			}else{
				check = -1;
				throw new Exception("대출처리 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.setAutoCommit(true); } catch (Exception e2) { e2.printStackTrace(); }
		}
		return check;
	}
}
