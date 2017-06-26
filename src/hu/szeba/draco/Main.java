package hu.szeba.draco;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Draco Editor");
        primaryStage.setMaximized(true);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuAbout = new Menu("About");
        MenuItem fileOpen = new MenuItem("Open");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuAbout);
        menuFile.getItems().add(fileOpen);

        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("something");
        tab.setClosable(false);
        tabPane.getTabs().add(tab);

        VBox box = new VBox();
        box.getChildren().addAll(menuBar, tabPane);

        File file = new File("resources/tileset.png");
        Image image = new Image(file.toURI().toString());

        Canvas mapCanvas = new Canvas();
        Pane mapWrapper = new Pane();
        mapWrapper.getChildren().add(mapCanvas);
        mapCanvas.widthProperty().bind(mapWrapper.widthProperty());
        mapCanvas.heightProperty().bind(mapWrapper.heightProperty());

        TileMap tileMap = new TileMap(mapCanvas, image);

        Canvas tileCanvas = new Canvas();
        Pane tileWrapper = new Pane();
        tileWrapper.setMinWidth(100);
        tileWrapper.setMaxWidth(400);
        tileWrapper.getChildren().add(tileCanvas);
        tileCanvas.widthProperty().bind(tileWrapper.widthProperty());
        tileCanvas.heightProperty().bind(tileWrapper.heightProperty());
        tileCanvas.widthProperty().addListener(event -> drawTestCanvas(tileCanvas));
        tileCanvas.heightProperty().addListener(event -> drawTestCanvas(tileCanvas));
        drawTestCanvas(tileCanvas);

        SplitPane sp = new SplitPane();
        sp.getItems().addAll(mapWrapper, tileWrapper);
        sp.setDividerPositions(0.85f);

        BorderPane root = new BorderPane();
        root.setTop(box);
        root.setCenter(sp);

        Scene scene = new Scene(root, 1080, 600);
        scene.getStylesheets().add("style/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void drawTestCanvas(Canvas canvas) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
        gc.setFill(Color.BLUE);
        gc.fillOval(-30, -30, 60, 60);
        gc.fillOval(-30 + width, -30, 60, 60);
        gc.fillOval(-30, -30 + height, 60, 60);
        gc.fillOval(-30 + width, -30 + height, 60, 60);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
