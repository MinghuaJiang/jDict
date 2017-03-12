package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import util.DateUtilities;
import domain.PeriodState;
import domain.Word;

public class WordRepository {
	private static WordRepository repository = new WordRepository();
	private Set<Word> allWords;
	private Map<String, Set<Word>> newWordsMap;
	private Map<String, Set<Word>> reviewWordsMap;
	private String dictName;

	public static WordRepository getInstance() {
		return repository;
	}

	private WordRepository() {

	}

	@SuppressWarnings({ "unchecked" })
	public void init(String dictName) {
		this.dictName = dictName;
		File allWordsFile = new File(dictName + "_allWords.dat");
		File newWordsFile = new File(dictName + "_newWords.dat");
		File reviewWordsFile = new File(dictName + "_reviewWords.dat");

		try {
			if (!allWordsFile.exists()) {
				allWords = new LinkedHashSet<Word>();
			} else {
				ObjectInputStream allWordsStream = new ObjectInputStream(
						new FileInputStream(allWordsFile));
				allWords = (Set<Word>) allWordsStream.readObject();
				allWordsStream.close();
			}
			if (!newWordsFile.exists()) {
				newWordsMap = new HashMap<String, Set<Word>>();
			} else {
				ObjectInputStream newWordsStream = new ObjectInputStream(
						new FileInputStream(newWordsFile));
				newWordsMap = (Map<String, Set<Word>>) newWordsStream
						.readObject();
				newWordsStream.close();
			}
			if (!reviewWordsFile.exists()) {
				reviewWordsMap = new HashMap<String, Set<Word>>();
			} else {
				ObjectInputStream reviewWordsStream = new ObjectInputStream(
						new FileInputStream(reviewWordsFile));
				reviewWordsMap = (Map<String, Set<Word>>) reviewWordsStream
						.readObject();
				reviewWordsStream.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAddedNewWords() {
		Date today = getToday();
		String day = DateUtilities.YYYYMMDD(today);
		return newWordsMap.containsKey(day);
	}

	public void addNewWord(Word word) {
		allWords.add(word);
		Date today = getToday();
		String day = DateUtilities.YYYYMMDD(today);
		word.setAddingDay(day);
		Set<Word> newWordSet = newWordsMap.get(day);
		if (newWordSet == null) {
			newWordSet = new LinkedHashSet<Word>();
			newWordsMap.put(day, newWordSet);
		}
		newWordSet.add(word);
		PeriodState state = word.getState();
		String reviewDay = state.calculateTargetDay(day);
		Set<Word> reviewWordSet = reviewWordsMap.get(reviewDay);
		if (reviewWordSet == null) {
			reviewWordSet = new LinkedHashSet<Word>();
			reviewWordsMap.put(reviewDay, reviewWordSet);
		}
		reviewWordSet.add(word);
	}

	public void addNewWords(List<Word> words) {
		for (Word word : words) {
			addNewWord(word);
		}
	}

	public Set<Word> getAllWords() {
		return allWords;
	}

	public Set<Word> getNewWords() {
		Date today = getToday();
		String day = DateUtilities.YYYYMMDD(today);
		Set<Word> words = newWordsMap.get(day);
		if (words == null) {
			return new LinkedHashSet<Word>();
		}
		return words;
	}

	// TODO changes equals here
	public Set<Word> getReviewWords() {
		Date today = getToday();
		String day = DateUtilities.YYYYMMDD(today);
		Set<Word> words = reviewWordsMap.get(day);
		Dict dict = DictRepository.getInstance().getDict(dictName);
		if (words != null) {
			for (Word word : words) {
				if (!word.getState().equals(PeriodState.COMPLETE)
						&& day.equals(word.getState().calculateTargetDay(
								word.getAddingDay()))) {
					word.setState(word.getState().getNextState());
					if (word.getState().equals(PeriodState.COMPLETE)) {
						dict.addToCompleteSet(word);
						continue;
					}
					String newDay = word.getState().calculateTargetDay(
							word.getAddingDay());
					Set<Word> reviewWordSet = reviewWordsMap.get(newDay);
					if (reviewWordSet == null) {
						reviewWordSet = new LinkedHashSet<Word>();
						reviewWordsMap.put(newDay, reviewWordSet);
					}
					reviewWordSet.add(word);
				}
			}
		}
		if (words == null) {
			return new LinkedHashSet<Word>();
		}
		return words;
	}

	public void storeData() {
		if (dictName != null) {
			File allWordsFile = new File(dictName+"_allWords.dat");
			File newWordsFile = new File(dictName+"_newWords.dat");
			File reviewWordsFile = new File(dictName+"_reviewWords.dat");
			try {
				ObjectOutputStream allWordsStream = new ObjectOutputStream(
						new FileOutputStream(allWordsFile));
				ObjectOutputStream newWordsStream = new ObjectOutputStream(
						new FileOutputStream(newWordsFile));
				ObjectOutputStream reviewWordsStream = new ObjectOutputStream(
						new FileOutputStream(reviewWordsFile));
				allWordsStream.writeObject(allWords);
				newWordsStream.writeObject(newWordsMap);
				reviewWordsStream.writeObject(reviewWordsMap);
				allWordsStream.close();
				newWordsStream.close();
				reviewWordsStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Date getToday() {
		return new Date();
	}
	
	public String getCurrentDictName(){
		return dictName;
	}

}
