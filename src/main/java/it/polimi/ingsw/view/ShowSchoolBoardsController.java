package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.model.board.SchoolBoard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class ShowSchoolBoardsController {

    private ClientMain clientMain;

    @FXML
    private AnchorPane schoolBoardsPane;

    private ArrayList<Node> nodes;

    public void initialize(ModelMessage model){
        int i = 1;
        nodes = new ArrayList<>(schoolBoardsPane.getChildren());
        ArrayList<ColorStudent> colorStudents = new ArrayList<>();
        colorStudents.add(ColorStudent.GREEN);
        colorStudents.add(ColorStudent.RED);
        colorStudents.add(ColorStudent.YELLOW);
        colorStudents.add(ColorStudent.BLUE);
        colorStudents.add(ColorStudent.PINK);

        for(String s : model.getNameSchoolBoardMap().keySet()){
            if(!s.equals(clientMain.getUsername())){
                Label name = (Label) nodes.get(i);
                name.setText("Player: " + s);
                name.setOpacity(1);
                i++;
            }
        }
        i = 4;
        for(String s : model.getNameSchoolBoardMap().keySet()){
            if(!s.equals(clientMain.getUsername())){
                for(ColorStudent colorStudent : colorStudents){
                    StackPane stackPane = (StackPane) nodes.get(i);
                    stackPane.setOpacity(1);
                    Label label = (Label) stackPane.getChildren().get(1);
                    label.setText(""+model.getNameSchoolBoardMap().get(s).getStudentSize(colorStudent));
                    label.setOpacity(1);
                    ImageView image = (ImageView) stackPane.getChildren().get(0);
                    image.setOpacity(1);
                    i++;
                }
            }
        }
        i = 19;
        int g = 0;
        int y = 0;
        int b = 0;
        int r = 0;
        int p = 0;
        for(String s : model.getNameSchoolBoardMap().keySet()){
            if(!s.equals(clientMain.getUsername())){
                for(Student student : model.getNameSchoolBoardMap().get(s).getEntrance().getStudents()){
                    switch (student.getColor()){
                        case GREEN ->{
                            g++;
                            StackPane stackPane = (StackPane) nodes.get(i);
                            Label label = (Label) stackPane.getChildren().get(1);
                            stackPane.setOpacity(1);
                            label.setText(""+g);
                            label.setOpacity(1);
                            ImageView image = (ImageView) stackPane.getChildren().get(0);
                            image.setOpacity(1);
                        }
                        case RED ->{
                            r++;
                            StackPane stackPane = (StackPane) nodes.get(i+1);
                            Label label = (Label) stackPane.getChildren().get(1);
                            stackPane.setOpacity(1);
                            label.setText(""+r);
                            label.setOpacity(1);
                            ImageView image = (ImageView) stackPane.getChildren().get(0);
                            image.setOpacity(1);
                        }

                        case YELLOW ->{
                            y++;
                            StackPane stackPane = (StackPane) nodes.get(i+2);
                            Label label = (Label) stackPane.getChildren().get(1);
                            stackPane.setOpacity(1);
                            label.setText(""+y);
                            label.setOpacity(1);
                            ImageView image = (ImageView) stackPane.getChildren().get(0);
                            image.setOpacity(1);
                        }
                        case BLUE ->{
                            b++;
                            StackPane stackPane = (StackPane) nodes.get(i + 3);
                            stackPane.setOpacity(1);
                            Label label = (Label) stackPane.getChildren().get(1);
                            label.setText(""+b);
                            label.setOpacity(1);
                            ImageView image = (ImageView) stackPane.getChildren().get(0);
                            image.setOpacity(1);
                        }
                        case PINK ->{
                            p++;
                            StackPane stackPane = (StackPane) nodes.get(i+4);
                            stackPane.setOpacity(1);
                            Label label = (Label) stackPane.getChildren().get(1);
                            label.setText(""+p);
                            label.setOpacity(1);
                            ImageView image = (ImageView) stackPane.getChildren().get(0);
                            image.setOpacity(1);
                        }
                    }
                }
                i += 5;
            }
        }
    }

    public void setClientMain(ClientMain clientMain) {
        this.clientMain = clientMain;
    }
}
