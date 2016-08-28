package com.atguigu.mycyc.bean;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/27 on 14:18.
 * 作用:
 */
public class CommunityBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"post_id":"2040","user_id":"520029","figure":"http://img01.cycangcdn.com/ugc/post/img/201608/14722217383502873.jpeg","saying":"5毛wifi!","add_time":"1472221739","likes":"2","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"塔塔塔塔塔塔理子","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":[]},{"post_id":"2039","user_id":"545284","figure":"http://img01.cycangcdn.com/ugc/post/img/201608/147221234361569.jpeg","saying":"看到这个好开心，明天就能取啦~","add_time":"1472212367","likes":"0","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"夜吟","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/1470128686047236.jpeg","is_like":"0","comment_list":[]},{"post_id":"2038","user_id":"554283","figure":"http://img01.cycangcdn.com/ugc/post/img/201608/14722079471099610.png","saying":"这是预售东西吗？","add_time":"1472207947","likes":"0","comments":"2","is_hot":"0","is_top":"0","is_essence":"0","username":"段梁修誉","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":["30号发货","是的"]},{"post_id":"2037","user_id":"564787","figure":"http://img01.cycangcdn.com/ugc/post/img/201608/14722072040952001.png","saying":"终于等到了  好开心  比我想象中的要大  不过也是很美腻的\n","add_time":"1472207205","likes":"6","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"Heart is too small°","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14722199796656040.png","is_like":"0","comment_list":[]},{"post_id":"2036","user_id":"563046","figure":"http://img01.cycangcdn.com/ugc/post/img/201608/14722055059662662.jpeg","saying":"胖次到了，，，包装确实挺棒的，但是这快递好慢呢！","add_time":"1472205515","likes":"2","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"君酱","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":[]}]
     */

    private int code;
    private String msg;
    /**
     * post_id : 2040
     * user_id : 520029
     * figure : http://img01.cycangcdn.com/ugc/post/img/201608/14722217383502873.jpeg
     * saying : 5毛wifi!
     * add_time : 1472221739
     * likes : 2
     * comments : 0
     * is_hot : 0
     * is_top : 0
     * is_essence : 0
     * username : 塔塔塔塔塔塔理子
     * avatar : http://s1.cycangcdn.com/img/user_icon.png
     * is_like : 0
     * comment_list : []
     */

    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String post_id;
        private String user_id;
        private String figure;
        private String saying;
        private String add_time;
        private String likes;
        private String comments;
        private String is_hot;
        private String is_top;
        private String is_essence;
        private String username;
        private String avatar;
        private String is_like;
        private List<String> comment_list;

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getSaying() {
            return saying;
        }

        public void setSaying(String saying) {
            this.saying = saying;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getIs_essence() {
            return is_essence;
        }

        public void setIs_essence(String is_essence) {
            this.is_essence = is_essence;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public List<String> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<String> comment_list) {
            this.comment_list = comment_list;
        }
    }
}
