package de.thorsten.shoppinglist;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;
    private String synonym;
    private String category;

    public String getSynonym() { return synonym; }

    public void setSynonym(String synonym) { this.synonym = synonym; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", synonym='" + synonym + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food that = (Food) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(synonym, that.synonym) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, synonym, category);
    }
}
