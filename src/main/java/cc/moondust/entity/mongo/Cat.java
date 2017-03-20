package cc.moondust.entity.mongo;

import javax.persistence.Id;

/**
 * Created by MIKU on 2017/3/18.
 */
public class Cat {

    @Id
    private  Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
