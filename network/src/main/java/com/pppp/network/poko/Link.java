package com.pppp.network.poko;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Link
{
    @Attribute(name = "rel")
    private String rel;

    @Attribute(name = "type")
    private String type;

    @Attribute(name = "href")
    private String href;

}
			
			