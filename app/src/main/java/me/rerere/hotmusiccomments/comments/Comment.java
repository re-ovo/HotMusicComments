package me.rerere.hotmusiccomments.comments;

import java.util.Objects;

public class Comment {
    private String content; // 热评内容
    private String user; // 热评作者
    private String music; // 热评歌曲
    private String musicAuthor; // 音乐作者
    private int id; // 歌曲ID
    private int userid; // 用户ID

    public Comment(String content, String user, String music, String musicAuthor, int id, int userid) {
        this.content = content;
        this.user = user;
        this.music = music;
        this.musicAuthor = musicAuthor;
        this.id = id;
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public String getUser() {
        return user;
    }

    public String getMusic() {
        return music;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", user='" + user + '\'' +
                ", music='" + music + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", id=" + id +
                ", userid=" + userid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                userid == comment.userid &&
                Objects.equals(content, comment.content) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(music, comment.music) &&
                Objects.equals(musicAuthor, comment.musicAuthor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content, user, music, musicAuthor, id, userid);
    }
}
