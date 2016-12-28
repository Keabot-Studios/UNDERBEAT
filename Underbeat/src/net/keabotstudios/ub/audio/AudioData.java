package net.keabotstudios.ub.audio;

public class AudioData {
	private String name;
	private boolean loop;
	
	public AudioData(String name, boolean loop)
	{
		this.name = name;
		this.loop = loop;
	}
	
	public String getName()
	{
		return name;		
	}
	
	public boolean doesLoop()
	{
		return loop;
	}
}
