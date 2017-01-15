package handler;

import java.util.Set;

import core.WordRepository;
import domain.Word;

public class ReviewedWordHandler {
	public Set<Word> handle() throws Exception{
		return WordRepository.getInstance().getReviewWords();
	}
}
