package LIS.Model.DAO;

import LIS.Model.VO.ArticleVO;
import LIS.Model.VO.BoardVO;
import LIS.Model.VO.BookVO;
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

public class BoardDAO {

	private static final BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	private BoardDAO() { }

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

	// 게시판 종류 불러오기
	public List<BoardVO> getBoardTypes() {
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM board";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()){
				BoardVO board = new BoardVO();
				board.setBoardId(rs.getInt("boardId"));
				board.setBoardName(rs.getString("boardName"));
				board.setRegDate(rs.getString("regDate"));
				list.add(board);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 글 작성하기
	public int writeArticle(ArticleVO article) {
		int check = -1;
		try {
			conn = getConnection();
			String sql = "INSERT INTO article(boardId, writer, title, content) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,article.getBoardId());
			pstmt.setString(2,article.getWriter());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			check = pstmt.executeUpdate();

			if(check==1){ // 글쓰기 성공
				String sql2 = "SELECT articleId FROM article WHERE writer=? AND title=?";

				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getTitle());
				rs = pstmt.executeQuery();
				if(rs.next()){
					check = rs.getInt(1);
				}
			}else{
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

}
