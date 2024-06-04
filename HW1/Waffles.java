//represents a waffle with or without toppings
interface IWaffle {
}

class Plain implements IWaffle {
  String flour;
  //represents a plain waffle

  Plain(String flour) {
    this.flour = flour; 
  }
}

//represents a waffle with toppings 
class Topped implements IWaffle {
  IWaffle below;
  String topping;

  Topped(IWaffle below , String topping) {
    this.below = below;
    this.topping = topping;
  }
}

//represents examples of waffles ordered
class ExamplesWaffle {
  IWaffle almond = new Plain("almond");
  IWaffle choc = new Topped(this.almond, "chocolate chips");
  IWaffle whipped = new Topped(this.choc, "whipped cream");
  IWaffle straw = new Topped(this.whipped, "strawberries");
  IWaffle order1 = new Topped(this.straw, "walnuts");
  
  IWaffle buckwheat = new Plain("buckwheat");
  IWaffle chick = new Topped(this.buckwheat, "chicken");
  IWaffle order2 = new Topped(this.chick, "gravy");
}