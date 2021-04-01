package LIS.Model.VO;

public class LoanBook {
    private LoanVO loan;
    private BookVO book;

    public LoanVO getLoan() { return loan; }
    public void setLoan(LoanVO loan) { this.loan = loan; }
    public BookVO getBook() { return book; }
    public void setBook(BookVO book) { this.book = book; }
}
