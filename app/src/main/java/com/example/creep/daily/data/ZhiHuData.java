package com.example.creep.daily.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by creep on 2016/9/18.
 */

public class ZhiHuData implements Serializable{

    /**
     * date : 20160918
     * stories : [{"images":["http://pic2.zhimg.com/ad8cf9dd4d7ec431115e36a8931cf729.jpg"],"type":0,"id":8805113,"ga_prefix":"091815","title":"为什么我们上得了天，却很难进入深空探索？"},{"images":["http://pic2.zhimg.com/6fa8bb7721a8105285a5333198c78589.jpg"],"type":0,"id":8805288,"ga_prefix":"091814","title":"财务工作干得久了，喜欢在商场里数人，开车用两个导航仪"},{"images":["http://pic4.zhimg.com/2c50a6c936953d41cb0b165090b3d9b3.jpg"],"type":0,"id":8803915,"ga_prefix":"091813","title":"iPhone 7 拍的照片有点怪？因为苹果用了一种特殊的色域"},{"images":["http://pic2.zhimg.com/98675c9430a4828fcac78cf3ed7d183d.jpg"],"type":0,"id":8804570,"ga_prefix":"091812","title":"大误 · 宝玉和她的十二兄弟"},{"images":["http://pic3.zhimg.com/44879ab6961fa8885560d8acb401fa2e.jpg"],"type":0,"id":8803295,"ga_prefix":"091811","title":"按下电源键之后，电脑又默默干了很多事"},{"title":"看到这拱形与圆顶，就知道是伊斯兰风格的建筑","ga_prefix":"091810","images":["http://pic4.zhimg.com/e2050ceb2ef38531d9297fee0ffefb37.jpg"],"multipic":true,"type":0,"id":8803970},{"images":["http://pic2.zhimg.com/ff9e4dc5435d16a2a69612b0b3567619.jpg"],"type":0,"id":8803804,"ga_prefix":"091809","title":"问「如何提高人际交往能力」的人，可能从一开始就错了"},{"title":"F1 的赛道绕来绕去，因为简单的弯道实在太危险","ga_prefix":"091808","images":["http://pic1.zhimg.com/c1403664d393c1c3db67ee743ddaba7c.jpg"],"multipic":true,"type":0,"id":8803175},{"images":["http://pic1.zhimg.com/03dc215df17db87a287fff030784c9f0.jpg"],"type":0,"id":8804007,"ga_prefix":"091807","title":"父母要求孩子做某件事时，「我数三下」的方法为何十分有效？"},{"title":"与你们人类不同，机器人光是双脚走路就拼尽全力了","ga_prefix":"091807","images":["http://pic4.zhimg.com/fb19d10778aedc72d36dd5df9bd690bf.jpg"],"multipic":true,"type":0,"id":8803969},{"images":["http://pic3.zhimg.com/ea86790013aa5a7c7b1b9bbf34bb6562.jpg"],"type":0,"id":8802347,"ga_prefix":"091807","title":"不要把什么都算作「资本的阴谋」，比如经济全球化"},{"images":["http://pic2.zhimg.com/e72bfcffd19c3ba575e6676f11fd9785.jpg"],"type":0,"id":8803805,"ga_prefix":"091807","title":"读读日报 24 小时热门 TOP 5 · 他们都曾饱受抑郁症困扰"},{"images":["http://pic3.zhimg.com/b439bb7232d3e7a48d39bab7184b18ca.jpg"],"type":0,"id":8801444,"ga_prefix":"091806","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic2.zhimg.com/3e648354906d9de2f1a5c0c66bee7c3d.jpg"],"type":0,"id":8794939,"ga_prefix":"091806","title":"这里是广告 · 乘坐自动驾驶的车辆是怎样一种体验？"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/67e60aa29107a01c5ab72c7b633b08e2.jpg","type":0,"id":8803915,"ga_prefix":"091813","title":"iPhone 7 拍的照片有点怪？因为苹果用了一种特殊的色域"},{"image":"http://pic1.zhimg.com/60f2589fa3f3d688756be9ef8fe8756c.jpg","type":0,"id":8803295,"ga_prefix":"091811","title":"按下电源键之后，电脑又默默干了很多事"},{"image":"http://pic4.zhimg.com/0b7bce8de1d32efd868ffce966a9c9c3.jpg","type":0,"id":8803804,"ga_prefix":"091809","title":"问「如何提高人际交往能力」的人，可能从一开始就错了"},{"image":"http://pic4.zhimg.com/c745fed07fb56697652029a71f846f5b.jpg","type":0,"id":8804007,"ga_prefix":"091807","title":"父母要求孩子做某件事时，「我数三下」的方法为何十分有效？"},{"image":"http://pic2.zhimg.com/429b9271a759e713372e74a316ad74b9.jpg","type":0,"id":8803805,"ga_prefix":"091807","title":"读读日报 24 小时热门 TOP 5 · 他们都曾饱受抑郁症困扰"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/ad8cf9dd4d7ec431115e36a8931cf729.jpg"]
     * type : 0
     * id : 8805113
     * ga_prefix : 091815
     * title : 为什么我们上得了天，却很难进入深空探索？
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic3.zhimg.com/67e60aa29107a01c5ab72c7b633b08e2.jpg
     * type : 0
     * id : 8803915
     * ga_prefix : 091813
     * title : iPhone 7 拍的照片有点怪？因为苹果用了一种特殊的色域
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
