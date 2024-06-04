// CS 2510, Assignment 3

import tester.*;

// to represent a list of Strings
interface ILoString {
  // combine all Strings in this list into one
  String combine();
  ILoString mergeHelper(ILoString that);
  ILoString sort();
  ILoString isSorted();
  ILoString isSortedHelper();
  ILoString interleave(ILoString that);
  ILoString mergeHelper(String that);
  ILoString merge(ILoString that);
  ILoString reverse();
  ILoString reverser(ILoString that);
  boolean isDoubledList();
  boolean isPalindromeList();
  boolean isDoubledListHelper(String first);

}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString()

  // combine all Strings in this list into one
  public String combine() {
    return "";
  } 

  //takes in an empty string and sorts it lexicographically          //HOW TO MAKE LOWER CASE ??
  public ILoString sort() {
    return new MtLoString();
  }
  //takes in two empty lists and combines them
  public ILoString interleave(ILoString that) {
    return new MtLoString();
  }
  //produces a sorted list of strings from two empty lists
  public ILoString merge(ILoString that) {
    return that;
  }

  //switches the order of the empty LoStrings
  public ILoString reverseHelper() {
    return new MtLoString();
  }

  //produces an empty list in reverse order
  public ILoString reverse() {
    return new MtLoString();
  }

  public boolean isDoubledList() {
    return false;
  }
  
  public boolean isDoubledListHelper() {
    return false;
  }
  @Override
  public ILoString isSorted() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public ILoString isSortedHelper() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public boolean isPalindromeList() {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public ILoString mergeHelper(ILoString that) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public ILoString mergeHelper(String that) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public ILoString reverser() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public ILoString reverser(ILoString that) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public boolean isDoubledListHelper(String first) {
    // TODO Auto-generated method stub
    return tr;
  }
}

// represents a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest){
    this.first = first;
    this.rest = rest;  

  }

  /*
     TEMPLATE
     FIELDS:
     ... this.first ...         -- String
     ... this.rest ...          -- ILoString

     METHODS
     ...this.combine() ...     -- String
     ...this.sort()...          ---ILoString
     ...this.isSorter()...      ---ILoString
     ...this.isSortedHelper()...---ILoString
     ...this.interleave()...    ---ILoString
     ...this.merge()...         ---ILoString
     ...this.reverse()...       ---ILoString
     ...this.reverseHelper()... ---ILoString
     ...isDoubledList()...      ---boolean
     ...isPalindromList()...    ---boolean

     METHODS FOR FIELDS
     ...this.first.concat(String) ...        -- String
     ...this.first.compareTo(String) ...     -- int
     ...this.rest.combine() ...              -- String
     ...this.rest.sort(ILoString)...         -- ILoString
     ...this.rest.reverseHelper()...         --ILoString
     ...this.rest.interleave()...            --ILoString    
     ...this.rest.merge()...                 --ILoString  
     ...this.rest.interleave()...            --ILoString
     ...this.rest.isSortedHelper             --boolean       
     ...this.rest.reverseHelper()...         --ILoString            


     ...isDoubledList()...      ---boolean
     ...isPalindromList()...    ---boolean



   */
  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }  

  //takes in a non-empty string and sorts it lexicographically        
  public ILoString sort() {
    return new ConsLoString(this.first, this.rest.sort());
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////////
  //returns a boolean if the first string of a given list is or isnt sorted     
  public boolean isSortedHelper(String Strang) {
    if (this.first.compareTo(Strang) > 0 ) {
      return false;
    }
    else {
      return true;
    }
  }

  //determines whether the rest of the strings of a given list is or isnt sorted 
  public boolean isSorted(ILoString Strang) {
    return this.rest.isSortedHelper();
  }
  ////////////////////////////////////////////////////////////////////////////////////////////
  
  //takes in two lists and combines them
  public ILoString interleave(ILoString that) {
    return new ConsLoString(this.first, this.rest.interleave(that));
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////////
  
  //produces a sorted list of strings from two lists
  public ILoString merge(ILoString that) {
    return this.rest.merge(that.mergeHelper(this.first));
  }
  
  //inserts a string into a list
  public ILoString mergeHelper(String that) {
    if ((this.first.compareTo(that)) < 0) {
      return new ConsLoString(this.first, this.rest.mergeHelper(that));
    }
    else {
      return new ConsLoString(that, this);
    }
  }
  ////////////////////////////////////////////////////////////////////////////////////////////
  
//produces a new list of String in reverse

  public ILoString reverse() {

    return this.rest.reverse().reverser(this.first);

  }

  //adds a given string to the end of this list

  public ILoString reverser(ILoString that) {

    return new ConsLoString(this.first, this.rest.reverser(that));

  }

  //assesses whether the first of the rest of the list is the same as the first of the list
  public boolean isDoubledListHelper(String prev) {
    return this.first.equals(prev);
  }

  public boolean isDoubledList() {
    if (this.combine().length() % 2 == 1) {
      return false;
    }
    else {
      return (this.rest.isDoubledListHelper(this.first));
    }
  }

  @Override
  public ILoString isSorted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ILoString isSortedHelper() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ILoString mergeHelper(ILoString that) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isPalindromeList() {
    // TODO Auto-generated method stub
    return false;
  }

}

/*  String combine();
  ILoString sort();
  ILoString isSorted();
  ILoString isSortedHelper();
  ILoString interleave(ILoString that);
  ILoString merge(ILoString that);
  ILoString reverse();
  ILoString reverseHelper();
  boolean isDoubledList();
  boolean isPalindromeList();
 */
// to represent examples for lists of strings
class ExamplesString{
  ILoString alph = new ConsLoString("Apples",
      new ConsLoString("Bob ",
          new ConsLoString("Candy ", 
              new ConsLoString("Dad ",
                  new MtLoString()))));

  ILoString mary = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", new MtLoString())))));

  ILoString cow = new ConsLoString("The ",
      new ConsLoString("cow ",
          new ConsLoString("jumped ",
              new ConsLoString("over ",
                  new ConsLoString("the ",
                      new ConsLoString("moon.", new MtLoString()))))));

  ////////////////////////////////////////////////////////////////////////////////////////////
  // test the method combine for the lists of Strings
  boolean testCombine1(Tester t) {
    return 
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  boolean testCombine2(Tester t) {
    return
        t.checkExpect(this.alph.combine(), "Apples Bob Candy Dad");
  }

  boolean testCombine3(Tester t) {
    return
        t.checkExpect(this.cow.combine(), "The cow jumbed over the moon.");
  }


  ////////////////////////////////////////////////////////////////////////////////////////////
  //tester for sort function 
  boolean testSort1(Tester t) {
    return
        t.checkExpect(this.cow.sort(),new ConsLoString("cow ",
            new ConsLoString("jumped ",
                new ConsLoString("moon ",
                    new ConsLoString("over ",
                        new ConsLoString("the",
                            new ConsLoString("the", 
                                new MtLoString())))))));
  }

  boolean testSort2(Tester t) {
    return
        t.checkExpect(this.alph.sort(), new ConsLoString("Apples ", 
            new ConsLoString("Bob ",
                new ConsLoString("Candy ",
                    new ConsLoString("Dad",
                        new MtLoString())))));
  }

  boolean testSort3(Tester t ) {
    return
        t.checkExpect(this.mary.sort(), new ConsLoString("a ",
            new ConsLoString("had ",
                new ConsLoString("lamb.",
                    new ConsLoString("little ",
                        new ConsLoString("Mary",
                            new MtLoString()))))));
  }


  ////////////////////////////////////////////////////////////////////////////////////////////

  //tests for the isSorted function
  boolean testIsSorted1(Tester t) {
    return
        t.checkExpect(this.cow.isSorted(), false);
  }

  boolean testIsSorted2(Tester t) {
    return
        t.checkExpect(this.alph.isSorted(), true);
  }

  boolean testISSorted3(Tester t) {
    return
        t.checkExpect(this.mary.isSorted(), false);
  }

  //////////////////////////////////////////////////////////////////////////////////////////// 


  boolean testInterleave1(Tester t) {
    return 
        t.checkExpect(this.cow.interleave(alph),
            new ConsLoString("Mary ",
                new ConsLoString("The ",
                    new ConsLoString("had ",
                        new ConsLoString("cow ",
                            new ConsLoString("a ",
                                new ConsLoString("jumped ",
                                    new ConsLoString("little ",
                                        new ConsLoString("over ",
                                            new ConsLoString("lamb.",
                                                new ConsLoString("the ",
                                                    new ConsLoString("moon.",
                                                        new MtLoString()))))))))))));
  }


  boolean testInterleave2(Tester t) {
    return 
        t.checkExpect(this.alph.interleave(cow),
            new ConsLoString("Apples ",
                new ConsLoString("The ",
                    new ConsLoString("Bob ",
                        new ConsLoString("cow ",
                            new ConsLoString("Candy ",
                                new ConsLoString("jumped ",
                                    new ConsLoString("Dad ",
                                        new ConsLoString("over ",
                                            new ConsLoString("the ",
                                                new ConsLoString("moon.",
                                                    new MtLoString())))))))))));
  }

  boolean testInterleave3(Tester t) {
    return 
        t.checkExpect(this.mary.interleave(alph),
            new ConsLoString("Mary ",
                new ConsLoString("Apples ",
                    new ConsLoString("had ",
                        new ConsLoString("Bob ",
                            new ConsLoString("a ",
                                new ConsLoString("Candy ",
                                    new ConsLoString("little ",
                                        new ConsLoString("Dad ",
                                            new ConsLoString("lamb.",
                                                new MtLoString()))))))))));
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////// 
  
  
  boolean testIsSortedHelper1(Tester t) {
    return
        t.checkExpect(this.cow.isSortedHelper(), false );
  }

  boolean testIsSortedHelper2(Tester t) {
    return 
        t.checkExpect(this.mary.isSortedHelper(), false);
  }
  
  boolean testIsSortedHelper3(Tester t) {
    return
        t.checkExpect(this.alph.isSortedHelper(), true);
  }
  //////////////////////////////////////////////////////////////////////////////////////////// 
  
  boolean testMergeHelper1(Tester t) {
    return 
        t.checkExpect(this.alph.mergeHelper("hello"),
            new ConsLoString("hello",
                new ConsLoString("Apples",
                    new ConsLoString("Bob ",
                        new ConsLoString("Candy ",
                            new ConsLoString("Dad",
                                new MtLoString()))))));
  }
  boolean testMergeHelper2(Tester t) {
    return
        t.checkExpect(this.cow.mergeHelper("test2"),
            new ConsLoString("test2",
                new ConsLoString("The ",
                    new ConsLoString("cow ",
                        new ConsLoString("jumped ",
                            new ConsLoString("over ",
                                new ConsLoString("the ",
                                    new ConsLoString("moon.", new MtLoString()))))));
  }

}
