package LIS.Model.VO;

public class SearchHistoryVO {
    private String sh_id;
    private String sh_keyword;
    private String sh_user;
    private String sh_query;
    private String sh_date;

    public String getSh_id() { return sh_id; }
    public void setSh_id(String sh_id) { this.sh_id = sh_id; }
    public String getSh_keyword() { return sh_keyword; }
    public void setSh_keyword(String sh_keyword) { this.sh_keyword = sh_keyword; }
    public String getSh_user() { return sh_user; }
    public void setSh_user(String sh_user) { this.sh_user = sh_user; }
    public String getSh_query() { return sh_query; }
    public void setSh_query(String sh_query) { this.sh_query = sh_query; }
    public String getSh_date() { return sh_date; }
    public void setSh_date(String sh_date) { this.sh_date = sh_date; }
}
