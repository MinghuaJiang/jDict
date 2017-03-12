package ui.menu;

import javax.swing.JMenuBar;

public class CustomMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7261112491189720830L;
	private static CustomMenuBar myMenuBar = new CustomMenuBar();
	private InterfaceMenu interfaceMenu;
	private FileMenu fileMenu;
	private DictMenu dictMenu;
	private AboutMenu aboutMenu;
	
	private CustomMenuBar(){
		interfaceMenu = InterfaceMenu.getInstance();
		fileMenu = FileMenu.getInstance();
		dictMenu = DictMenu.getInstance();
		aboutMenu = AboutMenu.getInstance();
		add(interfaceMenu);
		add(fileMenu);
		add(dictMenu);
		add(aboutMenu);
		fileMenu.disableMenuItems();
		dictMenu.disableMenuItems();
	}
	public static CustomMenuBar getInstance(){
		return myMenuBar;
	}
}
