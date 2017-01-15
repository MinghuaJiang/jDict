package handler;

import java.util.List;
import java.util.Set;

import core.Dict;
import core.DictRepository;
import core.WordRepository;
import domain.Word;

public class NewWordHandler {

	    public Set<Word> handle(String dictName, int learningCount) throws Exception
	    {
	        Dict dict = DictRepository.getInstance().getDict(dictName);
	        int currentCount = dict.getCurrentCount();
	        List<Word> words = dict.getWords().subList(currentCount,
	                currentCount + learningCount);
	        WordRepository.getInstance().addNewWords(words);
	        dict.addCurrentCount(learningCount);
	        return WordRepository.getInstance().getNewWords();
	    }
}
