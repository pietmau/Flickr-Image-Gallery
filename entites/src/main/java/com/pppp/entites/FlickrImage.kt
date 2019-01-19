package com.pppp.entites

interface FlickrImage {
    val id: String?
    val content: Content?
    val author: Author?
    val title: String?
    val category: Category?
    val updated: String?
    val link: List<Link>?
    val published: String?
    val displaycategories: String?
    val imageUrl: String?
}