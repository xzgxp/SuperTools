package top.danny.tools.bean.entity;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Person2
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 10:49:28
 */
public class Person2 {
    public Person2() {
    }

    public Person2(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public Person2 setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person2 setName(String name) {
        this.name = name;
        return this;
    }
}
