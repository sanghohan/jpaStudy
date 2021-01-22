package jpabook.japshop.domain;

import javax.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;


    @ManyToOne
    private Parent parent;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
