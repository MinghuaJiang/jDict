package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ui.action.ExportAction;

public class ExportMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7734947230435172607L;
	private JMenuItem allWords = new JMenuItem("已背生词");
	private JMenuItem newWords = new JMenuItem("今日生词");
	private JMenuItem reviewWords = new JMenuItem("今日复习内容");
	public ExportMenu(String title){
		super(title);
		add(allWords);
		add(newWords);
		add(reviewWords);
		ExportAction action = new ExportAction();
		allWords.addActionListener(action);
		newWords.addActionListener(action);
		reviewWords.addActionListener(action);
	}
	
}
