package LIS.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

	private static ManagerDAO instance = new ManagerDAO();

	// MngrDBBean객체를 리턴하는 메소드
	public static ManagerDAO getInstance() {
		return instance;
	}

	private ManagerDAO() {
	}
	// 커넥션 풀에서 커넥션 객체를 얻어내는 메소드

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() throws Exception {
		String dbURL = "jdbc:mysql://localhost:3306/mvc2_bookstore?serverTimezone=UTC";
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

	// 관리자 인증 메서드
	public int userCheck(String id, String passwd) {
		int check = -1;
		try {
			conn = getConnection();

			String sql = "SELECT managerPw FROM manager WHERE managerId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 해당 아이디가 있으면 수행
			if (rs.next()) {
				String dbpw = rs.getString("managerPw");
				if (passwd.equals(dbpw)) {
					check = 1;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[check]" + check);
		return check;
	}

	// 전체 등록된 책의 수를 얻어내는 메서드
	public int getBookCount() {

		int count = 0;

		try {
			conn = getConnection();

			String sql = "SELECT COUNT(*) FROM book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<ManagerBean> getBooks(String book_kind) {

		List<ManagerBean> bookList = null;

		try {

			conn = getConnection();

			String sql1 = "SELECT * FROM book";
			String sql2 = "SELECT * FROM book ";
			sql2 += "WHERE book_kind=? ORDER BY reg_date DESC";

			if (book_kind.equals("all")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, book_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookList = new ArrayList<ManagerBean>();
				do {
					ManagerBean book = new ManagerBean();

					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));

					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getInt("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getInt("discount_rate"));
					book.setReg_date(rs.getString("reg_date"));

					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}

	// 이미 등록된 책을 검증
	public int registedBookconfirm(String book_kind, String book_title, String author) {

		int check = 1; // 해당 책이 등록되어 있지 않음

		try {

			conn = getConnection();

			String sql = "SELECT book_title FROM book ";
			sql += " WHERE book_kind=? AND book_title=? AND author=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book_kind);
			pstmt.setString(2, book_title);
			pstmt.setString(3, author);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = -1; // 해당 책이 이미 등록되어 있음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	// 책 등록 메서드
	public void insertBook(ManagerBean book) {

		int num = 0;

		try {
			conn = getConnection();

			// num의 최대값을 꺼내와 1증가 시키기
			String sql = "SELECT COUNT(book_id) FROM book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO book(book_id,book_kind,book_title,book_price,";
			sql += "book_count,author,publishing_com,publishing_date,book_image,";
			sql += "book_content,discount_rate,reg_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,now())";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, book.getBook_kind());
			pstmt.setString(3, book.getBook_title());
			pstmt.setInt(4, book.getBook_price());
			pstmt.setInt(5, book.getBook_count());
			pstmt.setString(6, book.getAuthor());
			pstmt.setString(7, book.getPublishing_com());
			pstmt.setString(8, book.getPublishing_date());
			pstmt.setString(9, book.getBook_image());
			pstmt.setString(10, book.getBook_content());
			pstmt.setInt(11, book.getDiscount_rate());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// bookId에 해당하는 책의 정보를 얻어내는 메소드로
	// 등록된 책을 수정하기 위해 수정폼으로 읽어들기이기 위한 메서드
	public ManagerBean getBook(int bookId) {

		ManagerBean book = null;

		try {
			conn = getConnection();

			String sql = "SELECT * FROM book WHERE book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				book = new ManagerBean();

				book.setBook_kind(rs.getString("book_kind"));
				book.setBook_title(rs.getString("book_title"));
				book.setBook_price(rs.getInt("book_price"));
				book.setBook_count(rs.getInt("book_count"));
				book.setAuthor(rs.getString("author"));
				book.setPublishing_com(rs.getString("publishing_com"));
				book.setPublishing_date(rs.getString("publishing_date"));
				book.setBook_image(rs.getString("book_image"));
				book.setBook_content(rs.getString("book_content"));
				book.setDiscount_rate(rs.getInt("discount_rate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	// 등록된 책의 정보를 수정시 사용하는 메소드
    public void updateBook(ManagerBean book, int book_id) {
        
    	try {
            conn = getConnection();
            
            String sql = "UPDATE book SET book_kind=?,book_title=?,book_price=?";
            sql += ",book_count=?,author=?,publishing_com=?,publishing_date=?";
            sql += ",book_image=?,book_content=?,discount_rate=?";
            sql += " where book_id=?";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getBook_kind());
            pstmt.setString(2, book.getBook_title());
            pstmt.setInt(3, book.getBook_price());
            pstmt.setInt(4, book.getBook_count());
            pstmt.setString(5, book.getAuthor());
            pstmt.setString(6, book.getPublishing_com());
			pstmt.setString(7, book.getPublishing_date());
			pstmt.setString(8, book.getBook_image());
			pstmt.setString(9, book.getBook_content());
			pstmt.setInt(10, book.getDiscount_rate());
			pstmt.setInt(11, book_id);
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // bookId에 해당하는 책의 정보를 삭제시 사용하는 메소드
    public void deleteBook(int bookId) {
        
        try {
        	
			conn = getConnection();
			
			String sql = "DELETE FROM book WHERE book_id=?"; 
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 // 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간책목록을 얻어내는 메소드
 	public ManagerBean[] getBooks(String book_kind, int count) {

 		ManagerBean bookList[]=null;
         
 		int i = 0;
         
         try {
             conn = getConnection();
             
             String sql = "SELECT * FROM book WHERE book_kind=? ";
             sql += "ORDER  BY reg_date DESC LIMIT ?,?";
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, book_kind);
             pstmt.setInt(2, 0);
             pstmt.setInt(3, count);
             
         	rs = pstmt.executeQuery();

             if (rs.next()) {
             
             	bookList = new ManagerBean[count];
                 
             	do{
                 	ManagerBean book= new ManagerBean();
                 	
                     book.setBook_id(rs.getInt("book_id"));
                     book.setBook_kind(rs.getString("book_kind"));
                     book.setBook_title(rs.getString("book_title"));
                     book.setBook_price(rs.getInt("book_price"));
                     book.setBook_count(rs.getInt("book_count"));
                     book.setAuthor(rs.getString("author"));
                     book.setPublishing_com(rs.getString("publishing_com"));
                     book.setPublishing_date(rs.getString("publishing_date"));
                     book.setBook_image(rs.getString("book_image"));
                     book.setDiscount_rate(rs.getInt("discount_rate"));
                     book.setReg_date(rs.getString("reg_date"));
                      
                     bookList[i]=book;
                      
                     i++;
                     
 			    }while(rs.next());
 			}
         } catch(Exception e) {
             e.printStackTrace();
         }
 		return bookList;
     }
}
