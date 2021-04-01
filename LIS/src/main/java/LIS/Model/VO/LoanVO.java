package LIS.Model.VO;

public class LoanVO {
    private int loanId;
    private String loaner;
    private int bookId;
    private String loanDate;
    private String returnDeadline;
    private int isExtended;
    private int isReturn;
    private String returnDate;

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }
    public String getLoaner() { return loaner; }
    public void setLoaner(String loaner) { this.loaner = loaner; }
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getLoanDate() { return loanDate; }
    public void setLoanDate(String loanDate) { this.loanDate = loanDate; }
    public String getReturnDeadline() { return returnDeadline; }
    public void setReturnDeadline(String returnDeadline) { this.returnDeadline = returnDeadline; }
    public int getIsExtended() { return isExtended; }
    public void setIsExtended(int isExtended) { this.isExtended = isExtended; }
    public int getIsReturn() { return isReturn; }
    public void setIsReturn(int isReturn) { this.isReturn = isReturn; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }}
