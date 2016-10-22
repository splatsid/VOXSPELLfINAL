package quiz;

import java.awt.event.KeyAdapter;
import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/*
 * This class is an extension of the quiz class for the purposes of a new quiz. This child class is there to represent the New Quiz type.
 */
public class NewQuiz extends Quiz {
	
	protected TextField _textfield;
	protected boolean correctness;
	//Constructor for the new quiz.
	public NewQuiz(File f,TextField tf, Integer level, Label tally) {
		super(f, tf,level, tally);
		_textfield=tf;
		CreateActionListener();
		correctness = false;
		count=0;
	}
	


}







