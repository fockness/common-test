package com.example.demo.cases.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

//抽象组件
public interface AbstractComponent {
	void killVirus();
}

//叶子组件
class ImageFile implements AbstractComponent{

	private String name;
	
	public ImageFile(String name) {
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("图片:" + name + "正在查杀");
	}
	
}

//叶子组件
class VideoFile implements AbstractComponent{

	private String name;
	
	public VideoFile(String name) {
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("视频:" + name + "正在查杀");
	}
	
}

//容器组件
class Folder implements AbstractComponent{

	private String name;
	private List<AbstractComponent> list = new ArrayList<AbstractComponent>();

	public Folder(String name) {
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("文件夹:" + name + "正在查杀");
		for(AbstractComponent c : list){
			c.killVirus();//递归这个文件夹下面的文件或者文件夹，直接调用killVirus
		}
	}

	//添加叶子组件或容器组件
	public void add(AbstractComponent c){
		list.add(c);
	}

	public void remove(AbstractComponent c){
		list.remove(c);
	}

	public AbstractComponent getChild(int index){
		return list.get(index);
	}
}
