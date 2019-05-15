package com.example.demo.cases.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;


//聚合类
public class Aggregate {

	private List<Object> list = new ArrayList<Object>();

	public void addObj(Object o){
		list.add(o);
	}

	public void removeObj(Object o){
		list.remove(o);
	}

	public MyIterator createIterator(){
		return new ConcreteIterator();
	}

	//内部类,实现了迭代器接口
	private class ConcreteIterator implements MyIterator{

		private int cursor;

		@Override
		public void first() {
			cursor = 0;
		}

		@Override
		public void last() {
			cursor = list.size() - 1;
		}

		@Override
		public void next() {
			if(cursor < list.size()){
				cursor ++;
			}
		}

		@Override
		public boolean isFirst() {
			return cursor == 0;
		}

		@Override
		public boolean isLast() {
			return cursor == list.size() -1;
		}

		@Override
		public Object getCurrentObj() {
			return list.get(cursor);
		}

		@Override
		public boolean hasNext() {
			if(cursor < list.size()){
				return true;
			}else {
				return false;
			}
		}
	}
}
