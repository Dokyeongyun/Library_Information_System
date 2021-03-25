package LIS.Model.DAO;

import LIS.Model.VO.UserVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private static final UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	private UserDAO() { }

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

	// 회원가입
	public int register(UserVO user) {
		int check = -1;
		try {
			conn = getConnection();
			String sql = "INSERT INTO user(userId, password, name, stuNum, dept, phone, email) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getStuNum());
			pstmt.setString(5, user.getDept());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getEmail());

			check = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[회원가입 결과]" + user.getUserId()+" "+check);
		return check;
	}

	// 로그인
	public int login(UserVO user) {
		int check = -1;
		try {
			conn = getConnection();
			String sql = "SELECT COUNT(*) FROM user WHERE userId=? AND password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeQuery();

			if(rs.next()){
				int result = rs.getInt(1);
				check = result == 1 ? 1 : -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[로그인 결과]" + user.getUserId()+" "+check);
		return check;
	}

	// 회원정보 가져오기
	public UserVO getUser(UserVO user) {
		try {
			conn = getConnection();
			String sql = "SELECT * FROM user WHERE userId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());

			rs = pstmt.executeQuery();

			UserVO userInfo = new UserVO();
			if(rs.next()){
				userInfo.setUserId(rs.getString("userId"));
				userInfo.setName(rs.getString("name"));
				userInfo.setStuNum(rs.getInt("stuNum"));
				userInfo.setDept(rs.getString("dept"));
				userInfo.setUserStatus(rs.getString("userStatus"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setMileage(rs.getInt("mileage"));
				userInfo.setIsManager(rs.getInt("isManager"));
				userInfo.setRegDate(rs.getString("regDate"));

				return userInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 회원정보 가져오기
	public int modifyUser(UserVO user) {
		int result = -1;
		try {
			conn = getConnection();
			String sql = "UPDATE user SET email = ?, phone = ? WHERE userId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getUserId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
