package com.example.demo.cases.poi;

import lombok.Data;

@Data
public class ImportAction {

    private String target;

    private Integer dataStartRow = 1;

    private Integer dataEndRowCount = 0;

    private Integer sheetStart = 0;

}
