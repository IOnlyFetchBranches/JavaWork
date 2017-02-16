package Tools;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.*;


public class xls {
    public static void main(String[] args) {
    }
    public  void book(int i,String n, String s, String p, long salary,int pos) {
        //Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        int num=pos+1;
        int prop=5;
        // Create a row and put some cells in it. Rows are 0 based.
        for (int x=0; x<num; x++) {

            for (int y = 0; y < prop; y++) {
                Row row = sheet.createRow((short) x);
                // Create a cell and put a value in it.
                Cell cell = row.createCell(y);
                if (y==0) {
                    cell.setCellValue(i);
                }
                if (y==1) {
                    cell.setCellValue( createHelper.createRichTextString(n));
                }
                if (y==2) {
                    cell.setCellValue( createHelper.createRichTextString(s));
                }
                if (y==3) {
                    cell.setCellValue( createHelper.createRichTextString(p));
                }
                if (y==4) {
                    cell.setCellValue(salary);
                }
            }
        }
        /* Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);
        */
        try {
            // Write the output to a file
            System.out.println("Generating Workbook........");
            FileOutputStream fileOut = new FileOutputStream("workbook.xls");
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Done!");
        }catch(Exception e){ }
    }
}
