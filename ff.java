
//import libraries 
import tester.Tester;
import java.util.Random;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
//////////////////////////////////////////////////////////////////

// Interface for fish
interface IFish {
  WorldScene draw(WorldScene bg);

  // checks to see if fish will collide using size and location
  boolean willItCollide(int x, int y, int size);

  // checks to see if fish will collide
  boolean doesCollide(IFish backFish);

  // checks to see if player and list of fish will collide
  boolean doesItCollide(ILoFish backFish);

  // moves fish to left
  IFish moveLeft();

  // moves fish to right
  IFish moveRight();

  // moves fish up
  IFish moveUp();

  // moves fish down
  IFish moveDown();

  // checks if its eaten
  boolean eatenHuh(AFish player);

  // checks if background fish and player overlap
  boolean overlaps(int playX, int playY, int playSize);

  // checks if smaller than player
  boolean isSmaller(int size);

  // sizes of the fish
  IFish sizer();

  // removes the eaten fish
  World removeCollisions(ILoFish backFish);

  // checks if the fish can be eaten
  boolean canBeEaten(BackFish back);
}

//////////////////////////////////////////////////////////////////

// Represents a class of fish with color, size, and location
abstract class AFish implements IFish {
  int size;
  int x;
  int y;
  Color c;
  Random rand;

  // Constructor for the background fish with color, and randomized size and
  // location
  AFish() {
    this.rand = new Random();
    this.size = rand.nextInt(10);
    this.x = rand.nextInt(600);
    this.y = rand.nextInt(400);
  }

  // Constructor for the player fish with color, location, and a starting size of
  // 2
  AFish(int x, int y) {
    this.size = 20;
    this.x = x;
    this.y = y;
    this.rand = new Random();
  }

  // checks if 2 fish are in the same spot and which is bigger
  boolean biggerHuh(AFish other) {
    int distance = (int) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    return this.size > other.size && distance < this.size / 2;
  }

  // returns true if the the fish can be eaten
  public boolean eatenHuh(AFish other) {
    return other.biggerHuh(this);
  }

  // checks if the fish overlap each other
  public boolean overlaps(int playX, int playY, int playSize) {
    int dx = this.x - playX;
    int dy = this.y - playY;
    int dist = (int) Math.sqrt(dx * dx + dy * dy);
    return dist < (this.size + playSize) / 2;
  }

  // checks if the fish actually collided with each other
  @Override
  public boolean doesItCollide(ILoFish backFish) {
    return backFish.doesCollide(this.x, this.y, this.size);
  }

  // checks which fish is smaller
  public boolean isSmaller(int size) {
    return this.size < size;
  }

  // checks which fish is bigger
  public boolean isBigger(int size) {
    return this.size > size;
  }
}

//////////////////////////////////////////////////////////////////

//class to represent the background fish
class BackFish extends AFish {
  int direction;

  BackFish() {
    super();
    this.direction = rand.nextInt(4);
  }

  BackFish(int x) {
    super();
    this.x = x;
  }

  // constructor
  BackFish(int size, int x, int y, int direction, Color c) {
    this.size = size;
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.c = c;
  }

  public BackFish(int xStart, int yStart) {
    this.x = xStart;
    this.y = yStart;
    this.size = rand.nextInt(25);
    this.c = new Color(0, 0, 255);
  }

  // Method to draw the fish on the scene
  public WorldScene draw(WorldScene bg) {
    return bg.placeImageXY(new CircleImage(this.size, OutlineMode.SOLID, this.c), this.x, this.y);
  }

  // Move methods
  public IFish moveLeft() {
    return new BackFish(this.size, this.x - 5, this.y, this.direction, this.c);
  }

  // Move methods
  public IFish moveRight() {
    return new BackFish(this.size, this.x + 5, this.y, this.direction, this.c);
  }

  // Move methods
  public IFish moveUp() {
    return new BackFish(this.size, this.x, this.y - 5, this.direction, this.c);
  }

  // Move methods
  public IFish moveDown() {
    return new BackFish(this.size, this.x, this.y + 5, this.direction, this.c);
  }

  // checks if the two collided
  public boolean willItCollide(int x, int y, int size) {
    return this.overlaps(x, y, size);
  }

  // null method bc of interface
  public IFish sizer() {
    return this;
  }

  // null method bc of interface
  public World removeCollisions(ILoFish backFish) {
    return null;
  }

  // can this background Fish eat the given background Fish?
  public boolean canBeEaten(BackFish back) {
    return false;
  }

  // checks to see if fish will collide
  public boolean doesCollide(IFish backFish) {
    return false;
  }
}

//////////////////////////////////////////////////////////////////

//class to represent a player fish
class PlayerFish extends AFish {

  PlayerFish(int x, int y) {
    super(x, y);
  }

  // constructor
  public PlayerFish(int x, int y, int size, Color c) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.c = c;
  }

  // Method to draw the player fish on the scene
  public WorldScene draw(WorldScene bg) {
    return bg.placeImageXY(new CircleImage(this.size, OutlineMode.SOLID, this.c), this.x, this.y);
  }

  // Move methods also makes it so when u leave on the left, you reenter on the
  // right
  public IFish moveLeft() {
    int newX = this.x - 5;
    if (newX < 0) {
      newX = 600; // Set x to the right edge of the screen
    }
    return new PlayerFish(newX, this.y, this.size, this.c);
  }

  // Move methods also makes it so when u leave on the right, you reenter on the
  // left
  public IFish moveRight() {
    int newX = this.x + 5;
    if (newX > 600) {
      newX = 0; // Set x to the left edge of the screen
    }
    return new PlayerFish(newX, this.y, this.size, this.c);
  }

  // moves the fish upwards and makes it so when u leave on top, u come out bottom
  public IFish moveUp() {
    int newY = this.y - 5;
    if (newY < 0) {
      newY = 400; // Set y to the bottom edge of the screen
    }
    return new PlayerFish(this.x, newY, this.size, this.c);
  }

  // makes it so when u leave on bottom, u come out on top
  public IFish moveDown() {
    int newY = this.y + 5;
    if (newY > 400) {
      newY = 0; // Set y to the top edge of the screen
    }
    return new PlayerFish(this.x, newY, this.size, this.c);
  }

  // Increases the size of the player when it eats
  public IFish sizer() {
    return new PlayerFish(this.x, this.y, this.size + 1, this.c);
  }

  // checks to see if fish will collide
  public boolean doesCollide(IFish backFish) {
    return backFish.willItCollide(this.x, this.y, this.size);
  }

  // this removes the background fish when theyre eaten by the player fish
  public World removeCollisions(ILoFish backFish) {
    if (backFish.canBeEaten(this.x, this.y, this.size)) {
      return new FishWorld(backFish.removeCollision(this.x, this.y, this.size), this.sizer());
    }
    else {
      return new FishWorld(backFish, this);
    }
  }

  // checks if they will collide using location and size
  public boolean willItCollide(int x, int y, int size) {
    return this.overlaps(x, y, size);
  }

  // can this player Fish eat the given background Fish?
  public boolean canBeEaten(BackFish back) {
    return this.size > back.size && back.doesCollide(this);
  }


  // Interface for lists of fish
  interface ILoFish {
    WorldScene emptyDraw(WorldScene bg);

    WorldScene wDraw(WorldScene bg);

    ILoFish removeCollision(int x, int y, int size);

    boolean canBeEaten(int x, int y, int size);

    boolean doesCollide(int x, int y, int size);

    ILoFish moveAll();

    ILoFish nom(PlayerFish player);

    WorldScene draw(WorldImage draw);
  }


  //represents an empty list of fish 
  class MtLoFish implements ILoFish {

    // draws an empty worldscene but with worldscene
    public WorldScene emptyDraw(WorldScene bg) {
      return bg;
    }

    // moves the empty list of fish
    public ILoFish moveAll() {
      return this;
    }

    // lets player eat nothing
    public ILoFish nom(PlayerFish player) {
      return this;
    }

    // draws on empty list of fish but worldimage
    public WorldScene draw(WorldImage draw) {
      return null;
    }

    // handles empty collisions
    public boolean doesCollide(int x, int y, int size) {
      return false;
    }

    // means nothing in empty context
    public boolean canBeEaten(int x, int y, int size) {
      return false;
    }

    // also means nothing in empty context
    public ILoFish removeCollision(int x, int y, int size) {
      return this;
    }

    @Override
    public WorldScene wDraw(WorldScene bg) {
      // TODO Auto-generated method stub
      return null;
    }


    /*
     * TEMPLATE Fields: (none)
     * 
     * Methods: this.draw(WorldScene bg) ... WorldScene this.nom(playerfish player)
     * ... ILoFish this.moveAll () ... ILoFish this.draw(WorldImage draw) ...
     * WorldScene this.doesCollide(int x, int y, int size) ... boolean
     * this.canBeEaten(int x, int y, int size)...boolean this.removeCollision(int x,
     * int y, int size) ...ILoFish
     */
  }


  //represents a non-empty list of fish 
  class ConsLoFish implements ILoFish {
    IFish first;
    ILoFish rest;

    ConsLoFish(IFish first, ILoFish rest) {
      this.first = first;
      this.rest = rest;
    }

    // moves the list of fish
    public ILoFish moveAll() {
      return new ConsLoFish(this.first.moveRight(), this.rest.moveAll());
    }

    // allows player fish to eat background fish
    public ILoFish nom(PlayerFish player) {
      if (this.first.eatenHuh(player)) {
        return this.rest;
      }
      else {
        return new ConsLoFish(this.first, this.rest.nom(player));
      }
    }

    // draws the world
    public WorldScene draw(WorldScene bg) {
      return this.rest.wDraw(this.first.draw(bg));
    }

    // null method
    @Override
    public WorldScene draw(WorldImage draw) {
      return null;
    }

    // evaluates collisions
    public boolean doesCollide(int x, int y, int size) {
      if (this.first.overlaps(x, y, size)) {
        return true;
      }
      else {
        return this.rest.doesCollide(x, y, size);
      }
    }

    // evaluates which fish is smaller and if player can eat
    public boolean canBeEaten(int x, int y, int size) {
      return this.first.isSmaller(size) && this.first.overlaps(x, y, size)
          || this.rest.canBeEaten(x, y, size);
    }

    // removes background fish if they overlap and are smaller than player
    public ILoFish removeCollision(int x, int y, int size) {
      if (this.first.overlaps(x, y, size)) {
        return this.rest;
      }
      else {
        return new ConsLoFish(this.first, this.rest.removeCollision(x, y, size));
      }
    }
    /*
     * TEMPLATE Fields: this.first...IFish this.rest...ILoFish
     * 
     * Methods: this.draw(WorldScene bg) ... WorldScene this.nom(playerfish player)
     * ... ILoFish this.moveAll () ... ILoFish this.draw(WorldImage draw) ...
     * WorldScene this.doesCollide(int x, int y, int size) ... boolean
     * this.canBeEaten(int x, int y, int size)...boolean this.removeCollision(int x,
     * int y, int size) ...ILoFish
     * 
     * Methods of Fields: this.first.draw(WorldScene bg) ... WorldScene
     * this.first.nom(playerfish player) ... ILoFish this.first.moveAll () ...
     * ILoFish this.first.draw(WorldImage draw) ... WorldScene
     * this.first.doesCollide(int x, int y, int size) ... boolean
     * this.first.canBeEaten(int x, int y, int size)...boolean
     * this.first.removeCollision(int x, int y, int size) ...ILoFish
     * this.rest.draw(WorldScene bg) ... WorldScene this.rest.nom(playerfish player)
     * ... ILoFish this.rest.moveAll () ... ILoFish this.rest.draw(WorldImage draw)
     * ... WorldScene this.rest.doesCollide(int x, int y, int size) ... boolean
     * this.rest.canBeEaten(int x, int y, int size)...boolean
     * this.rest.removeCollision(int x, int y, int size) ...ILoFish
     */

    public WorldScene emptyDraw(WorldScene bg) {
      return null;
    }

  }


  //world class for our scene
  class FishWorld extends World {
    ILoFish backFish;
    IFish playFish;

    // constructor
    FishWorld(ILoFish backFish, IFish playFish) {
      this.backFish = backFish;
      this.playFish = playFish;
    }

    // null
    public FishWorld() {
      // TODO Auto-generated constructor stub
    }

    // Draws the fish onto the background
    public WorldScene makeScene() {
      return this.playFish.draw(this.backFish.draw(new WorldScene(600, 400)));
    }

    // Change the location of the player Fish on the world scene when the arrow keys
    // are pressed
    public FishWorld onKeyEvent(String key) {
      if (key.equals("left")) {
        return new FishWorld(this.backFish, this.playFish.moveLeft());
      }
      else if (key.equals("right")) {
        return new FishWorld(this.backFish, this.playFish.moveRight());
      }
      else if (key.equals("up")) {
        return new FishWorld(this.backFish, this.playFish.moveUp());
      }
      else if (key.equals("down")) {
        return new FishWorld(this.backFish, this.playFish.moveDown());
      }
      else {
        return this;
      }
    }

    // handles our spawning and random
    public World onTick() {
      Random rand = new Random();
      int xStart = rand.nextInt(2) * 600;
      int yStart = rand.nextInt(400);

      // Check if the player fish collides with any background fish
      if (playFish.doesItCollide(backFish)) {
        return playFish.removeCollisions(backFish);
      }

      // Create a new background fish at the starting position
      ILoFish add = new ConsLoFish(new BackFish(xStart, yStart), this.backFish);

      // Move all the background fish
      return new FishWorld(add.moveAll(), this.playFish);

    }
    /*
     * TEMPLATE Fields: this.backFish ... ILoFish this.playFish ... PlayerFish
     * 
     * Methods: this.makeScene() ... WorldScene this.onTick() ... World
     * this.onKeyEvent(String key) ... World
     * 
     * Methods on Fields: this.backgroundFish.draw(WorldScene scene) ... WorldScene
     * this.backgroundFish.checkCollision(int PlayerX, int PlayerY, int PlayerSize)
     * ... boolean this.backgroundFish.add()...ILoFish
     * this.backgroundFish.moveAll(Random rand) ... ILoFish
     * this.backgroundFish.doesCollide(int x, int y, int size, PlayerFish
     * playerFish) ... ILoFish this.player.draw(WorldScene scene) ... WorldScene
     * this.player.moveAll(String key) ... PlayerFish this.player.checkWalls() ...
     * PlayerFish this.player.scoreToImage() ... WorldImage
     */
  }


  //represents examples and test for the Fish World
  class ExamplesFish {

    PlayerFish player1 = new PlayerFish(200, 200, 10, Color.red);
    PlayerFish player2 = new PlayerFish(200, 200, 15, Color.green);
    PlayerFish player3 = new PlayerFish(200, 200, 20, Color.blue);

    BackFish back1 = new BackFish(5);
    BackFish back2 = new BackFish(5);
    BackFish back3 = new BackFish(10);
    BackFish back4 = new BackFish(15);

    BackFish nBack = new BackFish(8, 200, 200, 1, Color.magenta);
    BackFish nBack1 = new BackFish(28, 200, 200, 1, Color.magenta);
    BackFish nBack2 = new BackFish(5, 200, 200, 1, Color.magenta);
    BackFish nBack3 = new BackFish(15, 200, 200, 1, Color.magenta);

    ILoFish mt = new MtLoFish();

    ILoFish lo1 = new ConsLoFish(this.back1, this.mt);
    ILoFish lo2 = new ConsLoFish(this.back2, this.lo1);
    ILoFish lo3 = new ConsLoFish(this.back3, this.lo2);
    ILoFish lo4 = new ConsLoFish(this.back4, this.lo3);
    ILoFish lo5 = new ConsLoFish(this.nBack, this.lo4);

    WorldScene scene = new WorldScene(400, 400);

    //test the moveLeft method on the Fish
    boolean testMoveLeft(Tester t) {
      return t.checkExpect(this.player1.moveLeft(), new PlayerFish(195, 200, 10, Color.red))
          && t.checkExpect(back1.moveLeft(), new BackFish(8));
    }

    //test for moveRight()
    boolean testMoveRight(Tester t) {
      return t.checkExpect(player1.moveRight(), new PlayerFish(205, 200, 10, Color.red))
          && t.checkExpect(back1.moveRight(), new BackFish(8));
    }

    //test for moveUp()
    boolean testMoveUp(Tester t) {
      return t.checkExpect(player1.moveUp(), new PlayerFish(200, 195, 10, Color.red))
          && t.checkExpect(back1.moveUp(), new BackFish(3));
    }

    //test for moveDown()
    boolean testMoveDown(Tester t) {
      return t.checkExpect(player1.moveDown(), new PlayerFish(200, 205, 10, Color.red))
          && t.checkExpect(back1.moveDown(), new BackFish(6));
    }

    //Test the moveAll method for ConsLoFish
    boolean testMoveAll(Tester t) {
      return t.checkExpect(lo4.moveAll(),
          new ConsLoFish(this.back4.moveRight(),
              new ConsLoFish(this.back3.moveRight(), new ConsLoFish(this.back2.moveRight(),
                  new ConsLoFish(this.back1.moveRight(), this.mt)))));
    }

    // Test the canBeEaten method for ILoFish
    boolean testCanBeEaten(Tester t) {
      return t.checkExpect(lo5.canBeEaten(200, 200, 15), true) // nBack can be eaten by player2
          && t.checkExpect(lo1.canBeEaten(200, 200, 15), false) // back1 cannot be eaten by player2
          && t.checkExpect(mt.canBeEaten(200, 200, 15), false); // no fish in mt, so false
    }

    //Test the doesCollide method for PlayerFish
    boolean testDoesCollide(Tester t) {
      return t.checkExpect(player1.doesCollide(lo5), true)
          && t.checkExpect(player1.doesCollide(lo1), false);
    }

    //Test the sizer method for PlayerFish
    boolean testSizer(Tester t) {
      return t.checkExpect(player1.sizer(), new PlayerFish(200, 200, 11, Color.red))
          && t.checkExpect(player2.sizer(), new PlayerFish(200, 200, 16, Color.green));
    }

    //Test the eatenHuh method for PlayerFish
    boolean testEatenHuh(Tester t) {
      return t.checkExpect(player1.eatenHuh(nBack), false)
          && t.checkExpect(player1.eatenHuh(nBack1), true);
    }

    //Test the onTick method for FishWorld
    boolean testOnTick(Tester t) {
      FishWorld world = new FishWorld(lo5, player1);
      ILoFish movedFish = new ConsLoFish(nBack.moveRight(),
          new ConsLoFish(back4.moveRight(), new ConsLoFish(back3.moveRight(),
              new ConsLoFish(back2.moveRight(), new ConsLoFish(back1.moveRight(), mt)))));
      FishWorld expectedWorld = new FishWorld(movedFish, player1);

      return t.checkExpect(world.onTick(), expectedWorld);
    }

    boolean testBigBang(Tester t) {
      // Create a player fish at the center of the screen
      PlayerFish playerFish = new PlayerFish(300, 200, 7, Color.RED);

      // Create an empty list of background fish
      ILoFish emptyFishList = new MtLoFish();

      // Create the fish world with the player fish and the empty list of background
      // fish
      FishWorld world = new FishWorld(emptyFishList, playerFish);

      // Run the game with a canvas size of 600x400 and a tick rate of 0.1 seconds
      world.bigBang(600, 400, .5);
      return true;
    }
  }
}
