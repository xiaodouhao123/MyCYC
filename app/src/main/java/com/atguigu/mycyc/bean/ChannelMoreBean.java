package com.atguigu.mycyc.bean;

import java.util.List;

/**
 * Created by 殴打小熊猫
 * on 2016/8/28 on 16:25.
 * 作用：
 */
public class ChannelMoreBean {


    /**
     * code : 200
     * msg : 请求成功
     * result : [{"option":2,"type":1,"channel_name":"服饰仓","image":"http://s1.cycangcdn.com/app/img/menu-cyc.png","value":{"channel_id":"8"}},{"option":2,"type":1,"channel_name":"游戏仓","image":"http://s1.cycangcdn.com/app/img/menu-game.png","value":{"channel_id":"4"}},{"option":2,"type":1,"channel_name":"动漫仓","image":"http://s1.cycangcdn.com/app/img/menu-carttoon.png","value":{"channel_id":"3"}},{"option":2,"type":1,"channel_name":"cosplay","image":"http://s1.cycangcdn.com/app/img/menu-cosplay.png","value":{"channel_id":"5"}},{"option":2,"type":1,"channel_name":"古风仓","image":"http://s1.cycangcdn.com/app/img/menu-oldage.png","value":{"channel_id":"6"}},{"option":2,"type":1,"channel_name":"漫展票务","image":"http://s1.cycangcdn.com/app/img/menu-collect.png","value":{"channel_id":"9"}},{"option":2,"type":1,"channel_name":"文具仓","image":"http://s1.cycangcdn.com/app/img/menu-stationery.png","value":{"channel_id":"11"}},{"option":2,"type":1,"channel_name":"零食仓","image":"http://s1.cycangcdn.com/app/img/menu-snack.png","value":{"channel_id":"10"}},{"option":2,"type":1,"channel_name":"首饰仓","image":"http://s1.cycangcdn.com/app/img/menu-jewelry.png","value":{"channel_id":"12"}},{"option":2,"type":1,"channel_name":"手办仓","image":"http://s1.cycangcdn.com/app/img/menu-model.png","value":{"channel_id":"13"}}]
     */

    private int code;
    private String msg;
    /**
     * option : 2
     * type : 1
     * channel_name : 服饰仓
     * image : http://s1.cycangcdn.com/app/img/menu-cyc.png
     * value : {"channel_id":"8"}
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
        private int option;
        private int type;
        private String channel_name;
        private String image;
        /**
         * channel_id : 8
         */

        private ValueBean value;

        public int getOption() {
            return option;
        }

        public void setOption(int option) {
            this.option = option;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getChannel_name() {
            return channel_name;
        }

        public void setChannel_name(String channel_name) {
            this.channel_name = channel_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public ValueBean getValue() {
            return value;
        }

        public void setValue(ValueBean value) {
            this.value = value;
        }

        public static class ValueBean {
            private String channel_id;

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }
        }
    }
}
