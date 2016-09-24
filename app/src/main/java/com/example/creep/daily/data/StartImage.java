package com.example.creep.daily.data;

import java.io.Serializable;

/**
 * Created by creep on 2016/9/19.
 */

public class StartImage implements Serializable {

    /**
     * text : Noah Silliman
     * img : https://pic4.zhimg.com/v2-b427763c3d75885c041d4de069923e93.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
