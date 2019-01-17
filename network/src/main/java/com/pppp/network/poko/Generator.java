package com.pppp.network.poko;

public class Generator
{
    private String content;

    private String uri;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", uri = "+uri+"]";
    }
}
			
			