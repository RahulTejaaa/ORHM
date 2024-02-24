package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelFileUtils 
{
	Workbook wb;

	public ExcelFileUtils(String path)throws Throwable
	{
		FileInputStream fi=new FileInputStream(path);
		wb = WorkbookFactory.create(fi);
	}
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	public String getCellData(String sheetname,int row,int column) 
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int col = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(col);
		}else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String sheetname,int row,int column,String status,String WriteExcel) throws Throwable
	{
		Sheet ws=wb.getSheet(sheetname);
		Row rw = ws.getRow(row);
		Cell cell=rw.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);		
		}else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
	}
	/*public static void main(String[] args) throws Throwable
	{
		ExcelFileUtils ex = new ExcelFileUtils("D:/Sample.xlsx");
		int rc = ex.rowCount("Emp");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname = ex.getCellData("Emp", i, 0);
			String mname = ex.getCellData("Emp", i , 1);
			String lname = ex.getCellData("Emp", i, 2);
			String eid = ex.getCellData("Emp", i , 3);
			System.out.println(fname+" "+mname+" "+lname+" "+eid);
			//ex.setCellData("Emp", i, 4, "pass", "D:/results123.xlsx");
			//ex.setCellData("Emp", i, 4, "fail", "D:/results123.xlsx");
			ex.setCellData("Emp", i, 4, "blocked", "D:/results123.xlsx");
		}
	}*/
}
