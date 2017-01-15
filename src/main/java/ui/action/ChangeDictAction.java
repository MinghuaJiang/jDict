package ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JOptionPane;

import ui.panel.StatusPanel;
import core.DictRepository;
import core.WordRepository;

public class ChangeDictAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		WordRepository.getInstance().storeData();
		
		Set<String> dicts = DictRepository.getInstance().getDictNames();
		int size = dicts.size();
		if (size == 0) {
			JOptionPane.showMessageDialog(null, "还没有导入任何生词表", "提示",
					JOptionPane.WARNING_MESSAGE);
		} else {
			String[] choices = dicts.toArray(new String[0]);
			String input = (String) JOptionPane.showInputDialog(null,
					"从下拉菜单选一生词表", "选择生词表", JOptionPane.QUESTION_MESSAGE, null,

					choices, // Array of choices
					choices[0]);
			if (input != null) {
				StatusPanel.getInstance().updateDictName(input);
			}
		}
	}

}
