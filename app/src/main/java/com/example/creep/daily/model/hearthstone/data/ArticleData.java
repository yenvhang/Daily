package com.example.creep.daily.model.hearthstone.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by creep on 2016/9/7.
 */

public class ArticleData implements Serializable {


    /**
     * articles : [[25276,"VS外服环境周报第十六期",1473094367,"LookItzJoe",147,49619,"[19:1][12:-1]","http://static.iyingdi.com/common/2016/09/06/c0980565-8c9f-4449-8d9d-6f87184dc9c2.png@410h_720w_1e_1c_1l.jpg","译文",-1,1,3,"",502936,"苍苍苍之霜临"],[25307,"【三苍阿尼基】国服主播卡组日报0906",1473153172,"苍苍苍之霜临",55,27087,"[12:1]","http://static.iyingdi.com/common/2016/09/06/b68ea527-8b58-4f51-8fe6-cb8637efbc7d.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502965,"苍苍苍之霜临"],[25293,"狂欢夜：卡拉赞新思路测评报告（一）",1473144295,"苏孤乡",78,40221,"[12:1]","http://static.iyingdi.com/common/2016/09/06/23627dfd-8ab2-4cac-92b9-aaac50112e82.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502857,"苏孤乡"],[25289,"黄金超级联赛秋季赛64强首日综述",1473139834,"炉石官网",116,24111,"[12:1]","http://static.iyingdi.com/common/2016/09/06/8be43d6c-9212-4de2-9f62-30320713f206.png@410h_720w_1e_1c_1l.jpg","赛事",1,1,3,"",502948,"苍苍苍之霜临"],[25279,"BB互动拾遗：对社区的关注 和竞技场平衡",1473129904,"Bennidge",198,29692,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/06/7d759d95-8210-48c7-b6a7-40337e66b219.jpg@410h_720w_1e_1c_1l.jpg","译文",1,1,3,"",502939,"Bennidge"],[25278,"合理运用运气 巴内斯中速猎",1473127920,"海生HS",159,67862,"[12:1]","http://static.iyingdi.com/common/2016/09/04/35064b25-12d1-49ed-8273-33115a2ba8cd.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502736,"海生HS"],[25268,"如何评价炉石现在的发展",1473087685,"旅法师营地",780,86944,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/05/dd3b95cd-6f02-4f78-baf3-42c46b97798f.jpg@410h_720w_1e_1c_1l.jpg","话题",1,1,3,"",502927,"旅法师营地"],[25266,"【三苍阿尼基】国服主播卡组日报0905",1473081392,"苍苍苍之霜临",105,122491,"[12:1]","http://static.iyingdi.com/common/2016/09/05/cd4ccd05-311d-4885-9494-cd9629540f76.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502915,"苍苍苍之霜临"],[25265,"【企鹅欧尼酱】外服直播卡组日报0905-三套登顶！",1473073252,"企鹅欧尼酱",230,150332,"[12:1]","http://static.iyingdi.com/common/2016/09/05/fefea860-9bf1-4d2c-b1e4-1bc8ce0e4a95.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502924,"企鹅欧尼酱"],[25260,"【天天素材库】谁的巴内斯更炫酷？",1473067337,"天天卡牌",94,94155,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/05/ce21d6d5-188f-492d-8529-89d745e42cd6.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502921,"苍苍苍之霜临"],[25249,"【插眼外服】 出奇制胜 独具匠心的自闭猎",1473056604," Abar",452,141386,"[12:1]","http://static.iyingdi.com/common/2016/09/05/2e7fafad-c40f-4ecc-b441-a40dfcd23482.jpg@410h_720w_1e_1c_1l.jpg","译文",1,1,3,"",502903,"苍苍苍之霜临"],[25240,"【插眼外服】近日流行卡组速递",1473051500,"苍苍苍之霜临",102,134752,"[12:1]","http://static.iyingdi.com/common/2016/09/05/fb3ebd2a-44dc-49d2-a0f6-b02a509ff502.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502881,"苍苍苍之霜临"],[25231,"【看看怎么赢 第6期】巨人奇迹贼",1473045762,"ctz0900",69,46056,"[12:1]","http://static.iyingdi.com/common/2016/09/05/ead2e043-ddf5-4437-a181-6767896451d5.jpg@410h_720w_1e_1c_1l.jpg","视频",1,1,3,"",502882,"ctz0900"],[25228,"双人现开赛第二天 不二、华佗、罗西基、秋日晋级",1473044368,"炉石官网",89,57678,"[12:1]","http://static.iyingdi.com/common/2016/09/05/a45d616b-f046-4c5e-8670-be18c63a6a9c.jpg@410h_720w_1e_1c_1l.jpg","新闻",1,1,3,"",502885,"苍苍苍之霜临"],[25224,"斩杀极高 新版核弹牧",1473042338,"慕玄一",179,116235,"[12:1]","http://static.iyingdi.com/common/2016/09/05/73c8d9d8-aa20-4817-bff6-15f0a4a66257.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502840,"慕玄一"],[25222,"【插眼外服】Lifecoach和Thjis相继登顶",1473006464,"旅法师营地",388,135733,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/05/5823cc70-c073-476d-a7d9-f97be1f7ccc4.jpg@410h_720w_1e_1c_1l.jpg","八卦",1,1,3,"",502878,"旅法师营地"],[25217,"【炉石说】Ep30 安度因的信仰秘典",1472985968,"十方无敌喻小帅",169,79566,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/04/a828a882-ddbe-4af4-8466-980ec19dc789.png@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502867,"十方无敌喻小帅"],[25215,"【企鹅欧尼酱】外服直播卡组日报0904-欧服双登顶",1472981211,"企鹅欧尼酱",277,178501,"[12:1]","http://static.iyingdi.com/common/2016/09/03/2e3c8424-2493-41a3-9887-6e2f24d1c80c.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502851,"企鹅欧尼酱"],[25212,"复活牧使用指南 卡组构筑和对局测试",1472961711,"Stonekeep",349,186095,"[19:1][12:1]","http://static.iyingdi.com/common/2016/09/04/54e6cd36-f3fb-4ef1-bbce-b1de298f0321.jpg@410h_720w_1e_1c_1l.jpg","译文",1,1,3,"",502855,"苍苍苍之霜临"],[25211,"奇迹贼中的新卡取舍 巴内斯和奥术巨人",1472961697,"小年1号",203,84442,"[12:1]","http://static.iyingdi.com/common/2016/09/04/8d754a11-b243-4dff-9a73-88617d5fff60.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502735,"小年1号"],[25210,"【思路研讨】奥秘法尝试",1472961689,"woshihok86",121,82820,"[12:1]","http://static.iyingdi.com/common/2016/09/04/1eb2fd84-a29b-43e1-b95f-8fc6e8d98150.jpg@410h_720w_1e_1c_1l.jpg","原创",1,1,3,"",502598,"woshihok86"]]
     * success : true
     */

    private boolean success;
    private List<List<String>> articles;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<List<String>> getArticles() {
        return articles;
    }

    public void setArticles(List<List<String>> articles) {
        this.articles = articles;
    }

}
