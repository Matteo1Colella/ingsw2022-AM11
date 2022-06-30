package it.polimi.ingsw.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterView {

    @FXML
    private Image image;
    private int num;

    public Image getImage() {
        return image;
    }

    public int getNum() {
        return num;
    }

    public CharacterView(int num) {
        this.num = num;
        setImage();
    }

    /**
     * Set the correct image, based on the number of the caracter, in the ActionFase section
     * dedicated to CharacterCards
     */
    private void setImage(){
        switch(num){
            case 1:
                image = new Image("Assets/Personaggi/CarteTOT_front.jpg");
                break;
            case 2:
                image = new Image("Assets/Personaggi/CarteTOT_front12.jpg");
                break;
            case 3:
                image = new Image("Assets/Personaggi/CarteTOT_front2.jpg");
                break;
            case 4:
                image = new Image("Assets/Personaggi/CarteTOT_front3.jpg");
                break;
            case 5:
                image = new Image("Assets/Personaggi/CarteTOT_front4.jpg");
                break;
            case 6:
                image = new Image("Assets/Personaggi/CarteTOT_front5.jpg");
                break;
            case 7:
                image = new Image("Assets/Personaggi/CarteTOT_front6.jpg");
                break;
            case 8:
                image = new Image("Assets/Personaggi/CarteTOT_front7.jpg");
                break;
            case 9:
                image = new Image("Assets/Personaggi/CarteTOT_front8.jpg");
                break;
            case 10:
                image = new Image("Assets/Personaggi/CarteTOT_front9.jpg");
                break;
            case 11:
                image = new Image("Assets/Personaggi/CarteTOT_front10.jpg");
                break;
            case 12:
                image = new Image("Assets/Personaggi/CarteTOT_front11.jpg");
                break;
        }
    }
}