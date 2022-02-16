package com.example.demo.cases.designpattern.nullobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser {

    protected String name;

    protected Integer age;

    public abstract Boolean isNull();

    public abstract String predicate();

}
