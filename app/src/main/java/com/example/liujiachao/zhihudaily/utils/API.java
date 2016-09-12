package com.example.liujiachao.zhihudaily.utils;

/**
 * Created by liujiachao on 2016/7/29.
 */
public class API {


   /**
    * 知乎每日消息列表的Json数据，以下是2016年8月2号的情况
    * http://news-at.zhihu.com/api/4/stories/before/20160803，虽然是8月3号,但是该接口取出的内容显示的是8月2号的消息
   * {"date":"20160802","stories":[{"images":["http:\/\/pic4.zhimg.com\/3078e8e89d118ddbfbaa4c3cca7d51d3.jpg"],"type":0,"id":8640277,"ga_prefix":"080222","title":"小事 · 骗子也在进步"},{"images":["http:\/\/pic1.zhimg.com\/8df70e7c790badecf7a2fbbca17d973c.jpg"],"type":0,"id":8640113,"ga_prefix":"080221","title":"这部电影是一头怪物"},{"images":["http:\/\/pic1.zhimg.com\/c598f9d164023c3f9a4bfbbabeec94a0.jpg"],"type":0,"id":8639944,"ga_prefix":"080219","title":"想让用户快速增长，数据分析很有用，但一定不是万能的"},{"title":"如何系统地学习毛笔书法？（多图）","ga_prefix":"080218","images":["http:\/\/pic4.zhimg.com\/01a46ce8f916bef8c48d087c21fd6ebb.jpg"],"multipic":true,"type":0,"id":8639629},{"images":["http:\/\/pic3.zhimg.com\/272fc3ff3acc6ac13f7cc4b5a18fb722.jpg"],"type":0,"id":8635759,"ga_prefix":"080217","title":"知乎好问题 · 跟别人聊天没有话题，怎么办？"},{"title":"怎么鉴别假钱，应该不会有比这更权威的答案了","ga_prefix":"080216","images":["http:\/\/pic4.zhimg.com\/c291ecc1ade6c7e8dc4e837cdbdbe5f7.jpg"],"multipic":true,"type":0,"id":8639755},{"images":["http:\/\/pic2.zhimg.com\/fa026f9fb1fa0a1d8981e1041af34d99.jpg"],"type":0,"id":8634940,"ga_prefix":"080215","title":"「病人、来访者、消费者」，你觉得哪个称呼更合适？"},{"images":["http:\/\/pic3.zhimg.com\/7362cec70d515f6fff35c26a3731cdf6.jpg"],"type":0,"id":8635432,"ga_prefix":"080214","title":"卖版权的盖蒂图片社犯了一个错，代价是 10 亿美元"},{"images":["http:\/\/pic3.zhimg.com\/ff1850a48538580f68973cdd9ff0cdc6.jpg"],"type":0,"id":8635129,"ga_prefix":"080213","title":"在创业公司待久了的你，工作还开心吗？"},{"images":["http:\/\/pic3.zhimg.com\/c20550ac061a1b008ea640e98435a9f2.jpg"],"type":0,"id":8638583,"ga_prefix":"080212","title":"大误 · 单恋女神和我被困在电梯里"},{"title":"出了事故之后，老司机表示，我是真的没看到啊","ga_prefix":"080211","images":["http:\/\/pic2.zhimg.com\/145a59c90cf217335c0e1e04880ba195.jpg"],"multipic":true,"type":0,"id":8637039},{"images":["http:\/\/pic4.zhimg.com\/04bf664188b13700cf7eae367c5d0d6b.jpg"],"type":0,"id":8636755,"ga_prefix":"080210","title":"从大战到并购，灰色到合法，打车软件这一路是怎么走来的"},{"images":["http:\/\/pic3.zhimg.com\/752c99a86142d95496e5198fe26be4fe.jpg"],"type":0,"id":8639267,"ga_prefix":"080209","title":"商务部：「等等，还没跟我申报，怎么就宣布合并了」"},{"images":["http:\/\/pic4.zhimg.com\/405365ad77791df8cdcca6604712ae4f.jpg"],"type":0,"id":8631053,"ga_prefix":"080209","title":"为什么中国的 GDP 平减指数和消费者物价指数相差很大？"},{"images":["http:\/\/pic1.zhimg.com\/9e78fb8d9e2fbcce3b0a9a2c4311002c.jpg"],"type":0,"id":8637688,"ga_prefix":"080208","title":"同样是阅读，「刷」和「搜索」，差异巨大"},{"images":["http:\/\/pic1.zhimg.com\/89a09d6b9ff1f0adb33a0f4c656769e8.jpg"],"type":0,"id":8637621,"ga_prefix":"080207","title":"那些以为滴滴不会与 Uber 合并的人，还是低估了资本的力量"},{"images":["http:\/\/pic2.zhimg.com\/ee4517b834b8a51cf9ae37c2e82c7571.jpg"],"type":0,"id":8623801,"ga_prefix":"080207","title":"天空为什么是蓝的？熟悉的「散射」并不是问题的全部"},{"images":["http:\/\/pic1.zhimg.com\/b0edbcf5b02e81dfb93750323feccd48.jpg"],"type":0,"id":8629958,"ga_prefix":"080207","title":"小时候，我真的以为这是外星人在地球修建的基地"},{"images":["http:\/\/pic4.zhimg.com\/98867ac6733e0bcf3787f98ebde1e267.jpg"],"type":0,"id":8637343,"ga_prefix":"080207","title":"读读日报 24 小时热门 TOP 5 · 卧底「教你连接宇宙能量」的心灵培训班"},{"images":["http:\/\/pic1.zhimg.com\/11507461011f7f666ba40af0c61a76b8.jpg"],"type":0,"id":8632997,"ga_prefix":"080206","title":"瞎扯 · 如何正确地吐槽"}]}
   */

    /**
     * http://news-at.zhihu.com/api/4/story/8640098
     * 知乎文章内容
     * {"body":"<div class=\"main-wrap content-wrap\">\n<div class=\"headline\">\n\n<div class=\"img-place-holder\"><\/div>\n\n\n\n<div class=\"headline-background\">\n<a class=\"headline-background-link\" href=\"http:\/\/m.jiemian.com\/article\/776138.html\">\n<div class=\"heading\">相关新闻<\/div>\n<div class=\"heading-content\">曾经千呼万唤的《魔兽世界》月卡终于来了 但是玩家们却炸了<\/div>\n<i class=\"icon-arrow-right\"><\/i>\n<\/a>\n<\/div>\n\n<\/div>\n\n<div class=\"content-inner\">\n\n\n\n<div class=\"question\">\n<h2 class=\"question-title\">魔兽收费制度改革——药丸的开端<\/h2>\n<div class=\"answer\">\n\n<div class=\"meta\">\n<img class=\"avatar\" src=\"http:\/\/pic3.zhimg.com\/b9f92e33fe99b2178af2e9f85eb40ba6_is.jpg\">\n<span class=\"author\">苍月小心，<\/span><span class=\"bio\">游戏数值策划\/经济学爱好者\/略懂英语<\/span>\n<\/div>\n\n<div class=\"content\">\n<p>我想从专业一点的角度来解读一下这次魔兽的收费改革。<\/p>\r\n<p>开门见山直接下结论，如果毫不犹豫地按时推行月卡制度，那么官方的收入会只减不增，而 CWOW 则会大踏步走向药丸的节奏。<\/p>\r\n<p>1、高端核心玩家的利益并不像很多人说的那样受益<\/p>\r\n<p>月卡制改革伤害最大的群体是休闲玩家吗？不是，是多号玩家。维持一个账号运作一年需要 720 元，而如果有 4 个账号呢？将近 3000 元。是的，虽然给了转的机会，但是要注意到的是，很多人的账号是未必能转的，很可能出自不同的身份证，因为或多或少在漫长的 WOW 旅途里，我们会接手无数朋友们离去时的馈赠，这些号可能仍然在艾泽拉斯奋斗着，比如我手上就自少有 5 个账号不是自己身份证。其二，即使都转成了同一个账号，丧失了多开的功能对于公会活动也是很大的伤害，很多高端玩家的角色之间是互补的，都是公会开荒的候补成员，这一来等同于公会开荒的局限性会大大增加。<\/p>\r\n<p>其次，核心玩家即使一个号，买月卡也多半是亏的，我刚才打开了我的猎人小号，这个号于 2011 年 10 月建立，到 2012 年 11 月不玩了。显示总游戏时间为 30 天，当前等级 90 时间为 4 天。练级用了一张卡多点，就算 3 天，那么在 85 级游戏的时间大约为 23 天，一共刚好是一年，平均每天是 1.5 小时。这个小号在 85 级是主玩账号，练满级后参与 H 火源开荒，并在 4.2 打通，4.3 开荒 HDS 过程中因为分配问题退会，通过 NGA 招募找到了一个老牌强力公会，当时因为人员问题招募，进入后全程主力打 HDS，在 4 月初通的 25HDS。这个公会 4.3 成绩不是很好，但是其他版本基本包揽服务器 FD，mop 时期在国服排名前 30。<\/p>\r\n<p>要知道，核心 PVE 玩家，也就是在开荒期上线时间较长，比如我那个号主要开荒期有大约 5 个月，这样平均下来，每天大约 3 小时的样子（毕竟开荒团队也有周末休息）。而开荒结束的大把时间，月卡基本是血亏的，一周活动了不起 4~5 小时打完，一个月上线 30~40 小时足够了。<\/p>\r\n<p>事实上，今天早上我看到公会群里都开始慌着冲点卡了，用实际行动表示对月卡制的不认可了。<\/p>\r\n<p>而更普通一点的玩家，打普通难度甚至随机难度的，消费的时间必然更少。<\/p>\r\n<p>而以上两部分玩家则占据了玩家群体的绝大多数。<\/p>\r\n<p>只有极其稀少的顶尖冲进度团队和开金团的才可能在月卡制度下受益。<\/p>\r\n<p>2、会有大批公会倒闭<\/p>\r\n<p>新政一出，必然大批老玩家流失，很多中小公会勉强维持的都将无以为继，想招人也不可能，因为各个公会都存在这个问题，自身的小号储备又受到冲击，进一步加剧了这个问题。<\/p>\r\n<p>3、工作室也未必受益<\/p>\r\n<p>工作室主要是通过提供各种服务来牟利的，分为代打、金团和资源类。<\/p>\r\n<p>代打和金团业务必然陡降，因为这些活动的金主都是休闲玩家，休闲玩家基本死绝了，生意还怎么做？资源类的金主是冲进度的公会，公会倒闭必然也有影响，而且资源类的收获成果是金币，金币还是需要通过休闲玩家来购买变现，所以这条路也死了。<\/p>\r\n<p>休闲玩家自然不必说了，本来只是轻度游戏，成本被极度提高了，肯定是被大面积扫荡了。<\/p>\r\n<p>所以说，从这样的角度来看，怎么可能收入提高？我看是吃枣药丸！<\/p>\r\n<p>最后只能是一声叹息，打败 WOW 的只能是 WOW 自己，这一次，我们也许不能再拯救满目疮痍的艾泽拉斯了。<\/p>\r\n<p>本想偶尔上去看看熟悉的那个世界，在曾经战斗过的地方流连怀旧。<\/p>\r\n<p>本想在新版本开始的时候上线，看着灰了很久的朋友们一个个亮起来，品味新版本的一切。<\/p>\r\n<p>本想带着那些离去朋友的帐号一起奋斗，见证每一个艾泽拉斯的新故事。<\/p>\r\n<p>没想到，期望月卡的时候，你只给我点卡，只需要点卡的时候，你强行月卡了。<\/p>\r\n<p>我的 8 个角色分布在 5 个不同的帐号里，难道我每年要花费近 4 千元用于游戏吗？Sorry，I can't.<\/p>\r\n<p>会不会是我们魔兽之旅的终点呢？莫名心酸。<\/p>\r\n<p>这一次，老东家，你让我失望了。<\/p>\r\n<p>也让全体魔兽玩家失望了，六年前的雷霆崖顶上，看你妹高呼，我们是魔兽玩家！无数 WOWER 高举双手流下了泪水。<\/p>\r\n<p>今天，无数魔兽玩家到了频临被迫离开艾泽拉斯的时刻了，我们能继续呼喊吗？<\/p>\n\n<div class=\"view-more\"><a href=\"http:\/\/zhuanlan.zhihu.com\/p\/21814133\">查看知乎讨论<\/a><\/div>\n\n<\/div>\n<\/div>\n<\/div>\n\n\n<\/div>\n<\/div>","image_source":"知乎用户","title":"行内人来告诉你，为什么魔兽玩家炸了？","image":"http:\/\/pic3.zhimg.com\/d41a939d381193f5efdec9b58f559cce.jpg","share_url":"http:\/\/daily.zhihu.com\/story\/8640098","js":[],"ga_prefix":"080309","images":["http:\/\/pic1.zhimg.com\/dab27ccddfd4b45ea97e837270b20200.jpg"],"type":0,"id":8640098,"css":["http:\/\/news-at.zhihu.com\/css\/news_qa.auto.css?v=4b3e3"]}
     */

    /**
     * http://news-at.zhihu.com/api/4/stories/latest，这个接口返回的数据是当天最新的消息列表
     * {"date":"20160803","stories":[{"images":["http:\/\/pic4.zhimg.com\/fc0d1d108510cfbba1760d39df6edbd3.jpg"],"type":0,"id":8642339,"ga_prefix":"080316","title":"- 哇，最近食欲不好我瘦了好多诶\r\n- 嗯……我们还是去看看病吧"},{"images":["http:\/\/pic3.zhimg.com\/809c5fb707f71c3298cff319ac44801e.jpg"],"type":0,"id":8643679,"ga_prefix":"080315","title":"赚钱赚翻天的 Facebook 广告系统和谷歌区别在哪？"},{"images":["http:\/\/pic2.zhimg.com\/b9b116a93cf3ddb2f2c1dcf4b3435e65.jpg"],"type":0,"id":8643341,"ga_prefix":"080314","title":"欧洲都这么发达了，怎么人均收入还比美国低"},{"title":"要打造一个美家，照明的设置就相当于「化妆品」","ga_prefix":"080313","images":["http:\/\/pic1.zhimg.com\/6d5cf7f1628a031f948c150443b48450.jpg"],"multipic":true,"type":0,"id":8643228},{"images":["http:\/\/pic2.zhimg.com\/9c8008102220914774ac89a70ce543c5.jpg"],"type":0,"id":8640262,"ga_prefix":"080312","title":"大误 · 万磁王和 X 教授终于吻在了一起"},{"images":["http:\/\/pic4.zhimg.com\/21533a428e1501723119f17779673c93.jpg"],"type":0,"id":8635891,"ga_prefix":"080311","title":"电影里那些让人心惊肉跳的镜头是怎么拍的？看完这两个例子我懂了"},{"images":["http:\/\/pic1.zhimg.com\/af124f90e7fcd28cf9e5f9b48df71fb4.jpg"],"type":0,"id":8641547,"ga_prefix":"080310","title":"滴滴优步成了一家子，我们拿出《反垄断法》来逐条分析一下"},{"images":["http:\/\/pic1.zhimg.com\/dab27ccddfd4b45ea97e837270b20200.jpg"],"type":0,"id":8640098,"ga_prefix":"080309","title":"行内人来告诉你，为什么魔兽玩家炸了？"},{"title":"你想去火葬场参观吗？如果是这一座，有点想","ga_prefix":"080308","images":["http:\/\/pic1.zhimg.com\/4ad67030bec318818f319a8ddc28abc8.jpg"],"multipic":true,"type":0,"id":8632986},{"images":["http:\/\/pic2.zhimg.com\/b7fa0990d3dd31a94de7763274180eb1.jpg"],"type":0,"id":8641525,"ga_prefix":"080308","title":"VR 实现 120 帧刷新，索尼这回又搞出黑科技了吗？"},{"images":["http:\/\/pic4.zhimg.com\/b5450cdac337c8132950141bd8f6f1af.jpg"],"type":0,"id":8634602,"ga_prefix":"080307","title":"竞争激烈，技术含量高，还得了解法律和风险，高利贷真不好做"},{"images":["http:\/\/pic2.zhimg.com\/8fcfb3efb6ce1f0e3e6a8ae32434c819.jpg"],"type":0,"id":8638859,"ga_prefix":"080307","title":"滴滴说「收购」，Uber 说「合并」，来捋一捋这次交易的股权"},{"images":["http:\/\/pic3.zhimg.com\/43a4e283ff1b1ca65d49f9a487ec4c56.jpg"],"type":0,"id":8641734,"ga_prefix":"080307","title":"读读日报 24 小时热门 TOP 5 · 东北人都这么贼拉拉的喜欢它"},{"images":["http:\/\/pic3.zhimg.com\/dd9e805ea3ac1d9bc8331342d7470486.jpg"],"type":0,"id":8637363,"ga_prefix":"080306","title":"瞎扯 · 如何正确地吐槽"}],"top_stories":[{"image":"http:\/\/pic2.zhimg.com\/a62f9985cae17fe535a99901db18eba9.jpg","type":0,"id":8643341,"ga_prefix":"080314","title":"欧洲都这么发达了，怎么人均收入还比美国低"},{"image":"http:\/\/pic3.zhimg.com\/0c1b448f79d7b61d692a5249668ec70e.jpg","type":0,"id":8641734,"ga_prefix":"080307","title":"读读日报 24 小时热门 TOP 5 · 东北人都这么贼拉拉的喜欢它"},{"image":"http:\/\/pic3.zhimg.com\/d41a939d381193f5efdec9b58f559cce.jpg","type":0,"id":8640098,"ga_prefix":"080309","title":"行内人来告诉你，为什么魔兽玩家炸了？"},{"image":"http:\/\/pic3.zhimg.com\/acfb89e0119c8bf6ccc0fc0874c78aee.jpg","type":0,"id":8638859,"ga_prefix":"080307","title":"滴滴说「收购」，Uber 说「合并」，来捋一捋这次交易的股权"},{"image":"http:\/\/pic2.zhimg.com\/ab4a7e672deab68ee46d5901c31e0dc1.jpg","type":0,"id":8639944,"ga_prefix":"080219","title":"想让用户快速增长，数据分析很有用，但一定不是万能的"}]}
     */

    // request type, latest stands for getting newly information
    public static final int TYPE_LATEST = 0;
    public static final int TYPE_BEFORE = 1;

    //request tag, added to cancel that request conveniently (to avoid bugs).
    public static final Object TAG_ZHIHU = "zhihu";
    public static final String TAG_SPLASH = "splash";
    //Zhihu API
    public static final String NEWS_EXTRA = "http://news-at.zhihu.com/api/4/story-extra/";
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";
    public static final String NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static final String NEWS_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";
    public static final String SPLASH = "http://news-at.zhihu.com/api/4/start-image/1080*1920";
    public static final String DAILY_THEMES = "http://news-at.zhihu.com/api/4/themes";
    public static final String THEME_CONTENT = "http://news-at.zhihu.com/api/4/theme/";
    public static final String STORY_LONG_COMMENTS = "http://news-at.zhihu.com/api/4/story/%1$d/long-comments";
    public static final String STORY_SHORT_COMMENTS = "http://news-at.zhihu.com/api/4/story/%1$d/short-comments";

// {
// "comments": [
// {
//  "author": "Galel",
//         "content": "讲真我非常反对你这种为枪文洗地的做法，以及你通过滥用人们心中的言论和认知自由来掩盖将此篇粗陋与偏颇之文放在日报头条会产生恶劣影响的思维方式。\n\n两个不同的东西放在那，差异是客观的，除了抹黑其中一方，还可以选择无视差异只谈一方或者将两者客观比较。讽刺的是，您是如何通过“从不比较两者的好坏”就能“只发觉两者的优点以应用在更加合适的场景？”发现更优选择难道不正是比较两者在同一场景下使用的结果？为了让更多的人达到你的境界，我们需要的是通过陈列细节，客观分析得出的的悉心指导，而非一篇矫揉造作，混淆视听的蓄意误导。更加讽刺可怕的是，你却将此误导视为言论自由，以此来剥夺人们心中真正的认知和言论自由。自信可嘉。\n\n随着知乎社区的扩张，各路网络神棍，软文写手，虚伪大神带着为己牟利的动机涌入，诈骗，抹黑时有发生，已经逐渐却严重破坏了知乎社区核心用户价值。文章质量下降已经是懒的辩驳的现实。（可悲的是，还总有不明真相者为之洗地。）小编作为内容推送最后的把关者，选文若不能做到“见解深刻，逻辑完备，客观真实”，那也应该做到起码的“客观真实”，避免过度向话题性和娱乐性妥协。推送这种后来引起巨大争议的水文早就不是第一次。小编难辞其咎啊。",
//         "avatar": "http://pic1.zhimg.com/916845535289c6bcf62c730caf3aacbc_im.jpg",
//         "time": 1473102487,
//         "reply_to": {
//  "content": "答主轻描淡写的几个不足点，就被你扣上各种黑苹果的帽子，同样有两台设备的我从不会比较两个系统的好坏，我只发掘两者不同的优点好应用在更加合适的使用场景。\n\n\n每个人的使用习惯都不同，也肯定每人对不同设备有不同的见解，大家都有发言权，所有人也有认同其观点的自由。小编的工资请照常发。",
//          "status": 0,
//          "id": 26436672,
//          "author": "骗骗骗骗骗骗骗骗骗骗骗子"
// },
//  "id": 26462185,
//         "likes": 3
// },
// {
//  "author": "那些年疯狂脑补的日子",
//         "content": "osx 有osx的好，win有win的好，不过……个人习惯，加上长久以往的接触，还是对win10很满意的，偶尔有点小兼容，无伤大雅，不过最要命的，全单位的外网现在都自动升了10，那天脑抽擦自己Microsoft账号登录上去了，注销不了了…这样强行绑架…哪天离职咋办啊，又不能格了，略感烦躁(•̀へ •́ ╮ )",
//         "avatar": "http://pic1.zhimg.com/768473c90d29d30b30a7519e128cdc74_im.jpg",
//         "time": 1473099412,
//         "id": 26462078,
//         "likes": 0
// },
// {
//  "author": "沉人启示",
//         "content": "对啊手势功能win8是有，你知道OS X在啥时候就有了么？毫无疑问win每一代都有更好的改变，但其实很多新改变都是借鉴回来的。当然互相借鉴是件好事，好的产品都是这样来的OS X 也有借鉴win的地方。我只是觉这篇文的倾向性太明显了，太过避重就轻。",
//         "avatar": "http://pic1.zhimg.com/4f3ec4439b33d7202604b3dcb26b818c_im.jpg",
//         "time": 1473070510,
//         "reply_to": {
//  "content": "这些手势操作win 8开始就已经支持了",
//          "status": 0,
//          "id": 26446891,
//          "author": "Harry Lee"
// },
//  "id": 26456499,
//         "likes": 1
// },
// {
//  "author": "沉人启示",
//         "content": "三指横推直接切换任务，三指上推快速任务栏鼠标直选任务，屏幕四角自定义功能，全任务直铺桌面，很多方式都可以实现，只是个人使用习惯而已，用得多就不会不记得。mac切换任务其实不需要cmd+tab,不过用惯win会习惯win+tab,alt+tab所以很多人到OS X也沿用了下来。",
//         "avatar": "http://pic1.zhimg.com/4f3ec4439b33d7202604b3dcb26b818c_im.jpg",
//         "time": 1473070111,
//         "reply_to": {
//  "content": "mac深度用户，互联网行业，最近7、8年工作和生活只用mac，毫无疑问觉得还是windows好用。mac最让我崩溃的就是command+tab很多时候调不出窗口。mac的很多设定噱头大于实用，windows正相反，实用大于噱头（我说的就是多指触摸那些操作，除了三指拖动窗口以外其他都想不起来用）",
//          "status": 0,
//          "id": 26447743,
//          "author": "何亚虎"
// },
//  "id": 26456358,
//         "likes": 0
// },
// {
//  "author": "沉人启示",
//         "content": "没有喷，作为一个实用主义用户，本身并没有特别偏爱哪一个系统，只是需要用哪些软件就用哪个系统，也用过很多代，两边每代加入的新元素都了解。只是觉得本文就避重就轻，欠公平客观。你可以对比一下win10的所谓新功能，OS X在哪年代就有了。",
//         "avatar": "http://pic1.zhimg.com/4f3ec4439b33d7202604b3dcb26b818c_im.jpg",
//         "time": 1473069424,
//         "reply_to": {
//  "content": "尼玛这些多指操作win7以后都支持啊。。这还能喷",
//          "status": 0,
//          "id": 26450142,
//          "author": "风间苍月圆"
// },
//  "id": 26456150,
//         "likes": 0
// },
// {
//  "author": "何亚虎",
//         "content": "mac深度用户，互联网行业，最近7、8年工作和生活只用mac，毫无疑问觉得还是windows好用。mac最让我崩溃的就是command+tab很多时候调不出窗口。mac的很多设定噱头大于实用，windows正相反，实用大于噱头（我说的就是多指触摸那些操作，除了三指拖动窗口以外其他都想不起来用）",
//         "avatar": "http://pic2.zhimg.com/1d8109b59_im.jpg",
//         "time": 1473008247,
//         "reply_to": {
//  "content": "十分同意。同为win和OS X 双系统用户，使用时间几乎1:1,觉得本文其实主要还是站在微软那边说话。很多OS X的操作优点只字未提，比如touchpad的二指放大缩小，二指滚轮，三指拖动窗口，三指横拨切换程序，多指抓合和扩张切换桌面和launchpad等等十多种手势。。。个人其实并无特别偏爱其中一个系统，都是需要用哪个独占软件就进哪个系统。win10除去设计图标画风外，其实多数的所谓新操作在win7都有。两个系统各有优劣，win10并没有领先得那么神",
//          "status": 0,
//          "id": 26437619,
//          "author": "沉人启示"
// },
//  "id": 26447743,
//         "likes": 5
// },
// {
//  "author": "瑬辰",
//         "content": "win8的风格其实就是一下变化太大，用户适应不过来所以被喷，我用了一段时间习惯以后就觉得挺好用，现在用win10了还是照样用开始屏幕，我觉得很惨是是全世界都在喷win8可是全世界都学了微软的扁平化，而且可悲的是很多人不知道他们手机网站等接触到的界面元素都是借鉴win8而来，用着各种扁平化风格的软件接着喷微软的扁平化垃圾，有的简直是win8win10无脑喷，看了挺无奈的",
//         "avatar": "http://pic3.zhimg.com/817bee22166e714d848d24259f2b5156_im.jpg",
//         "time": 1472983962,
//         "id": 26443944,
//         "likes": 6
// },
// {
//  "author": "Will_吴凡Silicon-Obelisk",
//         "content": "其实win10出现的bug只要不是升级上去的基本就没有了，体感。\n\n另外果粉真玻璃心，摇头摇头。\n我也是，不过游戏玩家怎么可能会用mac。还有就是我相信很多看答案的人都是没钱买mac或者根本没打算买mac的，点进来就是看个纵向比较看看win10值得不值得装，谁在乎你果怎么回事？\n\nwin10我觉得最大问题是怂了，还是win8把微软搞怕了，其实Metro继续完善还是会更好的。现在win10比较像一个win7和win8的整合体，最终完成版。虽然我更希望有更新的好创意在里面，但没法否认它的完成度。",
//         "avatar": "http://pic2.zhimg.com/6870dff73e15160200e928bf7019b66d_im.jpg",
//         "time": 1472981243,
//         "id": 26443666,
//         "likes": 3
// },
// {
//  "author": "lima19",
//         "content": "带了个bash，然后unbuntu repository上的软件应该只能用命令行工具。并没有用到官方linux内核，只是一个Ubuntu兼容层，就跟wine反过来一样。当然具体要用了才知道。不过并不觉得桌面Linux的需求能全部实现。",
//         "avatar": "http://pic4.zhimg.com/01d9e29ae2ff6c4f973a5a7c7b93a73b_im.jpg",
//         "time": 1472971449,
//         "reply_to": {
//  "content": "看来很多人都不知道啊……最新的win10都支持linux系统了",
//          "status": 0,
//          "id": 26442117,
//          "author": "风灵追梦"
// },
//  "id": 26442558,
//         "likes": 0
// },
// {
//  "author": "IKissedAGirlKatyPerry",
//         "content": "同学用的双系统我就没注意，不过其他的比如altium designer，labview，keil，protuce，iar，quartus，multisim等等都有吗？还有奇葩的伟福仿真器，苦逼通信狗这些可是都要用的。除了图形开发方面我服mac，其他的～～～",
//         "avatar": "http://pic3.zhimg.com/a33006c92_im.jpg",
//         "time": 1472948161,
//         "reply_to": {
//  "content": "低级黑。matlab有mac版，eclipse也有mac版。",
//          "status": 0,
//          "id": 26437888,
//          "author": "爱与正义的魔王"
// },
//  "id": 26438631,
//         "likes": 2
// },
// {
//  "author": "高贵的萌萌哒贤贤",
//         "content": "其实各有优缺点，主要还是看个人喜好。作者偏向性太严重，感觉不是高端黑就是纯枪手。mac个人不爱用，但不会否认它的各方面优点，win10也有它的优点，但事实是身边有太多朋友升级成win10以后受不了各种bug而重装成win7。不是认为win10不好，只是都是懒货，想等win10优化的再好点再来使用，仅此而已",
//         "avatar": "http://pic4.zhimg.com/01d9e29ae2ff6c4f973a5a7c7b93a73b_im.jpg",
//         "time": 1472941768,
//         "id": 26437753,
//         "likes": 6
// },
// {
//  "author": "沉人启示",
//         "content": "十分同意。同为win和OS X 双系统用户，使用时间几乎1:1,觉得本文其实主要还是站在微软那边说话。很多OS X的操作优点只字未提，比如touchpad的二指放大缩小，二指滚轮，三指拖动窗口，三指横拨切换程序，多指抓合和扩张切换桌面和launchpad等等十多种手势。。。个人其实并无特别偏爱其中一个系统，都是需要用哪个独占软件就进哪个系统。win10除去设计图标画风外，其实多数的所谓新操作在win7都有。两个系统各有优劣，win10并没有领先得那么神",
//         "avatar": "http://pic1.zhimg.com/4f3ec4439b33d7202604b3dcb26b818c_im.jpg",
//         "time": 1472937020,
//         "reply_to": {
//  "content": "真被这枪文恶心到了，不知是群体无意识行为的受害者，还是被很多买了mac的脑残粉莫名其妙高人一等的优越感恶心到了，作者也下定决心搞一片技术性软文怒黑一波苹果来慰藉被脑残粉优越感伤害的心灵。而从点赞来看，被水果脑残粉恶心到的人还是很多的……\n\n你避重就轻的写了win的发展史就是要最后一段蓄力黑一波，然而mac真的不是金玉其外的beats，用对待beats脑残粉的方式中伤mac实在有损作者的声誉。本文就针对答主的偏见部分进行反驳。鄙人本身同是Mac/Win重度用户，Win Insiders早期参与者。鄙人本身对win和mac都爱得深沉，因为她们是我工作成就的载体，亦故极其反感引战枪文。\n\n如果说一个公司的UI设计在长达30年的领先时间里毫无建树，那才是天大的笑话。然而近几年微软的UI进化着实是被水果的逆势增长逼出来的。且不说那些被你轻描淡写或压根只字未提的尴尬。\n\n你断章取义的将乔老爷的概念“为用户定义体验”扭曲为“苹果喜欢强奸用户而微软注重用户体验”，而事实上，发觉用户潜在需求—设计产品—投放市场并收集反馈—改进与产品迭代 这是任何伟大产品诞生的过程，比如Windows。这压根就不是苹果的东西，试想，当我们第一次用到XP和win7时那种发自内心愉悦不正是来自于“哇，原来OS可以这么好用，这不就是我想要的吗”。只是在《乔布斯传》中用以显示乔帮主极其重视发掘用户潜在需求，却被你们恶名化，标签化，也不知道是谁的不幸。\n\n接着，还有5天才正式发布的siri for mac就已经被你口中的小娜完爆了…………还是说你认为小娜在win10表现完爆了siri在iPhone的表现？那你又是如何等价比较的？而你说小娜完爆spotlight就更是大言不惭了……spotlight也即“焦点快速搜索”是可以让用户输入简短的关键字快速定位并预览目标文件的程序，是串联用户思想的精灵，熟练的使用者几乎可以不离键盘的跨维度调用操作系统的资源和文件来工作，而不用中途停下来翻找“那张图片/文档/程序去哪里了”，Windows在win10以前几乎就没有这个概念。更不要说OSX下还有两大效率神器alfred和launch bar，他们极大的扩展了spotlight的功能，是你几乎可以瞬间准确定位一个文件并立即对其执行一个工作流，甚至可以编制一个automator使满足一定条件的文件处理流程自动化，比如让下载的文件按照你设定的条件自动归档。在文字输入方面可借助text expander极大提高效率，将常用字符串（比如邮箱）编码成小的文字片段（比如;em ），将一大段八股性质的文字编码成带有变量的snippets，可以实现键字如飞，文字工作者必备神器。对于鼠标指针有一个强力扩展叫popclip，让你在用指针选中一段文字时弹出一个工具栏，实现朗读，高级搜索，字符统计，将选中字符编码为二维码，进行url编码，大小写繁简转换，加粗倾斜下划线，翻译等等等等。由以上几个初级效率工具定义的底层效率体验，至今远未被win超越。\n\n其他答主说的win相对于osx的优点压根就都不在点上。比如finder多窗口和边缘悬停，作者对此点的暧昧批评就让人很难想象他是一个如他所言的“重度mac使用者”。因为mac是一个“多桌面空间”的操作系统，其操作逻辑奠基在多点触控上面，这与win的单桌面空间+键鼠不同的传统方式非常不同。通常mac下工作的人会将不同种类的应用放在不同工作空间，比如浏览器，写文档的，写代码的，剪片子的放在不同空间，通过四指左右滑动来快速切换空间。每个空间都可以全屏以求沉浸式体验。对于在一个桌面空间的多个窗口，往往通过四指上推的mission contral来管理，此时系统会进入鸟瞰视角，将所有窗口没有覆盖的分类整理，定位所需窗口一目了然。你很难想象在win的单桌面空间以及堆叠式任务栏打开50个网页加一堆应用还可以优雅自如的多任务切换。这些特性对于作为一个“重度使用者”的答主竟然只字未提。\n\n我觉得把微软苹果抑或是什么明星宗教化进行崇拜，在对竞争对手泼脏水本身就是一件未开化的事情。我们希望的难道不是企业的良性竞争为我们带来更好的产品？答主有着广泛的兴趣爱好，流畅的表达能力，却为何不正确的引导它们？我在这狂踩你是因为希望你能给出真正有价值的答案而不是道听途说来的片面观点，这中间就差一个偏见。\n\n对于知乎的吃瓜小编也想吐槽一番。知乎的内容消费用户依然绝大多数是小白，并且他们往往认为可以从知乎学到真知灼见。如果为了点击量让标题党，偏见黑和引战文霸占日报头条并且还要推送，那跟某些娱乐客户端有何区别？难道不该扣工资？真知，这是我觉得知乎必须守住的底线。",
//          "status": 0,
//          "id": 26435828,
//          "author": "Galel"
// },
//  "id": 26437619,
//         "likes": 8
// },
// {
//  "author": "IKissedAGirlKatyPerry",
//         "content": "就看看你们的高逼格。手机用的安卓，因为连到电脑上拷视频方便，另外我自己用确实不卡，也没遇到过诈骗或是怎么的，用ADM和ES配合还可以破解限速云下载东西。\n电脑没用mac因为，matlab有mac版？eclipse有吗？用mac的同学学校做实训还得装个双系统，最主要的是，刷机还得用windows😲，最最主要的是，我电脑也就一联想4000块的机子，加了条4个g，偶尔卡硬盘，遇到的不兼容的应用，电信的天翼校园客户端，没遇到很卡超多bug的情况啊。",
//         "avatar": "http://pic3.zhimg.com/a33006c92_im.jpg",
//         "time": 1472924896,
//         "id": 26437078,
//         "likes": 0
// },
// {
//  "author": "Not_Visionary",
//         "content": "win10bug太多了多到想骂娘！更新不成功无限循环，逼得我把更新关闭；WiFi经常莫名其妙掉线。。。。关键这些都已经出现了很多次了还没解决！上次网卡驱动更新不成功显示超时，然而没有本地策略组这个东西来调整时间限制，后来莫名又不超时把无线网卡驱动装上之后，WiFi整个模块都不见了，连开关都找不到，适配器里面没有无线网卡，但是设备管理器里又有无线网卡😣😣😣😣😤😤😤😤😦",
//         "avatar": "http://pic4.zhimg.com/1e9e8254f_im.jpg",
//         "time": 1472922195,
//         "id": 26436848,
//         "likes": 5
// },
// {
//  "author": "不知道丶",
//         "content": "对，确实有些说出了我最近的看法，以前从美观程度上说MacOS从设计感到系统的人性化,Win 都是有望尘莫及的。但是随着7的稳健，8的冒险到现在10的磨合，我也从当年真心买不起Mac的羡慕到最近一两年很明显的感觉到Win的成长让我已经对Mac淡去渴望。最近总Android 7对于iOS的大幅冲刺。真的不好意思说一句，apple确实已不再让我感到惊叹，尤其ip4以后从工业设计的语言上他的硬件外观也让我少了哪一丝钦佩。反观微软和Google我有着更多期待。不过最新的macbook的猜想中的键盘上方的oLED小显示器这一点还是很好奇的。",
//         "avatar": "http://pic3.zhimg.com/899d211ca_im.jpg",
//         "time": 1472917675,
//         "id": 26436155,
//         "likes": 3
// },
// {
//  "author": "Galel",
//         "content": "真被这枪文恶心到了，不知是群体无意识行为的受害者，还是被很多买了mac的脑残粉莫名其妙高人一等的优越感恶心到了，作者也下定决心搞一片技术性软文怒黑一波苹果来慰藉被脑残粉优越感伤害的心灵。而从点赞来看，被水果脑残粉恶心到的人还是很多的……\n\n你避重就轻的写了win的发展史就是要最后一段蓄力黑一波，然而mac真的不是金玉其外的beats，用对待beats脑残粉的方式中伤mac实在有损作者的声誉。本文就针对答主的偏见部分进行反驳。鄙人本身同是Mac/Win重度用户，Win Insiders早期参与者。鄙人本身对win和mac都爱得深沉，因为她们是我工作成就的载体，亦故极其反感引战枪文。\n\n如果说一个公司的UI设计在长达30年的领先时间里毫无建树，那才是天大的笑话。然而近几年微软的UI进化着实是被水果的逆势增长逼出来的。且不说那些被你轻描淡写或压根只字未提的尴尬。\n\n你断章取义的将乔老爷的概念“为用户定义体验”扭曲为“苹果喜欢强奸用户而微软注重用户体验”，而事实上，发觉用户潜在需求—设计产品—投放市场并收集反馈—改进与产品迭代 这是任何伟大产品诞生的过程，比如Windows。这压根就不是苹果的东西，试想，当我们第一次用到XP和win7时那种发自内心愉悦不正是来自于“哇，原来OS可以这么好用，这不就是我想要的吗”。只是在《乔布斯传》中用以显示乔帮主极其重视发掘用户潜在需求，却被你们恶名化，标签化，也不知道是谁的不幸。\n\n接着，还有5天才正式发布的siri for mac就已经被你口中的小娜完爆了…………还是说你认为小娜在win10表现完爆了siri在iPhone的表现？那你又是如何等价比较的？而你说小娜完爆spotlight就更是大言不惭了……spotlight也即“焦点快速搜索”是可以让用户输入简短的关键字快速定位并预览目标文件的程序，是串联用户思想的精灵，熟练的使用者几乎可以不离键盘的跨维度调用操作系统的资源和文件来工作，而不用中途停下来翻找“那张图片/文档/程序去哪里了”，Windows在win10以前几乎就没有这个概念。更不要说OSX下还有两大效率神器alfred和launch bar，他们极大的扩展了spotlight的功能，是你几乎可以瞬间准确定位一个文件并立即对其执行一个工作流，甚至可以编制一个automator使满足一定条件的文件处理流程自动化，比如让下载的文件按照你设定的条件自动归档。在文字输入方面可借助text expander极大提高效率，将常用字符串（比如邮箱）编码成小的文字片段（比如;em ），将一大段八股性质的文字编码成带有变量的snippets，可以实现键字如飞，文字工作者必备神器。对于鼠标指针有一个强力扩展叫popclip，让你在用指针选中一段文字时弹出一个工具栏，实现朗读，高级搜索，字符统计，将选中字符编码为二维码，进行url编码，大小写繁简转换，加粗倾斜下划线，翻译等等等等。由以上几个初级效率工具定义的底层效率体验，至今远未被win超越。\n\n其他答主说的win相对于osx的优点压根就都不在点上。比如finder多窗口和边缘悬停，作者对此点的暧昧批评就让人很难想象他是一个如他所言的“重度mac使用者”。因为mac是一个“多桌面空间”的操作系统，其操作逻辑奠基在多点触控上面，这与win的单桌面空间+键鼠不同的传统方式非常不同。通常mac下工作的人会将不同种类的应用放在不同工作空间，比如浏览器，写文档的，写代码的，剪片子的放在不同空间，通过四指左右滑动来快速切换空间。每个空间都可以全屏以求沉浸式体验。对于在一个桌面空间的多个窗口，往往通过四指上推的mission contral来管理，此时系统会进入鸟瞰视角，将所有窗口没有覆盖的分类整理，定位所需窗口一目了然。你很难想象在win的单桌面空间以及堆叠式任务栏打开50个网页加一堆应用还可以优雅自如的多任务切换。这些特性对于作为一个“重度使用者”的答主竟然只字未提。\n\n我觉得把微软苹果抑或是什么明星宗教化进行崇拜，在对竞争对手泼脏水本身就是一件未开化的事情。我们希望的难道不是企业的良性竞争为我们带来更好的产品？答主有着广泛的兴趣爱好，流畅的表达能力，却为何不正确的引导它们？我在这狂踩你是因为希望你能给出真正有价值的答案而不是道听途说来的片面观点，这中间就差一个偏见。\n\n对于知乎的吃瓜小编也想吐槽一番。知乎的内容消费用户依然绝大多数是小白，并且他们往往认为可以从知乎学到真知灼见。如果为了点击量让标题党，偏见黑和引战文霸占日报头条并且还要推送，那跟某些娱乐客户端有何区别？难道不该扣工资？真知，这是我觉得知乎必须守住的底线。",
//         "avatar": "http://pic1.zhimg.com/916845535289c6bcf62c730caf3aacbc_im.jpg",
//         "time": 1472916275,
//         "id": 26435828,
//         "likes": 113
// },
// {
//  "author": "Mingerest",
//         "content": "是啊，全新的设备恢复一个备份贼慢，而且极容易失败，前几天就是不停提示我连接失败连接失败，一怒之下一个个从App Store下.....此外，如果你备忘录这种多一点的话...纯文字的哦....恢复要十几分钟........",
//         "avatar": "http://pic3.zhimg.com/5065bf556_im.jpg",
//         "time": 1472913683,
//         "reply_to": {
//  "content": "什么意思？从iCloud恢复App安装？\nApp本来就没备份到iCloud上啊。iCloud里备份的是App的一部分数据。\n从iCloud恢复的时候，会自动从App Store下载App，然后恢复里面的数据。",
//          "status": 0,
//          "id": 26434429,
//          "author": "王珺"
// },
//  "id": 26435192,
//         "likes": 0
// },
// {
//  "author": "王珺",
//         "content": "什么意思？从iCloud恢复App安装？\nApp本来就没备份到iCloud上啊。iCloud里备份的是App的一部分数据。\n从iCloud恢复的时候，会自动从App Store下载App，然后恢复里面的数据。",
//         "avatar": "http://pic1.zhimg.com/d4efe0954d1f361c294970190d98b988_im.jpg",
//         "time": 1472910318,
//         "reply_to": {
//  "content": "从iCloud恢复备份的时候就蛋疼了.....恢复个备份还不如重新在App Store重新下一遍（哪怕App Store也不算快）.........［蜜汁微笑］［手动再见］",
//          "status": 0,
//          "id": 26433873,
//          "author": "Mingerest"
// },
//  "id": 26434429,
//         "likes": 0
// },
// {
//  "author": "伯Yeah",
//         "content": "感觉用SSD、支持dx12的显卡、微软官方版iso（或者官方版Win7 8 8.1升级的10）才能准确评价10吧。有多少人是用各种阉割的ghost win7升级成10然后说10不行的呢？起码本人用到现在，没有遇到各种神奇的崩溃或者不兼容的，常用软件全部可以用。\n蛮好奇是什么不兼容的，如果是各种老一代NT5软件我就没办法了……话说为啥没人说现在的iOS软件不支持iOS4系统的呢？也是不兼容啊，只不过苹果强制要全部软件更新新内核，不然就下架，这和微软新系统不支持老软件，有区别吗？\nUI见仁见智，个人感觉10的UI还是可以的，另外各种主题、桌面软件可以随便魔改，官方不好看又有什么所谓呢？",
//         "avatar": "https://pic1.zhimg.com/da8e974dc_s.jpg",
//         "time": 1472909836,
//         "id": 26434375,
//         "likes": 11
// },
// {
//  "author": "Good-Sunny",
//         "content": "win10莫名其妙的BUG确实很多，之前1511版本遇上无限升级失败\nBUG，周年版遇上点开声音不能调节的BUG和打开软件桌面假死的BUG，这只是whn10 BUG中的一小部分，这些BUG都有大量用户遇上，且到现在微软都没解决，也没有个彻底解决的办法。",
//         "avatar": "http://pic1.zhimg.com/2cfd74b0530e8812ab8b07c18a118a48_im.jpg",
//         "time": 1472906623,
//         "reply_to": {
//  "content": "😃夸OSX踩windows之前先把你搭载windows系统的电脑配置全部升级到高贵的mac一样那么高再说话，别忘了固态硬盘。",
//          "status": 0,
//          "id": 26433397,
//          "author": "Teanooo"
// },
//  "id": 26434037,
//         "likes": 2
// }
// ]
//}

  //http://news-at.zhihu.com/api/4/theme/7/before/3829977 加载以前主题消息接口

 //{"stories":[{"images":["http:\/\/pic2.zhimg.com\/7f80ad26f37af321db624792cee27f69.jpg"],"type":1,"id":3887444,"title":"值得打飞机去参加的欧洲五大地下电子音乐节"},{"images":["http:\/\/pic2.zhimg.com\/e3375994b2cdd67eab47940aa1d07d59_t.jpg"],"type":0,"id":3878455,"title":"手把手教你各种音乐的跳舞秘诀"},{"images":["http:\/\/pic2.zhimg.com\/53a68692f1c7f9866d61d2693754b585_t.jpg"],"type":0,"id":3876540,"title":"当电子音乐舞曲遇上迪斯尼"},{"images":["http:\/\/pic2.zhimg.com\/a13a7132c22bf5e3fe1cf46cae153451_t.jpg"],"type":0,"id":3874812,"title":"威廉王子和凯特王妃都去做DJ了，你还在等什么呢？！"},{"images":["http:\/\/pic4.zhimg.com\/78ae4e0e903e6a05036310178b7b7cb6_t.jpg"],"type":0,"id":3874673,"title":"吐槽史上20首最难听的Dubstep混音作品"},{"images":["http:\/\/pic4.zhimg.com\/371f7486ad0692620412876ab02fe978_t.jpg"],"type":0,"id":3868871,"title":"Steve Aoki 为什么现场爱扔蛋糕？"},{"images":["http:\/\/pic4.zhimg.com\/1c5db1c40f7f9634d0f571700d0b7af7.jpg"],"type":1,"id":3862719,"title":"给那些想要成为DJ的年轻人"},{"images":["http:\/\/pic1.zhimg.com\/864ba36a23470538e7d86a6f5a87265a_t.jpg"],"type":0,"id":3859982,"title":"2014年4月全球十大电子音乐节"},{"images":["http:\/\/pic3.zhimg.com\/6563d02ff31a1cd3b34c57a0773035f4_t.jpg"],"type":0,"id":3857912,"title":"EDM 大辩论"},{"images":["http:\/\/pic3.zhimg.com\/03bfff0dc5c14825079cdf81be154107_t.jpg"],"type":2,"id":3856062,"title":"欢迎来到Mu星球"},{"images":["http:\/\/pic4.zhimg.com\/c80988df9b52032edf4089a39235e559_t.jpg"],"type":0,"id":3853194,"title":"天堂的悠扬之声—电音优质女声制作人Haley"},{"images":["http:\/\/pic2.zhimg.com\/5b1c280f7a265165ce23d2cfc9d2f602_t.jpg"],"type":2,"id":3853103,"title":"贝克汉姆将进军 DJ 界"},{"images":["http:\/\/pic3.zhimg.com\/113fdb5b275ebf96007dc38316b71841_t.jpg"],"type":1,"id":3853017,"title":"如何包装你自己：关于 DJ 营销的一切"},{"images":["http:\/\/pic1.zhimg.com\/07021359b66d6e80739bd21f84b324f6_t.jpg"],"type":1,"id":3852943,"title":"电子音乐的“钱途”一片光明"},{"type":2,"id":3852894,"title":"法国电子音乐文化之旅"},{"images":["http:\/\/pic2.zhimg.com\/e77b1efc06d47a8ef1d5d6cef65551ba_t.jpg"],"type":0,"id":3852820,"title":"什么是 Trap 音乐？"},{"type":0,"id":3829986,"title":"刚接触数字音乐的时候，大都无法理解「合成器」"},{"type":0,"id":3829977,"title":"史上 30 首最精彩的 Dubstep 作品"}]}
}
