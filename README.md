# Skuid Game

#### A game with easy-to-understand mini-games inspired by a popular Netflix show, *Squid Game*.

This application is a game that contains simple mini-games based on classic children's game.
The games are supposed to be of on luck, not the skill of the player. The user will play against the computer which will
also play by randomization and chance. *This application does not capture the functions of Squid Game, just only the 
concept of chance in mini-games. Nevertheless, this application will be called SkuidGame.*

This would be for anyone who wants to pass time and test their luck with simple mini-games or view previous 
players attempts.

This project is of interest to me because I want to recreate games played in childhood in a much simpler virtual way. I also
believe that creating this project will also allow me to learn and apply concepts
abstraction, adding objects to lists, data persistence, and creating a user interface.

## User Stories
- As a user, I want to be able to add my player information to a list of past players sorted
by the year they played in.
- As a user, I want to be able to view a list of previous players and their information.
- As a user, I want to be able to input my answer for the game.
- As a user, I want to be able to play again as another player without terminating the application.
- As a user, I want to be able to save my attempt to file.
- As a user, I want to be able to load a list of saved previous attempts from file.

I am proud of how I designed the panels because I have two abstract classes that handles their specified panel
functionality. For example, the CoinTossPanel and RockPaperScissorsPanel that handles the GUI of the game extends 
the abstract class, GamePanel. I did this because CoinTossPanel and RockPaperScissorsPanel had a lot of duplicate code
and are very similar. So, if I wanted to add another game that have similar functionality of being based on chance 
(such as Guess The Number), I can easily create a new game panel and extend GamePanel class.

However, if I had more time, I would create a model class of the games. Again, with the same design structure, but 
it would handle the functionality of the game, while the panel classes will handle the GUI of their game. This way,
the panels only does the work of one class which is the GUI. Currently, the GamePanel handles methods such as comparing
player result with computer randomly generated answer, keeping track of game status (whether or not player wins that 
specific game or not), etc., which can probably be handled in a Game class that would be in model package.

Additionally, if I had more time to work on the project, I would split the SkuidGameGUI into two classes because it 
currently handles a lot of functionality. I would change it in a way so that it only handles the switching of 
panels and create a new class that handles all the functionality of save, load, initiation player, etc. 
For example, it would be called SkuidGame and it would be in the model package. It would have all the functionalities 
of the Skuid Game application, while the SkuidGameGUI would handle all the GUI functionality instead of all the 
functionality of the game. 


