# 一.登录注册

## 1.手机号校验

### 	接口功能

> 校验注册手机号的唯一性

### 	url

> user/check

### 	请求方式

> GET

### 	请求参数

> | 参数  | 必选 |  类型  | 说明 |
> | :---: | :--: | :----: | :--: |
> | phone | true | string |      |

### 	返回结果

> 状态码

> | 成功 |        失败         |
> | :--: | :-----------------: |
> | 200  | 400（手机号已存在） |

## 2.发送验证码

### 接口功能

> 后台发送验证码

### 	url

> code/send

### 	请求方式

> POST

### 	请求参数

> | 参数  | 必选 |  类型  | 说明 |
> | :---: | :--: | :----: | :--: |
> | phone | true | string |      |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

>返回参数
>
>| 参数 |  类型  | 说明 |
>| :--: | :----: | :--: |
>| code | string |      |

## 3.注册

### 	接口功能

>注册用户

### 	url

> user/register

### 	请求方式

>POST

### 	请求参数

>|   参数   | 必选 |  类型  |  说明  |
>| :------: | :--: | :----: | :----: |
>|  phone   | true | string |        |
>|   code   | true | string | 验证码 |
>| password | true | string |        |

### 	返回结果
### 	返回结果

>状态码
>
>| 成功 |       失败        |
>| :--: | :---------------: |
>| 201  | 400（验证码错误） |

## 4.登录校验

### 接口功能

> 检验是否已经登录，刷新token时间，若已经登录则返回用户头像及用户名

### 	url

> user/verify

### 	请求方式

> GET

### 	请求参数

> | 参数 | 必选 | 类型 | 说明 |
> | :--: | :--: | :--: | :--: |
> |  无  |      |      |      |

### 	返回结果

> 状态码
>
> | 成功 |     失败      |
> | :--: | :-----------: |
> | 200  | 401（未登录） |

### 返回示例

> {
>
> ​    "userhead": "http://myheadzs",
>
> ​    "username": "张三
>
> }

## 5.短信登录

### 接口功能

> 验证码登录，服务端发送cookie，做无状态登录

### 	url

> user/login

### 	请求方式

> GET

### 	请求参数

> | 参数  | 必选 |  类型  | 说明 |
> | :---: | :--: | :----: | :--: |
> | phone | true | string |      |
> | code  | true | string |      |

### 	返回结果

> 状态码
>
> | 成功 |       失败        |
> | :--: | :---------------: |
> | 200  | 404（手机号错误） |
> |      | 400（验证码错误） |

## 6.密码登陆

### 接口功能

> 密码登录，服务端发送cookie，做无状态登录

### 	url

> user/login

### 	请求方式

> POST

### 	请求参数

> |   参数   | 必选 |  类型  | 说明 |
> | :------: | :--: | :----: | :--: |
> |  phone   | true | string |      |
> | password | true | string |      |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 404  |

## 7.忘记密码校验

### 接口功能

> 验证码校验

### 	url

> code/check

### 	请求方式

> GET

### 	请求参数

> | 参数  | 必选 |  类型  | 说明 |
> | :---: | :--: | :----: | :--: |
> | phone | true | string |      |
> | code  | true | string |      |

### 	返回结果

> 状态码
>
> | 成功 |      失败       |
> | :--: | :-------------: |
> | 200  | 400(验证码错误) |

## 8.重置密码

### 	接口功能

> 验证成功后设置新密码

### 	url

> user/

### 	请求方式

> PUT

### 	请求参数

> |   参数   | 必选 |  类型  | 说明 |
> | :------: | :--: | :----: | :--: |
> |  phone   | true | string |      |
> | password | true | string |      |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 204  | 500  |

# 二.个人信息

## 9.查看个人信息

### 接口功能

> 查看个人信息

### 	url

> user/detail

### 	请求方式

> GET

### 	请求参数

> | 参数 | 必选 | 类型 | 说明 |
> | :--: | :--: | :--: | :--: |
> |  无  |      |      |      |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

> 返回参数
>
> | 类型类型 | 参数 | 说明 |
> | :------: | :--: | :--: |
> |   如下   |      |      |

### 	返回示例

>{ 
>
>​	"userId": 8,
>
>​    "username": "张三",
>
>​    "realname": "苗爷",
>
>​    "phone": "15389093292",
>
>​    "birth": "2000-01-01",
>
>​    "college": "网安院",
>
>​    "profession": "信息安全",
>
>​    "email": "12345@qq.com",
>
>​    "hobby": "搬砖",
>
>​    "head": "http://myheadzs"
>
>}

## 10.修改个人信息

### 接口功能

> 保存个人信息，用户手机号不能修改

### 	url

> user/detail

### 	请求方式

> PUT

### 	请求格式

> JSON

### 	请求参数

> |    参数    | 必选  |                类型                |
> | :--------: | :---: | :--------------------------------: |
> |  username  | false | string(注：每个用户有个初始用户名) |
> |  realname  | false |               string               |
> |   birth    | false |               string               |
> |  college   | false |               string               |
> | profession | false |               string               |
> |   email    | false |               string               |
> |   hobby    | false |               string               |
> |    head    | false |               string               |

### 	返回结果

> 状态码
>
> | 成功 |              失败               |
> | :--: | :-----------------------------: |
> | 204  | 400(用户名为空，电话号码被修改) |

# 三.论文

## 11.帖子列表

### 接口功能

> 获取帖子列表

### 	url

> article/list

### 	请求方式

> post

### 	请求参数

> | 参数  | 必选  |  类型  |                       说明                        |
> | :---: | :---: | :----: | :-----------------------------------------------: |
> | type  | false |  int   | 帖子类型（1 数模论文（默认）2 技术论文   3 搜素） |
> | ishot | false |  bool  |   热门 （按浏览量对全部帖子排序（默认false））    |
> | page  | false |  int   |                  当前页（默认1）                  |
> |  row  | false |  int   |              每页显示的行数（默认7）              |
> |  key  | false | string |                   搜索的关键字                    |
> | year  | false | string |        年份（格式：2019 可不传 默认今年）         |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

> 返回参数
>
> |   参数    | 类型 |    说明    |
> | :-------: | :--: | :--------: |
> |   click   | int  |   浏览量   |
> |  comment  | int  |   评论数   |
> | totalPage | int  | 总共多少页 |
> |   istop   | true |  是否置顶  |

### 	返回示例

>{
>  “articles”：[
>{	
>   “id”：1
>  “title”：“标题”
>  “authorName”：“作者”
>  “authorHead”: "作者头像(url)",
>  “create”：“2019-01-01 23:59”
>   “click”：120
>   “commentCount”：12，
>  “istop”：true
>}，
>{	
>   “id”：2
>  “title”：“标题2”
>  “authorName”：“作者2”
>  “authorHead”: "http://touxiang.jpg",
>  “create”：“2019-01-01 20:59”
>   “click”：121
>   “commentCount”：12，
>  “istop”：false
>}，
>{	
>   “id”：3
>  “title”：“标题3”
>  “authorName”：“作者3”
>  “authorHead”: "http://touxiang.jpg",
>  “create”：“2019-01-01 22:59”
>   “click”：122
>   “commentCount”：12,
>  “istop”：false
>}
>  ]，
>
>totalPage”:  26,
>
>  “sum”：{
>  		“today”：150，
>	“yesterday”：160，
>	“total”：1600，
>	"years": [
>           "2018",
>           "2019"
>       ]
>  }
>}

## 12.热搜列表

​	接口功能

> 获取全部帖子排行信息

### 	url

> article/ranklist

### 	请求方式

> get

### 	请求参数

> | 参数 | 必选  | 类型 |               说明               |
> | :--: | :---: | :--: | :------------------------------: |
> | date | false | int  | 日期（1今天（默认）2 本周 3 本月 |

### 	返回结果

> 状态码
>
> | 失败失败 | 成功 |
> | :------: | :--: |
> |   500    | 200  |



> 返回参数
>
> | 参数      | 类型   | 说明                   |
> | --------- | ------ | ---------------------- |
> | articleId | int    | 文章ID（用于打开文章） |
> | title     | string | 文章标题               |

### 	返回示例

> {
> 	[
> 		"articleId":"1",
> 		"title":"数模一篇"
> 	],
> 	[
> 		"articleId":"2",
> 		"title":"数模二篇"
> 	],
> 	[
> 		"articleId":"3",
> 		"title":"数模三篇"
> 	],
> 	[
> 		"articleId":"4",
> 		"title":"数模四篇"
> 	],
> 	[
> 		"articleId":"5",
> 		"title":"数模五篇"
> 	]
> }

## 13.发帖

### 接口功能

> 发布帖子

### 	url

> article/post

### 	请求方式

> POST

### 请求格式

>JSON

### 	请求参数

> |  参数   | 必选  |  类型  |              说明              |
> | :-----: | :---: | :----: | :----------------------------: |
> |  type   | true  |  int   |      1.数模论文 2.技术文       |
> | content | true  | string |              内容              |
> |  title  | true  | string |              标题              |
> | images  | false | string | 图片路径（多张图片用 ， 隔开） |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

## 14.打开帖子

### 接口功能

> 查看某个帖子，其中评论区及回复可参考QQ看点

### 	url

> article/{id}

### 	请求方式

> GET

### 	请求参数

> | 参数 | 必选 | 类型 |  说明  |
> | :--: | :--: | :--: | :----: |
> |  id  | true | int  | 帖子id |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

> 返回参数
>
> |     参数     |  类型  |         说明         |
> | :----------: | :----: | :------------------: |
> |    create    | string |       发布时间       |
> |     like     |  int   |      帖子点赞数      |
> |   comLike    |  int   |      评论点赞数      |
> |   comments   |        |       评论集合       |
> | commentCount |  int   |      帖子评论数      |
> |   repCount   |  int   |      评论回复数      |
> |    images    | string | 图片url(多图 ，隔开) |

### 	返回示例

>{
	    "artId": 1,
	    "images": "http://24hs738861.zicp.vip:40272/20191023094700.jpeg",
	     "like": 2,
	    "authorName": "张三",
	    "create": "2019-10-20 23:35:48",
	    "title": "数模论文1",
	    "click": 211,
	    "content": "这是一篇数模论文",
	    "commentCount": 27,
	    "comments": [
	        {
	            "id": 32,
	            "comContent": "什么是数模啊？",
	            "comCreate": "2019-11-05 21:25:17",
	            "comAuthor": 22,
	            "comAuthorName": "啦啦啦",
	            "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 31,
	            "comContent": "写得好",
	            "comCreate": "2019-11-05 21:23:59",
	            "comAuthor": 0,
	            "comAuthorName": "游客_44b14739",
	             "comAuthorHead": null,
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 29,
	            "comContent": "hi sister.",
	            "comCreate": "2019-11-05 20:57:18",
	            "comAuthor": 8,
	            "comAuthorName": "张三",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 28,
	            "comContent": "嘎嘎嘎嘎嘎3333",
	            "comCreate": "2019-11-03 19:52:28",
	            "comAuthor": 9,
	            "comAuthorName": "李四",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 24,
	            "comContent": "hhhhh22222",
	            "comCreate": "2019-11-03 03:54:53",
	            "comAuthor": 8,
	            "comAuthorName": "张三",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 1,
	            "comContent": "数模评论1",
	            "comCreate": "2019-10-21 01:23:05",
	            "comAuthor": 8,
	            "comAuthorName": "张三",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 1,
	            "repCount": 6
	        },
	        {
	            "id": 4,
	            "comContent": "赵六评论数模1",
	            "comCreate": "2019-10-05 01:24:50",
	            "comAuthor": 11,
	            "comAuthorName": "赵六",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        },
	        {
	            "id": 3,
	            "comContent": "王五评论数模1",
	            "comCreate": "2019-10-03 01:24:01",
	            "comAuthor": 10,
	            "comAuthorName": "王五",
	             "comAuthorHead": "http://myheadzs",
	            "comLike": 0,
	            "repCount": 0
	        }
	    ]
	}

## 15.打开回复列表

### 接口功能

> 查看某个评论的回复

### url

> reply/{commentId}

### 请求方式

> GET

### 请求参数

> |   参数    | 必选 | 类型 |  说明  |
> | :-------: | :--: | :--: | :----: |
> | commentId | true | int  | 评论id |

### 返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 500  |

> 返回参数
>
> |      参数       |  类型  |                           说明                           |
> | :-------------: | :----: | :------------------------------------------------------: |
> |     repLike     |  int   |                        回复点赞数                        |
> |     repType     |  int   | 回复的类型 1.对评论回复(此时无下面两个字段) 2.对回复回复 |
> |   toRepAuthor   |  int   |                        回复对象id                        |
> | toRepAuthorName | string |                       回复对象name                       |

### 返回示例

> [
>
> ​	{
> ​	        "repContent": "哈哈哈哈哈2222",
> ​	        "repCreate": "2019-11-07 18:03:42",
> ​	        "repAuthor": 8,
> ​	        "repAuthorHead": "http://myheadzs",
> ​	        "repAuthorName": "张三",
> ​	        "repLike": 0,
> ​	        "repType": 2,
> ​	        "toRepAuthorName": "啦啦啦123",
> ​	        "toRepAuthor": 22
> ​	    },
> ​	    {
> ​	        "repContent": "哈哈哈哈哈",
> ​	        "repCreate": "2019-11-07 18:02:53",
> ​	        "repAuthor": 8,
> ​	        "repAuthorHead": "http://myheadzs",
> ​	        "repAuthorName": "张三",
> ​	        "repLike": 0,
> ​	        "repType": 1,
> ​	        "toRepAuthorName": null,
> ​	        "toRepAuthor": null
> ​	    },
> ​	    {
> ​	        "repContent": "写得好！！！！！",
> ​	        "repCreate": "2019-11-05 21:16:35",
> ​	        "repAuthor": 22,
> ​	        "repAuthorHead": "http://myhead01",
> ​	        "repAuthorName": "啦啦啦",
> ​	        "repLike": 0,
> ​	        "repType": 1,
> ​	        "toRepAuthorName": null,
> ​	        "toRepAuthor": null
> ​	    },
> ​	    {
> ​	        "repContent": "hi man...",
> ​	        "repCreate": "2019-11-05 01:11:02",
> ​	        "repAuthor": 0,
> ​	        "repAuthorHead": null,
> ​	        "repAuthorName": "游客_e26e7308",
> ​	        "repLike": 0,
> ​	        "repType": 1,
> ​	        "toRepAuthorName": null,
> ​	        "toRepAuthor": null
> ​	    },
> ​	    {
> ​	        "repContent": "李四大哥",
> ​	        "repCreate": "2019-11-05 00:39:53",
> ​	        "repAuthor": 8,
> ​	        "repAuthorHead": "http://myheadzs",
> ​	        "repAuthorName": "张三",
> ​	        "repLike": 0,
> ​	        "repType": 2,
> ​	        "toRepAuthorName": "李四",
> ​	        "toRepAuthor": 9
> ​	    },
> ​	    {
> ​	        "repContent": "张三大大",
> ​	        "repCreate": "2019-10-21 01:27:43",
> ​	        "repAuthor": 12,
> ​	        "repAuthorHead": "http://myheadlq",
> ​	        "repAuthorName": "老七",
> ​	        "repLike": 0,
> ​	        "repType": 1,
> ​	        "toRepAuthorName": null,
> ​	        "toRepAuthor": null
> ​	    },
> ​	    {
> ​	        "repContent": "张三小老弟",
> ​	        "repCreate": "2019-10-21 01:27:18",
> ​	        "repAuthor": 9,
> ​	        "repAuthorHead": "http://myheadls",
> ​	        "repAuthorName": "李四",
> ​	        "repLike": 0,
> ​	        "repType": 1,
> ​	        "toRepAuthorName": null,
> ​	        "toRepAuthor": null
> ​	 }
> ]

## 16.评论

### 接口功能

> 评论，另外管理端可以设置不登录评论

### 	url

> comment

### 	请求方式

> POST

### 请求格式

> JSON

### 	请求参数

> |   参数    | 必选 |  类型  |   说明   |
> | :-------: | :--: | :----: | :------: |
> | articleId | true |  int   |  文章id  |
> |  content  | true | string | 评论内容 |

### 	返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 201  | 500  |

### 请求示例

>{"content":"咔咔咔咔咔咔","articleId":1}

## 17.回复

### 接口功能

> 回复，可参考QQ看点，另外管理端可以设置不登录评论

### url

> reply

### 请求方式

> post

### 请求格式

> json

### 请求参数

> |    参数     | 必选  |  类型  |                说明                |
> | :---------: | :---: | :----: | :--------------------------------: |
> |    type     | false |  int   | 1.回复评论(默认)  2.对回复进行回复 |
> |  articleId  | true  |  int   |               文章ID               |
> |  commentId  | true  |  int   |               评论ID               |
> |   content   | true  | string |              评论内容              |
> | toRepAuthor | false |  int   |    若type为2，传入回复对象的id     |

### 返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 201  | 500  |

### 请求示例

>{"content":"哈哈哈哈哈","articleId":1,"commentId":1}
>
>{"content":"哈哈哈哈哈2222","articleId":1,"commentId":1,"toRepAuthor":22,"type":2}

## 
## 18.点赞

### 接口功能

> 给文章，评论，回复点赞

### 	url

> like

### 	请求方式

> GET

### 请求格式

>JSON

### 	请求参数

> | 参数 | 必选  | 类型 |         说明         |
> | :--: | :---: | :--: | :------------------: |
> | type | false | int  | 1.文章 2.评论 3.回复 |
> | toId | true  | int  |        对应id        |

### 	返回结果

> 状态码
>
> | 成功 |          失败           |
> | :--: | :---------------------: |
> | 204  | 400（一天只能点一次赞） |

## 19.上传

### 接口功能

> 上传图片，返回url

### url

> upload/image

### 请求方式

> POST

### 请求格式

>multipart/form-data

### 请求参数

> | 参数 | 必选 | 类型 | 说明 |
> | :--: | :--: | :--: | :--: |
> | file | true |      | 图片 |

### 返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 201  | 400  |

### 返回示例

>{"http://image.hahaha.com"}

# 四.下载资源

## 20.下载资源列表

### 接口功能

> 返回已有的下载资源列表

### url

> source/list

### 请求方式

> GET

### 请求参数

> | 参数 | 必选  | 类型 |       说明        |
> | :--: | :---: | :--: | :---------------: |
> | page | false | int  |  当前页（默认1）  |
> | row  | false | int  | 页面大小（默认7） |

### 返回结果

> 状态码
>
> | 成功 | 失败 |
> | :--: | :--: |
> | 200  | 404  |

### 返回示例

>{

“total":120,
	 	"totalPage": 5，
	"source" : 【
>{	
>	   “sourceId”：1,
>	  “sourceTitle”：“标题”,
>	  “authorName”：“作者”,
>	  “sourceCreate”：“2019-01-01 23:59”,
>	   “sourceClick”：120,
		}，
>{	
>	   “sourceId”：1,
>	  “sourceTitle”：“标题”,
>	  “authorName”：“作者”,
>	  “sourceCreate”：“2019-01-01 23:59”,
>	   “sourceClick”：120,
		}，
>{	
>	   “sourceId”：1,
>	  “sourceTitle”：“标题”,
>	  “authorName”：“作者”,
>	  “sourceCreate”：“2019-01-01 23:59”,
>	   “sourceClick”：120,
		}
】
	}


## 21.下载资源详情

### 接口功能

> 下载资源详情页，需登陆后才能打开

### url

> source/detail/{id}

### 请求方式

> GET

### 请求参数

> | 参数 | 必选 | 类型 |  说明  |
> | :--: | :--: | :--: | :----: |
> |  id  | true |      | 资源id |

### 返回结果

> 状态码
>
> | 成功 |        失败         |               |
> | :--: | :-----------------: | ------------- |
> | 201  | 401(未授权，没登陆) | 403（要付费） |

### 返回示例

> {
> 	“sourceTitle”：“标题”,
> 	“authorName”：“作者”,
> 	“sourceCreate”：“2019-01-01 23:59”,
> 	"baiduyun":"http://baiduyun/aaaaaa",
> 	"code":"neob",
> 	"github":"github.com"
> }

