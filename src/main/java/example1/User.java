package example1;

public class User
{
	private String name="dname";

	private String password="dpassword";

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void get(Object o)
	{
		System.out.println("first:"+o);
	}
	public Object test(String get)
	{
		return "hello,"+get;
	}
}
