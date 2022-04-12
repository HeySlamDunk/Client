import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sun.tools.jar.CommandLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Consumer;

public class Morra extends Application {

	private HashMap<String, Scene> sceneMap;

	MorraInfo x;

	// Objects for the MorraWelcome Screen
	// 1 Button, 3 Text Labels, 2 TextBoxes
	private Button morEnter;
	public Client ClientConnection, clientConn;
	private TextField textField1, textField2;
	public int portNum;
	public String ipAddress;
	private Text Title, port, server;
	//

	// Objects for gameState Screen
	// tbd
	private Text titleGameState,pickDis;
	private TextField entGuess, points;
	private Button sendData;
	public ListView<String> clientList;
	Consumer<Serializable> data;
	Consumer<Serializable> gameD;


	// Opponent labels and plays, guess
	private TextField opGuess;
	private Text opPlayed;





	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Morra");
		sceneMap = new HashMap<>();

		sceneMap.put("Morra", MorraWelcome(primaryStage));
		sceneMap.put("gameState", gameState(primaryStage));
		sceneMap.put("ending", endingScreen(primaryStage));

		// setOnaAction for Enter
		morEnter.setOnAction(e->{
			data = d -> {
				Platform.runLater(() -> {
					clientList.getItems().add(data.toString());
				});
			};

			gameD = g -> {
				Platform.runLater(() -> {
					x = (MorraInfo) g;
				});
			};
			portNum = Integer.parseInt(textField1.getText());
			ipAddress = textField2.getText();
			ClientConnection = new Client(data, portNum, ipAddress, gameD);

			ClientConnection.start();

			primaryStage.setScene(sceneMap.get("gameState"));
		});

		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(sceneMap.get("gameState"));
		primaryStage.show();
	}



	public Scene MorraWelcome(Stage primaryStage) {

		VBox pane = new VBox();

		// Labels for Welcome Screen
		Title = new Text("Welcome to Morra\n");
		port = new Text("Port Number: \n");
		server = new Text("\nIP Address: \n");

		// TextField for Port Number
		textField1 = new TextField();
		textField1.setPrefWidth(80);
		textField1.setMaxWidth(80);

		// TextField for IP Address
		textField2 = new TextField();
		textField2.setPrefWidth(80);
		textField2.setMaxWidth(80);

		// Button for Welcome Screen
		morEnter = new Button("Connect to game");


		// Font settings for MoraWelcome
		pane.setAlignment(Pos.CENTER);
		Title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		port.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		server.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

		// Add all items to pane
		pane.getChildren().addAll(Title, port, textField1, server, textField2, morEnter);
		pane.setStyle("-fx-background-color: lightBlue;");


		return new Scene(pane,500, 500);

	}

	public Scene gameState(Stage primaryStage) throws Exception {

		VBox mainPane = new VBox();
		VBox pane = new VBox();
		VBox pane2 = new VBox();
		VBox opPane =  new VBox();
		clientList = new ListView<String>();
		MorraInfo player1 = new MorraInfo();
		MorraInfo player2 = new MorraInfo();

		Image one = new Image(new FileInputStream("src/main/java/One.png"));
		ImageView imageOne = new ImageView(one);
		Image two = new Image(new FileInputStream("src/main/java/Two.png"));
		ImageView imageTwo = new ImageView(two);
		Image three = new Image(new FileInputStream("src/main/java/Three.png"));
		ImageView imageThree = new ImageView(three);
		Image four = new Image(new FileInputStream("src/main/java/Four.png"));
		ImageView imageFour = new ImageView(four);
		Image five = new Image(new FileInputStream("src/main/java/Five.png"));
		ImageView imageFive = new ImageView(five);

		sendData = new Button("Finalize");
		titleGameState = new Text("Morra");
		points = new TextField("0 \n\n");
		points.setEditable(false);


		entGuess = new TextField("Erase and enter a Guess 1-5\n");

		pickDis = new Text("Pick an image 1-5\n\n");
		imageOne.setFitHeight(50);
		imageOne.setFitWidth(50);
		imageTwo.setFitHeight(50);
		imageTwo.setFitWidth(50);
		imageThree.setFitHeight(50);
		imageThree.setFitWidth(50);
		imageFour.setFitHeight(50);
		imageFour.setFitWidth(50);
		imageFive.setFitHeight(50);
		imageFive.setFitWidth(50);

		imageOne.setOnMouseClicked((MouseEvent e) ->{

			if(x.playerID == 1) {
				player1.myMove = 1;
				clientList.getItems().add("You have picked 1");
			} else if(x.playerID == 2) {
				player2.p2Move = 1;
			}
		});

		imageTwo.setOnMouseClicked((MouseEvent e) ->{

			if(x.playerID == 1) {
				player1.myMove = 2;
				clientList.getItems().add("You have picked 2");
			} else if(x.playerID == 2) {
				player2.p2Move = 2;
			}
		});

		imageThree.setOnMouseClicked((MouseEvent e) ->{

			if(x.playerID == 1) {
				player1.myMove = 3;
				clientList.getItems().add("You have picked 3");
			} else if (x.playerID == 2) {
				player2.p2Move = 3;
			}
		});

		imageFour.setOnMouseClicked((MouseEvent e) ->{

			if(x.playerID == 1) {
				player1.myMove = 4;
				clientList.getItems().add("You have picked 4");
			} else if(x.playerID == 2) {
				player2.p2Move = 4;
			}
		});

		imageFive.setOnMouseClicked((MouseEvent e) ->{

			if(x.playerID == 1) {
				player1.myMove = 5;
				clientList.getItems().add("You have picked 5");
			} else if (x.playerID == 2) {
				player2.p2Move = 5;

			}
		});

		// Formatting for Morra title gameState
		pane.setAlignment(Pos.TOP_CENTER);
		titleGameState.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));


		// Formatting for points and label
		pane2.setAlignment(Pos.CENTER_LEFT);

		entGuess.setMaxWidth(400);
		entGuess.prefHeight(400);
		points.setMaxWidth(200);
		points.prefHeight(200);

		points.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); // points myPoints
		pickDis.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); // pick (1-5)

		pane2.getChildren().addAll(points, entGuess, pickDis, imageOne, imageTwo, imageThree, imageFour, imageFive, sendData, clientList);
		pane.getChildren().addAll(titleGameState);


		sendData.setOnAction(e-> {

			if(x.playerID == 1) {
				player1.myGuess = Integer.parseInt(entGuess.getText());
				ClientConnection.send((Serializable) player1);
			} else if(x.playerID == 2) {
				player2.p2Guess = Integer.parseInt(entGuess.getText());
				ClientConnection.send((Serializable) player2);
			}

			clientList.getItems().add("Your guess was " + player1.myGuess);
			clientList.getItems().add("You picked " + player1.myMove);
			clientList.getItems().add("Oppenents Guess was " + player2.p2Guess);
			clientList.getItems().add("Opponent picked " + player2.p2Move);

		});

		opPane.setAlignment(Pos.TOP_RIGHT);
		int opGues = player2.p2Guess;
		int opActual = player2.p2Move;

		opGuess = new TextField(Integer.toString(opGues));
		opPlayed = new Text(Integer.toString(opActual));

		if(opActual == 1) {
			Image oneOP = new Image(new FileInputStream("src/main/java/One.png"));
			ImageView imageOneOP = new ImageView(oneOP);
			pane.getChildren().addAll(opGuess);
			pane.getChildren().add(opPlayed);
			pane.getChildren().add(imageOneOP);
		} else if (opActual == 2) {
			Image twoOP = new Image(new FileInputStream("src/main/java/Two.png"));
			ImageView imagetwoOP = new ImageView(twoOP);
			pane.getChildren().add(opGuess);
			pane.getChildren().add(opPlayed);
			pane.getChildren().add(imagetwoOP);
		} else if(opActual == 3) {
			Image threeOP = new Image(new FileInputStream("src/main/java/Three.png"));
			ImageView imagethreeOP = new ImageView(threeOP);
			pane.getChildren().addAll(opGuess);
			pane.getChildren().add(opPlayed);
			pane.getChildren().add(imagethreeOP);
		} else if (opActual == 4) {
			Image fourOP = new Image(new FileInputStream("src/main/java/Four.png"));
			ImageView imagefourOP = new ImageView(fourOP);
			pane.getChildren().addAll(opGuess);
			pane.getChildren().add(opPlayed);
			pane.getChildren().add(imagefourOP);
		} else if (opActual == 5) {
			Image fiveOP = new Image(new FileInputStream("src/main/java/Five.png"));
			ImageView imagefiveOP = new ImageView(fiveOP);
			opPane.getChildren().addAll(opGuess);
			pane.getChildren().add(opPlayed);
			pane.getChildren().add(imagefiveOP);
		}




		mainPane.getChildren().addAll(pane,pane2);

		return new Scene(mainPane,900, 900);

	}

	public Scene endingScreen(Stage primaryStage) {

		VBox pane = new VBox();










		return new Scene(pane,700, 700);
	}



















}