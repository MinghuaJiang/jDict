package ui.menu;

import javax.swing.JMenuBar;

public class CustomMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7261112491189720830L;
	private static CustomMenuBar myMenuBar = new CustomMenuBar();
	private FileMenu fileMenu;
	private DictMenu dictMenu;
	private AboutMenu aboutMenu;
	
	private CustomMenuBar(){
		fileMenu = FileMenu.getInstance();
		dictMenu = DictMenu.getInstance();
		aboutMenu = AboutMenu.getInstance();
		add(fileMenu);
		add(dictMenu);
		add(aboutMenu);
		
		
	}
	public static CustomMenuBar getInstance(){
		return myMenuBar;
	}
}
