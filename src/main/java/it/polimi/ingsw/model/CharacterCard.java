package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CharacterCard {
    private int num;
    private ArrayList<Student> students;
    private ArrayList<NoEntryTile> tiles;
    private Bag bag;

    public CharacterCard(int num,  Bag bag) {
        this.bag = bag;
        switch(num){
            case 1:
                for(int i = 0; i < 4; i++){
                    students.add(bag.draw());
                }
                break;

            case 5:
                for(int i = 0; i < 4; i++){
                    NoEntryTile tile = new NoEntryTile(false, null);
                    tiles.add(tile);
                }
                break;

            case 7:
                for(int i = 0; i < 6; i++){
                    students.add(bag.draw());
                }
                break;

            case 11:
                for(int i = 0; i < 4; i++){
                    students.add(bag.draw());
                }
                break;
        }
        this.num = num;
    }


    public void effect(Player activePlayer){
        switch (this.num) {
            case 1:
                //waits for position input and passes into setPosition
                students.get(1).setPosition();
                students.remove(1);
                students.add(bag.draw());
                break;

            case 2:
                // adds 1 influence point to player
                break;

            case 3:
                activePlayer.getPlayerGame().islandDominance();
                break;

            case 4:
                activePlayer.setMotherNatureMoves(activePlayer.getMotherNatureMoves()+2);
                break;

            case 5:
                //waits for player input to set island card position
                NoEntryTile temp = null;
                for(NoEntryTile t : tiles){
                    if (!t.isUsed()) {
                        temp = t;
                    }
                }
                if (temp == null) break;
                temp.setPosition(null);
                temp.setUsed(true);
                break;

            case 6:
                // no tower island dominance, need to create a function
                activePlayer.getPlayerGame().islandDominance();
                break;

            case 7:
                ArrayList<Student> chosenstudents = new ArrayList<>();
                for(int i = 0; i < 3; i++){
                    int j = 1; //player will input j
                    chosenstudents.add(students.get(j));
                    students.remove(j);
                }
                for(int i = 0; i < 3; i++){
                    int j = 1; //player will input j
                    students.add(activePlayer.getSchoolBoard().getEntrance().getStudents().get(j));
                    activePlayer.getSchoolBoard().getEntrance().getStudents().remove(j);

                }

                activePlayer.getSchoolBoard().getEntrance().addStudents(chosenstudents);

                break;

            case 8:
                // player get 2 more points
                activePlayer.getPlayerGame().islandDominance();
                break;

            case 9:
                // one color doesn't count
                activePlayer.getPlayerGame().islandDominance();
                break;

            case 10:
                // exchange students from DiningRoom and Entrance
                break;

            case 11:
                //Player selects an index j
                int j = 0;
                activePlayer.getSchoolBoard().getEntrance().getStudents().add(students.get(j));
                students.remove(j);
                students.add(bag.draw());
                break;

            case 12:
                //activePlayer.getPlayerGame().effect12();
                break;
        }



    }

}
