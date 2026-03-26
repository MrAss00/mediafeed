package com.example.mediafeed

object SampleData {
    val images = listOf(
        ImagePost("img01", "Name_1", R.drawable.img_01),
        ImagePost("img02", "Name_2", R.drawable.img_02),
        ImagePost("img03", "Name_3", R.drawable.img_03),
        ImagePost("img04", "Name_4", R.drawable.img_04),
        ImagePost("img05", "Name_5", R.drawable.img_05),
        ImagePost("img06", "Name_6", R.drawable.img_06),
        ImagePost("img07", "Name_7", R.drawable.img_07),
        ImagePost("img08", "Name_8", R.drawable.img_08),
        ImagePost("img09", "Name_9", R.drawable.img_09),
        ImagePost("img10", "Name_10", R.drawable.img_10),
    )

    val videos = listOf(
        VideoPost("vid01", "Video_1", R.raw.vid_01),
        VideoPost("vid02", "Video_2", R.raw.vid_02),
        VideoPost("vid03", "Video_3", R.raw.vid_03),
        VideoPost("vid04", "Video_4", R.raw.vid_04),
        VideoPost("vid05", "Video_5", R.raw.vid_05),
    )
}