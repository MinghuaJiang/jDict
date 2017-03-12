package domain;

import java.util.List;

/**
 * Created by cutehuazai on 3/12/17.
 */
public class RemoteWord{
    private String word;
    private String pron;
    private List<String> meaning;
    private List<String> phrases;
    public RemoteWord(String word, String pron, List<String> meaning, List<String> phrases){
        this.word = word;
        this.pron = pron;
        this.meaning = meaning;
        this.phrases = phrases;
    }
}
