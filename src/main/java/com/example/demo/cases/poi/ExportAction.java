package com.example.demo.cases.poi;

import lombok.Data;

import java.util.Map;

@Data
public class ExportAction {

    private String target;

    private Map<String, String> parameters;
}
