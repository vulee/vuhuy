package vn.dev.karaoke.dto;

public class Song {

    private String id;
    private String vol;
    private String songName;
    private String songLyric;
    private String songAuthor;
    private String singer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
