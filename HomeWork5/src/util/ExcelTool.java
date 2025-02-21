package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.CartItem;

public class ExcelTool {
	public static final double moneyPerPoint = 300.0;
	public static String WriteCartToExcel(File excelFile, String customername,List<CartItem> cartItems, int sum) {
		try {
			Workbook workbook;
			if (excelFile==null) {
				workbook = new XSSFWorkbook();
			}else {
				workbook = new XSSFWorkbook(excelFile);
			}
			Sheet sheet = workbook.createSheet("明細 "+LocalDateTime.now());
			
			Row nameRow = sheet.createRow(0);
			CellStyle nameCellStyle = workbook.createCellStyle();
			Font nameFont = workbook.createFont();
			nameFont.setFontName("微軟正黑體");
			nameFont.setBold(true);
			nameFont.setFontHeightInPoints((short) 24);
			nameCellStyle.setFont(nameFont);
			Cell nameCell = nameRow.createCell(0);
			nameCell.setCellValue(customername);
			nameCell.setCellStyle(nameCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			
			Row headerRow = sheet.createRow(1);
			String[] headers = {"商品編號","商品名稱","單價","數量","小計"};
			CellStyle headerStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setFontName("微軟正黑體");
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 16);
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			headerStyle.setFont(headerFont);
			headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
			for (int i = 0; i < headers.length; i++) {
				Cell headerCell = headerRow.createCell(i);
				headerCell.setCellStyle(headerStyle);
				headerCell.setCellValue(headers[i]);
			}
			CellStyle bodyStyle = workbook.createCellStyle();
			Font bodyFont =workbook.createFont();
			bodyFont.setFontName("微軟正黑體");
			bodyFont.setFontHeightInPoints((short) 12);
			bodyStyle.setFont(bodyFont);
			
			int rowNum = 2;
			for (CartItem item : cartItems) {
				Row row = sheet.createRow(rowNum++);
				Cell cell;
				cell = row.createCell(0);
				cell.setCellValue(item.getProductno());
				cell.setCellStyle(bodyStyle);
				cell = row.createCell(1);
				cell.setCellValue(item.getProductname());
				cell.setCellStyle(bodyStyle);
				cell = row.createCell(2);
				cell.setCellValue(item.getPrice());
				cell.setCellStyle(bodyStyle);
				cell = row.createCell(3);
				cell.setCellValue(item.getAmount());
				cell.setCellStyle(bodyStyle);
				cell = row.createCell(4);
				cell.setCellValue(item.getSum());
				cell.setCellStyle(bodyStyle);
				row.setRowStyle(bodyStyle);
			}
			
			sheet.setColumnWidth(0, 16*256);
			sheet.setColumnWidth(1, 24*256);
			sheet.setColumnWidth(2, 12*256);
			sheet.setColumnWidth(3, 8*256);
			sheet.setColumnWidth(4, 16*256);
			FileOutputStream fos;
			if (excelFile == null) {
				File newFile = new File("output.xlsx");
				fos = new FileOutputStream(newFile);
				workbook.write(fos);
				workbook.close();
				fos.close();
				return newFile.getAbsolutePath();
			}else {
				fos = new FileOutputStream(excelFile.getAbsolutePath()+".new");
				workbook.write(fos);
				workbook.close();
				fos.close();
				Files.move(Paths.get(excelFile.getAbsolutePath()+".new"), Paths.get(excelFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
				return excelFile.getAbsolutePath();
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String ExcelPay(File excelFile, String customername, int sum) {
		try {
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowEnd = sheet.getPhysicalNumberOfRows();
			for(int i=0;i<=rowEnd;i++) {
				Row row = sheet.getRow(i);
				Cell nameCell = row.getCell(0);
				String name = nameCell.getStringCellValue();
				if (name.equals(customername)) {
					Cell moneyCell = row.getCell(1);
					try {
						double money = moneyCell.getNumericCellValue();
						if (money>= sum) {
							double point = money / moneyPerPoint;
							row.createCell(2).setCellValue(point);
							FileOutputStream fos = new FileOutputStream(excelFile.getAbsolutePath()+".new");
							workbook.write(fos);
							fos.close();
							workbook.close();
							Files.move(Paths.get(excelFile.getAbsolutePath()+".new"), Paths.get(excelFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
							return "success";
						}
					} catch (NumberFormatException | IllegalStateException e) {
						// TODO: handle exception
					}
					
				}
			}

			workbook.close();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "read error";
		}
		
		return "not found";
	}
}
