package com.pppp.network.poko;

public class Feed
{
    private String id;

    private String icon;

    private String title;

    private String updated;

    private Link[] link;

    private Entry[] entry;

    private String subtitle;

    private Generator generator;

    private String xmlns;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated ()
    {
        return updated;
    }

    public void setUpdated (String updated)
    {
        this.updated = updated;
    }

    public Link[] getLink ()
    {
        return link;
    }

    public void setLink (Link[] link)
    {
        this.link = link;
    }

    public Entry[] getEntry ()
    {
        return entry;
    }

    public void setEntry (Entry[] entry)
    {
        this.entry = entry;
    }

    public String getSubtitle ()
    {
        return subtitle;
    }

    public void setSubtitle (String subtitle)
    {
        this.subtitle = subtitle;
    }

    public Generator getGenerator ()
    {
        return generator;
    }

    public void setGenerator (Generator generator)
    {
        this.generator = generator;
    }

    public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", icon = "+icon+", title = "+title+", updated = "+updated+", link = "+link+", entry = "+entry+", subtitle = "+subtitle+", generator = "+generator+", xmlns = "+xmlns+"]";
    }
}
			
			