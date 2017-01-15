package ui.panel;

import java.util.Collection;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Word;

public class WordPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2167999440408073393L;
	private static WordPanel panel = new WordPanel();
	private JList<Word> list;
	private JScrollPane pane;
	public static WordPanel getInstance() {
		return panel;
	}

	public WordPanel(){
		list = new JList<Word>();
		try {
			this.setSize(180,600);
			this.setLayout(null);
			pane = new JScrollPane(list);
			add(pane);
			pane.setSize(170,568);
			pane.setLocation(5, 0);
			list.setVisibleRowCount(33);
			list.addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {
					final Word word = list.getSelectedValue();
					ExplainPanel.getInstance().updateArea(word);
				}
				
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateList(Collection<Word> words) {
		list.setListData(words.toArray(new Word[0]));
	}

}
