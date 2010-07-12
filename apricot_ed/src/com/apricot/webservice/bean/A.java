package com.apricot.webservice.bean;

public class A {
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
		
		A a=new A();
		a.setName("Ðí´ó»¢");
		a.setAge("28");
		
		Row row=new Row(a,new String[]{"name","age"},new String[]{"f1","f2"});
	}
}
