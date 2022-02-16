//package com.example.demo.cases.poi.test;
//
//import com.github.stupdit1t.excel.verify.AbstractCellValueVerify;
//import com.github.stupdit1t.excel.verify.AbstractCellVerify;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//
//public class CollectStringVerify extends AbstractCellVerify {
//
//    private String cellName;
//    private AbstractCellValueVerify cellValueVerify;
//    private boolean allowNull;
//    private List<ErrorInfo> errors;
//
//    public CollectStringVerify(String cellName, boolean allowNull) {
//        this.cellName = cellName;
//        this.allowNull = allowNull;
//    }
//
//    public CollectStringVerify(String cellName, AbstractCellValueVerify cellValueVerify, boolean allowNull) {
//        super();
//        this.cellName = cellName;
//        this.cellValueVerify = cellValueVerify;
//        this.allowNull = allowNull;
//    }
//
//    @Override
//    public Object verify(Object cellValue) throws Exception {
//        if (allowNull) {
//            if (cellValue != null && StringUtils.isNotEmpty(format(cellValue))) {
//                cellValue = format(cellValue);
//                if (null != cellValueVerify) {
//                    cellValue = cellValueVerify.verify(cellValue);
//                }
//                return cellValue;
//            }
//            return cellValue;
//        }
//
//        if (cellValue == null || StringUtils.isEmpty(format(cellValue))) {
////            throw POIException.newMessageException(cellName + "不能为空");
//            errors.add(ErrorInfo.builder().column().build());
//        }
//
//        cellValue = format(cellValue);
//        if (null != cellValueVerify) {
//            cellValue = cellValueVerify.verify(cellValue);
//        }
//        return cellValue;
//    }
//
//    private String format(Object fileValue) {
//        return String.valueOf(fileValue);
//    }
//}
