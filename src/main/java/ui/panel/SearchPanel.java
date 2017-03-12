package ui.panel;

import core.DictRepository;
import core.RemoteWordParser;
import core.WordRepository;
import domain.Word;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cutehuazai on 3/7/17.
 */
public class SearchPanel extends JPanel {
    private JTextField field;
    private JEditorPane editorPane;
    private JButton search;
    private JButton save;

    private static SearchPanel searchPanel = new SearchPanel();

    public static SearchPanel getInstance() {
        return searchPanel;
    }

    private SearchPanel() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 640);
        JPanel buttonPanel = new JPanel();

        field = new JTextField(40);
        field.setText("在此输入要查询的英语单词");
        field.setForeground(Color.LIGHT_GRAY);
        field.setCaretPosition(0);
        field.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (field.getText().equals("在此输入要查询的英语单词")) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setCaretPosition(0);
                }
            }

            public void keyReleased(KeyEvent e) {
                if (field.getText() == null || field.getText().isEmpty()) {
                    field.setText("在此输入要查询的英语单词");
                    field.setForeground(Color.LIGHT_GRAY);
                    field.setCaretPosition(0);
                }
            }
        });
        search = new JButton("Search");
        save = new JButton("Save");

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (field.getText().equals("在此输入要查询的英语单词")) {
                    return;
                }
                RemoteWordParser parser = RemoteWordParser.getInstance();
                try {
                    List<String> chinese = parser.getChineseExplain(field.getText());
                    StringBuilder sb = new StringBuilder();
                    for (String str : chinese) {
                        sb.append(str).append("\n");
                    }
                    editorPane.setText(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (field.getText().equals("在此输入要查询的英语单词")) {
                    return;
                }
                String currentDict = WordRepository.getInstance().getCurrentDictName();
                if (currentDict == null) {
                    String input = JOptionPane.showInputDialog(null, "尚未创建生词库，请输入你想创建的生词库", "提示", JOptionPane.INFORMATION_MESSAGE);
                    //TODO create dict here.
                    if(input != null){
                        DictRepository.getInstance().addDict(input, new ArrayList<Word>());
                        StatusPanel.getInstance().updateDictName(input);
                        JOptionPane.showMessageDialog(null, "成功创建生词库:" + input, "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                String[] text = editorPane.getText().split("\n");
                List<String> chinese = Arrays.asList(text);
                Word word = new Word(field.getText(), chinese, null, chinese);
                currentDict = WordRepository.getInstance().getCurrentDictName();
                String message = "成功保存生词 " + field.getText() + " 到生词库:" + currentDict;
                JOptionPane.showMessageDialog(null,message, "提示", JOptionPane.INFORMATION_MESSAGE);
                DictRepository.getInstance().getDict(currentDict).addWord(word);
            }
        });

        buttonPanel.add(field);
        buttonPanel.add(search);
        buttonPanel.add(save);
        add(buttonPanel, BorderLayout.NORTH);
        editorPane = new JEditorPane();
        add(new JScrollPane(editorPane), BorderLayout.CENTER);

    }

}
