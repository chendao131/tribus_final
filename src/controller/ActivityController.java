package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Activity;
import model.ActivityAlbum;
import model.ActivityClassified;
import model.ActivityComment;
import model.ActivityFollow;
import model.ActivityGoing;
import model.ActivityPic;

import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.GetLatLong;
import vo.ActivityGoingMax;
import vo.ActivityGoingTempResult;
import vo.Ele;
import vo.FriendComment;
import vo.MyActivity;
import vo.SuperActivity;
import vo.UserComment;
import vo.UserCommentSupper;
import vo.UserComments;

import dao.ActivityAlbumDao;
import dao.ActivityClassifiedDao;
import dao.ActivityCommentDao;
import dao.ActivityDao;
import dao.ActivityFollowDao;
import dao.ActivityGoingDao;
import dao.ActivityPicDao;
import dao.FollowDao;

import dao.UserDao;

@Controller
// 告诉容器这是controller处理类
@RequestMapping("activity")
// 告诉容器这是controller里面的对应activity的处理类
public class ActivityController {
	private ActivityDao activityDao = new ActivityDao();
	private ActivityFollowDao activityFollowDao = new ActivityFollowDao();
	private ActivityGoingDao activityGoingDao = new ActivityGoingDao();
	private FollowDao followDao = new FollowDao();	
	private ActivityPicDao activityPicDao = new ActivityPicDao();
	private ActivityAlbumDao activityAlbumDao = new ActivityAlbumDao();
	private ActivityCommentDao activityCommentDao = new ActivityCommentDao();
	private ActivityClassifiedDao activityClassifiedDao = new ActivityClassifiedDao();
	private UserDao userDao = new UserDao();
	
	private FollowDao fd = new FollowDao();
	private Integer userId = 123;

	@RequestMapping("creat")
	public ModelAndView indexActivity() {

		ModelAndView view = new ModelAndView();
		view.setViewName("activity/creat");

		return view;
	}

	@RequestMapping("activityCreatInit")
	// 创建活动
	public ModelAndView activityCreateInit(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		List<ActivityClassified> ac = activityClassifiedDao
				.getAllActivityClassified();
		System.out.println(ac.size());
		view.addObject("activityClassified", ac);// 将activityList放入容器
		view.setViewName("activity/create_activity");// 设置对应的视图view/activity/myActivity.jsp

		return view;
	}

	@RequestMapping("activityCreat")
	// 创建活动
	public String creatActivity(HttpServletRequest request,
			HttpServletResponse response) {

		String activityName = request.getParameter("activityName");
		Date activityStartTime = null;
		Date activityFinishTime = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			activityStartTime = dateFormat.parse(request
					.getParameter("activitystartDate"));
			activityFinishTime = dateFormat.parse(request
					.getParameter("activityfinishDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer classifiedId;
		if(request.getParameter("classifiedId")==null){
			classifiedId=6;
		}else{
		 classifiedId = Integer.parseInt(request
				.getParameter("classifiedId"));}
		String activityDuration = request.getParameter("activityDuration");
		String activityDetail = request.getParameter("activityDetail");


		Double activityFees = Double.parseDouble(request
				.getParameter("activityFees"));
		String activityCity = request.getParameter("activityCity");
		String activityApt = request.getParameter("activityApt");
		String activityStreet = request.getParameter("activityStreet");
		// List<String> activityPic = new ArrayList(Arrays.asList(request
		// .getParameterValues("pic")));// 从页面空间取出pic绝对路径
		String activityPic = request.getParameter("hidden_para2");// 从页面空间取出pic绝对路径

		Date recordDate = new Date();

		Activity activity = new Activity();
		activity.setActivityCity(activityCity);
		activity.setActivityApt(activityApt);
		activity.setActivityStreet(activityStreet);
		activity.setActivityDetail(activityDetail);
		activity.setActivityDuration(activityDuration);
		activity.setActivityFees(activityFees);
		activity.setActivityFinishTime(activityFinishTime);

		// activity.setActivityLati(activityLati);
		// activity.setActivityLongi(activityLongi);
//		activity.setActivityMaxNumber(activityMaxNumber);
		activity.setActivityName(activityName);

		activity.setActivityPic(activityPic);

		activity.setActivityStartTime(activityStartTime);

		activity.setClassifiedId(classifiedId);
		activity.setRecordDate(recordDate);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		activity.setUserId(userId);
		activity.setRecordDate(recordDate);
		if (activityDao.addActivity(activity)) {// 持久化活动对象
			return "activity/creatSuccess";

		} else {
			return "activity/error";
		}

	}

	@RequestMapping("index")
	// 同城主页
	public ModelAndView indexActivity(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();

		List<Activity> hottestActivityList = activityDao.getHottestActivity();// 取出推荐同城列表(5个推荐度从上到下依次增大)

		// 取出我关注的同城列表
		// Integer userId = Integer.parseInt(request.getParameter("userId"));//
		// 从request取出我的userId
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			userId = 123;
		} else {
			userId = u.getUserId();
		}
		List<ActivityFollow> activityFollowList = activityFollowDao
				.getActivityFollowByCondition(0, userId);
		Iterator iterator1 = activityFollowList.iterator();
		List<Activity> myActivityList = new ArrayList<Activity>();// 创建我的活动列表

		while (iterator1.hasNext()) {
			Integer activityId = ((ActivityFollow) iterator1.next())
					.getActivityId();
			myActivityList.add(activityDao.getActivityById(activityId));// 将新得到的activity对象放入我关注的活动列表里面;
		}// 得到我关注的同城列表

		// 得到好友关注同城列表

		List<User> friendList = fd.getAllFriends(userId);
		List<Activity> friendActivityList = new ArrayList();
		Iterator iterator2 = friendList.iterator();

		while (iterator2.hasNext()) {
			User user = (User) iterator2.next();
			List<ActivityFollow> activityFriendFollowList = activityFollowDao
					.getActivityFollowByCondition(0, user.getUserId());// 获得一个好友的关注活动对象列表
			Iterator iteratorTemp = activityFriendFollowList.iterator();
			// List<Activity> activityListTemp = new ArrayList<Activity>();//
			// 创建一个好友活动列表
			while (iteratorTemp.hasNext()) {
				Integer activityIdTemp = ((ActivityFollow) iteratorTemp.next())
						.getActivityId();
				Activity activityTemp = activityDao
						.getActivityById(activityIdTemp);// 得到好友的一个活动
				friendActivityList.add(activityTemp);// 将新得到的activity对象放入好友关注的活动列表
			}

		}// 得到好友关注同城列表friendsActivity

		if (u == null) {
			userId = 123;
		} else {
			userId = u.getUserId();
		}
		// 得到用户最近的当月活动
		List<ActivityGoing> latestActivity = activityGoingDao
				.getActivityGoingByCondition(0, userId);// 得到活动参与表对象
		List<Activity> activityList = new ArrayList();// 创建一个活动对象列表
		Iterator iteratorActivityGoing = latestActivity.iterator();
		List<Activity> activityAfterFilter = new ArrayList();
		while (iteratorActivityGoing.hasNext()) {
			activityList.add(activityDao
					.getActivityById(((ActivityGoing) iteratorActivityGoing
							.next()).getActivityId()));
		}
		// 得到当前用户参加的所有活动list(包括今天以前的)

		Comparator comp = new Activity();// 导出排序规则；
		Collections.sort(activityList, comp);// 排序完成从小到大
		Date date = new Date();// 获得当前时间

		for (int i = 0; i < activityList.size(); i++) {
			if (date.after(activityList.get(i).getActivityStartTime())) {// 如果当前时间晚于当前活动时间
				// 则跳过

			} else {
				activityAfterFilter.add(activityList.get(i));// 将满足条件的活动添加进去结果List
			}
		}// 至此得到了当前用户未来参加activity的list，然后继续判断
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH);// 获取月份

		if (activityAfterFilter.size() == 0
				|| activityAfterFilter.get(0).getActivityStartTime().getMonth() != month) {// 如果最终结果集为零或者结果集中任何一个元素都不在当月

			view.addObject("latestActivity", null);// 当月无活动
		} else {

			view.addObject("latestActivity", activityAfterFilter.get(0));
		}
		List activityGoingNumbersList = activityGoingDao
				.getActivityGoingAsNumbers();// 取出三个人最多的活动
		List activityGoingMaxList = new ArrayList();// 创建 volist
		Iterator iteratorGoing = activityGoingNumbersList.iterator();
		while (iteratorGoing.hasNext()) {
			ActivityGoingMax activityGoingMax = new ActivityGoingMax();
			ActivityGoingTempResult tempResult = (ActivityGoingTempResult) iteratorGoing
					.next();
			activityGoingMax.setActivityId(activityDao.getActivityById(// vo对象组装开始
					tempResult.getActivityId()).getActivityId());
			activityGoingMax.setPicPath(activityDao.getActivityById(
					tempResult.getActivityId()).getActivityPic());
			activityGoingMax.setNumbers(tempResult.getNumber());
			activityGoingMax.setActivityName(activityDao.getActivityById(
					tempResult.getActivityId()).getActivityName());
			activityGoingMax.setUserName(userDao.getUserById(
					activityDao.getActivityById(tempResult.getActivityId())
							.getUserId()).getUserAlias());
			activityGoingMax.setActivityStartTime(activityDao.getActivityById(
					tempResult.getActivityId()).getActivityStartTime());// vo对象组装完成
			activityGoingMaxList.add(activityGoingMax);// vo对象添加list
		}
		// 得到活动最多的3个城市
		List commentListRandom = new ArrayList();// 随机评论表
		List<ActivityComment> activityCommentList = activityCommentDao
				.getActivityCommentRandom();//随即取出一个评论list
		Iterator commentIterator = activityCommentList.iterator();
		while (commentIterator.hasNext()) {
			ActivityComment activityComment = (ActivityComment) commentIterator
					.next();
			List<ActivityComment> ActivityCommentSonList=activityCommentDao.getActivityCommentByCondition(activityComment.getActivityId(), 0);//对某一条活动找出所有此活动的评论
			
			Iterator aCommentsIterator = ActivityCommentSonList.iterator();
			
			List<UserComments> comments = new ArrayList();
			while (aCommentsIterator.hasNext()) {
				ActivityComment aComment = (ActivityComment) aCommentsIterator
						.next();
				UserComments userComments = new UserComments();
				userComments.setUserComment(aComment
						.getCommentContent());
				userComments.setUserId(aComment.getUserId());
				userComments.setUserPic(userDao.getUserById(
						aComment.getUserId()).getUserPic());
				comments.add(userComments);// 组装好评论List
					
			}
			FriendComment friendComment = new FriendComment();
			Activity a =activityDao.getActivityById(activityComment.getActivityId());//找出这个评论记录对应的活动
			friendComment.setActivity(a);
			if(comments.size()>4){
				comments=comments.subList(0, 4);
			}
			friendComment.setUserComment(comments);
			commentListRandom.add(friendComment);// 添加对象,加进list
		}
		
		List activityTagsList = activityClassifiedDao
				.getAllActivityClassified();
		if (activityGoingMaxList.size() > 4) {

			activityGoingMaxList = activityGoingMaxList.subList(0, 4);// 如果大于4个
			// 那么只截取4个
		}

		List<FriendComment> commentListRandom1 = new ArrayList();
		// 登录状态下，抓取好友参加过的活动
		if (u != null) {

			// 从follow表里拿出followee (User对象)
			List followeeUser = followDao.getAllFriends(u.getUserId());
			Iterator followeeUserIterator = followeeUser.iterator();
			List<UserComments> comments = new ArrayList();
			while (followeeUserIterator.hasNext()) {
				User fu = (User) followeeUserIterator.next();
				List<ActivityGoing> activityGoingList = activityGoingDao
						.getActivityGoingByCondition(0, fu.getUserId());
				if (activityGoingList == null) {// 如果为空说明 这傻逼没有参加过任何活动
					continue;
				} else {// 拿出一个user对象的参加的一个活动
					// 去activitiyGoing，查处对应的活动Id
					// 通过活动ID去活动列表里面查出对应的活动
					Activity a = activityDao.getActivityById(activityGoingList
							.get(0).getActivityId());
					// 查出这个活动的所有评论对象+评论人对象
					List aComments = activityCommentDao
							.getActivityCommentByCondition(a.getActivityId(), 0);
					Iterator aCommentsIterator = aComments.iterator();
				

					while (aCommentsIterator.hasNext()) {
						ActivityComment aComment = (ActivityComment) aCommentsIterator
								.next();
						UserComments userComments = new UserComments();
						userComments.setUserComment(aComment
								.getCommentContent());
						userComments.setUserId(aComment.getUserId());
						userComments.setUserPic(userDao.getUserById(
								aComment.getUserId()).getUserPic());
						comments.add(userComments);// 组装好评论List
						

						
					}
					FriendComment friendComment = new FriendComment();
					friendComment.setActivity(a);
					friendComment.setUserComment(comments);
					commentListRandom1.add(friendComment);// 添加对象,加进list
				}

			}

		}

		// //取出reminds list
		// List<ActivityComment>
		// acList=activityCommentDao.getActivityCommentByCondition(0,
		// userId);//把用户的所有评论取出
		// Iterator acListIterator=acList.iterator();
		// while(acListIterator.hasNext()){
		// ActivityComment ac=(ActivityComment)acListIterator.next();
		// List<ActivityComment>
		// acSonList=activityCommentDao.getActivityCommentByCondition(ac.getActivityId(),
		// 0);//其中的每一条扫描，找出其中每一条的所有评论对象
		// Iterator acSonListIterator = acSonList.iterator();
		// while(acSonListIterator.hasNext()){
		// ActivityComment acSon=(ActivityComment)acListIterator.next();
		// if(ac.getCommentDate())
		// }
		//			
		// }

		List topTribusCity = activityDao.getTopTribusCity();
		view.addObject("activityTagsList", activityTagsList);// 将所有标签list放入容器
		view.addObject("topTribusCity", topTribusCity);// 放入topTribusCity
		view.addObject("myActivityList", myActivityList);// 放入我的同城
		view.addObject("hottestActivityList", hottestActivityList);// 放入推荐同城
		view.addObject("hottestActivity", hottestActivityList.get(0));// 放入推荐同城
		view.addObject("friendActivityList", friendActivityList);// 放入好友同城

		view.addObject("activityGoingMaxList", activityGoingMaxList);// 将最多参加人数活动列表放入容器传回页面
		if (u == null) {
			view.addObject("commentListRandom", commentListRandom);// 将随机评论列表放入容器传回页面
		} else {
			view.addObject("commentListRandom", commentListRandom1);// 将好友随机评论列表放入容器传回页面
		}
		view.addObject("user", u);//将session里面的user对象传到页面
		view.setViewName("activity/index");// 设置视图地址为http：//www.tribus.com/view/activity/index.jsp
		return view;// 返回视图

	}

	@RequestMapping("info")
	// 同城详细主页
	public ModelAndView activityInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// 从request取出活动activityId
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		Activity activity = activityDao.getActivityById(activityId);// 得到此活动的对象

		List<ActivityGoing> activityGoingList = activityGoingDao
				.getActivityGoingByCondition(activityId, 0);// 得到此活动所有ActivityGoing对象
		Iterator iterator = activityGoingList.iterator();
		List<User> userList = new ArrayList();// 创建所有going此活动的userList
		while (iterator.hasNext()) {
			ActivityGoing activityGoing = (ActivityGoing) iterator.next();
			userList.add(userDao.getUserById(activityGoing.getUserId()));// 把f参加此活动的user添加到list
		}

	List<User> friends = followDao.getAllFriends(userId);// 得到此userId所有followee对象

		List<User> friendFollow = new ArrayList<User>(); // 创建空容器"好友参加活动list"

		for (User user1 : friends) {
			if (userList.contains(user1)) {
				friendFollow.add(user1);
			}
		}

		List<ActivityAlbum> activityAlbumList = new ArrayList();
		activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				activityId, 0);// 根据一个活动的id得到相册list
		Integer flag=0;
		if (activityAlbumList.size() > 10) {// 如不满10则补齐佢
			activityAlbumList=activityAlbumList.subList(0, 10);
flag=1;
		}
		GetLatLong getLatLong = new GetLatLong();
		String[] location = getLatLong.getLatlng(activity.getActivityStreet()
				+ "," + activity.getActivityCity());

		List followList = activityFollowDao.getActivityFollowByCondition(
				activityId, userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动
		String followed = null;
		String joined = null;
		String owner = null;

		if (userId == activity.getUserId()) {// 判断当前用户是否是此活动的发起者
			owner = "true";

		} else {
			if (followList.size() > 0) {
				followed = "true";// 表示已关注
			} else {
				followed = "false";// 表示未关注
			}
			List goingList = activityGoingDao.getActivityGoingByCondition(
					activityId, userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

			if (goingList.size() > 0) {
				joined = "true";// 表示已join
			} else {
				joined = "false";// 表示未join
			}
		}
		List commentList = activityCommentDao.getActivityCommentByCondition(
				activityId, 0);// 评论dao
		List userCommentList = new ArrayList();
		Iterator iteratorComment = commentList.iterator();
		while (iteratorComment.hasNext()) {
			ActivityComment activityComment = (ActivityComment) iteratorComment
					.next();
			UserComment userComment = new UserComment();// VO对象封装开始
			userComment.setCommentContent(activityComment.getCommentContent());
			userComment.setCommentDate(activityComment.getCommentDate());
			userComment.setUserName(userDao.getUserById(
					activityComment.getUserId()).getUserAlias());
			userComment.setUserPic(userDao.getUserById(
					activityComment.getUserId()).getUserPic());// VO对象封装结束
			userComment.setUserId(activityComment.getUserId());
			userCommentList.add(userComment);
		}
String activtiyClassified=activityClassifiedDao.getClassifiedTagById(activity.getClassifiedId());
		List recommentActivity = activityDao.getAllActivity();// 取推荐活动
		recommentActivity = recommentActivity.subList(7, 10);// 取推荐活动
		view.setViewName("activity/info");// 设置对应的视图view/activity/info.jsp
		view.addObject("activityAlbum", activityAlbumList);// 将相册list放入容器，传回页面
		view.addObject("flag", flag);// 若相册listsize大于10 此值为1 反之则为0将其放入容器，传回页面
		view.addObject("activityClassified", activtiyClassified);// 将activtiyClassified放入容器 传回页面
		view.addObject("activityLat", location[2]);// 将地址纬度放入容器，传回页面
		view.addObject("activityLong", location[3]);// 将地址经度放入容器，传回页面
		view.addObject("activityInfo", activity);// 将activity放入容器 传回页面
		view.addObject("followList", friendFollow);// 将followList放入容器 传回页面
		view.addObject("followed", followed);// 将followed状态放入容器传回页面
		view.addObject("joined", joined);// 将joined状态放入容器传回页面
		view.addObject("owner", owner);// 将owner状态放入容器传回页面
		view.addObject("userList", userList);// 将所有参加此活动的用户放入容器，传回页面
		view.addObject("userCommentList", userCommentList);// 将留言VO对象放入容器传回页面
		view.addObject("recommentActivity", recommentActivity);// 将推荐活动list放入容器传回页面
		view.addObject("activityId", activityId);// 将activityId状态放入容器传回页面

		view.setViewName("activity/activity");

		return view;
	}// 返回视图

	@RequestMapping("friendsActivity")
	// 同城详细主页
	public ModelAndView friendsActivity(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		List<Activity> activityListByFriend=new ArrayList();
		List<User> friends = followDao.getAllFriends(userId);// 得到此userId所有followee对象
		Iterator iterator=friends.iterator();
		while(iterator.hasNext()){
			User u=(User) iterator.next();
			List<ActivityGoing> activityGoingList=activityGoingDao.getActivityGoingByCondition(0, u.getUserId());//得到某一个好友的所有参加的活动List
			Iterator<ActivityGoing> activityGoingListIterator=activityGoingList.iterator();
			while(activityGoingListIterator.hasNext()){//遍历某个好友参加的所有活动goingList
				ActivityGoing activityGoing=activityGoingListIterator.next();//得到某一好友参加的的某一个活动going对象
				Activity activity=activityDao.getActivityById(activityGoing.getActivityId());//得到某一好友参加的的某一个activity对象
				activityListByFriend.add(activity);//添加到结果集中
			}
		}

		String followed = null;
		String joined = null;
		String owner = null;
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		}

		List<SuperActivity> superActivityList = new ArrayList();//vo 对象 最终传回页面的list
		Iterator activityListByFriendIterator=activityListByFriend.iterator();
		while(activityListByFriendIterator.hasNext()){
			
			Activity a=(Activity)activityListByFriendIterator.next();
			List followList = activityFollowDao.getActivityFollowByCondition(a
					.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

			if (userId == a.getUserId()) {// 判断当前用户是否是此活动的发起者
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// 表示已关注
				} else {
					followed = "false";// 表示未关注
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否参加此活动

				if (goingList.size() > 0) {
					joined = "true";// 表示已join
				} else {
					joined = "false";// 表示未join
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);//最终的结果集（其实还需要做一步分页处理）传回页面
		}
		
		
		
		
		List recommentActivity = activityDao.getAllActivity();// 取推荐活动
		recommentActivity = recommentActivity.subList(7, 10);// 取推荐活动
		List result = new ArrayList();// 最终的结果集
		String[] pages = null;
		if (superActivityList.size() == 0) {// 如果结果集为空的时候

		} else {
			result = getListByPage(superActivityList, 4, page);// 4条一页 得到第page页！

			int pageNumbers = getPageNumber(superActivityList, 4);// 4条一页
			// ，得到一共几页
			pages = new String[pageNumbers];
			for (Integer i = 0; i < pageNumbers; i++) {
				pages[i] = i + 1 + "";
			}
		}
		List topTribusCity = activityDao.getTopTribusCity();
		view.addObject("topTribusCity", topTribusCity);//top tribus city放入容器传回页面
		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("activityList", result);// 将得到的结果（指定页码位置）放入容器 传回页面！
	view.addObject("followed", followed);// 将followed状态放入容器传回页面
		view.addObject("joined", joined);// 将joined状态放入容器传回页面
		view.addObject("owner", owner);// 将owner状态放入容器传回页面
		view.addObject("recommentActivity", recommentActivity);// 将推荐活动list放入容器传回页面
	
		view.setViewName("activity/friends_activity");
		return view;
	}

	@RequestMapping("activityAlbum/{activityAlbumId}")
	// 打开一个相册
	public ModelAndView activityAlbumPic(
			@PathVariable("activityAlbumId") Integer activityAlbumId) {
		ModelAndView view = new ModelAndView();

		List<ActivityPic> activityPicList = activityPicDao
				.getActivityPicByCondition(0, activityAlbumId);// 通过某一相册id获得此相册下的所有picList;
		view.setViewName("activity/pic");// 设置对应的视图view/activity/pic.jsp
		view.addObject("activityPic", activityPicList);// 将相片list放入容器，传回页面
		return view;// 返回视图
	}

	@RequestMapping("search")
	// 搜索框
	public ModelAndView searchActivity(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		String condition = request.getParameter("searchCondition");// 从搜索框里取出条件
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		}
		String followed = null;
		String joined = null;
		String owner = null;
		List<Activity> activityList = activityDao
				.getActivityByAbstractCondition(condition);
		List<SuperActivity> superActivityList = new ArrayList();// 最终传回页面的list
		Iterator activityListIterator = activityList.iterator();
		while (activityListIterator.hasNext()) {
			Activity a = (Activity) activityListIterator.next();

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				userId = 123;
			} else {
				userId = user.getUserId();
			}
			List followList = activityFollowDao.getActivityFollowByCondition(a
					.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

			if (userId == a.getUserId()) {// 判断当前用户是否是此活动的发起者
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// 表示已关注
				} else {
					followed = "false";// 表示未关注
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// 从数据库给出双条件查询，探测出当前用户是否参加此活动

				if (goingList.size() > 0) {
					joined = "true";// 表示已join
				} else {
					joined = "false";// 表示未join
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);
		}
		List result = new ArrayList();// 最终的结果集
		String[] pages = null;
		if (superActivityList.size() == 0) {// 如果结果集为空的时候

		} else {
			result = getListByPage(superActivityList, 4, page);// 4条一页 得到第page页！

			int pageNumbers = getPageNumber(superActivityList, 4);// 4条一页
			// ，得到一共几页
			pages = new String[pageNumbers];
			for (Integer i = 0; i < pageNumbers; i++) {
				pages[i] = i + 1 + "";
			}
		}
		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("activityList", result);// 将得到的结果（指定页码位置）放入容器 传回页面！
		view.addObject("condition", condition);// 将搜索条件放入容器传回页面 留作分页之用
		view.addObject("followed", followed);// 将followed状态放入容器传回页面
		view.addObject("joined", joined);// 将joined状态放入容器传回页面
		view.addObject("owner", owner);// 将owner状态放入容器传回页面
		view.setViewName("activity/search_result");// 设置对应的视图view/activity/view.jsp
		return view;
	}

	@RequestMapping("byCityTag")
	// 按照热门城市标签搜索
	public ModelAndView searchActivityByCityTag(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		}

		String activityCity = request.getParameter("city");// 取出tag标签的值

		List<Activity> activityList = activityDao.getActivityByCondition(null,
				activityCity, null, null);

		String followed = null;
		String joined = null;
		String owner = null;
		List<SuperActivity> superActivityList = new ArrayList();// 最终传回页面的list
		Iterator activityListIterator = activityList.iterator();
		while (activityListIterator.hasNext()) {
			Activity a = (Activity) activityListIterator.next();

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				userId = 123;
			} else {
				userId = user.getUserId();
			}
			List followList = activityFollowDao.getActivityFollowByCondition(a
					.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

			if (userId == a.getUserId()) {// 判断当前用户是否是此活动的发起者
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// 表示已关注
				} else {
					followed = "false";// 表示未关注
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

				if (goingList.size() > 0) {
					joined = "true";// 表示已join
				} else {
					joined = "false";// 表示未join
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);
		}

		List result = new ArrayList();// 最终的结果集

		result = getListByPage(superActivityList, 4, page);// 4条一页 得到第page页！

		int pageNumbers = getPageNumber(superActivityList, 4);// 4条一页 ，得到一共几页
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}
		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("activityList", result);// 将得到的结果（指定页码位置）放入容器 传回页面！
		view.addObject("city", activityCity);// 将此时的搜索条件放回页面，留着分页再用
		view.addObject("followed", followed);// 将followed状态放入容器传回页面
		view.addObject("joined", joined);// 将joined状态放入容器传回页面
		view.addObject("owner", owner);// 将owner状态放入容器传回页面
		view.setViewName("activity/search_result");// 设置对应的视图view/activity/view.jsp

		return view;
	}

	@RequestMapping("followActivity")
	// 关注/取消关注此活动
	public void followUnFollowAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// 取出当前的活动id

		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}

		List<ActivityFollow> followList = activityFollowDao
				.getActivityFollowByCondition(activityId, userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

		if (followList.size() > 0) {// 此时是关注的状态，说明用户想执行解关注操作

			if (activityFollowDao.delActivityFollow(followList.get(0))) {
				response.getWriter().write("Follow");// 标志传回页面，活动变为加关注状态
			}

		} else {// 此时是未关注的状态，说明用户想执行加关注操作
			ActivityFollow activityFollow = new ActivityFollow();

			// 开始组装活动关注者对象
			activityFollow.setActivityId(activityId);
			activityFollow.setUserId(userId);// 活动关注者对象组装完成
			if (activityFollowDao.addActivityFollow(activityFollow)) {
				response.getWriter().write("unFollow");// 标志传回页面，活动变为加关注状态

			} else {
				response.getWriter().write("fail to follow!sorry");// 标志传回页面(加关注失败）
			}
		}

	}

	@RequestMapping("joinActivity")
	// 参加/取消参加此活动
	public void joinUnjoinAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// 取出当前的活动id

		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}

		List<ActivityGoing> goingList = activityGoingDao
				.getActivityGoingByCondition(activityId, userId);// 从数据库给出双条件查询，探测出此用户是否join此活动

		if (goingList.size() > 0) {// 此时是join的状态，说明用户想执行解join操作

			if (activityGoingDao.delActivityGoing(goingList.get(0))) {
				response.getWriter().write("join");// 标志传回页面，活动变为Join状态
			}

		} else {// 此时是未join的状态，说明用户想执行加join操作
			ActivityFollow activityFollow = new ActivityFollow();

			// 开始组装活动关注者对象
			activityFollow.setActivityId(activityId);
			activityFollow.setUserId(userId);// 活动关注者对象组装完成

			ActivityGoing activityGoing = new ActivityGoing();

			// 开始组装活动join者对象
			activityGoing.setActivityId(activityId);
			activityGoing.setUserId(userId);// 活动join者对象组装完成
			if (activityGoingDao.addActivityGoing(activityGoing)) {
				if (activityFollowDao.getActivityFollowByCondition(activityId,
						userId).size() == 0) {// 说明没有加关注，所以此时添加关注
					activityFollowDao.addActivityFollow(activityFollow);
				}
				response.getWriter().write("unjoin");// 标志传回页面，活动变为going状态

			} else {
				response.getWriter().write("fail to join!sorry");// 标志传回页面(join失败）
			}
		}

	}

	@RequestMapping("addComment")
	// 用户留言
	public ModelAndView addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// 取出当前的活动id
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		String commentContent = request.getParameter("commentContent");// 取出当前的留言内容

		ActivityComment activityComment = new ActivityComment();
		activityComment.setActivityId(activityId);
		activityComment.setCommentContent(commentContent);
		activityComment.setCommentDate(new Date());
		activityComment.setUserId(userId);
		activityCommentDao.addActivityComment(activityComment);
		view.setViewName("redirect:info.action?activityId=" + activityId);// 设置对应的视图view/activity/info.jsp
		return view;
	}

	@RequestMapping("addPicIndex")
	// 上传活动图片初始页面
	public ModelAndView addPicIndex(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));
		List<ActivityAlbum> activityAlbumList = activityAlbumDao
				.getActivityAlbumByCondition(activityId, 0);
		String size = "0";
		if (activityAlbumList.size() == 0) {

		} else {
			size = "1";
		}
		view.addObject("size", size);// 如果没有创建相册 则放入此值 传回页面。以作检查
		view.addObject("activityAlbumList", activityAlbumList);// 将得到的结果放入容器
		view.addObject("activityId", activityId);// 将activityId放入容器
		// 传回页面！
		view.setViewName("activity/upload_pics");// 设置对应的视图view/activity/view.jsp
		return view;

	}

	@RequestMapping("addPic")
	// 上传活动图片
	public ModelAndView addPic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		// ***********************************
		String activityPicPath1 = request.getParameter("pic_link1");// 从页面空间取出pic绝对路径
		if (!activityPicPath1.equals("")) {// 判断如果第一个相框不为空的话
			String activityAlbumName1;
			String picDescription1;
			activityAlbumName1 = request.getParameter("albumNamee1");// 从页面空间取出albumname属性
			picDescription1 = request.getParameter("desc1");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName1.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId1"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath1);
				activityAlbum.setAlbumName(activityAlbumName1);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId再存进pic表里

			}
			activityPic.setPicPath(activityPicPath1);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription1);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// ********************************
		String activityPicPath2 = request.getParameter("pic_link2");// 从页面空间取出pic绝对路径
		if (!activityPicPath2.equals("")) {// 判断如果第2个相框不为空的话
			String activityAlbumName2;
			String picDescription2;
			activityAlbumName2 = request.getParameter("albumNamee2");// 从页面空间取出albumname属性
			picDescription2 = request.getParameter("desc2");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName2.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId2"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath2);
				activityAlbum.setAlbumName(activityAlbumName2);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId再存进pic表里

			}
			activityPic.setPicPath(activityPicPath2);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription2);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// ***************************
		String activityPicPath3 = request.getParameter("pic_link3");// 从页面空间取出pic绝对路径
		if (!activityPicPath3.equals("")) {// 判断如果第3个相框不为空的话
			String activityAlbumName3;
			String picDescription3;
			activityAlbumName3 = request.getParameter("albumNamee3");// 从页面空间取出albumname属性
			picDescription3 = request.getParameter("desc3");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName3.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId3"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath3);
				activityAlbum.setAlbumName(activityAlbumName3);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId然后加1再存进pic表里

			}
			activityPic.setPicPath(activityPicPath3);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription3);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// ************************
		String activityPicPath4 = request.getParameter("pic_link4");// 从页面空间取出pic绝对路径
		if (!activityPicPath4.equals("")) {// 判断如果第4个相框不为空的话
			String activityAlbumName4;
			String picDescription4;
			activityAlbumName4 = request.getParameter("albumNamee4");// 从页面空间取出albumname属性
			picDescription4 = request.getParameter("desc4");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName4.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId4"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath4);
				activityAlbum.setAlbumName(activityAlbumName4);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId然后加1再存进pic表里

			}
			activityPic.setPicPath(activityPicPath4);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription4);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// **************
		String activityPicPath5 = request.getParameter("pic_link5");// 从页面空间取出pic绝对路径
		if (!activityPicPath5.equals("")) {// 判断如果第5个相框不为空的话
			String activityAlbumName5;
			String picDescription5;
			activityAlbumName5 = request.getParameter("albumNamee5");// 从页面空间取出albumname属性
			picDescription5 = request.getParameter("desc5");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName5.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId5"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath5);
				activityAlbum.setAlbumName(activityAlbumName5);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId然后加1再存进pic表里

			}
			activityPic.setPicPath(activityPicPath5);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription5);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// **************
		String activityPicPath6 = request.getParameter("pic_link6");// 从页面空间取出pic绝对路径
		if (!activityPicPath6.equals("")) {// 判断如果第一个相框不为空的话
			String activityAlbumName6;
			String picDescription6;
			activityAlbumName6 = request.getParameter("albumNamee6");// 从页面空间取出albumname属性
			picDescription6 = request.getParameter("desc6");
			ActivityPic activityPic = new ActivityPic();// 开始拼装activityPic对象

			if (activityAlbumName6.equals("")) {// 说明是加到了旧有相册--更新旧有相册
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId6"));// 从页面空间取出相册id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// 更新相片数量：将原有的数量+新的数量
				activityAlbumDao.updateActivityAlbum(activityAlbum);// 更新回數據庫
			} else {// 添加新相册
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath6);
				activityAlbum.setAlbumName(activityAlbumName6);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// 添加到相册表

				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// 找到最大albumId再存进pic表里

			}
			activityPic.setPicPath(activityPicPath6);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription6);
			activityPicDao.addActivityPic(activityPic);// 添加到相片表
		}

		// 传回页面！
		view.setViewName("redirect:info.action?activityId=" + activityId);// 设置对应的视图view/activity/showPicList.jsp
		return view;
	}

	@RequestMapping("album")
	// 展示专辑列表
	public ModelAndView showAlbumList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));
		HttpSession session = request.getSession();

		List<ActivityAlbum> activityAlbumList = new ArrayList();
		activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				activityId, 0);// 根据一个活动的id得到相册list

		List result = new ArrayList();// 最终的结果集
		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		} // 和 一个评论list
		result = getListByPage(activityAlbumList, 6, page);// 6条一页 得到第page页！
		int pageNumbers = getPageNumber(activityAlbumList, 6);// 6条一页 ，得到一共几页
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("activityId", activityId);
		view.addObject("activityAlbumList", result);

		view.setViewName("activity/album");
		return view;
	}

	@RequestMapping("editAlbum")
	// 上传活动图片
	public ModelAndView editAlbum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();

		// ******
		String albumId1 = request.getParameter("albumIdd1");
		if (albumId1 != null) {// 如果有第一个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc1");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId1));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		// ********
		String albumId2 = request.getParameter("albumIdd2");
		if (albumId2 != null) {// 如果有第2个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc2");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId2));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		// ********
		String albumId3 = request.getParameter("albumIdd3");
		if (albumId3 != null) {// 如果有第3个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc3");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId3));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		// *********
		String albumId4 = request.getParameter("albumIdd4");
		if (albumId4 != null) {// 如果有第4个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc4");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId4));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		// **********
		String albumId5 = request.getParameter("albumIdd5");
		if (albumId5 != null) {// 如果有第5个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc5");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId5));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		// ********
		String albumId6 = request.getParameter("albumIdd6");
		if (albumId6 != null) {// 如果有第6个框的话（说明必有元素，因为 如此 才能刷出个框来）

			String albumDescription = request.getParameter("desc6");

			List<ActivityAlbum> activityAlbumList = activityAlbumDao
					.getActivityAlbumByCondition(0, Integer.parseInt(albumId6));
			ActivityAlbum activityAlbum;
			if (activityAlbumList.size() != 0) {
				activityAlbum = activityAlbumList.get(0);
				activityAlbum.setAlbumDescription(albumDescription);
				activityAlbumDao.updateActivityAlbum(activityAlbum);
			}
		}
		String activityId = request.getParameter("activityId");
		// 传回页面！
		view.setViewName("redirect:info.action?activityId=" + activityId);// 设置对应的视图view/activity/showPicList.jsp
		return view;

	}

	@RequestMapping("showPicList")
	// 显示某一专辑所有图片内容
	public ModelAndView showPicList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer albumId = Integer.parseInt(request.getParameter("albumId"));
		List<ActivityPic> activityPics = activityPicDao
				.getActivityPicByCondition(0, albumId);// 得到相册id
		List activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				0, albumId);
		List result = new ArrayList();// 最终的结果集

		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		} // 和 一个评论list
		result = getListByPage(activityPics, 7, page);// 7条一页 得到第page页！
		int pageNumbers = getPageNumber(activityPics, 7);// 7条一页 ，得到一共几页
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("activityAlbum", activityAlbumList.get(0));
		view.addObject("activityPics", result);// 将activityPics放入容器
		// 传回页面！
		view.setViewName("activity/showPicList");// 设置对应的视图view/activity/showPicList.jsp
		return view;

	}

	@RequestMapping("showPic")
	// 上传活动图片
	public ModelAndView showPic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityPicId = Integer.parseInt(request
				.getParameter("activityPicId"));
		String albumName = request.getParameter("albumName");
		List<ActivityPic> activityPics = activityPicDao
				.getActivityPicByCondition(activityPicId, 0);// 得到相片id
		view.addObject("albumName", albumName);
		view.addObject("activityPics", activityPics.get(0));// 将activityPics放入容器
		// 传回页面！
		view.setViewName("activity/showPic");// 设置对应的视图view/activity/showPic.jsp
		return view;
	}

	@RequestMapping("searchByTag")
	// 按热门标签搜索
	public ModelAndView searchByTag(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		ModelAndView view = new ModelAndView();
		String tagName = request.getParameter("tagName");
		Integer classifiedId = activityClassifiedDao.getClassifiedIdByTag(tagName);
		List activityList = activityDao.getActivityByClassifiedId(classifiedId);
		List activityTagsList = activityClassifiedDao.getAllActivityClassified();
		List topTribusCity = activityDao.getTopTribusCity();
		
		
		List<User> followList = new ArrayList();// 将来的followList列表
		List<UserCommentSupper> UserComment = new ArrayList();// userCommetnSupper对象辖有activity对象
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		} // 和 一个评论list
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
				
		Iterator activityListIterator = activityList.iterator();
				
		while (activityListIterator.hasNext()) {
			UserCommentSupper u = new UserCommentSupper();
			Activity a = (Activity) activityListIterator.next();

			List activityCommentList = activityCommentDao
					.getActivityCommentByCondition(a.getActivityId(), 0);// 得到评论列表
			Iterator activityCommentListIterator = activityCommentList
					.iterator();// 遍历列表 去userDao里找出对应的User，然后组装生成 uc list
			List<UserComment> userCommentList = new ArrayList();// 得到volist
			while (activityCommentListIterator.hasNext()) {
				ActivityComment ac = (ActivityComment) activityCommentListIterator
						.next();
				UserComment uc = new UserComment();// 开始组装uc
				uc.setCommentContent(ac.getCommentContent());
				uc.setUserName(userDao.getUserById(ac.getUserId())
						.getUserAlias());
				uc.setUserPic(userDao.getUserById(ac.getUserId())
						.getUserAlias());// uc组装结束
				userCommentList.add(uc);// 添加到vo list
			}
			u.setActivity(a);
			while (userCommentList.size() < 2) {
				userCommentList.add(null);
			}
			u.setUserComment(userCommentList);
			UserComment.add(u);

			List<ActivityGoing> activityGoingList = activityGoingDao
					.getActivityGoingByCondition(a.getActivityId(), 0);// 得到此活动的userId
			Iterator activityGoingListIterator = activityGoingList.iterator();
			while (activityGoingListIterator.hasNext()) {
				ActivityGoing activityGoing = (ActivityGoing) activityGoingListIterator
						.next();
				followList.add(userDao.getUserById(activityGoing.getUserId()));
			}
		}		
		
		List result = new ArrayList();// 最终的结果集

		result = getListByPage(UserComment, 6, page);// 4条一页 得到第page页！
		while (result.size() < 6) {
			result.add(null);
		}

		int pageNumbers = getPageNumber(UserComment, 6);// 4条一页 ，得到一共几页
		int[] pages = new int[pageNumbers];
		for (int i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 ;
		}

		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		List recommendActivity = activityDao.getAllActivity();// 取推荐活动
		recommendActivity = recommendActivity.subList(1, 4);// 取推荐活动
		view.addObject("tagName", tagName);// 取出标签查询条件 放入容器传回页面，留作分页之途
		view.addObject("recommendActivity", recommendActivity);// 将recommendActivity放入容器//
		// // 传回页面！
		view.addObject("followList", followList);// 将followList放入容器// 传回页面！
		view.addObject("topTribusCity", topTribusCity);// 将热门城市放入容器// 传回页面！
		view.addObject("activityList", result);// 将activityList放入容器// 传回页面！
		view.addObject("activityTagsList", activityTagsList);// 将所有标签list放入容器
		view.setViewName("activity/tagResult");// 设置对应的视图view/activity/showPic.jsp
		return view;
	}

	@RequestMapping("myActivity")
	// 上传活动图片
	public ModelAndView myActivity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		String owner = null;// Host状态
		String joined = null;// 加入状态
		String followed = null;// 关注状态
		User u = (User) session.getAttribute("user");
		if (u == null) {
			userId = 123;
		} else {
			userId = u.getUserId();
		}

		List<ActivityFollow> activityFollowList = activityFollowDao
				.getActivityFollowByCondition(0, userId);// 得到当前用户follow的活动
		List<MyActivity> activityList = new ArrayList();
		Iterator activityFollowListIterator = activityFollowList.iterator();
		while (activityFollowListIterator.hasNext()) {
			ActivityFollow af = (ActivityFollow) activityFollowListIterator
					.next();
			Activity activity = activityDao.getActivityById(af.getActivityId());

			List activityCommentList = activityCommentDao
					.getActivityCommentByCondition(activity.getActivityId(), 0);// 得到评论列表
			Iterator activityCommentListIterator = activityCommentList
					.iterator();// 遍历列表 去userDao里找出对应的User，然后组装生成 uc list
			List<UserComments> userCommentsList = new ArrayList();// 得到volist
			while (activityCommentListIterator.hasNext()) {
				ActivityComment ac = (ActivityComment) activityCommentListIterator
						.next();
				UserComments uc = new UserComments();// 开始组装uc
				uc.setUserComment(ac.getCommentContent());
				uc.setUserId(ac.getUserId());
				uc.setUserPic(userDao.getUserById(ac.getUserId())
						.getUserAlias());// uc组装结束
				userCommentsList.add(uc);// 添加到vo list

				List followList = activityFollowDao
						.getActivityFollowByCondition(activity.getActivityId(),
								userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动
				if (userId == activity.getUserId()) {// 判断当前用户是否是此活动的发起者
					owner = "true";
				} else {
					if (followList.size() > 0) {
						followed = "true";// 表示已关注
					} else {
						followed = "false";// 表示未关注
					}
					List goingList = activityGoingDao
							.getActivityGoingByCondition(activity
									.getActivityId(), userId);// 从数据库给出双条件查询，探测出此用户是否关注此活动

					if (goingList.size() > 0) {
						joined = "true";// 表示已join
					} else {
						joined = "false";// 表示未join
					}
				}
			}

			MyActivity myActivity = new MyActivity();
			myActivity.setActivity(activity);// 组装vo，放进活动类属性
			myActivity.setUser(userDao.getUserById(activity.getUserId()));// 组装vo,放进用户类属性
			myActivity.setUserComments(userCommentsList);// 组装vo，放进评论list属性
			myActivity.setFollowed(followed);
			myActivity.setJoined(joined);
			myActivity.setOwner(owner);
			activityList.add(myActivity);
		}

		List recommentActivity = activityDao.getAllActivity();// 取推荐活动
		recommentActivity = recommentActivity.subList(7, 10);// 取推荐活动
		List topTribusCity = activityDao.getTopTribusCity();
		List<User> friends = followDao.getAllFriends(userId);// 得到此userId所有followee对象

		List result = new ArrayList();// 最终的结果集

		int page;

		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// 对应第几页
		} // 和 一个评论list
		result = getListByPage(activityList, 4, page);// 4条一页 得到第page页！

		int pageNumbers = getPageNumber(activityList, 4);// 4条一页 ，得到一共几页
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		User user = userDao.getUserById(userId);// 得到当前用户对象
		view.addObject("pageNumbers", pages);// 将一共多少页放入容器传回页面
		view.addObject("user", user);// 将user放入容器
		view.addObject("friends", friends);// 将friends放入容器
		view.addObject("topTribusCity", topTribusCity);// 将topTribusCity放入容器
		view.addObject("recommentActivity", recommentActivity);// 将recommentActivity放入容器
		view.addObject("activityList", result);// 将activityList放入容器
		// 传回页面！
		view.setViewName("activity/my_Activity");// 设置对应的视图view/activity/myActivity.jsp
		return view;

	}

	@RequestMapping("byHotSearch")
	// 
	public ModelAndView hotSearch(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityPicId = Integer.parseInt(request
				.getParameter("activityPicId"));
		List<ActivityPic> activityPics = activityPicDao
				.getActivityPicByCondition(activityPicId, 0);// 得到相片id
		view.addObject("activityPics", activityPics.get(0));// 将activityPics放入容器
		// 传回页面！
		view.setViewName("activity/showPic");// 设置对应的视图view/activity/showPic.jsp
		return view;
	}

	// 分页函数，每个元素 有两个属性 一个是实际属性，一个是所在页数
	public List paging(List l, int watershed) { // 待搞的list，每页显示多少条目.
		int pageNumbers;// 一共几页
		if (l.size() % watershed == 0) {
			pageNumbers = (int) l.size() / watershed;// 如果没有余数的情况下，商取整
		} else {
			pageNumbers = (int) l.size() / watershed + 1;// 如果有余数的情况下，商取整+1
		}
		List resultList = new ArrayList();// 结果集
		for (int p = 0; p < pageNumbers; p++) {

			if (p + 1 == pageNumbers) {// 最后一页

				Iterator iterator1 = l.subList(p * 5, l.size()).iterator();// 截取最后一页
				while (iterator1.hasNext()) {
					Ele e = new Ele();
					e.setEle(iterator1.next());
					e.setPageNumber(p + 1);
					resultList.add(e);
				}
			} else {
				Iterator iterator = l.subList(p * 5, (p + 1) * 5).iterator();// from
				// 最后一页之前的
				while (iterator.hasNext()) {
					Ele e = new Ele();
					e.setEle(iterator.next());
					e.setPageNumber(p + 1);
					resultList.add(e);
				}
			}
		}
		return resultList;
	}

	// 分页函数简单版
	// 分页函数，每个元素 有两个属性 一个是实际属性，一个是所在页数
	public List getListByPage(List l, int watershed, int p) { // 待搞的list，每页显示多少条目.，想取第幾頁的值
		int pageNumbers = getPageNumber(l, watershed);
		if (p == pageNumbers) {// 最后一页

			return l.subList((p - 1) * watershed, l.size());// 截取最后一页

		} else {
			return l.subList((p - 1) * watershed, p * watershed);// from//
			// 最后一页之前的

		}

	}

	public int getPageNumber(List l, int watershed) {// 得到一共几页
		int pageNumbers;// 一共几页
		if (l.size() % watershed == 0) {
			pageNumbers = (int) (l.size() / watershed);// 如果没有余数的情况下，商取整
		} else {
			pageNumbers = (int) (l.size() / watershed) + 1;// 如果有余数的情况下，商取整+1
		}
		return pageNumbers;
	}

}
