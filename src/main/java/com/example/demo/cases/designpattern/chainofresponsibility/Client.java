package com.example.demo.cases.designpattern.chainofresponsibility;

public class Client {
	public static void main(String[] args) {
		
		//初始化责任链上的对象
		Leader l1 = new Director("张三");
		Leader l2 = new Manager("李四");
		Leader l3 = new GeneralManager("王五");

		//设置责任链的顺序
		l1.setNextLeader(l2);
		l2.setNextLeader(l3);

		LeaveReq req = new LeaveReq("Tom", 10, "noThing");
		LeaderUtil.newInstance().get("张三").handleRequest(req);


//		变式
//		List<LeaderChange> leaders = Arrays.asList(new LeaderA(), new LeaderB(), new LeaderC());
//		Chain chain = new Chain(leaders);
//		chain.proceed()
		
	}
}