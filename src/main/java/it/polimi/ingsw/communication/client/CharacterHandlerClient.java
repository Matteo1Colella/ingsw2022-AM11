package it.polimi.ingsw.communication.client;

import it.polimi.ingsw.communication.common.JSONtoObject;
import it.polimi.ingsw.communication.common.ObjectToJSON;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.communication.common.messages.UseCharacterMessage;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.characters.*;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CharacterHandlerClient {
    private ArrayList<CharacterCard> playableCharacters;
    private ModelMessage model;
    private int coinsOwned;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;

    public CharacterHandlerClient(ModelMessage model, Socket socket){
       this.model = model;
        playableCharacters = model.getCharacterCards();
        sendMessage = new ObjectToJSON(socket);
        receiveMessage = new JSONtoObject(socket);
    }

    public boolean askCharacter(){
        coinsOwned = model.getCoinOwned();
        String input = "";
        int choice = -1;
        while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
        {
            System.out.println("Do you want to use a character?");
            Scanner charscanner = new Scanner(System.in);
            input = charscanner.nextLine();
        }
        if(input.equals("no")){
            return false;
        }
        int n = 0, l = 0;
        for (CharacterCard temp : playableCharacters) {
            //System.out.println(temp.getNecessaryCoin());
            if (temp.getNecessaryCoin() <= coinsOwned) {
                System.out.println(l + ": Character " + temp.getNum());
                n++;
            }
            l++;
        }
        if (n == 0){
            System.out.println("No character usable");
            return false;
        }
        while (choice < 0 || choice >=l) {
            System.out.println("Select the character you want to use");
            Scanner charScanner = new Scanner(System.in);
            choice = charScanner.nextInt();
        }

        playCharacter(choice);
        return true;
    }

    private void playCharacter(int choice){
        UseCharacterMessage characterMessage = new UseCharacterMessage();
        switch (playableCharacters.get(choice).getNum()){
            case 1:
                // TODO: 23/05/2022 show in a different way the print of the students: in the model message must be an array containing this students
                //Character1 card1 = (Character1) playableCharacters.get(choice);
                int val = -1;
                int val2 = - 1;

                playableCharacters.get(choice).getStudents().stream().map(Student::getColor).forEach(System.out::println);
                while(val < 0 || val > playableCharacters.get(choice).getStudents().size()) {
                    System.out.println("Choose Student");
                    Scanner charscanner = new Scanner(System.in);
                    val = charscanner.nextInt();
                }

                while(val2 < 0 || val2 > model.getArchipelago().size()) {
                    System.out.println("Choose Island");
                    Scanner charscanner = new Scanner(System.in);
                    val2 = charscanner.nextInt();
                }
                sendMessage.sendCharacterMessage(characterMessage.useCharacter1Message(val2, val, choice));
                //card.effect(currentPlayer, newGame.getGameComponents().getArchipelago().get(val), val2);

                break;
            case 2:
                //Character2 card2 = (Character2) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter2Message(choice));
                //card2.effect(currentPlayer);
                break;
            case 3:
                //Character3 card3 = (Character3) playableCharacters.get(choice);
                int val3 = -1;

                while(val3 < 0 || val3 > model.getArchipelago().size()) {
                    System.out.println("Choose Island");
                    Scanner charscanner = new Scanner(System.in);
                    val3 = charscanner.nextInt();
                }
                sendMessage.sendCharacterMessage(characterMessage.useCharacter3Message(val3, choice));
                //card3.effect(currentPlayer,  newGame.getGameComponents().getArchipelago().get(val3));

                break;
            case 4:
                //Character4 card4 = (Character4) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter4Message(choice));
                //card4.effect(currentPlayer);
                break;
            case 5:
                //Character5 card5 = (Character5) playableCharacters.get(choice);
                int val5 = -1;

                while(val5 < 0 || val5 > model.getArchipelago().size()) {
                    System.out.println("Choose Island");
                    Scanner charscanner = new Scanner(System.in);
                    val5 = charscanner.nextInt();
                }
                sendMessage.sendCharacterMessage(characterMessage.useCharacter5Message(val5, choice));
                //card5.effect(currentPlayer, newGame.getGameComponents().getArchipelago().get(val5));

                break;
            case 6:
                //Character6 card6 = (Character6) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter6Message(choice));
                //card6.effect(currentPlayer);
                break;
            case 7:
                // TODO: 23/05/2022 show in a different way the print of the students: in the model message must be an array containing this students
                //Character7 card7 = (Character7) playableCharacters.get(choice);
                int val7 = -1;
                int[] fromEntrance = {-1, -1, -1};
                int[] fromCard = {-1, -1, -1};
                int duplicate = 0;

                for(int n7 = 0; n7 < 3; n7++){
                    val7 = -1;
                    while(val7 < 0 || val7 > model.getSchoolBoard().getEntrance().getStudents().size() || duplicate > 0) {
                        duplicate = 0;
                        System.out.println("Choose the " + n7 + " student from entrance");
                        Scanner charscanner = new Scanner(System.in);
                        val7 = charscanner.nextInt();

                        for(int j = 0; j < fromEntrance.length; j++){
                            if (fromEntrance[j] == val7){
                                duplicate ++;
                            }
                        }

                    }
                    fromEntrance[n7] = val7;
                }

                System.out.println("Students on character:");
                playableCharacters.get(choice).getStudents().stream().map(Student::getColor).forEach(System.out::println);
                for(int n7 = 0; n7 < 3; n7++){
                    val7 = -1;
                    while(val7 < 0 || val7 > playableCharacters.get(choice).getStudents().size()||duplicate>0) {
                        duplicate = 0;
                        System.out.println("choose the " + n7 + " student from the character");
                        Scanner charscanner = new Scanner(System.in);
                        val7 = charscanner.nextInt();

                        for(int j = 0; j < fromEntrance.length; j++){
                            if (fromCard[j] == val7){
                                duplicate ++;
                            }
                        }
                    }
                    fromCard[n7] = val7;
                }
                sendMessage.sendCharacterMessage(characterMessage.useCharacter7Message(fromEntrance, fromCard, choice));
                //card7.effect(currentPlayer, fromEntrance, fromCard);

                break;
            case 8:
                //Character8 card8 = (Character8) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter8Message(choice));
                //card8.effect(currentPlayer);

                break;
            case 9:
                String chosencolor = "";
                ColorStudent color = null;
                System.out.println("choose a color to exclude");
                while(!chosencolor.equals("red") && !chosencolor.equals("blue") && !chosencolor.equals("yellow") && !chosencolor.equals("pink") && !chosencolor.equals("green")){
                    Scanner charscanner = new Scanner(System.in);
                    chosencolor = charscanner.nextLine();
                }

                switch(chosencolor){
                    case "red":
                        color = ColorStudent.RED;
                        break;
                    case "blue":
                        color = ColorStudent.BLUE;
                        break;
                    case "green":
                        color = ColorStudent.GREEN;
                        break;
                    case "yellow":
                        color = ColorStudent.YELLOW;
                        break;
                    case "pink":
                        color = ColorStudent.PINK;
                        break;
                }

                //Character9 card9 = (Character9) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter9Message(color, choice));
                //card9.effect(currentPlayer, color);

                break;
            case 10:
                int i = 0;
                for(ColorStudent colorStudent : ColorStudent.values()){
                    i += model.getSchoolBoard().getDiningRoom(colorStudent).getStudentsSize();

                }
                if(i < 2){
                    return;
                }

                int[] fromE = {-1,-1,-1};
                ArrayList<Student> aFromDining = new ArrayList<>();
                int dup = 0;
                String diningroomcolor = "";
                ColorStudent c = null;
                int s = -1;
                int j = 0;

                int numred = 0;
                int numblue = 0;
                int numgreen = 0;
                int numyellow = 0;
                int numpink = 0;

                while(j<2){
                    s = -1;
                    while(s < 0 || s > model.getSchoolBoard().getEntrance().getStudents().size() || dup > 0) {
                        dup = 0;
                        System.out.println("choose the " + j + " student from entrance");
                        Scanner charscanner = new Scanner(System.in);
                        s = charscanner.nextInt();
                        for(int z = 0; z < fromE.length; z++){
                            if (fromE[z] == s){
                                dup ++;
                            }
                        }
                    }
                    fromE[j] = s;
                    j++;
                }


                j = 0;
                while(j<2){
                    diningroomcolor = "";
                    while(!diningroomcolor.equals("red") && !diningroomcolor.equals("blue") && !diningroomcolor.equals("yellow") && !diningroomcolor.equals("pink") && !diningroomcolor.equals("green")) {
                        System.out.println("choose the color of the dining room you want to draw a student from");
                        Scanner charscanner = new Scanner(System.in);
                        diningroomcolor = charscanner.nextLine();
                    }

                    if(diningroomcolor.equals("red")){
                        c = ColorStudent.RED;
                        numred++;
                    }
                    if(diningroomcolor.equals("blue")){
                        c = ColorStudent.BLUE;
                        numblue++;
                    }
                    if(diningroomcolor.equals("green")){
                        c = ColorStudent.GREEN;
                        numgreen++;
                    }
                    if(diningroomcolor.equals("pink")){
                        c = ColorStudent.PINK;
                        numpink++;
                    }
                    if(diningroomcolor.equals("yellow")){
                        c = ColorStudent.YELLOW;
                        numyellow++;
                    }

                    switch (c){
                        case RED:
                            if(model.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numred){
                                j++;
                                aFromDining.add(model.getSchoolBoard().getDiningRoom(c).getStudents().get(numred - 1));
                            }
                            System.out.println("not enough students");

                            break;
                        case GREEN:
                            if(model.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numgreen){
                                j++;
                                aFromDining.add(model.getSchoolBoard().getDiningRoom(c).getStudents().get(numgreen - 1));
                            }
                            System.out.println("not enough students");
                            break;
                        case BLUE:
                            if(model.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numblue){
                                j++;
                                aFromDining.add(model.getSchoolBoard().getDiningRoom(c).getStudents().get(numblue - 1));
                            }
                            System.out.println("not enough students");
                            break;
                        case PINK:
                            if(model.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numpink ){
                                j++;
                                aFromDining.add(model.getSchoolBoard().getDiningRoom(c).getStudents().get(numpink - 1));
                            }
                            System.out.println("not enough students");
                            break;
                        case YELLOW:
                            if(model.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numyellow){
                                j++;
                                aFromDining.add(model.getSchoolBoard().getDiningRoom(c).getStudents().get(numyellow - 1));
                            }
                            System.out.println("not enough students");
                            break;
                    }
                }
                int[] studentsFromEntrance = new int[3];
                for(i = 0; i < 2; i++){
                    studentsFromEntrance[i] = model.getSchoolBoard().getEntrance().getStudents().indexOf(aFromDining.get(i));
                }
                //Character10 card10 = (Character10)playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter10Message(studentsFromEntrance, fromE, choice));
                //card10.effect(currentPlayer, aFromDining, fromE);
                break;

            case 11:
                int val11 = -1;
                System.out.println("test");
                // TODO: 23/05/2022 show in a different way the print of the students: in the model message must be an array containing this students
                //Character11 card11 = (Character11) playableCharacters.get(choice);
                playableCharacters.get(choice).getStudents().stream().map(Student::getColor).forEach(System.out::println);

                while(val11 < 0 || val11 > playableCharacters.get(choice).getStudents().size()) {
                    System.out.println("choose Student");
                    Scanner charscanner = new Scanner(System.in);
                    val11 = charscanner.nextInt();
                }
                sendMessage.sendCharacterMessage(characterMessage.useCharacter11Message(val11, choice));
                //card11.effect(currentPlayer, val11);


                break;

            case 12:
                String col = "";
                ColorStudent c12 = null;
                while(!col.equals("red") && !col.equals("blue") && !col.equals("green") && !col.equals("yellow") && !col.equals("pink")){
                    System.out.println("choose Color");
                    Scanner charscanner = new Scanner(System.in);
                    col = charscanner.nextLine();
                }
                switch(col){
                    case "red":
                        c12 = ColorStudent.RED;
                        break;
                    case "blue":
                        c12 = ColorStudent.BLUE;
                        break;
                    case "green":
                        c12 = ColorStudent.GREEN;
                        break;
                    case "yellow":
                        c12 = ColorStudent.YELLOW;
                        break;
                    case "pink":
                        c12 = ColorStudent.PINK;
                        break;
                }

                System.out.println("test");
                //Character12 card12 = (Character12) playableCharacters.get(choice);
                sendMessage.sendCharacterMessage(characterMessage.useCharacter12Message(c12, choice));
                //card12.effect(currentPlayer, c12);
                break;
        }
    }

    public void setCoinsOwned(int coinsOwned){
        this.coinsOwned = coinsOwned;
    }

    public void setModel(ModelMessage model) {
        this.model = model;
    }
}
