package com.atguigu.mycyc.bean;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/29 on 13:00.
 * 作用:
 */
public class GoodDetailBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : {"product_info":{"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"http://f.p.cycangcdn.com/1457504361484.jpg","assoc_products":false,"sort_order":"0","sale_type":"2","sell_time_start":"1457452800","sell_time_end":"1458057600","origin_price":"250.00","supplier_name":"画影工作室","meiqia_ent_id":"0","tag_data":[],"meiqia_ent_url":"https://static.meiqia.com/dist/standalone.html?eid=17853&amp;metadata=%7B%22%5Cu5546%5Cu54c1%5Cu94fe%5Cu63a5%22%3A%22http%3A%5C%2F%5C%2Fcycang.com%5C%2Findex.php%3Fa%3Dp%26id%3D5181%22%7D","is_fav":0,"detail_url":"http://app.cycang.com/index.php?c=product&amp;a=detail&amp;id=5181","price_gap_string":"商品在紧急上架中","total_price":null},"gallery_info":false,"stock_info":[]}
     */

    private int code;
    private String msg;
    /**
     * product_info : {"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"http://f.p.cycangcdn.com/1457504361484.jpg","assoc_products":false,"sort_order":"0","sale_type":"2","sell_time_start":"1457452800","sell_time_end":"1458057600","origin_price":"250.00","supplier_name":"画影工作室","meiqia_ent_id":"0","tag_data":[],"meiqia_ent_url":"https://static.meiqia.com/dist/standalone.html?eid=17853&amp;metadata=%7B%22%5Cu5546%5Cu54c1%5Cu94fe%5Cu63a5%22%3A%22http%3A%5C%2F%5C%2Fcycang.com%5C%2Findex.php%3Fa%3Dp%26id%3D5181%22%7D","is_fav":0,"detail_url":"http://app.cycang.com/index.php?c=product&amp;a=detail&amp;id=5181","price_gap_string":"商品在紧急上架中","total_price":null}
     * gallery_info : false
     * stock_info : []
     */

    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * product_id : 5181
         * channel_id : 6
         * brand_id : 394
         * p_catalog_id : 10
         * supplier_type : 2
         * supplier_code : 1101037
         * name : 【画影】汉元素 古风日常—— 仲夏
         * cover_price : 250.00
         * brief :
         * figure : http://f.p.cycangcdn.com/1457504361484.jpg
         * assoc_products : false
         * sort_order : 0
         * sale_type : 2
         * sell_time_start : 1457452800
         * sell_time_end : 1458057600
         * origin_price : 250.00
         * supplier_name : 画影工作室
         * meiqia_ent_id : 0
         * tag_data : []
         * meiqia_ent_url : https://static.meiqia.com/dist/standalone.html?eid=17853&amp;metadata=%7B%22%5Cu5546%5Cu54c1%5Cu94fe%5Cu63a5%22%3A%22http%3A%5C%2F%5C%2Fcycang.com%5C%2Findex.php%3Fa%3Dp%26id%3D5181%22%7D
         * is_fav : 0
         * detail_url : http://app.cycang.com/index.php?c=product&amp;a=detail&amp;id=5181
         * price_gap_string : 商品在紧急上架中
         * total_price : null
         */

        private ProductInfoBean product_info;
        private boolean gallery_info;
        private List<?> stock_info;

        public ProductInfoBean getProduct_info() {
            return product_info;
        }

        public void setProduct_info(ProductInfoBean product_info) {
            this.product_info = product_info;
        }

        public boolean isGallery_info() {
            return gallery_info;
        }

        public void setGallery_info(boolean gallery_info) {
            this.gallery_info = gallery_info;
        }

        public List<?> getStock_info() {
            return stock_info;
        }

        public void setStock_info(List<?> stock_info) {
            this.stock_info = stock_info;
        }

        public static class ProductInfoBean {
            private String product_id;
            private String channel_id;
            private String brand_id;
            private String p_catalog_id;
            private String supplier_type;
            private String supplier_code;
            private String name;
            private String cover_price;
            private String brief;
            private String figure;
            private boolean assoc_products;
            private String sort_order;
            private String sale_type;
            private String sell_time_start;
            private String sell_time_end;
            private String origin_price;
            private String supplier_name;
            private String meiqia_ent_id;
            private String meiqia_ent_url;
            private int is_fav;
            private String detail_url;
            private String price_gap_string;
            private Object total_price;
            private List<?> tag_data;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getP_catalog_id() {
                return p_catalog_id;
            }

            public void setP_catalog_id(String p_catalog_id) {
                this.p_catalog_id = p_catalog_id;
            }

            public String getSupplier_type() {
                return supplier_type;
            }

            public void setSupplier_type(String supplier_type) {
                this.supplier_type = supplier_type;
            }

            public String getSupplier_code() {
                return supplier_code;
            }

            public void setSupplier_code(String supplier_code) {
                this.supplier_code = supplier_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public boolean isAssoc_products() {
                return assoc_products;
            }

            public void setAssoc_products(boolean assoc_products) {
                this.assoc_products = assoc_products;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getSale_type() {
                return sale_type;
            }

            public void setSale_type(String sale_type) {
                this.sale_type = sale_type;
            }

            public String getSell_time_start() {
                return sell_time_start;
            }

            public void setSell_time_start(String sell_time_start) {
                this.sell_time_start = sell_time_start;
            }

            public String getSell_time_end() {
                return sell_time_end;
            }

            public void setSell_time_end(String sell_time_end) {
                this.sell_time_end = sell_time_end;
            }

            public String getOrigin_price() {
                return origin_price;
            }

            public void setOrigin_price(String origin_price) {
                this.origin_price = origin_price;
            }

            public String getSupplier_name() {
                return supplier_name;
            }

            public void setSupplier_name(String supplier_name) {
                this.supplier_name = supplier_name;
            }

            public String getMeiqia_ent_id() {
                return meiqia_ent_id;
            }

            public void setMeiqia_ent_id(String meiqia_ent_id) {
                this.meiqia_ent_id = meiqia_ent_id;
            }

            public String getMeiqia_ent_url() {
                return meiqia_ent_url;
            }

            public void setMeiqia_ent_url(String meiqia_ent_url) {
                this.meiqia_ent_url = meiqia_ent_url;
            }

            public int getIs_fav() {
                return is_fav;
            }

            public void setIs_fav(int is_fav) {
                this.is_fav = is_fav;
            }

            public String getDetail_url() {
                return detail_url;
            }

            public void setDetail_url(String detail_url) {
                this.detail_url = detail_url;
            }

            public String getPrice_gap_string() {
                return price_gap_string;
            }

            public void setPrice_gap_string(String price_gap_string) {
                this.price_gap_string = price_gap_string;
            }

            public Object getTotal_price() {
                return total_price;
            }

            public void setTotal_price(Object total_price) {
                this.total_price = total_price;
            }

            public List<?> getTag_data() {
                return tag_data;
            }

            public void setTag_data(List<?> tag_data) {
                this.tag_data = tag_data;
            }
        }
    }
}
