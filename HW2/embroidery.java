import tester.Tester;

//represents a list of motifs
interface IMotif {

  // adds the difficulties of the motifs
  double difficulty();

  //adds the number of motifs in a list
  double counter();

  //puts the names and type of stitches into a String
  String stringer();
}

//represents an embroidery piece with a name and a motif
class EmbroideryPiece {
  String name;
  IMotif motif;

  //represents an embroidery piece
  EmbroideryPiece(String name , IMotif motif) {
    this.name = name;
    this.motif = motif;
    /*fields:
     * this.name...String
     * this.motif...IMotif
     * methods: 
     * this.averageDifficulty()...double
     * this.embroidertyInfo()...String
     * methods of fields:
     * this.motif.difficulty()...double
     * this.motif.counter()...double
     * this.motif.stringer()...String
     */
  }

  // helper that finds the avg difficulty of crossstitches and chainstitches in a motif
  double averageDifficulty() {
    if (this.motif.counter() != 0) {
      return this.motif.difficulty() / this.motif.counter();
    }
    else {
      return 0;
    }
  }

  //puts the names and tyes of chainstitches and crossstitches into a string
  String embroideryInfo() {
    return this.name + ": " + this.motif.stringer() + ".";
  }
}
///////////////////////////////////////////////////////////////////////////////


class CrossStitchMotif implements IMotif {
  String description;
  double difficulty;

  //represents a cross stitch motif 
  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty; 

    /*fields:
     * this.description...String
     * this.difficulty...double
     * methods:
     * this.crossStitchCounter(double)...double
     */
    //tallies the total number of difficulties


    /*    double crossStitchMotif(double d) {
      return + this.
     */
  }

  //adds the difficulties of the motifs
  public double difficulty() {
    return this.difficulty;
  }

  //adds the number of motifs in a list
  public double counter() {
    return 1;
  }

  //puts the names and cross stitch into a String
  public String stringer() {
    return this.description + " (cross stitch)";
  }
}


///////////////////////////////////////////////////////////////////////////////

class ChainStitchMotif implements IMotif {
  String description;
  double difficulty;

  //represents a chain stitch motif
  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*fields:
   * this.description = String
   * this.difficulty = double
   * methods:
   * this.stringer()...String
   * this.difficulty()...double
   * this.counter()...double
   */

  //adds the difficulties of the motifs
  public double difficulty() {
    return this.difficulty; 
  }

  //adds the number of motifs in a list
  public double counter() {
    return 1;
  }

  //puts the names and cross stitch into a String
  public String stringer() {
    return this.description + " (chain stitch)";
  }
}




///////////////////////////////////////////////////////////////////////////////
// represents a group of motifs
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs; 

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  /*fields:
   * this.name...String
   * this.motif...IMotif
   * methods: 
   * this.averageDifficulty()...double
   * this.embroidertyInfo()...String
   * methods of fields:
   * this.motif.difficulty()...double
   * this.motif.counter()...double
   * this.motif.stringer()...String
   */

  //adds the difficulties of the motifs
  public double difficulty() {
    return this.motifs.difficulty(); 
  }

  //adds the number of motifs in a list
  public double counter() {
    return this.motifs.counter();
  }

  //puts the names and cross stitch into a String
  public String stringer() {
    return this.motifs.stringer();
  }
}


///////////////////////////////////////////////////////////////////////////////


//represents a list of motifs
interface ILoMotif {

  // adds the difficulties of the motifs
  double difficulty();

  //adds the number of motifs in a list
  double counter();

  //puts the names and type of stitches into a String
  String stringer();
}

//represents an empty list of motifs
class MtLoMotif implements ILoMotif {

  public double difficulty() {
    return 0;
  }

  public double counter() {
    return 0; 
  }

  public String stringer() {
    return "";
  }
}


///////////////////////////////////////////////////////////////////////////////


//representes a non-empty list of Motifs
class ConsLoMotif implements ILoMotif {
  //count the total motifs in a list  
  IMotif first;
  ILoMotif rest;

  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }
  /*fields:
   * this.first...IMotif
   * this.rest...ILoMotif
   * methods: 
   * this.motif.difficulty()...double
   * this.motif.counter()...double
   * this.motif.stringer()...String
   * methods of fields:
   * this.first.difficulty() ...double
   * this.rest.difficulty()...double
   * this.first.counter()...double
   * this.rest.counter()...double
   * this.first.stringer()...String
   * this.rest.stringer()...String
   */

  //adds the difficulties of the motifs
  public double difficulty() {
    return this.first.difficulty() + this.rest.difficulty();
  }

  //counts the motifs in this non-empty list 
  public double counter() {
    return this.first.counter() + this.rest.counter();
  }

  //puts the names and type of stitches into a String 
  public String stringer() {
    if (this.rest.counter() < 1) {
      return this.first.stringer() + this.rest.stringer();
    }
    else {
      return this.first.stringer() + ", " + this.rest.stringer();
    }
  }
}  


///////////////////////////////////////////////////////////////////////////////

// represents examples of embroidery pieces
class ExamplesEmbroidery {
  IMotif rose = new CrossStitchMotif("rose", 5.0);
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif daisy = new CrossStitchMotif("daisy", 3.2);

  ILoMotif empty = new MtLoMotif();
  ILoMotif first = new ConsLoMotif(this.daisy, this.empty);
  ILoMotif second = new ConsLoMotif(this.poppy, this.first);
  ILoMotif third = new ConsLoMotif(this.rose, this.second);

  IMotif flowers = new GroupMotif("flowers", this.third);
  IMotif tree = new ChainStitchMotif("tree", 3.0);
  IMotif bird = new CrossStitchMotif("bird", 4.5);

  ILoMotif fourth = new ConsLoMotif(this.flowers, this.empty);
  ILoMotif fifth = new ConsLoMotif(this.tree, this.fourth);
  ILoMotif sixth = new ConsLoMotif(this.bird, this.fifth);
  IMotif nature = new GroupMotif("nature", sixth);

  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", this.nature);

  //averageDifficulty test
  boolean testAverageDifficulty(Tester t) {
    return
        t.checkInexact(this.pillowCover.averageDifficulty(), 4.5 , .05); 
  }

  //test for embroideryInfo
  boolean testembroideryInfo(Tester t) {
    return
        t.checkExpect(this.pillowCover.embroideryInfo(), "Pillow Cover: bird (cross stitch),"
            + "tree (chain stitch), rose (cross stitch), "
            + "poppy (chain stitch)," + "daisy (cross stitch).");
  }

  //test for difficulty 
  boolean testDifficulty(Tester t) {
    return 
        t.checkInexact(this.daisy.difficulty(), 3.2, .05) &&
        t.checkInexact(this.poppy.difficulty(), 4.75, .05) &&
        t.checkInexact(this.rose.difficulty(), 5.0, .05) &&
        t.checkInexact(this.flowers.difficulty(), 12.95, .05) &&
        t.checkInexact(this.tree.difficulty(), 3.0, .05) &&
        t.checkInexact(this.bird.difficulty(), 4.5, .05) &&
        t.checkInexact(this.empty.difficulty(), 0.00, .05) &&
        t.checkInexact(this.first.difficulty(), 3.4, .05) &&
        t.checkInexact(this.second.difficulty(), 7.95, .05) &&
        t.checkInexact(this.third.difficulty(), 12.95, .05) &&
        t.checkInexact(this.fourth.difficulty(), 25.9, .05);
  }

  //test for counter
  boolean testCounter(Tester t) {
    return 
        t.checkInexact(this.daisy.counter(), 1.0, .05) &&
        t.checkInexact(this.poppy.counter(), 1.0, .05) &&
        t.checkInexact(this.rose.counter(), 1.0, .05) &&
        t.checkInexact(this.flowers.counter(), 3.0, .05) &&
        t.checkInexact(this.tree.counter(), 1.0, .05) &&
        t.checkInexact(this.bird.counter(), 1.0, .05) &&
        t.checkInexact(this.poppy.counter(), 3.0, .05) &&
        t.checkInexact(this.empty.counter(), 0.0, .05) &&
        t.checkInexact(this.first.counter(), 1.0, .05) &&
        t.checkInexact(this.second.counter(), 2.0, .05) &&
        t.checkInexact(this.third.counter(), 3.0, .05) &&
        t.checkInexact(this.fourth.counter(), 4.0, .05) &&
        t.checkInexact(this.fifth.counter(), 5.0, .05) &&
        t.checkInexact(this.sixth.counter(), 6.0, .05);
  }

  boolean testStringer(Tester t) {
    return
        t.checkExpect(this.daisy.stringer(), "daisy (cross stitch)") &&
        t.checkExpect(this.poppy.stringer(), "poppy (chain stitch)") &&
        t.checkExpect(this.rose.stringer(), "rose (cross stitch)") &&
        t.checkExpect(this.flowers.stringer(), "daisy (cross stitch)");
  }

}