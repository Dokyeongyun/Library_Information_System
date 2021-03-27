package LIS.Model.VO;

public class ArticleVO {
    private int articleId;
    private int boardId;
    private String boardName;
    private String writer;
    private String title;
    private String content;
    private int hit;
    private String attachFile;
    private String regDate;

    public int getArticleId() { return articleId; }
    public void setArticleId(int articleId) { this.articleId = articleId; }
    public int getBoardId() { return boardId; }
    public void setBoardId(int boardId) { this.boardId = boardId; }
    public String getBoardName() { return boardName; }
    public void setBoardName(String boardName) { this.boardName = boardName; }
    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getHit() { return hit; }
    public void setHit(int hit) { this.hit = hit; }
    public String getAttachFile() { return attachFile; }
    public void setAttachFile(String attachFile) { this.attachFile = attachFile; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
}
