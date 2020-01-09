package com.example.demo.cases.enums;

/**
 * 算是策略模式的一种
 */
public enum BaseEnum implements BaseInterface {

    SAVE(1, "SAVE"){
        @Override
        public void operation() {
            System.out.println("BaseEnum.SAVE");
        }
    },
    UPDATE(2, "UPDATE"){
        @Override
        public void operation() {
            System.out.println("BaseEnum.UPDATE");
        }
    };

    private Integer type;
    private String value;
    BaseEnum(Integer type, String value){
        this.type = type;
        this.value = value;
    }
}
