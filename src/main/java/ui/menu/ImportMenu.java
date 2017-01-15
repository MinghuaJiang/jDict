package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ui.action.ImportAction;

public class ImportMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7964881275325704562L;
	private JMenuItem newWord = new JMenuItem("生词表");
	public ImportMenu(String title){
		super(title);
		add(newWord);
		newWord.addActionListener(new ImportAction());
	}
}
