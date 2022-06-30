
# Software Engeneering Final Test 2022

<img src="https://craniointernational.com/2021/wp-content/uploads/2021/06/14.jpg"/>

## Group - AM11

- ###   10682882    Aleksandro Aliaj ([@AleksandroAliaj](https://github.com/AleksandroAliaj))<br>aleksandro.aliaj@mail.polimi.it
- ###   10674905    Leonardo Cesani ([@LeonardoCesani](https://github.com/LeonardoCesani))<br>leonardo.cesani@mail.polimi.it
- ###   10666309    Matteo Colella ([@Matteo1Colella](https://github.com/Matteo1Colella))<br>matteo1.colella@mail.polimi.it


## Functionalities implemented

| Functionality   |                       State                        |
|:----------------|:--------------------------------------------------:|
| Basic rules     | 🟢 |
| Complete rules  | 🟢 |
| Socket          | 🟢 |
| GUI             | 🟢 |
| CLI             | 🟢 |
| Multiple games  | 🟢 |
| Persistence     | 🔴 |
| Character cards |🟢|
| 4 player game   | 🟢 |
| Keepalive       |🔴|


## Running
### Server

Open a terminal and go to the directory containing the jar. Once there, execute this command:

*java -jar AM11_server.jar*

### Client (CLI)

Open a terminal and go to the directory containing the jar. Once there, execute this command:

*java -jar AM11_cli.jar*

IP and port will be asked as the game begins

### Client (GUI)
#### For Windows users
Open a terminal and go to the directory containing the jar. Once there, execute this command:

*java -jar AM11_gui.jar*

#### For MAC users

1. Download javafx SDK from [here](https://gluonhq.com/products/javafx/) and extract it to the same folder containing the GUI jar
2. Open a terminal and go to the directory containing the jar. Once there, execute this command:

*java --module-path <PATH_TO_LIB> --add-modules javafx.controls --add-modules javafx.fxml --add-modules javafx.media -jar AM11_gui.jar*


#### For MAC users with Apple Silicon M1

1. Download javafx SDK from [here](https://download2.gluonhq.com/openjfx/18.0.1/openjfx-18.0.1_osx-aarch64_bin-sdk.zip) and extract it to the same folder containing the GUI jar
2. Open a terminal and go to the directory containing the jar. Once there, execute this command:

*java --module-path <PATH_TO_LIB> --add-modules javafx.controls --add-modules javafx.fxml --add-modules javafx.media -jar AM11_gui.jar*

IP and port will be asked as the game begins

## Test cases

| Package    | Tested Class    | Class       | Method        |      Line       |  
|:-----------|:----------------|:------------|:--------------|:---------------:|
| controller | ComplexLobby    | 50% (1/2)   | 54% (23/42)   |  33% (120/360)  |
| controller | DeckManager     | 100% (1/1)  | 100% (23/42)  |   95% (23/24)   |
| controller | GameManager     | 100% (1/1)  | 71% (5/7)     |  43% (53/121)   |
| controller | OrderComparator | 100% (1/1)  | 100% (1/1)    |    88% (8/9)    |
| model      | Global Package  | 97% (38/39) | 92% (234/252) | 74% (1046/1407) |

## How To Play (GUI)
### Connection Phase
You will be asked to insert IP and Port of the server. If left blank the client will connect to the default values: 

- IP: *"Localhost"*
- Port: *"2063"*


### Login Phase

You will need to insert the username, select the number of players to play the game and check whether you want to play pro rules.

Note that your username must be different from all the players logged in the server, not only the ones in the same lobby.

### Mage Selection Phase

You will need to choose the mage representing the deck you will be using during the game. 

### Action Phase

You can find the rules [here](https://craniointernational.com/2021/wp-content/uploads/2021/06/Eriantys_rules_small.pdf).

1. Click on the Assistant Card you want to play and click confirm. 
2. Once is your turn you will need to move the students:
   1. If you want to move a student to the entrance click on it and click "move student"
   2. If you want to move a student on an island click the student, then the island and finally "move student"
   3. If you clicked an island and you change your mind click "reset".
3. After moving the students you will need to enable mother nature by clicking on "move mother nature", then you must enter the number of steps and click "move mother nature" again
4. Finally you will need to choose the cloud card by clicking on it and then on "confirm cloud"

Between the phases above you can play a Character card, to see others' SchoolBoards just click on the related button.


