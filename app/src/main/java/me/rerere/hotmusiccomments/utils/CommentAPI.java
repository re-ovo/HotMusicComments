package me.rerere.hotmusiccomments.utils;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;

import me.rerere.hotmusiccomments.comments.Comment;

public class CommentAPI {

    private static final String API = "https://api.4gml.com/NeteaseMusic";

    /**
     * 通过网易云热评API获取热评
     *
     * @return 热评对象
     */
    public static Comment getHotComment(int min, int max) {
        String data = HttpUtil.connect(API); // 通过一言提供的网易云API获取Json数据
        Gson gson = new Gson(); // 实例化Gson
        Map map = gson.fromJson(data, Map.class); // 解析Json数据到MAP
        /** 开始读取Json内容 */
        String content = (String) map.get("content"); // 热评内容
        int songID = ((Double) map.get("songid")).intValue(); // 歌曲ID
        String song = (String) map.get("name"); // 歌曲名字
        String songAuthor = (String) map.get("songname"); // 歌曲作者
        int userid = ((Double) map.get("userid")).intValue(); // 用户ID
        String userName = (String) map.get("username"); // 用户名字
        return new Comment(content, userName, song, songAuthor, songID, userid); //创建对象并且返回
    }
}
