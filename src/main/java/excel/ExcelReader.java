package excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2017/9/7
 * 10:29.
 */
public class ExcelReader {
    private static Workbook wb;
    private static Sheet sheet;
    private static Row row;


    /**
     * 从Excel中读取数据
     * @param file  Excel文件
     * @return
     */
    public static List readExcelFile(File file) {
        try {
            InputStream is = new FileInputStream(file);

            String extension = file.getName().substring(file.getName().lastIndexOf('.'));

            switch (extension) {
                case ".xls":
                    // Excel 2003
                    wb = new HSSFWorkbook(new POIFSFileSystem(is));
                    sheet = wb.getSheetAt(0);
                    break;
                case ".xlsx":
                    wb = new XSSFWorkbook(is);
                    sheet = wb.getSheetAt(0);
                    break;
                default:
                    return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);

        Row titleRow = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        List<Employee> employees = new ArrayList<>();
        for (int index = 1; index <= rowNum; index++) {
            Row row = sheet.getRow(index);
            Employee employee = new Employee()
                    .name(getCellFormatValue(row.getCell(0)))
                    .idCard(getCellFormatValue(row.getCell(1)))
                    .sex(getCellFormatValue(row.getCell(2)))
                    .telephone(getCellFormatValue(row.getCell(3)));
            employees.add(employee);
        }

        return employees;
    }

    private static String getCellFormatValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_NUMERIC:
                    cellValue = String.valueOf(Math.round(cell.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else {
                        // 如果是纯数字取得当前Cell的数值
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case Cell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    // 默认的Cell值
                    cellValue = " ";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    public static void main(String... args) {
        Path path = Paths.get("src/main/resources/excel/data.xls");
        List<Employee> list = readExcelFile(path.toFile());
        list.forEach(System.out::println);
    }
}
