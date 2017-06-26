package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        Canvas canvas1 = new Canvas();

        Pane wrapperPane1 = new Pane();
        wrapperPane1.getChildren().add(canvas1);

        canvas1.widthProperty().bind(wrapperPane1.widthProperty());
        canvas1.heightProperty().bind(wrapperPane1.heightProperty());
        canvas1.widthProperty().addListener(event -> drawCanvas(canvas1));
        canvas1.heightProperty().addListener(event -> drawCanvas(canvas1));
        drawCanvas(canvas1);

        Canvas canvas2 = new Canvas();

        Pane wrapperPane2 = new Pane();
        wrapperPane2.setMinWidth(100);
        wrapperPane2.setMaxWidth(400);

        wrapperPane2.getChildren().add(canvas2);

        canvas2.widthProperty().bind(wrapperPane2.widthProperty());
        canvas2.heightProperty().bind(wrapperPane2.heightProperty());
        canvas2.widthProperty().addListener(event -> drawCanvas(canvas2));
        canvas2.heightProperty().addListener(event -> drawCanvas(canvas2));
        drawCanvas(canvas2);

        SplitPane sp = new SplitPane();
        sp.getItems().addAll(wrapperPane1, wrapperPane2);
        sp.setDividerPositions(0.85f);

        BorderPane root = new BorderPane();
        root.setTop(box);
        root.setCenter(sp);

        Scene scene = new Scene(root, 1080, 600);
        scene.getStylesheets().add("style/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void drawCanvas(Canvas canvas) {
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
