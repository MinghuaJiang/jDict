package ui.panel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.WordRepository;

public class StatusPanel extends JPanel {

	private static StatusPanel panel = new StatusPanel();

	private StatusPanel() {
		setSize(1000, 40);
		setLayout(null);
		add(dictLabel);
		dictLabel.setLocation(25, 0);
		dictLabel.setSize(70, 40);
		add(dictData);
		dictData.setLocation(100, 0);
		dictData.setSize(150, 40);
		add(allLabel);
		allLabel.setLocation(350,0);
		allLabel.setSize(85,40);
		add(allData);
		allData.setLocation(445,0);
		allData.setSize(50, 40);
		add(newLabel);
		newLabel.setLocation(570, 0);
		newLabel.setSize(85, 40);
		add(newData);
		newData.setLocation(660,0);
		newData.setSize(50, 40);
		add(reviewLabel);
		reviewLabel.setLocation(780, 0);
		reviewLabel.setSize(120,40);
		add(reviewData);
		reviewData.setLocation(910, 0);
		reviewData.setSize(50,40);
	}

	public void init() {
		try {
			File dict = new File("currentdict.dat");
			if (dict.exists()) {
				ObjectInputStream stream = new ObjectInputStream(
						new FileInputStream(dict));
				String dictName = (String) stream.readObject();
				stream.close();
				updateDictName(dictName);
			} else {
				updateDictName("");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static StatusPanel getInstance() {
		return panel;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3538780195244087978L;
	private JLabel dictLabel = new JLabel("当前生词表:");
	private JLabel dictData = new JLabel();
	private JLabel allLabel = new JLabel("已背生词个数:");
	private JLabel allData = new JLabel();
	private JLabel newLabel = new JLabel("今日生词个数:");
	private JLabel newData = new JLabel();
	private JLabel reviewLabel = new JLabel("今日需复习单词个数:");
	private JLabel reviewData = new JLabel();

	public void storeCurrentDictName() {
		try {
			File dict = new File("currentdict.dat");
			ObjectOutputStream dictStream = new ObjectOutputStream(
					new FileOutputStream(dict));
			dictStream.writeObject(this.getDictName());
			dictStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDictName() {
		return dictData.getText();
	}

	public void updateDictName(String dictName) {
		dictData.setText(dictName);
		if(!dictName.equals("")){
			WordRepository.getInstance().init(dictName);
			this.updateNewWordCount(WordRepository.getInstance().getNewWords().size());
			this.updateReviewWordCount(WordRepository.getInstance().getReviewWords().size());
			this.updateAllWordCount(WordRepository.getInstance().getAllWords().size());
		}
	}

	public void updateAllWordCount(int count) {
		allData.setText(count + "");
	}

	public void updateNewWordCount(int count) {
		newData.setText(count + "");
	}

	public void updateReviewWordCount(int count) {
		reviewData.setText(count + "");
	}

}
