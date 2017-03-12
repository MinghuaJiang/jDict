package ui.action;

import ui.menu.DictMenu;
import ui.menu.FileMenu;
import ui.menu.InterfaceMenu;
import ui.panel.EditorPanel;
import ui.panel.SearchPanel;
import ui.panel.WordPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cutehuazai on 3/7/17.
 */
public class InterfaceAction implements ActionListener {
    private JMenuItem newItem;
    private JMenuItem dictItem;
    public InterfaceAction(InterfaceMenu menu){
        newItem = menu.getNewMenu();
        dictItem = menu.getDictMenu();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        if(e.getSource() == newItem){
            EditorPanel.getInstance().setVisible(false);
            SearchPanel.getInstance().setVisible(true);
            FileMenu.getInstance().disableMenuItems();
            DictMenu.getInstance().disableMenuItems();
        }
        if(e.getSource() == dictItem){
            SearchPanel.getInstance().setVisible(false);
            EditorPanel.getInstance().setVisible(true);
            EditorPanel.getInstance().notifyCompletion();
            FileMenu.getInstance().enableMenuItems();
            DictMenu.getInstance().enableMenuItems();
        }

	}
}
