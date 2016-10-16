
package rewardsNStats;

import java.io.File;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


//This class is for the scene when the video reward is playing.
public class MovieScene {
	
	//Initialising fields.
	BorderPane _border;
	URL _url;
	Media m;
	MediaPlayer mp;
	MediaView mv;
	Button play,stop,fast,slow,pause,negate,normal;
	Stage _primaryStage;
	Scene _scene;
	File f;
	
	//Creating a constructor which will set up the video being played as having no effects.
	public MovieScene(Stage primaryStage,Scene scene) {

		_border = new BorderPane();
		_border.setPrefSize(700,600);

		//Formatting heading.
		Text heading = new Text();
		heading.setFill(Color.BLUE);
		heading.setText("LEVEL REWARD! \n Press Play");
		heading.setTextAlignment(TextAlignment.CENTER);
		heading.setFont(Font.font(null, FontWeight.BOLD, 24));
		heading.setUnderline(true);
		FlowPane htop = new FlowPane();
		htop.getChildren().add(heading);
		_border.setTop(htop);
		htop.setAlignment(Pos.CENTER);

		//Creating the media view and displaying the video in the middle.
		f = new File("movie.mp4");
		m = new Media(f.toURI().toString());
		mp = new MediaPlayer(m);
		mv = new MediaView(mp);
		mv.autosize();
		mv.setPreserveRatio(true);
		_border.setCenter(mv);
		mp.setAutoPlay(false);

		//Arranging the buttons along the bottom.
		FlowPane buttonpane = new FlowPane();
		play = new Button("Play");
		stop = new Button("Stop");
		slow = new Button("Watch slow version");
		fast = new Button("Watch fast version");
		pause = new Button ("Pause");
		normal = new Button("Watch normal version");
		negate = new Button("Watch negated version");
		
		//Adding event handlers of when a button is pressed.
		play.setOnAction(e -> handleButtonAction(e));
		stop.setOnAction(e -> handleButtonAction(e));
		slow.setOnAction(e -> handleButtonAction(e));
		fast.setOnAction(e -> handleButtonAction(e));
		pause.setOnAction(e -> handleButtonAction(e));
		negate.setOnAction(e -> handleButtonAction(e));
		normal.setOnAction(e -> handleButtonAction(e));
		
		//Formatting button pane.
		buttonpane.getChildren().addAll(play,stop,pause,slow,fast,negate,normal);
		_border.setBottom(buttonpane);
		buttonpane.setAlignment(Pos.CENTER);
		buttonpane.setPadding(new Insets(5, 10, 5, 10));
		buttonpane.setHgap(10);
		
		//Initialising some fields.
		_primaryStage=primaryStage;
		_scene=scene;

	}

	//Have different actions for when different buttons are pressed.
	//When the video is completed it will return back to the menu.
	public void handleButtonAction(ActionEvent event)
	{
		//Want to start the video and also resume from pause. 
		if(event.getSource()== play){
			mp.play();
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mp.stop();
				}
			});

			//Want to watch the video at a slower speed.
		}else if(event.getSource()==slow){
			mp.setRate(0.5);
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mp.stop();
				}
			});

			//Want to stop the video and go straight to the main menu.
		}else if(event.getSource()== stop){
			mp.stop();
			
			//Want to watch the video at a faster speed.
		}else if(event.getSource()== fast){
			mp.setRate(2);
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mp.stop();
				}
			});

			//Want to pause the video.
		}else if(event.getSource() ==pause){
			mp.pause();
			
			//BONUS MARK: This is when the user wants to play the manipulated version of the video. FFMPEG was used to
			//negate and save the manipulated video. If the user wants to view this version the original video will
			//stop and it will start this version.
		}else if(event.getSource() == negate){
			mp.stop();
			f = new File("negatedmovie.mp4");
			m = new Media(f.toURI().toString());
			mp = new MediaPlayer(m);
			mv = new MediaView(mp);
			mv.autosize();
			mv.setPreserveRatio(true);
			_border.setCenter(mv);
			mp.play();
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mp.stop();
				}
			});
			
			//When the negated clip is playing and the viewer wants to watch the normal version.
		}else if(event.getSource()==normal){
			mp.stop();
			f = new File("movie.mp4");
			m = new Media(f.toURI().toString());
			mp = new MediaPlayer(m);
			mv = new MediaView(mp);
			mv.autosize();
			mv.setPreserveRatio(true);
			_border.setCenter(mv);
			mp.play();
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mp.stop();
				}
			});
		}
	}
	
//Create the movie scene.
	public Scene moviescene() {
		Scene moviescene = new Scene(_border,850,600);
		return moviescene;

	}
	






}