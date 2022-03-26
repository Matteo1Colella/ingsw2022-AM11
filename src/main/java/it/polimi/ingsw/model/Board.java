package it.polimi.ingsw.model;

import java.util.Collection;

public interface Board {

    public Collection<Student> getStudents();
    public Collection<Student> getStudents(ColorStudent colorStudent);

}
