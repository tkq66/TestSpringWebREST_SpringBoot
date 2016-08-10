package hello;

public class GreetingMutable 
{
	public int publicVar;
	private long id;
	private String content;
	
	public GreetingMutable(){}
	
	public GreetingMutable(long id, String content)
	{
		this.id = id;
		this.content = content;
	}
	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
