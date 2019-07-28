package com.qfjy.project.weixin.service;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.pojo.AccessToken;
import com.qfjy.project.weixin.util.MessageUtil;
import com.qfjy.project.weixin.util.WeixinUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

import com.qfjy.project.meeting.bean.DailySign;
import com.qfjy.project.meeting.bean.User;
import com.qfjy.project.meeting.bean.Weiuser;
import com.qfjy.project.meeting.commons.util.DateUtil;
import com.qfjy.project.meeting.service.DailySignService;
import com.qfjy.project.meeting.service.UserService;
import com.qfjy.project.meeting.service.WeiuserService;
import com.qfjy.project.meeting.task.DemoTask;
import com.qfjy.project.weixin.api.Hitokoto.HitokotoApi;
import com.qfjy.project.weixin.api.tuling.TulingApiUtil;
import com.qfjy.project.weixin.api.userInfo.UserInfoService;
import com.qfjy.project.weixin.bean.resp.Article;
import com.qfjy.project.weixin.bean.resp.MusicMessage;
import com.qfjy.project.weixin.bean.resp.NewsMessage;
import com.qfjy.project.weixin.bean.resp.TextMessage;

@Service
public class CoreService {
	@Autowired
	private TulingApiUtil tulingApiUtil;
	@Autowired
	private HitokotoApi hitokotoApi;
	@Autowired
	private UserService userService;
	@Autowired
	private WeiuserService weiUserService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DailySignService dailySignService;
	@Autowired
	private DemoTask demoTask;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
					String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */
			System.out.println();
			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent = "您发送的是文本消息！";
				respContent=tulingApiUtil.sendMessage(content);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					userInfoService.userInfoService(fromUserName);
					respContent = "欢迎关注微信公众号";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("10")) {
						respContent = "菜单项被点击！";
						//判断用户是否被绑定
						Weiuser weiuser = weiUserService.selectByOpenid(fromUserName);
						User u = userService.selectByWid(weiuser.getId());
						// 单图文消息
						List<Article> articleList = new ArrayList<Article>();
						Article article = new Article();
						
						if(u==null) {
							article.setTitle("抱歉,您还没有登录");
							article.setDescription("需要正常访问请登录...");
							article.setPicUrl(
									"http://img5.imgtn.bdimg.com/it/u=2553535955,3283443910&fm=15&gp=0.jpg");
							article.setUrl(MenuManager.REAL_URL+"/pages/weixin/user/login.jsp?wid="+weiuser.getId());
						}else {//如果已登录
							//如果是发单组
							if(u.getRid()==1) {
								article.setTitle(weiuser.getNickname()+"抱歉,您是发单组,无权访问");
								article.setDescription("抢单功能只能由抢单组的用户使用...");
								article.setPicUrl(
										"http://img0.imgtn.bdimg.com/it/u=2045059736,860344984&fm=15&gp=0.jpg");
								article.setUrl(MenuManager.REAL_URL+"pages/weixin/unauth.jsp");
							}//发单组
							else if(u.getRid()==2) {
								article.setTitle(weiuser.getNickname()+"您是抢单组用户,欢迎使用");
								article.setDescription("抢单功能只能由抢单组的用户使用...");
								article.setPicUrl(
										"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2395172104,588652880&fm=11&gp=0.jpg");
								article.setUrl(MenuManager.REAL_URL+"pages/weixin/meetingGrab/meetingGrabList.jsp?uid="+u.getId());
							}
						}
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
						
					}else if(eventKey.equals("34")) {
						respContent = hitokotoApi.sendMessage();
					}else if(eventKey.equals("20")) {
						Weiuser weiuser = weiUserService.selectByOpenid(fromUserName);
						List<Article> articleList = new ArrayList<Article>();
						// 单图文消息
						Article article = new Article();
					
						User user = userService.selectByWid(weiuser.getId());
						if(user==null) {
							article.setTitle("抱歉,您还没有登录");
							article.setDescription("需要正常访问请登录...");
							article.setPicUrl(
									"http://img5.imgtn.bdimg.com/it/u=2553535955,3283443910&fm=15&gp=0.jpg");
							article.setUrl(MenuManager.REAL_URL+"/pages/weixin/user/login.jsp?wid="+weiuser.getId());
						}else{
							//如果已登录
							/*
							 * 根据用户id查询签到列表 
							 * 如果没查到就新增一条数据
							 * 查到了就更新数据
							 */
							//超越百分之多少的人: 查出表里所有比我分数低的人 再除以总人数
							DailySign daily1 = dailySignService.selectByUid(user.getId());
							
							if(daily1==null) {
								DailySign daily = new DailySign();
								daily.setSigntime(new Date());
								daily.setPoint(1);
								daily.setNum(1);
								daily.setCount(1);
								daily.setStatus(0);
								daily.setUid(user.getId());
								daily.setSignstr(DateUtil.getTodayDate());
								dailySignService.insertSelective(daily);
								DailySign daily2=dailySignService.selectByUid(user.getId());
								//求超越百分比
								int str =  DateUtil.getTranscend(dailySignService.selectCount(),dailySignService.selectCountByPoint(daily2.getPoint()) );
								article.setTitle("第一次签到");
								article.setDescription(DateUtil.getTodayDate() +"签到成功,您已成功签到"+daily2.getNum()+"天,连续签到了"+daily2.getCount()+"天"
										+"超越了"+str+"%人");
								article.setPicUrl(
										MenuManager.REAL_URL+"/images/1.png");
								article.setUrl(MenuManager.REAL_URL+"pages/weixin/sign/dailySign.jsp?signstr="+daily2.getSignstr()+
										"&point="+daily2.getPoint()+"&status="+daily2.getStatus()+"&str="+str);
								
							}else {
								//要判断是否连续登录  是连续登录的话积分+2,不是的话+1
								
								//当前时间和用户上次登录时间的差值
								int num = DateUtil.getCount(daily1.getSigntime(),new Date());
								
								//相差0天 就代表今天已经签到过了
								if(num==0) {
									
									DailySign daily2=dailySignService.selectByUid(user.getId());
									article.setTitle("您今天已经签到过了哦");
									article.setDescription(DateUtil.getTodayDate());
									//求超越百分比
									int str =  DateUtil.getTranscend(dailySignService.selectCount(),dailySignService.selectCountByPoint(daily2.getPoint()) );
									article.setPicUrl(
											MenuManager.REAL_URL+"/images/1.png");
									article.setUrl(MenuManager.REAL_URL+"pages/weixin/sign/dailySign.jsp?signstr="+daily2.getSignstr()+
											"&point="+daily2.getPoint()+"&status="+daily2.getStatus()+"&str="+str);
								}//代表是昨天签到
								else if(num==1) {
									//连续登录+2
									daily1.setPoint(daily1.getPoint()+2);
									//连续登录就把状态改为1
									daily1.setStatus(1);
									daily1.setNum(daily1.getNum()+1);
									//连续登录天数+1
									daily1.setCount(daily1.getCount()+1);
									daily1.setSigntime(new Date());
									//签到时间 做一个拼接
									String dailyStr = daily1.getSignstr()+","+DateUtil.getTodayDate();
									daily1.setSignstr(dailyStr);
									//然后更新到数据库中
									dailySignService.updateByPrimaryKeySelective(daily1);
									DailySign daily2=dailySignService.selectByUid(user.getId());
									//求超越百分比
									int str =  DateUtil.getTranscend(dailySignService.selectCount(),dailySignService.selectCountByPoint(daily2.getPoint()) );
									article.setTitle("每日签到");
									article.setDescription(DateUtil.getTodayDate() +"签到成功,您已成功签到"+daily2.getNum()+"天,连续签到了"+daily2.getCount()+"天"
											+"超越了"+str+"%人");
									article.setPicUrl(
											MenuManager.REAL_URL+"/images/1.png");
									article.setUrl(MenuManager.REAL_URL+"pages/weixin/sign/dailySign.jsp?signstr="+daily2.getSignstr()
									+"&point="+daily2.getPoint()+"&status="+daily2.getStatus()+"&str="+str);
								
								}else {//签到中断
									//+1
									daily1.setPoint(daily1.getPoint()+1);
									//中断就把状态改为0
									daily1.setStatus(0);
									daily1.setNum(daily1.getNum()+1);
									//连续登录天数清空为1
									daily1.setCount(1);
									daily1.setSigntime(new Date());
									//签到时间 做一个拼接
									String dailyStr = daily1.getSignstr()+","+DateUtil.getTodayDate();
									daily1.setSignstr(dailyStr);
									//然后更新到数据库中
									dailySignService.updateByPrimaryKeySelective(daily1);
									DailySign daily2=dailySignService.selectByUid(user.getId());
									//求超越百分比
									int str =  DateUtil.getTranscend(dailySignService.selectCount(),dailySignService.selectCountByPoint(daily2.getPoint()) );
									article.setTitle("每日签到");
									article.setDescription(DateUtil.getTodayDate() +"签到成功,您已成功签到"+daily2.getNum()+"天,连续签到了"+daily2.getCount()+"天"
											+"超越了"+str+"%人");
									article.setPicUrl(
											MenuManager.REAL_URL+"/images/1.png");
									article.setUrl(MenuManager.REAL_URL+"pages/weixin/sign/dailySign.jsp?signstr="+daily2.getSignstr()
									+"&point="+daily2.getPoint()+"&status="+daily2.getStatus()+"&str="+str);
								}
								

							}
							
							
							
							
							
							
							
							articleList.add(article);						
							// 设置图文消息个数
							newsMessage.setArticleCount(articleList.size());
							// 设置图文消息
							newsMessage.setArticles(articleList);
							// 将图文消息对象转换为XML字符串
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
							return respMessage;
						}
						
					}
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();
						
						// 单图文消息
						Article article = new Article();
						article.setTitle("标题");
						article.setDescription("描述内容");
						article.setPicUrl(
								"图片");
						article.setUrl("跳转连接");

						
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					
				}

			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
