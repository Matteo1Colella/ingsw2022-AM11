# ERIANTYS

# Prova Finale Ingegneria del Software 2022
## Gruppo AM11

- ###   10682882    Aleksandro Aliaj ([@AleksandroAliaj](https://github.com/AleksandroAliaj))<br>aleksandro.aliaj@mail.polimi.it
- ###   10674905    Leonardo Cesani ([@LeonardoCesani](https://github.com/LeonardoCesani))<br>leonardo.cesani@mail.polimi.it
- ###   10666309    Matteo Colella ([@Matteo1Colella](https://github.com/Matteo1Colella))<br>matteo1.colella@mail.polimi.it


## Functionality implemented

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

Open a terminal and go to the project target directory. Once there, execute this command:

*java -jar AM11_server.jar*

Then open another terminal, go to the project directory and digit

If you want to use the CLI: *java -jar AM11_cli.jar*

If you want to use the GUI: *java -jar AM11_gui.jar*


## Test cases

| Package    | Tested Class    | Class       | Method        |      Line       |  
|:-----------|:----------------|:------------|:--------------|:---------------:|
| controller | ComplexLobby    | 50% (1/2)   | 54% (23/42)   |  33% (120/360)  |
| controller | DeckManager     | 100% (1/1)  | 100% (23/42)  |   95% (23/24)   |
| controller | GameManager     | 100% (1/1)  | 71% (5/7)     |  43% (53/121)   |
| controller | OrderComparator | 100% (1/1)  | 100% (1/1)    |    88% (8/9)    |
| model      | Global Package  | 97% (38/39) | 92% (234/252) | 74% (1046/1407) |

