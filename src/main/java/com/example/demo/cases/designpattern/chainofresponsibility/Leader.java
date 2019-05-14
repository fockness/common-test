package com.example.demo.cases.designpattern.chainofresponsibility;

import java.util.HashMap;
import java.util.Map;


//抽象类
public abstract class Leader {

	protected String name;
	protected Leader nextLeader;//责任链上的后继对象

	public Leader(String name) {
		super();
		this.name = name;
	}

	public void setNextLeader(Leader nextLeader) {
		this.nextLeader = nextLeader;//设置责任链上的后继对象
	}

	public abstract void handleRequest(LeaveReq req);

}

//具体类1
class Director extends Leader{

	public Director(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveReq req) {
		if(req.getLeaveDays() < 3){
			System.out.println("员工:" + req.getName() + ", 请假:" + req.getLeaveDays() + "天, 请假理由:" + req.getReason());
			System.out.println("主任:" + this.name);
		}else{
			if(this.nextLeader != null){
				this.nextLeader.handleRequest(req);
			}
		}
	}
}

//具体类2
class Manager extends Leader{

	public Manager(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveReq req) {
		if(req.getLeaveDays() < 10){
			System.out.println("员工:" + req.getName() + ", 请假:" + req.getLeaveDays() + "天, 请假理由:" + req.getReason());
			System.out.println("经理:" + this.name);
		}else{
			if(this.nextLeader != null){
				this.nextLeader.handleRequest(req);
			}
		}
	}
}

//具体类3
class GeneralManager extends Leader{

	public GeneralManager(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveReq req) {
		if(req.getLeaveDays() < 20){
			System.out.println("员工:" + req.getName() + ", 请假:" + req.getLeaveDays() + "天, 请假理由:" + req.getReason());
			System.out.println("总经理:" + this.name);
		}else{
			System.out.println("拒绝");
		}
	}
}

class LeaderUtil{
	private static Map<String, Leader> map = null;

	private LeaderUtil(){}

	static{

	}

	public synchronized static Map<String, Leader> newInstance(){
		if(map == null){
			map = new  HashMap<String, Leader>();
			iniObj();
			return map;
		}
		return map;
	}

	private static void iniObj(){
		//初始化责任链上的对象
		Leader l1 = new Director("张三");
		Leader l2 = new Manager("李四");
		Leader l3 = new GeneralManager("王五");

		//设置责任链的顺序
		l1.setNextLeader(l2);
		l2.setNextLeader(l3);

		map.put("张三", l1);
		map.put("李四", l2);
		map.put("王五", l3);
	}
}
