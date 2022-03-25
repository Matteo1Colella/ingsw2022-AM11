package it.polimi.ingsw.model;

public class Professor extends Piece{
    private ColorStudent colorprofessor;

    public Professor(ColorStudent colorprofessor) {
        this.colorprofessor = colorprofessor;
    }

    public ColorStudent getColorprofessor() {
        return colorprofessor;
    }

    public void setColorprofessor(ColorStudent colorprofessor) {
        this.colorprofessor = colorprofessor;
    }

    //override of interface moovable
    @Override
    public Board setPosition() {
        return null;
    }
}
