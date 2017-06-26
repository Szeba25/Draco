package hu.szeba.draco;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Created by Szeba on 6/26/2017.
 */
public class TileMap {

    int scrollX;
    int scrollY;
    Canvas canvas;
    Image tileset;

    public TileMap(Canvas canvas, Image tileset) {
        this.scrollX = 0;
        this.scrollY = 0;
        this.canvas = canvas;
        this.canvas.widthProperty().addListener(event -> draw());
        this.canvas.heightProperty().addListener(event -> draw());

        draw();

        this.tileset = tileset;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int z = 0; z < 6; z++) {
            for (int x = 0 ; x < 30; x++) {
                for (int y = 0; y < 30; y++) {
                    gc.drawImage(this.tileset,
                            0, 80, 40, 40,
                            x*40 - this.scrollX, y*40 - this.scrollY,
                            40, 40);
                }
            }
        }
    }
}
