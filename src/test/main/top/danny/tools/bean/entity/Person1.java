package top.danny.tools.bean.entity;

import top.danny.tools.bean.BeanConvertUtilTest;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Person1
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 10:48:39
 */
public class Person1 {
    public Person1() {
    }

    public Person1(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
    private Long id;
    private String name;
    private Date birthday;

    public Long getId() {
        return id;
    }

    public Person1 setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person1 setName(String name) {
        this.name = name;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Person1 setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
