package jpabook.japshop.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long Id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    public List<Child> getChildren() {
        return children;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
