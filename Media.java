import tester.Tester;

////////////////////////////////////////////////////////////////////////////////////////////


//a piece of media
interface IMedia {

  // is this media really old?
  boolean isReallyOld();

  // a string showing the proper display of the media
  String format();

  //is this language available as a caption
  boolean isCaptionAvailable(String lang);

}

////////////////////////////////////////////////////////////////////////////////////////////

//a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  //was this language specified
  public boolean languageHuh(String lang) {
    return this.first.equals(lang) || this.rest.languageHuh(lang);    
  }
}

//an empty list of strings
class MtLoString implements ILoString {
  //helper function for isCaptionsAvailible
  public boolean languageHuh(String lang) {
    return false;
  }

}

//lists of strings
interface ILoString {

  //helper function for isCaptionsAvailible
  boolean languageHuh(String lang);

  //checks if the given captions are available for the given media
  //boolean isCaptionAvailable(String lang);


}

////////////////////////////////////////////////////////////////////////////////////////////
abstract class AMedia implements IMedia {
  String title;
  ILoString captionOptions;

  AMedia(String title, ILoString captionOptions) {
    this.title = title;
    this.captionOptions = captionOptions;
  }

  //is this language available as a caption
  public boolean isCaptionAvailable(String lang) {
    return this.captionOptions.languageHuh(lang);
  }
}


// represents a MOVIE
class Movie extends AMedia {
  int year;

  Movie(String title, int year, ILoString captionOptions) {
    super(title, captionOptions);
    this.year = year;
  }

  //evaluates whether a movie is considered really old (made 1930 or before)
  public boolean isReallyOld() {                           //dont need the extra lines
    return this.year < 1930;
  }

  public String format() {
    return this.title + " (" + this.year + ")";
  }

}


////////////////////////////////////////////////////////////////////////////////////////////


// represents a TV EPISODE
class TVEpisode extends AMedia {
  String showName;
  int seasonNumber;
  int episodeOfSeason;

  TVEpisode(String title, String showName, int seasonNumber, int episodeOfSeason,
      ILoString captionOptions) {
    super(title, captionOptions);
    this.showName = showName;
    this.seasonNumber = seasonNumber;
    this.episodeOfSeason = episodeOfSeason;
  }

  public boolean isReallyOld() {
    return false;
  }

  public String format() {
    return this.showName + " " + this.seasonNumber + "." 
        + this.episodeOfSeason + " - " + this.title;
  }

}

////////////////////////////////////////////////////////////////////////////////////////////


// represents a YOUTUBE VIDEO
class YTVideo extends AMedia {
  String channelName;

  public YTVideo(String title, String channelName, ILoString captionOptions) {
    super(title, captionOptions);
    this.channelName = channelName;
  }

  public boolean isReallyOld() {
    return false;
  }


  public String format() {
    return this.title + " by " + this.channelName;
  }

}



////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

class ExamplesMedia {
  //to represent examples of captions options 
  MtLoString List0 = new MtLoString();
  ConsLoString List1 = new ConsLoString("English", List0);
  ConsLoString List2 = new ConsLoString("Mandarin", List1);
  ConsLoString List3 = new ConsLoString("Spanish", List2);
  ConsLoString List4 = new ConsLoString("Hindi", List3);
  ConsLoString List5 = new ConsLoString("Vietnamese", List4);

  //to represent examples of movies
  Movie Juno = new Movie("Juno", 2007, List1);
  Movie Star = new Movie("Star Wars", 1800, List2);
  Movie Lego = new Movie("Lego Batman", 1930, List0);


  //to represent examples of TV shows
  TVEpisode Bojack = new TVEpisode("Yes and", "Bojack Horseman", 2, 10, List0);
  TVEpisode Fleabag = new TVEpisode("Its French", "Fleabag", 2, 8, List3);

  //to represent examples of Youtube Videos
  YTVideo Cancelled = new YTVideo("The Ex Episode", "Cancelled", List4);
  YTVideo Just = new YTVideo("I LOVE YOU JESUS", "Just Trish", List5);


  Movie movie1 = new Movie("Juno", 2007, new ConsLoString("English", new ConsLoString(
      "Spanish", new MtLoString())));
  Movie movie2 = new Movie("Star Wars", 1800, new ConsLoString("English", new MtLoString()));
  ////////////////////////////////////////////////////////////////////////////////////////////

  // tests for IsReallyOld 
  boolean testIsReallyOld1(Tester t) {
    return t.checkExpect(this.Juno.isReallyOld(), false);
  }

  boolean testIsReallyOld2(Tester t) {
    return t.checkExpect(this.Star.isReallyOld(), true);
  }


  //tests for languageHuh
  boolean testLanguageHuh1(Tester t) {
    return t.checkExpect(this.List1.languageHuh("English"), true);
  }

  boolean testLanguageHuh2(Tester t) {
    return t.checkExpect(this.List0.languageHuh("Hindi"), false);
  }

  boolean testLanguageHuh3(Tester t) {
    return t.checkExpect(this.List5.languageHuh("Mandarin"), true);
  }

  //tests for isCaptionAvailible
  boolean testIsCaptionAvailible(Tester t) {
    return t.checkExpect(this.Bojack.isCaptionAvailable("Chinese"), false)
        && t.checkExpect(this.Cancelled.isCaptionAvailable("Spanish"), true);
  }


  //tests for format

  boolean testFormat1(Tester t) {
    return t.checkExpect(this.Juno.format(), "Juno (2007)");
  }

  boolean testFormat2(Tester t) {
    return t.checkExpect(this.Fleabag.format(), "Fleabag 2.8 - Its French");
  }

  boolean testFormat3(Tester t) {
    return t.checkExpect(this.Cancelled.format(), "The Ex Episode by Cancelled");
  }
}