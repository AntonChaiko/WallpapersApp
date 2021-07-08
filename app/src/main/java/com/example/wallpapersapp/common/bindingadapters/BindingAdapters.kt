package com.example.wallpapersapp.common.bindingadapters

//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}
//
//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView,
//                     data: List<Results>?) {
//    val adapter = recyclerView.adapter as ImageGridAdapter
//    adapter.submitList(data)
//
//}