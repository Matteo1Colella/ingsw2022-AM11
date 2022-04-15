package it.polimi.ingsw.model.colors;

public enum ColorStudent {
    GREEN,
    RED,
    YELLOW,
    PINK,
    BLUE;

    @Override
    public String toString() {
        if(this.equals(ColorStudent.RED)){
            return "red";
        }
        if(this.equals(ColorStudent.YELLOW)){
            return "yellow";
        }
        if(this.equals(ColorStudent.BLUE)){
            return "blue";
        }
        if(this.equals(ColorStudent.PINK)){
            return "pink";
        }
        if(this.equals(ColorStudent.GREEN)){
            return "green";
        }
        else{
            return null;
        }
    }
}


