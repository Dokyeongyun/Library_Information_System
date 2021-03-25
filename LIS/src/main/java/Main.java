import LIS.Model.VO.BookVO;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        List<BookVO> books = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\aserv\\Desktop\\도경윤\\개발\\도서정보시스템\\도서목록.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수
            int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
            for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex); // 각 행을 읽어온다
                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();
                    BookVO book = new BookVO();
                    for (int columnIndex = 0; columnIndex <= cells; columnIndex++) {
                        XSSFCell cell = row.getCell(columnIndex); // 셀에 담겨있는 값을 읽는다.
                        String value = "";
                        if(cell != null){
                            CellType cellType = cell.getCellType();
                            if(cellType == CellType.NUMERIC){
                                value = cell.getNumericCellValue() +"";
                            }else if(cellType == CellType.STRING){
                                value = cell.getStringCellValue() + "";
                            }
                        }
                        switch (columnIndex){
                            case 0:
                                book.setBookId(Integer.parseInt(value));
                                break;
                            case 1:
                                book.setBookName(value);
                                break;
                            case 2:
                                book.setAuthors(value);
                                break;
                            case 3:
                                book.setPublisher(value);
                                break;
                            case 4:
                                if(value.equals("null") || value.equals("")){
                                    value="9999";
                                }
                                book.setPublicationYear((int)Double.parseDouble(value));
                                break;
                            case 5:
                                book.setISBN(value);
                                break;
                            case 6:
                                book.setBookImageURL(value);
                                break;
                            case 7:
                                int num = 0;
                                if(value.equals("")){ value = "0"; }
                                try{ num = Integer.parseInt(value);
                                }catch (NumberFormatException ignored){ }
                                book.setVol(num);
                                break;
                            case 8:
                                book.setCategory(value);
                                break;
                            case 9:
                                book.setStorageLocation(value);
                                break;
                        }
                        sb.append(value).append("// ");
                    }
                    books.add(book);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(books);
    }
}
