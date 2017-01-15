package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ui.action.DictAction;
import core.DictRepository;
import core.WordRepository;

public class DictMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3138434478333163316L;
	private static DictMenu dictMenu = new DictMenu("选项");
	private JMenuItem allWords = new JMenuItem("查看已背生词");
	private JMenuItem newWords = new JMenuItem("查看今日生词");
	private JMenuItem reviewWords = new JMenuItem("查看今日复习内容");

	private DictMenu(String title) {
		super(title);
		add(allWords);
		add(newWords);
		add(reviewWords);
		DictAction action = new DictAction();
		allWords.addActionListener(action);
		newWords.addActionListener(action);
		reviewWords.addActionListener(action);
		String dictName = WordRepository.getInstance().getCurrentDictName();
		if(dictName != null){
			if(DictRepository.getInstance().getDict(dictName).isComplete()){
				newWords.setEnabled(false);
				reviewWords.setEnabled(false);
			}
		}
	}

	public static DictMenu getInstance() {
		return dictMenu;
	}

	public JMenuItem getAllWords() {
		return allWords;
	}

	public void setAllWords(JMenuItem allWords) {
		this.allWords = allWords;
	}

	public JMenuItem getNewWords() {
		return newWords;
	}

	public void setNewWords(JMenuItem newWords) {
		this.newWords = newWords;
	}

	public JMenuItem getReviewWords() {
		return reviewWords;
	}

	public void setReviewWords(JMenuItem reviewWords) {
		this.reviewWords = reviewWords;
	}

}
