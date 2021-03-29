package LIS.Model.VO;

public class SearchFormPart {
    private String searchType;
    private String keyword;
    private String operator;

    public String getSearchType() { return searchType; }
    public void setSearchType(String searchType) { this.searchType = searchType; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    @Override
    public String toString() {
        String type = "";
        if(searchType.equals("bookName")) type = "서명";
        if(searchType.equals("authors")) type = "저자";
        if(searchType.equals("publisher")) type = "출판사";
        return type+": "+keyword;
    }

    public String toString2() {
        return "type: "+searchType+" || keyword: "+keyword+" || operator: "+operator+"\n";
    }
}
