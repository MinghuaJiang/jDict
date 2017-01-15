package ui.action;

import handler.AllWordHandler;
import handler.NewWordHandler;
import handler.ReviewedWordHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JOptionPane;

import ui.menu.DictMenu;
import ui.panel.StatusPanel;
import ui.panel.WordPanel;
import core.Dict;
import core.DictRepository;
import core.WordRepository;
import domain.Word;

public class DictAction implements ActionListener {

	private NewWordHandler newWordHandler;
	private AllWordHandler allWordHandler;
	private ReviewedWordHandler reviewedWordHandler;

	public DictAction() {
		newWordHandler = new NewWordHandler();
		reviewedWordHandler = new ReviewedWordHandler();
		allWordHandler = new AllWordHandler();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if (e.getSource().equals(DictMenu.getInstance().getNewWords())) {
				if (WordRepository.getInstance().isAddedNewWords()) {
					WordPanel.getInstance().updateList(
							WordRepository.getInstance().getNewWords());
				} else {
					Dict dict = DictRepository.getInstance().getDict(
							WordRepository.getInstance().getCurrentDictName());
					int maxLearningCount = dict.getWords().size()
							- dict.getCurrentCount();
					if (maxLearningCount == 0) {
						JOptionPane.showMessageDialog(null,
								"该生词表生词已学完,完成复习后即完成该生词表的学习", "提示",
								JOptionPane.WARNING_MESSAGE);
					}else{
						boolean isValid = false;
						do {
							try {
								String input = JOptionPane.showInputDialog(null,
										"请输入你今天想学习的生词数(最大" + (maxLearningCount)
												+ ")", "提示",
										JOptionPane.INFORMATION_MESSAGE);
								if (input != null) {
									int learningCount = Integer.parseInt(input);
									if (learningCount <= maxLearningCount) {
										Set<Word> words = newWordHandler.handle(
												WordRepository.getInstance().getCurrentDictName(),
												learningCount);
										WordPanel.getInstance().updateList(words);
										StatusPanel.getInstance()
												.updateNewWordCount(
														WordRepository
																.getInstance()
																.getNewWords()
																.size());
										StatusPanel.getInstance()
												.updateAllWordCount(
														WordRepository
																.getInstance()
																.getAllWords()
																.size());
										isValid = true;
									} 
								}

							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							}
							if(!isValid){
								JOptionPane.showMessageDialog(null, "请输入有效地数字",
										"提示", JOptionPane.WARNING_MESSAGE);
							}
						} while (!isValid);
					}
				}
			} else if (e.getSource().equals(
					DictMenu.getInstance().getReviewWords())) {
				Set<Word> words = reviewedWordHandler.handle();
				WordPanel.getInstance().updateList(words);
			} else {
				Set<Word> words = allWordHandler.handle();
				WordPanel.getInstance().updateList(words);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
