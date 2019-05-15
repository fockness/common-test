package com.example.demo.cases.designpattern.iterator;

//迭代器接口
public interface MyIterator {
	void first();
	void last();
	void next();
	boolean isFirst();
	boolean isLast();
	Object getCurrentObj();
	boolean hasNext();
}
