package ui.panel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.Dict;
import core.DictRepository;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4578242479887671661L;

	private StatusPanel statusPanel;

	private WordPanel wordPanel;

	private ExplainPanel explainPanel;

	private static EditorPanel editorPanel = new EditorPanel();

	public static EditorPanel getInstance() {
		return editorPanel;
	}

	private EditorPanel() {
		statusPanel = StatusPanel.getInstance();
		wordPanel = WordPanel.getInstance();
		explainPanel = ExplainPanel.getInstance();
		this.setLayout(null);
		add(statusPanel);
		statusPanel.setLocation(0, 5);
		statusPanel.init();
		add(wordPanel);
		wordPanel.setLocation(0, 50);
		add(explainPanel);
		explainPanel.setLocation(wordPanel.getWidth(), 50);
	}

	public void notifyCompletion() {
		String dictName = statusPanel.getDictName();
		if (!dictName.isEmpty()) {
			Dict dictionary = DictRepository.getInstance().getDict(dictName);
			if (dictionary.isComplete()) {
				JOptionPane.showMessageDialog(null, "恭喜你,当前生词库已背完,请添加更多生词或者更换生词库",
						"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
}
