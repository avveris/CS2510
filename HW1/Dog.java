//represents a dog at the American Kennel Club
class Dog {
  String name;
  String breed;
  int yob;
  String state;
  boolean hypoallergenic;


  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

//represents examples of Dogs at the American Kennel Club
class ExamplesDog {
  Dog huffle = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
  Dog pearl = new Dog("Pearl", "Labrador Retriever", 2016, "MA", false);
  Dog reagan = new Dog("Reagan", "Poodle", 2000, "PA", false);

}
