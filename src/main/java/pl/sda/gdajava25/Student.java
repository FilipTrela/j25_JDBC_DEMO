package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private int age;
    private Double average;
    private boolean alive;

    public Student(String name, int age, Double average, boolean alive) {
        this.name = name;
        this.age = age;
        this.average = average;
        this.alive = alive;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cześć! Jestem " + name + ". \n" +
                "Moje id to nr. " + id +
                ", lat mam " + age +
                ".\nMoja średnia wynosi " + average);
        if (alive) {
            builder.append(" i żyje!");
        } else
            builder.append(" i nie żyje!");
        builder.append("\n");
        return builder.toString();
    }
}
