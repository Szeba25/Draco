package hu.szeba.draco;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by Szeba on 6/26/2017.
 */
public class TileMap {

    public static int dragStartX;
    public static int dragStartY;
    public static int scrollX;
    public static int scrollY;

    Canvas canvas;
    Image tileset;

    public TileMap(Canvas canvas, Image tileset) {
        dragStartX = 0;
        dragStartY = 0;
        scrollX = 0;
        scrollY = 0;

        this.canvas = canvas;
        this.canvas.widthProperty().addListener(event -> draw());
        this.canvas.heightProperty().addListener(event -> draw());
        this.canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TileMap.dragStartX = (int) event.getSceneX();
                TileMap.dragStartY = (int) event.getSceneY();
            }
        });
        this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TileMap.scrollX -= (TileMap.dragStartX - event.getSceneX());
                TileMap.scrollY -= (TileMap.dragStartY - event.getSceneY());
            }
        });
        draw();

        this.tileset = tileset;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int z = 0; z < 6; z++) {
            for (int x = 0 ; x < 30; x++) {
                for (int y = 0; y < 30; y++) {
                    gc.drawImage(this.tileset,
                            0, 80, 40, 40,
                            x*40 - scrollX, y*40 - scrollY,
                            40, 40);
                }
            }
        }
    }
}
