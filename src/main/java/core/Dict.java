package core;

import java.io.Serializable;
import java.util.*;

import util.DateUtilities;
import domain.Word;

public class Dict implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2814728976699017362L;
	private String name;
    private int currentCount;
    private LinkedHashMap<Word, Word> words;
    private Set<Word> completeSet;

    public Dict(String name)
    {
        this.words = new LinkedHashMap<Word, Word>();
        this.completeSet = new HashSet<Word>();
    }

    public int getCurrentCount()
    {
        return currentCount;
    }

    public void addCurrentCount(int count)
    {
        this.currentCount += count;
    }

    public Set<Word> getWords()
    {
        return words.keySet();
    }

    public void addWords(List<Word> words)
    {
        for(Word word: words){
            addWord(word);
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
	public void addToCompleteSet(Word word){
		completeSet.add(word);
	}
	
	public boolean isComplete(){
		return completeSet.size() == words.size();
	}

	public boolean containsWord(Word word){
	    return this.words.containsKey(word);
    }

    public void addWord(Word word){
        if(this.words.containsKey(word)){
            Word key = this.words.get(word);
            key.setChinese(word.getChinese());
            this.words.put(key, key);
        }else{
            this.words.put(word, word);
        }
    }
}
