package core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private List<Word> words;
    private Set<Word> completeSet;
    private boolean isComplete;
    private String completeDay;
    private boolean isNotified;
    public Dict(String name, List<Word> words)
    {
        this.words = words;
    }

    public int getCurrentCount()
    {
        return currentCount;
    }

    public void addCurrentCount(int count)
    {
        this.currentCount += count;
    }

    public List<Word> getWords()
    {
        return words;
    }

    public void setWords(List<Word> words)
    {
        this.words = words;
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
		if(completeSet.size() == words.size()){
			isComplete = true;
			Date today = new Date();
			completeDay = DateUtilities.YYYYMMDD(today); 
		}
	}
	
	public boolean isComplete(){
		Date today = new Date();
		String day = DateUtilities.YYYYMMDD(today);
		if(isComplete){
			if((Integer.parseInt(day) - Integer.parseInt(completeDay)) > 0){
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean isNotified() {
		return isNotified;
	}

	public void setNotified(boolean isNotified) {
		this.isNotified = isNotified;
	}
	
}
