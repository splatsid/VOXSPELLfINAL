package rewardsNStats;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Word {
	
	private SimpleStringProperty word = new SimpleStringProperty();
	private SimpleIntegerProperty correct = new SimpleIntegerProperty();
	private SimpleIntegerProperty faulted= new SimpleIntegerProperty();
	private SimpleIntegerProperty failed = new SimpleIntegerProperty();
	
	
	public Word(String word, int correct, int faulted, int failed){
		this.word.set(word);
		this.correct.set(correct);
		this.faulted.set(faulted);
		this.failed.set(failed);
	}
	
	
	public SimpleStringProperty wordProperty(){
		return word;
	}
	
	public SimpleIntegerProperty correctProperty(){
		return correct;
	}
	public SimpleIntegerProperty faultedProperty(){
		return faulted;
	}
	
	public SimpleIntegerProperty failedProperty(){
		return failed;
	}

}
