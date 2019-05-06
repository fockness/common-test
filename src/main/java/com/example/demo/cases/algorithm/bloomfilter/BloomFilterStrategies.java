package com.test.algorithm.bloomfilter;
/**
* @author shibin
* @version 创建时间：2019年3月5日 下午5:17:55
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
