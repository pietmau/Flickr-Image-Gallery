package com.pppp.network.poko;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Entry
{
    private String id;

    private Content content;

    private Author author;

    @Element(name = "title")
    private String title;

    private Category category;

    private String updated;

    @ElementList(name = "link", required = false, inline = true)
    private List<Link> link;

    private String published;

    private String displaycategories;
}
			
			