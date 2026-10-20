package automation.edureka.utility;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class TestData {
    private Workbook workbook;
    public String getTData(String sheetName, int rowNum, int columnNum) throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Edureka_Updated_Testdata.xlsx");
        workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        String value = sheet.getRow(rowNum).getCell(columnNum).getStringCellValue();
        workbook.close();
        fis.close();
        return value;
    }
}