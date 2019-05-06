package com.example.demo.cases.algorithm.bloomfilter;

/**
* @author shibin
* @version 创建时间：2019年3月5日 下午5:16:54
* 
*/
public class BloomFilter {
	
	/**
	 * 具体使用何种hash方法
	 */
	private final Strategy strategy;

	interface Strategy{
		void put();
		
		boolean mightContain();
	}
	
	public static BloomFilter create() {
		return new BloomFilter(BloomFilterStrategies.MURMUR128_MITZ_64);
	}

	public BloomFilter(Strategy strategy) {
		super();
		this.strategy = strategy;
	}
	
}
