package es.us.isa.odin.domain.entity;

import es.us.isa.odin.annotations.CustomMongoCollection;

@CustomMongoCollection("Pelota")
public class FooEntity {

	private String foo;
	private String bar;
	
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	
	
}
