package com.example.demo.cases.algorithm.bloomfilter;

/**
* @author shibin
* @version ����ʱ�䣺2019��3��5�� ����5:16:54
* 
*/
public class BloomFilter {
	
	/**
	 * ����ʹ�ú���hash����
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
