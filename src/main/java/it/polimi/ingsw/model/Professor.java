package it.polimi.ingsw.model;

public class Professor {
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
}
