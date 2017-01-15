package domain;

import java.io.Serializable;
import java.util.List;

import core.RemoteWordParser;

public class Word implements Comparable<Word>, Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6735300174208362856L;
	private String english;
    private List<String> chinese;
    private PeriodState state;
    private String addingDay;
    private List<String> remoteMeaning;

    public Word(String english, List<String> chinese) throws Exception
    {
        this.english = english;
        this.chinese = chinese;
        this.state = PeriodState.ONE_DAY;
        this.remoteMeaning = RemoteWordParser.getInstance().getChineseExplain(english);
    }

    public String getEnglish()
    {
        return english;
    }

    public void setEnglish(String english)
    {
        this.english = english;
    }


    public List<String> getChinese()
    {
        return chinese;
    }

    public void setChinese(List<String> chinese)
    {
        this.chinese = chinese;
    }

    public List<String> getRemoteMeaning()
    {
        return remoteMeaning;
    }

    public void setRemoteMeaning(List<String> remoteMeaning)
    {
        this.remoteMeaning = remoteMeaning;
    }

    public PeriodState getState()
    {
        return state;
    }

    public void setState(PeriodState state)
    {
        this.state = state;
    }

    public String getAddingDay()
    {
        return addingDay;
    }

    public void setAddingDay(String addingDay)
    {
        this.addingDay = addingDay;
    }

    public int compareTo(Word o)
    {
        // TODO Auto-generated method stub
        return this.getEnglish().compareTo(o.getEnglish());
    }

    public String toString(){
    	return this.getEnglish();
    }
}
