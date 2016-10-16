package application;
/**
 * Sample Skeleton for 'scenebuilder.fxml' Controller Class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import quiz.NewQuiz;
import quiz.Quiz;
import quiz.ReadNWrite;
import quiz.ReviewQuiz;
import rewardsNStats.Stats;
import rewardsNStats.Word;

public class Control {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	@FXML
	private Text statstext, caption;

	@FXML
	private Button repeatWord, revRepeat,startQuiz,review,calcStats ;
	@FXML
	private Label revtally;

	@FXML
	private TableView statslist = new TableView<Word>();

	@FXML
	private ComboBox<String> statsViews, optionsCombo, reviewCombo, themeCombo, spellingcombo;
	
	@FXML
	private TableColumn word,correct,faulted,failed;


	@FXML
	private ProgressIndicator progSpell = new ProgressIndicator(0);
	@FXML
	private ProgressIndicator progRev = new ProgressIndicator(0);

	@FXML
	private Tab mainmenu, spellingquiz,reviewQuiz,statistics,options;

	@FXML
	private TextField inputText, reviewInput;


	private Quiz quiz;
	private ReviewQuiz rq;
	private String voice = "kal_diphone";
	private File file = new File("NZCER-spelling-lists.txt");
	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location = Control.class.getResource("scenebuilderfxml");

	@FXML
	private PieChart stats;

	private ArrayList<String> quizwords;
	@FXML
	private Label tally;

	@FXML
	private Text selectedfile,statsText, revtext, showWord;



	@FXML
	void changeBackGround(ActionEvent event) {
		if (themeCombo.getValue().equals("City")) {

		}

	}

	@FXML
	void calculateStats(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
	 if( statsViews.getValue() == null || statsViews.getValue().equals("Percentages") ){
		 		statstext.setText("Graph view");
				int a = Stats.getCorrectSize();
				int b = Stats.getFaultedSize();
				int c = Stats.getFailedSize();
			statslist.setVisible(false);
			stats.setVisible(true);
			if(a != 0 | b != 0 | c !=0 ){
			ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
					new PieChart.Data("Passed", (a)), new PieChart.Data("Faulted", b),
					new PieChart.Data("Failed", c));
			stats.setData(piechartData);
			stats.setTitle("Spelling Quiz Statistics");
			caption.setVisible(true);
			caption.setText("Passed: "+ Math.round((float)((float)a/((float)a +(float) b + (float)c) * 100)) + "% Faulted: "+ b/(a+c) + "% Failed: " + c/(a + b) + "%");
			
			
		} else {
			stats.setVisible(false);
			statstext.setText("No statistics yet! Complete a quiz.");
		}
			}else if (statsViews.getValue().equals("List")){
				caption.setVisible(false);
				stats.setVisible(false);
				statslist.setVisible(true);
				statstext.setText("List View");
				ArrayList<String> done = new ArrayList<String>();
				ArrayList<String> outp = new ArrayList<String>();
				ArrayList<String> a = Stats.getCorrectWords();
				ArrayList<String> b = Stats.getFaultedWords();
				ArrayList<String> c = Stats.getFailedWords();
				int correct  = 0;
				int faulted = 0;
				int failed = 0;
				
				this.word.setCellValueFactory(
						new PropertyValueFactory<Word,String>("word"));
				this.correct.setCellValueFactory(new PropertyValueFactory<Word,Integer>("correct"));
				this.faulted.setCellValueFactory(new PropertyValueFactory<Word,Integer>("faulted"));
				this.failed.setCellValueFactory(new PropertyValueFactory<Word,Integer>("failed"));
				 ObservableList<Word> data = FXCollections.observableArrayList();
				
				for(int i = 0; i < a.size(); i ++ ){
					if(done.contains(a.get(i))){
						continue;
					}else {
						done.add(a.get(i));
						correct = Collections.frequency(a, a.get(i));
						faulted = Collections.frequency(b, a.get(i));
						failed = Collections.frequency(c, a.get(i));
						data.add(new Word(a.get(i),correct,faulted,failed));
					
				}
				
			}			
				for(int i = 0; i < b.size(); i++){
					if(done.contains(b.get(i))){
						continue;
					}else {
						done.add(b.get(i));
						correct = Collections.frequency(a, b.get(i));
						faulted = Collections.frequency(b, b.get(i));
						failed = Collections.frequency(c, b.get(i));
						data.add(new Word(b.get(i),correct,faulted,failed));

					}
				}
				
				for(int i = 0; i < c.size(); i++){
					if(done.contains(c.get(i))){
						continue;
					}else {
						done.add(c.get(i));
						correct = Collections.frequency(a, c.get(i));
						faulted = Collections.frequency(b, c.get(i));
						failed = Collections.frequency(c, c.get(i));
						data.add(new Word(c.get(i),correct,faulted,failed));

					}
				}
				statslist.setItems(data);
	}
	}

	@FXML
	void changeVoice(ActionEvent event) {
		if (optionsCombo.getValue() == null) {
			voice = "kal_diphone";
		} else if (optionsCombo.getValue().equals("NZ Voice")) {
			voice = "akl_nz_jdt_diphone";
		} else {
			voice = "kal_diphone";
		}

	}

	@FXML
	void clearStats(ActionEvent event) {
		Stats.clearStats();
	}

	@FXML
	void repeatWord(ActionEvent event) {
		try {
			if (quiz != null) {
				if (!quiz.isComplete()) {
					quiz.sayText(quiz.currentWord());
				}
			} else {
				showWord.setText("Start new quiz");
			}
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						Thread.sleep(2000);// Waiting time
						return null;
					}
				};
			}
		};
		repeatWord.disableProperty().bind(service.runningProperty());
		service.start();
	}

	@FXML
	void revRepeat(ActionEvent event) {
		try {
			if (rq != null) {
				rq.sayText(rq.currentWord());
			}
		} catch (Exception e) {
			e.getMessage();
		}
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						Thread.sleep(2000);// Waiting time
						return null;
					}
				};
			}
		};
		revRepeat.disableProperty().bind(service.runningProperty());
		service.start();

	}

	@FXML
	void startQuiz(ActionEvent event) {
		rq = null;
		if (spellingcombo.getValue() == null && file.getName().equals("NZCER-spelling-lists.txt")) {
			showWord.setText("Choose a level");
		} else {
			startQuiz.setDisable(true);
			if(file.getName().equals("NZCER-spelling-lists.txt")){
			quiz = new NewQuiz(file, inputText, getLevel(spellingcombo.getValue()), tally);
			quiz.loadWords(spellingcombo.getValue());
			}else {
				quiz = new NewQuiz(file, inputText, 0, tally);
				quiz.loadWords("0");
			}
			quiz.setVoice(voice);
			quiz.setStart(startQuiz);
			quizwords = new ArrayList<String>(quiz.giveWords());
			quiz.setProg(progSpell);
			try {
				quiz.startspellingtest(quizwords);
				quiz.giveComboNShow(spellingcombo, showWord);
				showWord.setText("Spelling quiz started");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Service<Void> task = new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						protected Void call() throws Exception {
							while (quiz.getCount() != 10) {
								updateProgress(quiz.getCount() + 1, 10);
							}

							return null;
						}
					};

				}
			};
			task.restart();

			progSpell.progressProperty().bind(task.progressProperty());

		}
	}

	@FXML
	void startReview(ActionEvent event) {
		quiz = null;
		if (reviewCombo.getValue() == null) {
			revtext.setText("Select a level to review from");
		} else {
			int letternum = getLevel(reviewCombo.getValue());
			if (letternum == 12) {
				letternum--;
			}
			if(file.getName().equals("NZCER-spelling-lists.txt"))
			file = new File("reviewfiles/.reviewlevel" + letternum + ".txt");
			else{
				file = new File("reviewfiles/.generalreview.txt");
			}

			rq = new ReviewQuiz(file, reviewInput, letternum, revtally);
			rq.giveComboNShow(reviewCombo, revtext);
			rq.setVoice(voice);
			ArrayList<String> revwordsallforlevel = new ArrayList<String>();
			revwordsallforlevel = rq.getreviewwordsforlevel(letternum);
			if (rq == null || revwordsallforlevel.isEmpty() || file.length() == 1) {
				revtext.setText("No review words for this level");
			} else {

				if (revwordsallforlevel.get(0).equals(""))
					revwordsallforlevel.remove(0);
				showWord.setText("Review quiz started");

				int numrevwords = revwordsallforlevel.size();
				ArrayList<Integer> wordnumbers = new ArrayList<Integer>();
				ReadNWrite rnq = new ReadNWrite(file);
				wordnumbers = rnq.choosewords(revwordsallforlevel);
				rq.createreviewwordlist();
				rq.startreviewtest();

			}
		}

		Service<Void> task = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					protected Void call() throws Exception {
						while (rq.getCount() != 10) {
							updateProgress(rq.getCount(), rq.numreviewwords());
						}
						if (rq.getCount() == rq.numreviewwords()) {
							rq = null;
						}

						return null;
					}
				};

			}
		};
		task.restart();

		progRev.progressProperty().bind(task.progressProperty());

	}
	


	@FXML
	void chooseFile(ActionEvent event) throws IOException {

		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select Word List");
		filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt")

		);
		file = filechooser.showOpenDialog(new Stage());
		if (file != null) {
			selectedfile.setText(file.getName());
			if(!file.getName().equals("NZCER-spelling-lists.txt")){
			spellingcombo.setDisable(true);
			reviewCombo.setDisable(true);
			Stats.clearStats();
			}
		} else{
			file = new File("NZCER-spelling-lists.txt");
			spellingcombo.setDisable(false);
			reviewCombo.setDisable(false);
		}
		}

	// Fetches the current level that has been selected
	private Integer getLevel(String level) {
		if (level.length() == 7) {
			return Integer.parseInt(level.substring(6));
		} else {
			return Integer.parseInt(level.substring(6)) + Integer.parseInt(level.substring(7));
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		spellingcombo.getItems().addAll("Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7",
				"Level 8", "Level 9", "Level 10", "Level 11");
		themeCombo.getItems().addAll("Cities", "Food", "Cars", "Colours");
		reviewCombo.getItems().addAll("Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7",
				"Level 8", "Level 9", "Level 10", "Level 11");
		optionsCombo.getItems().addAll("Default", "NZ Voice");
		statsViews.getItems().addAll("Percentages","List");

	}

}