package org.example;

import java.time.LocalDate;

public class User {

    private final int id;
    private final String name;
    private final int level;
    private final LocalDate created;

    public User(int id, String name, int level, LocalDate created) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public LocalDate getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", created=" + created +
                '}';
    }
}
