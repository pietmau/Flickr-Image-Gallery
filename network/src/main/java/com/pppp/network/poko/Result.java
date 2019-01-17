package com.pppp.network.poko;

public class Result
{
    private Feed feed;

    public Feed getFeed ()
    {
        return feed;
    }

    public void setFeed (Feed feed)
    {
        this.feed = feed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [feed = "+feed+"]";
    }
}
		