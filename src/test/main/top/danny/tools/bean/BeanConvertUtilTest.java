package top.danny.tools.bean;

import org.junit.Test;
import top.danny.tools.bean.entity.Person1;
import top.danny.tools.bean.entity.Person2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: BeanConvertUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * 要拷贝字段的目标对象不可以是抽象类、接口，或者只有有参构造方法，
 * 否则在targetClass.newInstance()时会报java.lang.InstantiationException
 * @Company: lxjr.com
 * @Created on 2017-07-19 10:13:52
 */
public class BeanConvertUtilTest {

    @Test
    public void convertListTest(){
        List<Person1> person1List=getPerson1List();
        List<Person2> person2List=convertList(person1List,Person2.class);
        for (Person2 person:person2List){
            System.out.println("id:"+person.getId()+";name:"+person.getName());
        }
    }

    @Test
    public void convertPropertyTest() throws IllegalAccessException, InstantiationException {
        Person1 person1=new Person1(1L,"Danny1",new Date());
        Person2.class.newInstance();
        Person2 person2=convertIgnoreNullProperty(person1,Person2.class);
        System.out.println("id:"+person2.getId()+";name:"+person2.getName());
    }

    public <T> T convertIgnoreNullProperty(Object source, Class<T> targetClass) {
        return BeanConvertUtil.convert(source, targetClass);
    }

    protected  <T> List<T> convertList(List<?> sourceList, Class<T> targetClass) {
        return BeanConvertUtil.convert(sourceList, targetClass);
    }

    private List<Person1> getPerson1List() {
        List<Person1> personList=new ArrayList<Person1>();
        Person1 person1=new Person1(1L,"Danny1",new Date());
        Person1 person2=new Person1(2L,"Danny2",new Date());
        Person1 person3=new Person1(3L,"Danny3",new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        return personList;
    }

}
