package com.test.algorithm.bloomfilter;
/**
* @author shibin
* @version ����ʱ�䣺2019��3��5�� ����5:17:55
* 
*/
public enum BloomFilterStrategies implements BloomFilter.Strategy{
	
	MURMUR128_MITZ_32(){
		@Override
		public void put() {
			
		}
		@Override
		public boolean mightContain() {
			return false;
		}
	},
	MURMUR128_MITZ_64(){
		@Override
		public void put() {
			
		}
		@Override
		public boolean mightContain() {
			return false;
		}
	}
	;
}
