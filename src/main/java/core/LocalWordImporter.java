package core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import domain.Word;

public class LocalWordImporter
{
	
    public void importWords(String inputFile) throws Exception
    {
        parseInputFile(inputFile);
       
    }

    private List<Word> parseInputFile(String inputFile) throws Exception
    {
        List<Word> results = new ArrayList<Word>();
        File file = new File(inputFile);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);
            Cell englishCell = row.getCell(0);
            Cell chineseCell = row.getCell(1);
            List<String> chinese = new ArrayList<String>();
            chinese.add(chineseCell.getStringCellValue());
            Word word = new Word(englishCell.getStringCellValue(), chinese);
            results.add(word);
        }
        return results;
    }
}
