package com.example.liuhaoyuan.simplereader.bean.music;

import com.example.liuhaoyuan.simplereader.bean.RatingBean;

import java.util.List;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class MusicItemBean {
    /**
     * rating : {"max":10,"average":0,"numRaters":8,"min":0}
     * author : [{"name":"月亮"}]
     * alt_title :
     * image : https://img3.doubanio.com/spic/s4547601.jpg
     * tags : [{"count":2,"name":"一般般女声【华语】"},{"count":1,"name":"Dance"},{"count":1,"name":"EP"},{"count":1,"name":"Electronic"},{"count":1,"name":"FemaleVocal"},{"count":1,"name":"Pop"},{"count":1,"name":"华语"},{"count":1,"name":"月亮"}]
     * mobile_link : https://m.douban.com/music/subject/5376737/
     * attrs : {"publisher":["华谊音乐"],"singer":["月亮"],"version":["EP"],"pubdate":["2010-11-12"],"title":["Dancing girl"],"media":["CD"],"tracks":["01Dancing girl\n02爱情来了\n03不做你的谁"],"discs":["1"]}
     * title : Dancing girl
     * alt : https://music.douban.com/subject/5376737/
     * id : 5376737
     */

    public RatingBean rating;
    public String alt_title;
    public String image;
    public String mobile_link;
    public AttrsBean attrs;
    public String title;
    public String alt;
    public String id;
    public List<AuthorBean> author;
    public List<TagsBean> tags;

    public static class AttrsBean {
        public List<String> publisher;
        public List<String> singer;
        public List<String> version;
        public List<String> pubdate;
        public List<String> title;
        public List<String> media;
        public List<String> tracks;
        public List<String> discs;
    }

    public static class AuthorBean {
        /**
         * name : 月亮
         */

        public String name;
    }

    public static class TagsBean {
        /**
         * count : 2
         * name : 一般般女声【华语】
         */

        public int count;
        public String name;
    }
}
