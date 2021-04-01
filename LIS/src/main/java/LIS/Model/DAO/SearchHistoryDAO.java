package LIS.Model.DAO;

import LIS.Model.VO.SearchHistoryVO;
import LIS.Model.VO.UserVO;
import LIS.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchHistoryDAO {

	private static final SearchHistoryDAO instance = new SearchHistoryDAO();

	public static SearchHistoryDAO getInstance() {
		return instance;
	}

	private SearchHistoryDAO() { }

	Connection conn = DatabaseConnection.getInstance().conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	// 유저별 검색히스토리 가져오기
	public List<SearchHistoryVO> getSearchHistory(UserVO user) {
		List<SearchHistoryVO> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM searchhistory WHERE sh_user = ? ORDER BY sh_date DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());

			rs = pstmt.executeQuery();

			while(rs.next()){
				SearchHistoryVO vo = new SearchHistoryVO();
				vo.setSh_id(rs.getString("sh_id"));
				vo.setSh_keyword(rs.getString("sh_keyword"));
				vo.setSh_keyword2(rs.getString("sh_keyword2"));
				vo.setSh_user(rs.getString("sh_user"));
				vo.setSh_query(rs.getString("sh_query"));
				vo.setSh_date(rs.getString("sh_date"));
				vo.setSh_resultSize(rs.getInt("sh_resultSize"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// id 이용해 searchHistory 객체 가져오기
	public SearchHistoryVO getSearchHistoryById(int sh_id){
		SearchHistoryVO vo = new SearchHistoryVO();
		try {
			String sql = "SELECT * FROM searchhistory WHERE sh_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sh_id);

			rs = pstmt.executeQuery();

			if(rs.next()){
				vo.setSh_id(rs.getString("sh_id"));
				vo.setSh_keyword(rs.getString("sh_keyword"));
				vo.setSh_keyword2(rs.getString("sh_keyword2"));
				vo.setSh_user(rs.getString("sh_user"));
				vo.setSh_query(rs.getString("sh_query"));
				vo.setSh_date(rs.getString("sh_date"));
				vo.setSh_resultSize(rs.getInt("sh_resultSize"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
