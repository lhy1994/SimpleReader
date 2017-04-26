package com.example.liuhaoyuan.simplereader.bean;

import java.util.List;

/**
 * Created by liuhaoyuan on 2017/4/25.
 */

public class MovieHumanDetailBean {

    /**
     * mobile_url : https://movie.douban.com/celebrity/1047973/mobile
     * aka_en : ["Frank A. Darabont"]
     * name : 弗兰克·德拉邦特
     * works :
     * gender : 男
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"}
     * id : 1047973
     * aka : ["弗兰克·达拉邦特"]
     * name_en : Frank Darabont
     * born_place : 法国,杜省,蒙贝利亚尔
     * alt : https://movie.douban.com/celebrity/1047973/
     */

    public String mobile_url;
    public String name;
    public String gender;
    public ImagesBean avatars;
    public String id;
    public String name_en;
    public String born_place;
    public String alt;
    public List<String> aka_en;
    public List<WorksBean> works;
    public List<String> aka;

    public static class WorksBean {
        /**
         * roles : ["导演","编剧"]
         * subject : {"rating":{"max":10,"average":9.6,"stars":"50","min":0},"genres":["犯罪","剧情"],"title":"肖申克的救赎","casts":[{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}],"collect_count":1055021,"original_title":"The Shawshank Redemption","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}],"year":"1994","images":{"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg"},"alt":"https://movie.douban.com/subject/1292052/","id":"1292052"}
         */
        public MovieItemBean subject;
        public List<String> roles;
    }
}
