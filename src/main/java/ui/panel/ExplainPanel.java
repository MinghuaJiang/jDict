package ui.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Word;

public class ExplainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2586740708158131160L;
	private JTextArea area;
	
	private static ExplainPanel panel = new ExplainPanel();

	public static ExplainPanel getInstance() {
		return panel;
	}
	private JScrollPane pane;

	private ExplainPanel() {
		this.setSize(808, 600);
		this.setLayout(null);	
		area = new JTextArea();		
		pane = new JScrollPane(area);
		pane.setSize(808, 568);
		pane.setLocation(0, 0);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		add(pane);
		
	}

	public void updateStatus(String message){
		area.setText(message);
		//area.setCaretPosition(0);
	}
	
	public void updateArea(Word word) {
		area.setText(word.getEnglish() + "\n" + this.getExplain(word));
		area.setCaretPosition(0);
	}

	private String getExplain(Word word) {
		StringBuilder sb = new StringBuilder();
		List<String> localExplains = getLocalExplain(word);
		sb.append("生词表解释:").append("\n");
		if (localExplains != null) {
			for (String explain : localExplains) {
				sb.append(explain).append("\n");
			}
		}
		sb.append("\n");
		sb.append("网络解释:").append("\n");
		List<String> remoteExplains = getRemoteExplain(word);
		if (remoteExplains != null) {
			for (String remoteExplain : remoteExplains) {
				sb.append(remoteExplain).append("\n");
			}
		}
		return sb.toString();

	}

	private List<String> getLocalExplain(Word word) {
		return word.getChinese();
	}

	private List<String> getRemoteExplain(Word word) {
		return word.getRemoteMeaning();
	}
}
