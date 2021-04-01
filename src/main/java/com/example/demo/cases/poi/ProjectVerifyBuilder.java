package com.example.demo.cases.poi;

import com.github.stupdit1t.excel.verify.*;

import java.util.ArrayList;
import java.util.List;

public class ProjectVerifyBuilder extends AbstractVerifyBuidler {

	private static ProjectVerifyBuilder builder = new ProjectVerifyBuilder();

	public static ProjectVerifyBuilder getInstance() {
		return builder;
	}

	/**
	 * 定义列校验实体：提取的字段、提取列、校验规则
	 */
    /*@Override
    protected List<CellVerifyEntity> buildRule() {
        List<CellVerifyEntity> cellEntitys = new ArrayList<>();
		cellEntitys.add(new CellVerifyEntity("projectName", "B", new StringVerify("项目名称", true)));
		cellEntitys.add(new CellVerifyEntity("areaName", "C", new StringVerify("所属区域", true)));
		cellEntitys.add(new CellVerifyEntity("province", "D", new StringVerify("省份", true)));
		cellEntitys.add(new CellVerifyEntity("city", "E", new StringVerify("市", true)));
		cellEntitys.add(new CellVerifyEntity("people", "F", new StringVerify("项目所属人", true)));
		cellEntitys.add(new CellVerifyEntity("leader", "G", new StringVerify("项目领导人", true)));
		cellEntitys.add(new CellVerifyEntity("scount", "H", new IntegerVerify("总分", true)));
		cellEntitys.add(new CellVerifyEntity("avg", "I", new DoubleVerify("历史平均分", true)));
		cellEntitys.add(new CellVerifyEntity("createTime", "J", new DateTimeVerify("创建时间", "yyyy-MM-dd HH:mm", true)));
		cellEntitys.add(new CellVerifyEntity("img", "K", new ImgVerify("图片", false)));
        return cellEntitys;
	}*/

	@Override
	protected List<CellVerifyEntity> buildRule() {
		List<CellVerifyEntity> cellEntitys = new ArrayList<>();
		cellEntitys.add(new CellVerifyEntity("name", "A", new StringVerify("姓名", true)));
		cellEntitys.add(new CellVerifyEntity("age", "B", new StringVerify("年龄", true)));
		cellEntitys.add(new CellVerifyEntity("number", "C", new StringVerify("学号", true)));
		return cellEntitys;
	}
}