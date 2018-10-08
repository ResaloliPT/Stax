package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Carrier;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.bootcamp.gameobjects.brick.BrickFactory;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int PADDING = 10;
    public static final int BRICK_WIDTH = 70;
    public static final int BRICK_HEIGHT = 25;


    private int cols;
    private int score = 0;
    private Rectangle canvas = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
    private BeltGrid beltGrid;
    private CarrierGrid carrierGrid;
    private StackGrid stackGrid;
    private Carrier carrier;
    private boolean gameOver;
     

    public Game(int cols) {
        this.cols = cols;
    }

  /*  public void init() {
        beltGrid = new BeltGrid(cols, 10, Color.LIGHT_GRAY);
        carrierGrid = new CarrierGrid(cols, 2, Color.BLACK);
        stackGrid = new StackGrid(cols, 5, Color.LIGHT_GRAY);

        beltGrid.moveDown(0);
        carrierGrid.moveDown(beltGrid.getHeight());
        stackGrid.moveDown(beltGrid.getHeight() + carrierGrid.getHeight());

        canvas.draw();
        beltGrid.show();
        carrierGrid.show();
        stackGrid.show();
    } */

    public void start() {
        // carrier.init();

        while(!isGameOver()) {

            createBricks();
            moveBricks();
            finalRowCheck();
            endgame();
            addPoints();
        }
        
    }

    private void createBricks() {
        beltGrid.addNewBrick(BrickFactory.getNewBrick());
    }

    private void moveBricks() {
        beltGrid.moveAllBricks();
    }

    private void finalRowCheck() {

        Brick brick = beltGrid.getFallingBrick();

        if (carrier.getCol() == brick.getCol()){
            carrier.addBrick(brick);
        }
    }

    private Brick[] droppedbricks() {

        return carrierGrid.getReleasedBricks();
    }

    private void addPoints() {

        score += stackGrid.resetPointsScore();
    }

    private void endgame() {

        if (!stackGrid.receiveBrick(droppedbricks())) {
            setGameOver();

        }   else {
            addPoints();
        }
    }

    private boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }
}
