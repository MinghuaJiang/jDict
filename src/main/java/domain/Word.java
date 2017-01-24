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
    private String relative;
    private PeriodState state;
    private String addingDay;
    private List<String> remoteMeaning;

    public Word(String english, List<String> chinese, String relative)
    {
        this.english = english;
        this.chinese = chinese;
        this.state = PeriodState.ONE_DAY;
        this.relative = relative;
        try {
            this.remoteMeaning = RemoteWordParser.getInstance().getChineseExplain(english);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public List<String> getRemoteMeaning(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return english != null ? english.equals(word.english) : word.english == null;

    }

    @Override
    public int hashCode() {
        return english != null ? english.hashCode() : 0;
    }

    public String toString(){
    	return this.getEnglish();
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative){
        this.relative = relative;
    }
}
