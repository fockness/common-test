package com.example.demo.cases.poi.test;

import com.github.stupdit1t.excel.callback.ParseSheetCallback;
import com.github.stupdit1t.excel.common.ImportRspInfo;
import com.github.stupdit1t.excel.common.POIConstant;
import com.github.stupdit1t.excel.common.POIException;
import com.github.stupdit1t.excel.verify.AbstractCellVerify;
import com.github.stupdit1t.excel.verify.AbstractVerifyBuidler;
import com.github.stupdit1t.excel.verify.ImgVerify;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.util.*;

public class ExcelUtils extends com.github.stupdit1t.excel.ExcelUtils {

    public static <T> ImportRspInfo<T> parseSheet(Class<T> clss, AbstractVerifyBuidler verifyBuilder, Sheet sheet, int dataStartRow, int dataEndRowCount
            , ParseSheetCallback<T> callback, List<ErrorData<T>> list) {
        // 规则初始化
        verifyBuilder.init();
        ImportRspInfo<T> rsp = new ImportRspInfo<T>();
        List<T> beans = new ArrayList<>();
        // 获取excel中所有图片
        List<String> imgField = new ArrayList<>();
        Map<String, PictureData> pictures = null;
        Map<String, AbstractCellVerify> verifys = verifyBuilder.getVerifys();
        Set<String> keySet = verifys.keySet();
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        for (String key : keySet) {
            AbstractCellVerify abstractCellVerify = verifys.get(key);
            if (abstractCellVerify instanceof ImgVerify) {
                imgField.add(key);
                if (pictures == null || pictures.isEmpty()) {
                    pictures = getSheetPictures(sheetIndex, sheet);
                }
            }
        }
        StringBuilder errors = new StringBuilder();
        StringBuilder rowErrors = new StringBuilder();
        try {
            int rowStart = sheet.getFirstRowNum() + dataStartRow;
            // warn获取真实的数据行尾数
            int rowEnd = getLastRealLastRow(sheet.getRow(sheet.getLastRowNum())) - dataEndRowCount;
            for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
                Row r = sheet.getRow(rowNum);
                // 创建对象
                T t = clss.newInstance();
                int fieldNum = 0;
                int[] ints = POIConstant.convertToCellNum(verifyBuilder.getCellRefs());
                List<ErrorInfo> errorInfos = null;
                for (int cellNum : ints) {
                    // 列坐标
                    CellReference cellRef = new CellReference(rowNum, cellNum);
                    String filedName = verifyBuilder.getFiledNames()[fieldNum];
                    errorInfos = Lists.newArrayListWithCapacity(ints.length);
                    ErrorInfo errorInfo = null;
                    Object cellValue = null;
                    try {
                        if (imgField.size() > 0 && imgField.contains(filedName)) {
                            String pictrueIndex = sheetIndex + "," + rowNum + "," + cellNum;
                            PictureData remove = pictures.remove(pictrueIndex);
                            cellValue = remove == null ? null : remove.getData();
                        } else {
                            cellValue = getCellValue(r, cellNum);
                        }
                        // 校验和格式化列值
                        cellValue = verifyBuilder.verify(filedName, cellValue);
                        // 填充列值
                        FieldUtils.writeField(t, filedName, cellValue, true);
                    } catch (POIException e) {
                        errorInfos.add(ErrorInfo.builder().column(filedName).data(cellValue).errorMessage(e.getMessage()).build());
                        //rowErrors.append(cellRef.formatAsString()).append(":").append(e.getMessage()).append("\t");

                    }
                    fieldNum++;
                }
                // 回调处理一下特殊逻辑
                if (callback != null) {
                    callback.callback(t, rowNum);
                }
                beans.add(t);
                if(CollectionUtils.isNotEmpty(errorInfos)){
                    list.add(ErrorData.<T>builder().t(t).errorInfos(errorInfos).build());
                }
                if (rowErrors.length() > 0) {
                    errors.append(rowErrors).append("\r\n");
                    rowErrors.setLength(0);
                }
            }
        } catch (Exception e) {
            if (e instanceof POIException) {
                errors.append(new StringBuffer(e.getMessage()).append("\t"));
            } else {
                e.printStackTrace();
            }

        } finally {
            // throw parse exception
            if (errors.length() > 0) {
                rsp.setSuccess(false);
                rsp.setMessage(errors.toString());
            }
            rsp.setData(beans);
        }
        // 返回结果
        return rsp;
    }

    private static Map<String, PictureData> getSheetPictures(int sheetNum, Sheet sheet) {
        try {
            HSSFSheet sheetHssf = (HSSFSheet)sheet;
            return getSheetPictrues03(sheetNum, sheetHssf);
        } catch (Exception var4) {
            XSSFSheet sheetXssf = (XSSFSheet)sheet;
            return getSheetPictrues07(sheetNum, sheetXssf);
        }
    }

    private static Map<String, PictureData> getSheetPictrues03(int sheetNum, HSSFSheet sheet) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        List<HSSFPictureData> pictures = sheet.getWorkbook().getAllPictures();
        if (!pictures.isEmpty()) {
            HSSFPatriarch drawingPatriarch = sheet.getDrawingPatriarch();
            if (drawingPatriarch != null) {
                for (HSSFShape shape : drawingPatriarch.getChildren()) {
                    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                    if (shape instanceof HSSFPicture) {
                        HSSFPicture pic = (HSSFPicture) shape;
                        int pictureIndex = pic.getPictureIndex() - 1;
                        HSSFPictureData picData = pictures.get(pictureIndex);
                        String picIndex = String.valueOf(sheetNum) + "," + String.valueOf(anchor.getRow1()) + "," + String.valueOf(anchor.getCol1());
                        sheetIndexPicMap.put(picIndex, picData);
                    }
                }
            }
        }
        return sheetIndexPicMap;
    }

    private static Map<String, PictureData> getSheetPictrues07(int sheetNum, XSSFSheet sheet) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    if (shape instanceof XSSFPicture) {
                        XSSFPicture pic = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = pic.getPreferredSize();
                        CTMarker ctMarker = anchor.getFrom();
                        String picIndex = String.valueOf(sheetNum) + "," + ctMarker.getRow() + "," + ctMarker.getCol();
                        sheetIndexPicMap.put(picIndex, pic.getPictureData());
                    }
                }
            }
        }
        return sheetIndexPicMap;
    }

    private static Object getCellValue(Row r, int cellNum) {
        // 缺失列处理政策
        Cell cell = r.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Object obj = null;
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                obj = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    obj = cell.getDateCellValue();
                } else {
                    obj = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case FORMULA:
                obj = cell.getCellFormula();
                break;
            default:
                break;
        }
        return obj;
    }

    private static int getLastRealLastRow(Row row) {
        Sheet sheet = row.getSheet();
        short lastCellNum = row.getLastCellNum();
        if (lastCellNum == -1) {
            int rowNum = row.getRowNum();
            Row newRow = sheet.getRow(--rowNum);
            while (newRow == null) {
                newRow = sheet.getRow(--rowNum);
            }
            return getLastRealLastRow(newRow);
        } else {
            int blankCell = 0;
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = row.getCell(i);
                if (cell == null || cell.getCellType() == CellType.BLANK) {
                    blankCell++;
                }
            }
            if (blankCell >= lastCellNum) {
                int rowNum = row.getRowNum();
                Row newRow = sheet.getRow(--rowNum);
                while (newRow == null) {
                    newRow = sheet.getRow(--rowNum);
                }
                return getLastRealLastRow(newRow);
            }
        }
        return row.getRowNum();
    }

}
