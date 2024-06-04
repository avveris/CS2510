import tester.Tester;

//container for bagelHelper and approx
class Utils {

  //utils method
  double bagelHelper(double a, double b, String msg) {
    if (approx(a, b)) {
      return a;
    }
    else {
      throw new IllegalArgumentException(msg);
    }
  }

  //calculates if two numbers are within .001 of each other
  boolean approx(double num1, double num2) {
    return (Math.abs(num1 - num2) <= 0.001);
  }
}

////////////////////////////////////////////////////////////////////
//represents a recipe to make bagels
class BagelRecipe {
  double yeast;  
  double salt;
  double malt;
  double water;
  double flour;

  //utils constructor,,,
  //takes in all of the fields and enforce all above constraints to ensure a perfect bagel
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    this.flour = new Utils().bagelHelper(flour, water,
        "The weight of the flour should be equal to the weight of the water");
    this.water = new Utils().bagelHelper(water, flour,
        "The weight of the flour should be equal to the weight of the water");
    this.yeast = new Utils().bagelHelper(yeast, malt,
        "The weight of the yeast should be equal the weight of the malt");
    this.salt = (new Utils().bagelHelper((salt + yeast) * 20, water,
        "The weight of the salt + yeast should be 1/20th the weight of the flour") / 20) - yeast;
    this.malt = new Utils().bagelHelper(malt, yeast,
        "The weight of the yeast should be equal the weight of the malt");
  }

  ////////////////////////////////////////////////////////////////////
  /*
   * Template
   * Fields:
   * yeast ... double
   * salt ... double
   * malt ... double
   * water ... double
   * flour ... double
   * Methods:
   * sameRecipe() ... boolean
   * bagelHelper() ... double
   * approx() ... boolean
   * Methods of fields:
   * sameRecipe() ... boolean
   */
  ////////////////////////////////////////////////////////////////////


  //second constructor to check flour and yeast measurements with weight
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, flour / 20 - yeast, yeast);
  }

  //second constructor but with with volumes and salt
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, (yeast / 48) * 5, (salt / 48) * 10, (yeast / 48) * 5);
  }

  ////////////////////////////////////////////////////////////////////

  //returns true if the same ingredients have the same weights within .001oz
  boolean sameRecipe(BagelRecipe other) {
    return new Utils().approx(this.yeast, other.yeast)
        && new Utils().approx(this.salt, other.salt)
        && new Utils().approx(this.malt, other.malt)
        && new Utils().approx(this.water, other.water)
        && new Utils().approx(this.flour, other.flour);
  }
}
////////////////////////////////////////////////////////////////////

//to represent examples of bagels
class ExamplesBagelRecipe {
  BagelRecipe bagel1 = new BagelRecipe(10.0, 10.0, 0.5, 0.5, 0.5);
  BagelRecipe bagel2 = new BagelRecipe(20.0, 20.1, 1, 1, 1);
  BagelRecipe bagel3 = new BagelRecipe(10.0, 0.05);
  BagelRecipe bagel4 = new BagelRecipe(40.0, 1.0);


  // tests for approx
  boolean testApprox(Tester t) {
    return t.checkExpect(new Utils().approx(0.1, 0.1), true)
        && t.checkExpect(new Utils().approx(0.001, 0.0015), false)
        && t.checkExpect(new Utils().approx(1.001, 1.002), false);
  }

  // tests for sameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.bagel3.sameRecipe(this.bagel1), true)
        && t.checkExpect(this.bagel4.sameRecipe(this.bagel2), false);
  }
  
}