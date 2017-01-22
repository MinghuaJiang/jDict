package handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import domain.Word;

public class DictHandler {
	public List<Word> parse(String file) throws Exception {
		List<Word> words = new ArrayList<Word>();
		Workbook workbook = WorkbookFactory.create(new File(file));
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Cell englishCell = row.getCell(0);
			Cell chineseCell = row.getCell(1);
			Cell relativeCell = row.getCell(2);
			String english = englishCell.getStringCellValue();
			String chinese = chineseCell
					.getStringCellValue();
			List<String> chineseList = null;
			if(chinese != null){
				 chineseList = Arrays.asList(chinese.split("\n"));
			}
			String relative = null;
			if(relativeCell != null){
				relative = relativeCell.getStringCellValue();
			}

			Word word = new Word(english, chineseList, relative);
			words.add(word);
		}
		return words;
	}
}
