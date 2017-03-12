package ui.menu;

import ui.action.ImportAction;
import ui.action.InterfaceAction;

import javax.swing.*;

/**
 * Created by cutehuazai on 3/7/17.
 */
public class InterfaceMenu extends JMenu{
    private static final long serialVersionUID = 7964881275325704562L;
    private static InterfaceMenu interfaceMenu = new InterfaceMenu("界面");
    private JMenuItem newMenu = new JMenuItem("查询添加生词");
    private JMenuItem dictMenu = new JMenuItem("背单词");
    private InterfaceMenu(String title){
        super(title);
        add(newMenu);
        add(dictMenu);
        InterfaceAction action = new InterfaceAction(this);
        newMenu.addActionListener(action);
        dictMenu.addActionListener(action);
    }

    public static InterfaceMenu getInstance() {
        return interfaceMenu;
    }

    public JMenuItem getNewMenu() {
        return newMenu;
    }

    public JMenuItem getDictMenu() {
        return dictMenu;
    }
}
