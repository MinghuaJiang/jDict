package ui.action;

import handler.DictHandler;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import ui.panel.ExplainPanel;
import ui.panel.StatusPanel;
import core.DictRepository;
import domain.Word;

public class ImportAction implements ActionListener {
	private DictHandler handler;

	public ImportAction() {
		handler = new DictHandler();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("导入生词表");
			chooser.setFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					// TODO Auto-generated method stub
					return f.getName().endsWith(".xls") || f.getName().endsWith(".xlsx");
				}

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return "Excel文档(*.xls,*.xlsx)";
				}
				
			});
			int result = chooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				List<Word> words = handler.parse(file.getAbsolutePath());
				boolean flag = DictRepository.getInstance().addDict(file.getName(), words);
				if (!flag) {
					JOptionPane.showMessageDialog(null, "该生词表已经导入", "提示",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "生词表成功导入,该生词表包含"
							+ words.size() + "个生词","提示",
							JOptionPane.INFORMATION_MESSAGE);
					StatusPanel.getInstance().updateDictName(file.getName());
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
