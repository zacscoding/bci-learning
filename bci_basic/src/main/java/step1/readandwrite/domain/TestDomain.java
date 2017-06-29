package step1.readandwrite.domain;

public class TestDomain {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void testMethod(String str1, String str2, Integer int1, Long long1) {
		String local;
		local ="11";
	}
	
	public static void staticTestMethod(String str1, String str2, Integer int1, Long long1) {
		String local;
		local ="11";
	}
	
	protected void protectedMethod() {
		String local;
		local ="11";
	}
	
	private void privateMethod() {
		String local;
		local ="11";
	}
	
	
	@Override
	public String toString() {
		return "TestDomain [id=" + id + ", name=" + name + "]";
	}
}
