package com.example.demo.cases.signature;

public enum EnableAllInterfaceSignaturesEnum {

    TRUE("1", "true"),
    FALSE("2", "false"),;

    private String type;
    private String value;

    private EnableAllInterfaceSignaturesEnum(String type, String value){
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
