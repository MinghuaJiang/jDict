package ui.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import domain.Word;

public class WordPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 2167999440408073393L;
    private static WordPanel panel = new WordPanel();
    private JTree tree;
    private JScrollPane pane;
    private DefaultMutableTreeNode root;

    public static WordPanel getInstance() {
        return panel;
    }

    public WordPanel() {
        root = new DefaultMutableTreeNode("");
        tree = new JTree(root);

        final TreePopup treePopup = new TreePopup(tree);
        tree.addMouseListener(new MouseAdapter() {
            public void mouseReleased (MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    treePopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        try {
            this.setSize(180, 600);
            this.setLayout(null);
            pane = new JScrollPane(tree);
            add(pane);
            pane.setSize(170, 568);
            pane.setLocation(5, 0);
            tree.setVisibleRowCount(33);
            tree.setCellRenderer(new ColorRender());
            //tree.setRootVisible(false);
            tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            tree.getLastSelectedPathComponent();
                    if (node == null) return;
                    Object word = (Object)node.getUserObject();
                    if(word instanceof Word) {
                        ExplainPanel.getInstance().updateArea((Word)word);
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateTree(Collection<Word> words, boolean needGroupBy) {
        root.removeAllChildren();
        if (!needGroupBy) {
            for (Word b : words) {
                // this adds every book to the root node
                DefaultMutableTreeNode curBook = new DefaultMutableTreeNode(b);
                root.add(curBook);
            }
        } else {
            Map<String, List<Word>> map = words.stream().collect(Collectors.groupingBy(x -> x.getAddingDay(), TreeMap::new, Collectors.toList()));
            for (String key : ((TreeMap<String, List<Word>>) map).descendingMap().keySet()) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(key);
                root.add(newNode);
                for (Word word : map.get(key)) {
                    newNode.add(new DefaultMutableTreeNode(word));
                }
            }
        }
        expandAll(tree, new TreePath(root));

        tree.updateUI();
    }

    private void expandAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path);
            }
        }
        tree.expandPath(parent);
    }

    class ColorRender extends DefaultTreeCellRenderer{
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(
                    tree, value, sel, exp, leaf, row, hasFocus);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object object = node.getUserObject();
            if(object instanceof Word) {
                if(!((Word) object).isEnabled()){
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            return this;
        }
    }

    class TreePopup extends JPopupMenu {
        public TreePopup(JTree tree) {
            JMenuItem itemDelete = new JMenuItem("kill this word!");
            itemDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                    Object object = node.getUserObject();
                    if(object instanceof Word) {
                        ((Word) object).setEnabled(false);
                    }
                    tree.updateUI();
                }
            });
            add(itemDelete);
        }
    }
}
