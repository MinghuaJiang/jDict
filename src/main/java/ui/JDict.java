package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ui.menu.CustomMenuBar;
import ui.panel.EditorPanel;
import ui.panel.StatusPanel;
import util.ResourceReader;
import core.DictRepository;
import core.WordRepository;

public class JDict extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1411413601160098311L;

	public JDict(){
			
		try {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
			Thread.sleep(200);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			this.setIconImage(ResourceReader.getImageFromJar("/image/background.jpg", this.getClass()));
			setTitle("华仔背单词");
			DictRepository.getInstance().init();
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					DictRepository.getInstance().storeDict();
					WordRepository.getInstance().storeData();
					StatusPanel.getInstance().storeCurrentDictName();
					System.exit(0);
				}
			});
			setResizable(false);
			setVisible(true);
			setJMenuBar(CustomMenuBar.getInstance());
			// pack();
			EditorPanel panel = EditorPanel.getInstance();
			add(panel);
			setSize(1000, 670);
			
			setLocationRelativeTo(null);
			// this.setUndecorated(true);

		}
	

	public static void main(String[] args){
		new JDict();
	}
}