package com.example.demo.cases.algorithm;
/**
* @author shibin
* @version ����ʱ�䣺2019��3��5�� ����4:59:26
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
