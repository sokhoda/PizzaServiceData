package domain;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Access(AccessType.FIELD)
public class Oak implements Serializable{
//    @Id
//    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;
    private String type;
    private Integer height;


    public Oak() {
    }

    public Oak(Long id, String type, Integer height) {
        this.id = id;
        this.type = type;
        this.height = height;
    }

    public Oak(String type, Integer height) {
        this.type = type;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Access(AccessType.PROPERTY)
    public String getMyType() {
        return type;
    }

    public void setMyType(String type) {
        this.type = type;
    }

    public Integer getCustomNameHeight() {
        return height;
    }

    public void setCustomNameHeight(Integer height) {
        this.height = height;
    }
}
