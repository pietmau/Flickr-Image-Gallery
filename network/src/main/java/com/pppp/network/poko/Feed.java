package com.pppp.network.poko;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Feed {
    @Attribute(required = false)
    private String id;

    @Attribute(required = false)
    private String icon;

    @Attribute(required = false)
    private String title;

    @Attribute(required = false)
    private String updated;

    @ElementList(name = "link", required = false, inline = true)
    private Link[] link;

    @ElementList(name = "entry", required = false, inline = true)
    private List<Entry> entry;

    @Attribute(required = false)
    private String subtitle;

    @Element(required = false)
    private Generator generator;

    @Attribute(required = false)
    private String xmlns;
}
			
			