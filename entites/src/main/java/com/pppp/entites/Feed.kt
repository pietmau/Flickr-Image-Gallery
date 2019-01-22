package com.pppp.entites

interface Feed {
    var id: String?
    var icon: String?
    var title: String?
    var updated: String?
    val link: List<Link>?
    val images: List<FlickrImage>?
    var subtitle: String?
    val generator: Generator?
    var xmlns: String?
}