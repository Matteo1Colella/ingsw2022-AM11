package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.Characters.*;
import it.polimi.ingsw.model.pieces.NoEntryTile;

import java.util.ArrayList;

public class CharacterDeck {

    private ArrayList<CharacterCard> cards;
    private final int numCards = 12;
    private Game game;

    // Start of Getters, Setters, Constructor

    public CharacterDeck(Game game) {
        this.cards = new ArrayList<>();
        this.game = game;
        int size = 3;
        int min = 1;
        int[] nodup = {0, 0, 0};
        int random = 0;
        for (int i = 0; i < size; i++){

            while(random == nodup[0] || random == nodup[1] ||random == nodup[2]) {
                random = (int) Math.floor(Math.random() * (numCards - min + 1) + min);
            }
            nodup[i] = random;

            switch(random){
                case 1:
                    CharacterCard card1 = new Character1(random);
                    for(int j = 0; j < 4; j++){
                       card1.addSudent(this.game.getGameComponents().getBag().draw());
                    }
                    this.cards.add(card1);
                    break;
                case 2:
                    CharacterCard card2 = new Character2(random);
                    this.cards.add(card2);
                    break;
                case 3:
                    CharacterCard card3 = new Character3(random);
                    this.cards.add(card3);
                    break;
                case 4:
                    CharacterCard card4 = new Character4(random);

                    this.cards.add(card4);
                    break;
                case 5:
                    CharacterCard card5 = new Character5(random);
                    for(int j = 0; j < 4; j++){
                        NoEntryTile tile = new NoEntryTile(false, null);
                        card5.addTile(tile);

                    }
                    this.cards.add(card5);
                    break;
                case 6:
                    CharacterCard card6 = new Character6(random);
                    this.cards.add(card6);
                    break;

                case 7:
                    CharacterCard card7 = new Character7(random);
                    for(int j = 0; j < 6; j++){
                        card7.addSudent(this.game.getGameComponents().getBag().draw());
                    }
                    this.cards.add(card7);
                    break;
                case 8:
                    CharacterCard card8 = new Character8(random);
                    this.cards.add(card8);
                    break;
                case 9:
                    CharacterCard card9 = new Character9(random);
                    this.cards.add(card9);
                    break;
                case 10:
                    CharacterCard card10 = new Character10(random);
                    this.cards.add(card10);
                    break;
                case 11:
                    CharacterCard card11 = new Character11(random);
                    for(int j = 0; j < 4; j++){
                        card11.addSudent(this.game.getGameComponents().getBag().draw());
                    }
                    this.cards.add(card11);
                    break;
                case 12:
                    CharacterCard card12 = new Character12(random);
                    this.cards.add(card12);
                    break;
            }
        }
    }
    public CharacterCard getcard(int num){
     for(CharacterCard temp : this.cards)
     {
         if (temp.getNum()==num) return temp;
     }
     return null;
    }

    public ArrayList<CharacterCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CharacterCard> cards) {
        this.cards = cards;
    }

    // End of Getters, Setters, Constructor

}
