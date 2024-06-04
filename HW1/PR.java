interface IResource {
}

class Denial implements IResource {
  String subject;
  int believability;
  
  //represents a players denial
  Denial(String subject , int believability) {
    this.subject = subject;
    this.believability = believability;
  }
  /*
  fields
  ...this.subject... String
  ...this.believability... int
  */
}

class Bribe implements IResource {
  String target;
  int amount;
  
  //represents a player bribing
  Bribe(String target, int amount) {
    this.target = target;
    this.amount = amount;
  }
  /*
  fields
  ...this.target... String
  ...this.amount... int
  */
}

class Apology implements IResource {
  String excuse;
  boolean reusable;
  
  //represents a players apology
  Apology(String excuse, boolean reusable) {
    this.excuse = excuse;
    this.reusable = reusable;
  }
  /*
  fields
  ...this.excuse... String
  ...this.reusable... boolean
  */
}

class ExamplesGame {
  IResource iDidntKnow = new Denial("knowledge",51);
  IResource witness = new Bribe("innocent witness", 49);
  IResource iWontDoItAgain = new Apology("I won't do it again", false);
  IResource myBad = new Apology("My bad, I didn't mean it", true);
  IResource notMe = new Denial("that isn't me bro", 4);
  IResource payOff = new Bribe("fake criminal", 6);
  IAction purchase1 = new Purchase(300, this.iDidntKnow);
  IAction purchase2 = new Purchase(20, this.notMe);
  IAction swap1 = new Swap(this.witness, this.iDidntKnow);
  IAction swap2 = new Swap(this.payOff, this.notMe);
}

interface IAction{
}

class Purchase implements IAction {
  int cost;
  IResource item;
  
  //represents a player purchasing an item from the common pool
  Purchase(int cost, IResource item) {
    this.cost = cost;
    this.item = item;
  }
  /*
  fields
  ...this.cost... int
  ...this.item... IResource
  */
}

class Swap implements IAction {
  IResource consumed;
  IResource received;
  
  //represents a player swapping an item
  Swap(IResource consumed, IResource received) {
    this.consumed = consumed; 
    this.received = received;
  }
  /*
  fields
  ...this.consumed... ...IResource
  ...this.recieved... ...IResource
  */
}


