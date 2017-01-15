package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ui.action.ChangeDictAction;

public class FileMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7911500558704719085L;
	private static FileMenu fileMenu = new FileMenu("文件");
	private ImportMenu importMenu = new ImportMenu("导入");
	private ExportMenu exportMenu = new ExportMenu("导出");
	private JMenuItem changeMenu = new JMenuItem("切换当前生词表");

	private FileMenu(String title) {
		super(title);
		add(importMenu);
		add(exportMenu);
		add(changeMenu);
		changeMenu.addActionListener(new ChangeDictAction());
	}

	public static FileMenu getInstance() {
		return fileMenu;
	}
}
