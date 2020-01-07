package com.xtsoft.kernel.poi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POIUtil {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public XSSFWorkbook createXSSFWorkbook(List<HashMap<String, Object>> list, List<CellCol> colList, String sheetName) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			// 创建字体对象
			XSSFFont font = workbook.createFont();
			// 加粗
			font.setBold(true);
			// 字体用什么
			font.setFontName("黑体 ");
			// 单元格样式对象
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			// 单元格的字体用什么？就用上面设置好的东西
			cellStyle.setFont(font);
			cellStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
			XSSFSheet sheet = workbook.createSheet(sheetName);
			sheet.setDefaultColumnWidth(256 * 20);
			// 生成表头
			XSSFRow row = sheet.createRow((short) 0);
			if (colList != null && colList.size() > 0) {
				short j = 0;
				for (CellCol s : colList) {
					XSSFCell cell1 = row.createCell(j);
					cell1.setCellValue(colList.get(j).getColName());
					cell1.setCellStyle(cellStyle);
					if (s.getWidth() != null) {
						sheet.setColumnWidth(j, s.getWidth());
					}
					j = (short) (j + 1);
				}

			}
			XSSFCellStyle cs = workbook.createCellStyle();
			cs.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
			// 生成数据
			for (int i = 0; i < list.size(); i++) {
				XSSFRow datarow = sheet.createRow(i + 1);
				Map mp = list.get(i);
				for (short k = 0; k < colList.size(); k++) {
					XSSFCell datacell = datarow.createCell(k);
					datacell.setCellStyle(cs);
					datacell.setCellValue(mp.get(colList.get(k).getColKey()) == null ? "" : mp.get(colList.get(k).getColKey()) + "");
				}
			}

		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			logger.error("POIUtil.createXSSFWorkbook" + sw.toString());
		}
		return workbook;
	}

}
