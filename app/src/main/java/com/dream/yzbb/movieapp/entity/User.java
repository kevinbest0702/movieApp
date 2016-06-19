package com.dream.yzbb.movieapp.entity;

import android.databinding.ObservableField;

/**
 * Created by kevin on 16/6/18.
 */
public class User {
    //public class User{
    public final ObservableField<String> name = new ObservableField<>();
    //    private String name;
    private int age;


//    public void setName(String name) {
//        this.name.set(name);
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

//    @Bindable
//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//        notifyPropertyChanged(BR.name);
//    }

    public void setName(String name) {
        this.name.set(name);
    }

//    public User(String name, int age) {
//        this.name.set(name);
//        this.age = age;
//    }
//
//    public User(String name) {
//        this.name.set(name);
//    }
}
