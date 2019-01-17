package com.pppp.network.poko;

public class Entry
{
    private String id;

    private Content content;

    private Author author;

    private String title;

    private Category category;

    private String updated;

    private Link[] link;

    private String published;

    private String displaycategories;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Content getContent ()
    {
        return content;
    }

    public void setContent (Content content)
    {
        this.content = content;
    }

    public Author getAuthor ()
    {
        return author;
    }

    public void setAuthor (Author author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
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

    public String getPublished ()
    {
        return published;
    }

    public void setPublished (String published)
    {
        this.published = published;
    }

    public String getDisplaycategories ()
    {
        return displaycategories;
    }

    public void setDisplaycategories (String displaycategories)
    {
        this.displaycategories = displaycategories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", content = "+content+", author = "+author+", title = "+title+", category = "+category+", updated = "+updated+", link = "+link+", published = "+published+", displaycategories = "+displaycategories+"]";
    }
}
			
			