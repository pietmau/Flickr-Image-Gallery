package com.pppp.network.poko;

public class Author
{
    private String name;

    private String uri;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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
        return "ClassPojo [name = "+name+", uri = "+uri+"]";
    }
}