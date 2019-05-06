package com.test.algorithm;
/**
* @author shibin
* @version 创建时间：2019年3月5日 下午4:59:26
* 
*/
public enum InterfaceTest implements aaa{
	AAA(){
		@Override
		public void add() {
			
		}

		@Override
		public void delete() {
			
		}
	},
	BBB(){
		@Override
		public void add() {
			
		}

		@Override
		public void delete() {
			
		}
	}
	;
}

interface aaa{
	void add();
	void delete();
}
