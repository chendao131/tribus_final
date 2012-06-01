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
// ������������controller������
@RequestMapping("activity")
// ������������controller����Ķ�Ӧactivity�Ĵ�����
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
	// �����
	public ModelAndView activityCreateInit(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		List<ActivityClassified> ac = activityClassifiedDao
				.getAllActivityClassified();
		System.out.println(ac.size());
		view.addObject("activityClassified", ac);// ��activityList��������
		view.setViewName("activity/create_activity");// ���ö�Ӧ����ͼview/activity/myActivity.jsp

		return view;
	}

	@RequestMapping("activityCreat")
	// �����
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
		// .getParameterValues("pic")));// ��ҳ��ռ�ȡ��pic����·��
		String activityPic = request.getParameter("hidden_para2");// ��ҳ��ռ�ȡ��pic����·��

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
		if (activityDao.addActivity(activity)) {// �־û������
			return "activity/creatSuccess";

		} else {
			return "activity/error";
		}

	}

	@RequestMapping("index")
	// ͬ����ҳ
	public ModelAndView indexActivity(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();

		List<Activity> hottestActivityList = activityDao.getHottestActivity();// ȡ���Ƽ�ͬ���б�(5���Ƽ��ȴ��ϵ�����������)

		// ȡ���ҹ�ע��ͬ���б�
		// Integer userId = Integer.parseInt(request.getParameter("userId"));//
		// ��requestȡ���ҵ�userId
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
		List<Activity> myActivityList = new ArrayList<Activity>();// �����ҵĻ�б�

		while (iterator1.hasNext()) {
			Integer activityId = ((ActivityFollow) iterator1.next())
					.getActivityId();
			myActivityList.add(activityDao.getActivityById(activityId));// ���µõ���activity��������ҹ�ע�Ļ�б�����;
		}// �õ��ҹ�ע��ͬ���б�

		// �õ����ѹ�עͬ���б�

		List<User> friendList = fd.getAllFriends(userId);
		List<Activity> friendActivityList = new ArrayList();
		Iterator iterator2 = friendList.iterator();

		while (iterator2.hasNext()) {
			User user = (User) iterator2.next();
			List<ActivityFollow> activityFriendFollowList = activityFollowDao
					.getActivityFollowByCondition(0, user.getUserId());// ���һ�����ѵĹ�ע������б�
			Iterator iteratorTemp = activityFriendFollowList.iterator();
			// List<Activity> activityListTemp = new ArrayList<Activity>();//
			// ����һ�����ѻ�б�
			while (iteratorTemp.hasNext()) {
				Integer activityIdTemp = ((ActivityFollow) iteratorTemp.next())
						.getActivityId();
				Activity activityTemp = activityDao
						.getActivityById(activityIdTemp);// �õ����ѵ�һ���
				friendActivityList.add(activityTemp);// ���µõ���activity���������ѹ�ע�Ļ�б�
			}

		}// �õ����ѹ�עͬ���б�friendsActivity

		if (u == null) {
			userId = 123;
		} else {
			userId = u.getUserId();
		}
		// �õ��û�����ĵ��»
		List<ActivityGoing> latestActivity = activityGoingDao
				.getActivityGoingByCondition(0, userId);// �õ����������
		List<Activity> activityList = new ArrayList();// ����һ��������б�
		Iterator iteratorActivityGoing = latestActivity.iterator();
		List<Activity> activityAfterFilter = new ArrayList();
		while (iteratorActivityGoing.hasNext()) {
			activityList.add(activityDao
					.getActivityById(((ActivityGoing) iteratorActivityGoing
							.next()).getActivityId()));
		}
		// �õ���ǰ�û��μӵ����лlist(����������ǰ��)

		Comparator comp = new Activity();// �����������
		Collections.sort(activityList, comp);// ������ɴ�С����
		Date date = new Date();// ��õ�ǰʱ��

		for (int i = 0; i < activityList.size(); i++) {
			if (date.after(activityList.get(i).getActivityStartTime())) {// �����ǰʱ�����ڵ�ǰ�ʱ��
				// ������

			} else {
				activityAfterFilter.add(activityList.get(i));// �����������Ļ��ӽ�ȥ���List
			}
		}// ���˵õ��˵�ǰ�û�δ���μ�activity��list��Ȼ������ж�
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH);// ��ȡ�·�

		if (activityAfterFilter.size() == 0
				|| activityAfterFilter.get(0).getActivityStartTime().getMonth() != month) {// ������ս����Ϊ����߽�������κ�һ��Ԫ�ض����ڵ���

			view.addObject("latestActivity", null);// �����޻
		} else {

			view.addObject("latestActivity", activityAfterFilter.get(0));
		}
		List activityGoingNumbersList = activityGoingDao
				.getActivityGoingAsNumbers();// ȡ�����������Ļ
		List activityGoingMaxList = new ArrayList();// ���� volist
		Iterator iteratorGoing = activityGoingNumbersList.iterator();
		while (iteratorGoing.hasNext()) {
			ActivityGoingMax activityGoingMax = new ActivityGoingMax();
			ActivityGoingTempResult tempResult = (ActivityGoingTempResult) iteratorGoing
					.next();
			activityGoingMax.setActivityId(activityDao.getActivityById(// vo������װ��ʼ
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
					tempResult.getActivityId()).getActivityStartTime());// vo������װ���
			activityGoingMaxList.add(activityGoingMax);// vo�������list
		}
		// �õ������3������
		List commentListRandom = new ArrayList();// ������۱�
		List<ActivityComment> activityCommentList = activityCommentDao
				.getActivityCommentRandom();//�漴ȡ��һ������list
		Iterator commentIterator = activityCommentList.iterator();
		while (commentIterator.hasNext()) {
			ActivityComment activityComment = (ActivityComment) commentIterator
					.next();
			List<ActivityComment> ActivityCommentSonList=activityCommentDao.getActivityCommentByCondition(activityComment.getActivityId(), 0);//��ĳһ����ҳ����д˻������
			
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
				comments.add(userComments);// ��װ������List
					
			}
			FriendComment friendComment = new FriendComment();
			Activity a =activityDao.getActivityById(activityComment.getActivityId());//�ҳ�������ۼ�¼��Ӧ�Ļ
			friendComment.setActivity(a);
			if(comments.size()>4){
				comments=comments.subList(0, 4);
			}
			friendComment.setUserComment(comments);
			commentListRandom.add(friendComment);// ��Ӷ���,�ӽ�list
		}
		
		List activityTagsList = activityClassifiedDao
				.getAllActivityClassified();
		if (activityGoingMaxList.size() > 4) {

			activityGoingMaxList = activityGoingMaxList.subList(0, 4);// �������4��
			// ��ôֻ��ȡ4��
		}

		List<FriendComment> commentListRandom1 = new ArrayList();
		// ��¼״̬�£�ץȡ���Ѳμӹ��Ļ
		if (u != null) {

			// ��follow�����ó�followee (User����)
			List followeeUser = followDao.getAllFriends(u.getUserId());
			Iterator followeeUserIterator = followeeUser.iterator();
			List<UserComments> comments = new ArrayList();
			while (followeeUserIterator.hasNext()) {
				User fu = (User) followeeUserIterator.next();
				List<ActivityGoing> activityGoingList = activityGoingDao
						.getActivityGoingByCondition(0, fu.getUserId());
				if (activityGoingList == null) {// ���Ϊ��˵�� ��ɵ��û�вμӹ��κλ
					continue;
				} else {// �ó�һ��user����Ĳμӵ�һ���
					// ȥactivitiyGoing���鴦��Ӧ�ĻId
					// ͨ���IDȥ��б���������Ӧ�Ļ
					Activity a = activityDao.getActivityById(activityGoingList
							.get(0).getActivityId());
					// ����������������۶���+�����˶���
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
						comments.add(userComments);// ��װ������List
						

						
					}
					FriendComment friendComment = new FriendComment();
					friendComment.setActivity(a);
					friendComment.setUserComment(comments);
					commentListRandom1.add(friendComment);// ��Ӷ���,�ӽ�list
				}

			}

		}

		// //ȡ��reminds list
		// List<ActivityComment>
		// acList=activityCommentDao.getActivityCommentByCondition(0,
		// userId);//���û�����������ȡ��
		// Iterator acListIterator=acList.iterator();
		// while(acListIterator.hasNext()){
		// ActivityComment ac=(ActivityComment)acListIterator.next();
		// List<ActivityComment>
		// acSonList=activityCommentDao.getActivityCommentByCondition(ac.getActivityId(),
		// 0);//���е�ÿһ��ɨ�裬�ҳ�����ÿһ�����������۶���
		// Iterator acSonListIterator = acSonList.iterator();
		// while(acSonListIterator.hasNext()){
		// ActivityComment acSon=(ActivityComment)acListIterator.next();
		// if(ac.getCommentDate())
		// }
		//			
		// }

		List topTribusCity = activityDao.getTopTribusCity();
		view.addObject("activityTagsList", activityTagsList);// �����б�ǩlist��������
		view.addObject("topTribusCity", topTribusCity);// ����topTribusCity
		view.addObject("myActivityList", myActivityList);// �����ҵ�ͬ��
		view.addObject("hottestActivityList", hottestActivityList);// �����Ƽ�ͬ��
		view.addObject("hottestActivity", hottestActivityList.get(0));// �����Ƽ�ͬ��
		view.addObject("friendActivityList", friendActivityList);// �������ͬ��

		view.addObject("activityGoingMaxList", activityGoingMaxList);// �����μ�������б������������ҳ��
		if (u == null) {
			view.addObject("commentListRandom", commentListRandom);// ����������б������������ҳ��
		} else {
			view.addObject("commentListRandom", commentListRandom1);// ��������������б������������ҳ��
		}
		view.addObject("user", u);//��session�����user���󴫵�ҳ��
		view.setViewName("activity/index");// ������ͼ��ַΪhttp��//www.tribus.com/view/activity/index.jsp
		return view;// ������ͼ

	}

	@RequestMapping("info")
	// ͬ����ϸ��ҳ
	public ModelAndView activityInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// ��requestȡ���activityId
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		Activity activity = activityDao.getActivityById(activityId);// �õ��˻�Ķ���

		List<ActivityGoing> activityGoingList = activityGoingDao
				.getActivityGoingByCondition(activityId, 0);// �õ��˻����ActivityGoing����
		Iterator iterator = activityGoingList.iterator();
		List<User> userList = new ArrayList();// ��������going�˻��userList
		while (iterator.hasNext()) {
			ActivityGoing activityGoing = (ActivityGoing) iterator.next();
			userList.add(userDao.getUserById(activityGoing.getUserId()));// ��f�μӴ˻��user��ӵ�list
		}

	List<User> friends = followDao.getAllFriends(userId);// �õ���userId����followee����

		List<User> friendFollow = new ArrayList<User>(); // ����������"���Ѳμӻlist"

		for (User user1 : friends) {
			if (userList.contains(user1)) {
				friendFollow.add(user1);
			}
		}

		List<ActivityAlbum> activityAlbumList = new ArrayList();
		activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				activityId, 0);// ����һ�����id�õ����list
		Integer flag=0;
		if (activityAlbumList.size() > 10) {// �粻��10�����
			activityAlbumList=activityAlbumList.subList(0, 10);
flag=1;
		}
		GetLatLong getLatLong = new GetLatLong();
		String[] location = getLatLong.getLatlng(activity.getActivityStreet()
				+ "," + activity.getActivityCity());

		List followList = activityFollowDao.getActivityFollowByCondition(
				activityId, userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻
		String followed = null;
		String joined = null;
		String owner = null;

		if (userId == activity.getUserId()) {// �жϵ�ǰ�û��Ƿ��Ǵ˻�ķ�����
			owner = "true";

		} else {
			if (followList.size() > 0) {
				followed = "true";// ��ʾ�ѹ�ע
			} else {
				followed = "false";// ��ʾδ��ע
			}
			List goingList = activityGoingDao.getActivityGoingByCondition(
					activityId, userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

			if (goingList.size() > 0) {
				joined = "true";// ��ʾ��join
			} else {
				joined = "false";// ��ʾδjoin
			}
		}
		List commentList = activityCommentDao.getActivityCommentByCondition(
				activityId, 0);// ����dao
		List userCommentList = new ArrayList();
		Iterator iteratorComment = commentList.iterator();
		while (iteratorComment.hasNext()) {
			ActivityComment activityComment = (ActivityComment) iteratorComment
					.next();
			UserComment userComment = new UserComment();// VO�����װ��ʼ
			userComment.setCommentContent(activityComment.getCommentContent());
			userComment.setCommentDate(activityComment.getCommentDate());
			userComment.setUserName(userDao.getUserById(
					activityComment.getUserId()).getUserAlias());
			userComment.setUserPic(userDao.getUserById(
					activityComment.getUserId()).getUserPic());// VO�����װ����
			userComment.setUserId(activityComment.getUserId());
			userCommentList.add(userComment);
		}
String activtiyClassified=activityClassifiedDao.getClassifiedTagById(activity.getClassifiedId());
		List recommentActivity = activityDao.getAllActivity();// ȡ�Ƽ��
		recommentActivity = recommentActivity.subList(7, 10);// ȡ�Ƽ��
		view.setViewName("activity/info");// ���ö�Ӧ����ͼview/activity/info.jsp
		view.addObject("activityAlbum", activityAlbumList);// �����list��������������ҳ��
		view.addObject("flag", flag);// �����listsize����10 ��ֵΪ1 ��֮��Ϊ0�����������������ҳ��
		view.addObject("activityClassified", activtiyClassified);// ��activtiyClassified�������� ����ҳ��
		view.addObject("activityLat", location[2]);// ����ַγ�ȷ�������������ҳ��
		view.addObject("activityLong", location[3]);// ����ַ���ȷ�������������ҳ��
		view.addObject("activityInfo", activity);// ��activity�������� ����ҳ��
		view.addObject("followList", friendFollow);// ��followList�������� ����ҳ��
		view.addObject("followed", followed);// ��followed״̬������������ҳ��
		view.addObject("joined", joined);// ��joined״̬������������ҳ��
		view.addObject("owner", owner);// ��owner״̬������������ҳ��
		view.addObject("userList", userList);// �����вμӴ˻���û���������������ҳ��
		view.addObject("userCommentList", userCommentList);// ������VO���������������ҳ��
		view.addObject("recommentActivity", recommentActivity);// ���Ƽ��list������������ҳ��
		view.addObject("activityId", activityId);// ��activityId״̬������������ҳ��

		view.setViewName("activity/activity");

		return view;
	}// ������ͼ

	@RequestMapping("friendsActivity")
	// ͬ����ϸ��ҳ
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
		List<User> friends = followDao.getAllFriends(userId);// �õ���userId����followee����
		Iterator iterator=friends.iterator();
		while(iterator.hasNext()){
			User u=(User) iterator.next();
			List<ActivityGoing> activityGoingList=activityGoingDao.getActivityGoingByCondition(0, u.getUserId());//�õ�ĳһ�����ѵ����вμӵĻList
			Iterator<ActivityGoing> activityGoingListIterator=activityGoingList.iterator();
			while(activityGoingListIterator.hasNext()){//����ĳ�����Ѳμӵ����лgoingList
				ActivityGoing activityGoing=activityGoingListIterator.next();//�õ�ĳһ���Ѳμӵĵ�ĳһ���going����
				Activity activity=activityDao.getActivityById(activityGoing.getActivityId());//�õ�ĳһ���Ѳμӵĵ�ĳһ��activity����
				activityListByFriend.add(activity);//��ӵ��������
			}
		}

		String followed = null;
		String joined = null;
		String owner = null;
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		}

		List<SuperActivity> superActivityList = new ArrayList();//vo ���� ���մ���ҳ���list
		Iterator activityListByFriendIterator=activityListByFriend.iterator();
		while(activityListByFriendIterator.hasNext()){
			
			Activity a=(Activity)activityListByFriendIterator.next();
			List followList = activityFollowDao.getActivityFollowByCondition(a
					.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

			if (userId == a.getUserId()) {// �жϵ�ǰ�û��Ƿ��Ǵ˻�ķ�����
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// ��ʾ�ѹ�ע
				} else {
					followed = "false";// ��ʾδ��ע
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ�μӴ˻

				if (goingList.size() > 0) {
					joined = "true";// ��ʾ��join
				} else {
					joined = "false";// ��ʾδjoin
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);//���յĽ��������ʵ����Ҫ��һ����ҳ��������ҳ��
		}
		
		
		
		
		List recommentActivity = activityDao.getAllActivity();// ȡ�Ƽ��
		recommentActivity = recommentActivity.subList(7, 10);// ȡ�Ƽ��
		List result = new ArrayList();// ���յĽ����
		String[] pages = null;
		if (superActivityList.size() == 0) {// ��������Ϊ�յ�ʱ��

		} else {
			result = getListByPage(superActivityList, 4, page);// 4��һҳ �õ���pageҳ��

			int pageNumbers = getPageNumber(superActivityList, 4);// 4��һҳ
			// ���õ�һ����ҳ
			pages = new String[pageNumbers];
			for (Integer i = 0; i < pageNumbers; i++) {
				pages[i] = i + 1 + "";
			}
		}
		List topTribusCity = activityDao.getTopTribusCity();
		view.addObject("topTribusCity", topTribusCity);//top tribus city������������ҳ��
		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("activityList", result);// ���õ��Ľ����ָ��ҳ��λ�ã��������� ����ҳ�棡
	view.addObject("followed", followed);// ��followed״̬������������ҳ��
		view.addObject("joined", joined);// ��joined״̬������������ҳ��
		view.addObject("owner", owner);// ��owner״̬������������ҳ��
		view.addObject("recommentActivity", recommentActivity);// ���Ƽ��list������������ҳ��
	
		view.setViewName("activity/friends_activity");
		return view;
	}

	@RequestMapping("activityAlbum/{activityAlbumId}")
	// ��һ�����
	public ModelAndView activityAlbumPic(
			@PathVariable("activityAlbumId") Integer activityAlbumId) {
		ModelAndView view = new ModelAndView();

		List<ActivityPic> activityPicList = activityPicDao
				.getActivityPicByCondition(0, activityAlbumId);// ͨ��ĳһ���id��ô�����µ�����picList;
		view.setViewName("activity/pic");// ���ö�Ӧ����ͼview/activity/pic.jsp
		view.addObject("activityPic", activityPicList);// ����Ƭlist��������������ҳ��
		return view;// ������ͼ
	}

	@RequestMapping("search")
	// ������
	public ModelAndView searchActivity(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		String condition = request.getParameter("searchCondition");// ����������ȡ������
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		}
		String followed = null;
		String joined = null;
		String owner = null;
		List<Activity> activityList = activityDao
				.getActivityByAbstractCondition(condition);
		List<SuperActivity> superActivityList = new ArrayList();// ���մ���ҳ���list
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
					.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

			if (userId == a.getUserId()) {// �жϵ�ǰ�û��Ƿ��Ǵ˻�ķ�����
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// ��ʾ�ѹ�ע
				} else {
					followed = "false";// ��ʾδ��ע
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// �����ݿ����˫������ѯ��̽�����ǰ�û��Ƿ�μӴ˻

				if (goingList.size() > 0) {
					joined = "true";// ��ʾ��join
				} else {
					joined = "false";// ��ʾδjoin
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);
		}
		List result = new ArrayList();// ���յĽ����
		String[] pages = null;
		if (superActivityList.size() == 0) {// ��������Ϊ�յ�ʱ��

		} else {
			result = getListByPage(superActivityList, 4, page);// 4��һҳ �õ���pageҳ��

			int pageNumbers = getPageNumber(superActivityList, 4);// 4��һҳ
			// ���õ�һ����ҳ
			pages = new String[pageNumbers];
			for (Integer i = 0; i < pageNumbers; i++) {
				pages[i] = i + 1 + "";
			}
		}
		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("activityList", result);// ���õ��Ľ����ָ��ҳ��λ�ã��������� ����ҳ�棡
		view.addObject("condition", condition);// ����������������������ҳ�� ������ҳ֮��
		view.addObject("followed", followed);// ��followed״̬������������ҳ��
		view.addObject("joined", joined);// ��joined״̬������������ҳ��
		view.addObject("owner", owner);// ��owner״̬������������ҳ��
		view.setViewName("activity/search_result");// ���ö�Ӧ����ͼview/activity/view.jsp
		return view;
	}

	@RequestMapping("byCityTag")
	// �������ų��б�ǩ����
	public ModelAndView searchActivityByCityTag(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		}

		String activityCity = request.getParameter("city");// ȡ��tag��ǩ��ֵ

		List<Activity> activityList = activityDao.getActivityByCondition(null,
				activityCity, null, null);

		String followed = null;
		String joined = null;
		String owner = null;
		List<SuperActivity> superActivityList = new ArrayList();// ���մ���ҳ���list
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
					.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

			if (userId == a.getUserId()) {// �жϵ�ǰ�û��Ƿ��Ǵ˻�ķ�����
				owner = "true";
			} else {
				if (followList.size() > 0) {
					followed = "true";// ��ʾ�ѹ�ע
				} else {
					followed = "false";// ��ʾδ��ע
				}
				List goingList = activityGoingDao.getActivityGoingByCondition(a
						.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

				if (goingList.size() > 0) {
					joined = "true";// ��ʾ��join
				} else {
					joined = "false";// ��ʾδjoin
				}
			}
			SuperActivity s = new SuperActivity();
			s.setActivity(a);
			s.setFollowed(followed);
			s.setJoined(joined);
			s.setOwner(owner);
			superActivityList.add(s);
		}

		List result = new ArrayList();// ���յĽ����

		result = getListByPage(superActivityList, 4, page);// 4��һҳ �õ���pageҳ��

		int pageNumbers = getPageNumber(superActivityList, 4);// 4��һҳ ���õ�һ����ҳ
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}
		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("activityList", result);// ���õ��Ľ����ָ��ҳ��λ�ã��������� ����ҳ�棡
		view.addObject("city", activityCity);// ����ʱ�����������Ż�ҳ�棬���ŷ�ҳ����
		view.addObject("followed", followed);// ��followed״̬������������ҳ��
		view.addObject("joined", joined);// ��joined״̬������������ҳ��
		view.addObject("owner", owner);// ��owner״̬������������ҳ��
		view.setViewName("activity/search_result");// ���ö�Ӧ����ͼview/activity/view.jsp

		return view;
	}

	@RequestMapping("followActivity")
	// ��ע/ȡ����ע�˻
	public void followUnFollowAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// ȡ����ǰ�Ļid

		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}

		List<ActivityFollow> followList = activityFollowDao
				.getActivityFollowByCondition(activityId, userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

		if (followList.size() > 0) {// ��ʱ�ǹ�ע��״̬��˵���û���ִ�н��ע����

			if (activityFollowDao.delActivityFollow(followList.get(0))) {
				response.getWriter().write("Follow");// ��־����ҳ�棬���Ϊ�ӹ�ע״̬
			}

		} else {// ��ʱ��δ��ע��״̬��˵���û���ִ�мӹ�ע����
			ActivityFollow activityFollow = new ActivityFollow();

			// ��ʼ��װ���ע�߶���
			activityFollow.setActivityId(activityId);
			activityFollow.setUserId(userId);// ���ע�߶�����װ���
			if (activityFollowDao.addActivityFollow(activityFollow)) {
				response.getWriter().write("unFollow");// ��־����ҳ�棬���Ϊ�ӹ�ע״̬

			} else {
				response.getWriter().write("fail to follow!sorry");// ��־����ҳ��(�ӹ�עʧ�ܣ�
			}
		}

	}

	@RequestMapping("joinActivity")
	// �μ�/ȡ���μӴ˻
	public void joinUnjoinAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// ȡ����ǰ�Ļid

		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}

		List<ActivityGoing> goingList = activityGoingDao
				.getActivityGoingByCondition(activityId, userId);// �����ݿ����˫������ѯ��̽������û��Ƿ�join�˻

		if (goingList.size() > 0) {// ��ʱ��join��״̬��˵���û���ִ�н�join����

			if (activityGoingDao.delActivityGoing(goingList.get(0))) {
				response.getWriter().write("join");// ��־����ҳ�棬���ΪJoin״̬
			}

		} else {// ��ʱ��δjoin��״̬��˵���û���ִ�м�join����
			ActivityFollow activityFollow = new ActivityFollow();

			// ��ʼ��װ���ע�߶���
			activityFollow.setActivityId(activityId);
			activityFollow.setUserId(userId);// ���ע�߶�����װ���

			ActivityGoing activityGoing = new ActivityGoing();

			// ��ʼ��װ�join�߶���
			activityGoing.setActivityId(activityId);
			activityGoing.setUserId(userId);// �join�߶�����װ���
			if (activityGoingDao.addActivityGoing(activityGoing)) {
				if (activityFollowDao.getActivityFollowByCondition(activityId,
						userId).size() == 0) {// ˵��û�мӹ�ע�����Դ�ʱ��ӹ�ע
					activityFollowDao.addActivityFollow(activityFollow);
				}
				response.getWriter().write("unjoin");// ��־����ҳ�棬���Ϊgoing״̬

			} else {
				response.getWriter().write("fail to join!sorry");// ��־����ҳ��(joinʧ�ܣ�
			}
		}

	}

	@RequestMapping("addComment")
	// �û�����
	public ModelAndView addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));// ȡ����ǰ�Ļid
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			userId = 123;
		} else {
			userId = user.getUserId();
		}
		String commentContent = request.getParameter("commentContent");// ȡ����ǰ����������

		ActivityComment activityComment = new ActivityComment();
		activityComment.setActivityId(activityId);
		activityComment.setCommentContent(commentContent);
		activityComment.setCommentDate(new Date());
		activityComment.setUserId(userId);
		activityCommentDao.addActivityComment(activityComment);
		view.setViewName("redirect:info.action?activityId=" + activityId);// ���ö�Ӧ����ͼview/activity/info.jsp
		return view;
	}

	@RequestMapping("addPicIndex")
	// �ϴ��ͼƬ��ʼҳ��
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
		view.addObject("size", size);// ���û�д������ ������ֵ ����ҳ�档�������
		view.addObject("activityAlbumList", activityAlbumList);// ���õ��Ľ����������
		view.addObject("activityId", activityId);// ��activityId��������
		// ����ҳ�棡
		view.setViewName("activity/upload_pics");// ���ö�Ӧ����ͼview/activity/view.jsp
		return view;

	}

	@RequestMapping("addPic")
	// �ϴ��ͼƬ
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
		String activityPicPath1 = request.getParameter("pic_link1");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath1.equals("")) {// �ж������һ�����Ϊ�յĻ�
			String activityAlbumName1;
			String picDescription1;
			activityAlbumName1 = request.getParameter("albumNamee1");// ��ҳ��ռ�ȡ��albumname����
			picDescription1 = request.getParameter("desc1");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName1.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId1"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath1);
				activityAlbum.setAlbumName(activityAlbumName1);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumId�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath1);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription1);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// ********************************
		String activityPicPath2 = request.getParameter("pic_link2");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath2.equals("")) {// �ж������2�����Ϊ�յĻ�
			String activityAlbumName2;
			String picDescription2;
			activityAlbumName2 = request.getParameter("albumNamee2");// ��ҳ��ռ�ȡ��albumname����
			picDescription2 = request.getParameter("desc2");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName2.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId2"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath2);
				activityAlbum.setAlbumName(activityAlbumName2);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumId�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath2);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription2);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// ***************************
		String activityPicPath3 = request.getParameter("pic_link3");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath3.equals("")) {// �ж������3�����Ϊ�յĻ�
			String activityAlbumName3;
			String picDescription3;
			activityAlbumName3 = request.getParameter("albumNamee3");// ��ҳ��ռ�ȡ��albumname����
			picDescription3 = request.getParameter("desc3");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName3.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId3"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath3);
				activityAlbum.setAlbumName(activityAlbumName3);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumIdȻ���1�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath3);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription3);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// ************************
		String activityPicPath4 = request.getParameter("pic_link4");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath4.equals("")) {// �ж������4�����Ϊ�յĻ�
			String activityAlbumName4;
			String picDescription4;
			activityAlbumName4 = request.getParameter("albumNamee4");// ��ҳ��ռ�ȡ��albumname����
			picDescription4 = request.getParameter("desc4");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName4.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId4"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath4);
				activityAlbum.setAlbumName(activityAlbumName4);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumIdȻ���1�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath4);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription4);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// **************
		String activityPicPath5 = request.getParameter("pic_link5");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath5.equals("")) {// �ж������5�����Ϊ�յĻ�
			String activityAlbumName5;
			String picDescription5;
			activityAlbumName5 = request.getParameter("albumNamee5");// ��ҳ��ռ�ȡ��albumname����
			picDescription5 = request.getParameter("desc5");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName5.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId5"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath5);
				activityAlbum.setAlbumName(activityAlbumName5);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����
				System.out.println("asdasdasdas"
						+ activityAlbumDao.getMaxAlbumId());
				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumIdȻ���1�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath5);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription5);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// **************
		String activityPicPath6 = request.getParameter("pic_link6");// ��ҳ��ռ�ȡ��pic����·��
		if (!activityPicPath6.equals("")) {// �ж������һ�����Ϊ�յĻ�
			String activityAlbumName6;
			String picDescription6;
			activityAlbumName6 = request.getParameter("albumNamee6");// ��ҳ��ռ�ȡ��albumname����
			picDescription6 = request.getParameter("desc6");
			ActivityPic activityPic = new ActivityPic();// ��ʼƴװactivityPic����

			if (activityAlbumName6.equals("")) {// ˵���Ǽӵ��˾������--���¾������
				Integer albumId = Integer.parseInt(request
						.getParameter("albumId6"));// ��ҳ��ռ�ȡ�����id
				activityPic.setAlbumId(albumId);
				ActivityAlbum activityAlbum = activityAlbumDao
						.getActivityAlbumByCondition(0, albumId).get(0);
				activityAlbum.setPicNum(activityAlbum.getPicNum() + 1);// ������Ƭ��������ԭ�е�����+�µ�����
				activityAlbumDao.updateActivityAlbum(activityAlbum);// ���»ؔ�����
			} else {// ��������
				ActivityAlbum activityAlbum = new ActivityAlbum();
				activityAlbum.setActivityId(activityId);
				activityAlbum.setAlbumCover(activityPicPath6);
				activityAlbum.setAlbumName(activityAlbumName6);
				activityAlbum.setPicNum(1);
				activityAlbum.setUserId(userId);
				activityAlbumDao.addActivityAlbum(activityAlbum);// ��ӵ�����

				activityPic.setAlbumId(activityAlbumDao.getMaxAlbumId());// �ҵ����albumId�ٴ��pic����

			}
			activityPic.setPicPath(activityPicPath6);
			activityPic.setUserId(userId);
			activityPic.setPicDescription(picDescription6);
			activityPicDao.addActivityPic(activityPic);// ��ӵ���Ƭ��
		}

		// ����ҳ�棡
		view.setViewName("redirect:info.action?activityId=" + activityId);// ���ö�Ӧ����ͼview/activity/showPicList.jsp
		return view;
	}

	@RequestMapping("album")
	// չʾר���б�
	public ModelAndView showAlbumList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityId = Integer.parseInt(request
				.getParameter("activityId"));
		HttpSession session = request.getSession();

		List<ActivityAlbum> activityAlbumList = new ArrayList();
		activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				activityId, 0);// ����һ�����id�õ����list

		List result = new ArrayList();// ���յĽ����
		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		} // �� һ������list
		result = getListByPage(activityAlbumList, 6, page);// 6��һҳ �õ���pageҳ��
		int pageNumbers = getPageNumber(activityAlbumList, 6);// 6��һҳ ���õ�һ����ҳ
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("activityId", activityId);
		view.addObject("activityAlbumList", result);

		view.setViewName("activity/album");
		return view;
	}

	@RequestMapping("editAlbum")
	// �ϴ��ͼƬ
	public ModelAndView editAlbum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();

		// ******
		String albumId1 = request.getParameter("albumIdd1");
		if (albumId1 != null) {// ����е�һ����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		if (albumId2 != null) {// ����е�2����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		if (albumId3 != null) {// ����е�3����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		if (albumId4 != null) {// ����е�4����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		if (albumId5 != null) {// ����е�5����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		if (albumId6 != null) {// ����е�6����Ļ���˵������Ԫ�أ���Ϊ ��� ����ˢ����������

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
		// ����ҳ�棡
		view.setViewName("redirect:info.action?activityId=" + activityId);// ���ö�Ӧ����ͼview/activity/showPicList.jsp
		return view;

	}

	@RequestMapping("showPicList")
	// ��ʾĳһר������ͼƬ����
	public ModelAndView showPicList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer albumId = Integer.parseInt(request.getParameter("albumId"));
		List<ActivityPic> activityPics = activityPicDao
				.getActivityPicByCondition(0, albumId);// �õ����id
		List activityAlbumList = activityAlbumDao.getActivityAlbumByCondition(
				0, albumId);
		List result = new ArrayList();// ���յĽ����

		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		} // �� һ������list
		result = getListByPage(activityPics, 7, page);// 7��һҳ �õ���pageҳ��
		int pageNumbers = getPageNumber(activityPics, 7);// 7��һҳ ���õ�һ����ҳ
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("activityAlbum", activityAlbumList.get(0));
		view.addObject("activityPics", result);// ��activityPics��������
		// ����ҳ�棡
		view.setViewName("activity/showPicList");// ���ö�Ӧ����ͼview/activity/showPicList.jsp
		return view;

	}

	@RequestMapping("showPic")
	// �ϴ��ͼƬ
	public ModelAndView showPic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		Integer activityPicId = Integer.parseInt(request
				.getParameter("activityPicId"));
		String albumName = request.getParameter("albumName");
		List<ActivityPic> activityPics = activityPicDao
				.getActivityPicByCondition(activityPicId, 0);// �õ���Ƭid
		view.addObject("albumName", albumName);
		view.addObject("activityPics", activityPics.get(0));// ��activityPics��������
		// ����ҳ�棡
		view.setViewName("activity/showPic");// ���ö�Ӧ����ͼview/activity/showPic.jsp
		return view;
	}

	@RequestMapping("searchByTag")
	// �����ű�ǩ����
	public ModelAndView searchByTag(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		ModelAndView view = new ModelAndView();
		String tagName = request.getParameter("tagName");
		Integer classifiedId = activityClassifiedDao.getClassifiedIdByTag(tagName);
		List activityList = activityDao.getActivityByClassifiedId(classifiedId);
		List activityTagsList = activityClassifiedDao.getAllActivityClassified();
		List topTribusCity = activityDao.getTopTribusCity();
		
		
		List<User> followList = new ArrayList();// ������followList�б�
		List<UserCommentSupper> UserComment = new ArrayList();// userCommetnSupper����Ͻ��activity����
		Integer page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		} // �� һ������list
		
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
					.getActivityCommentByCondition(a.getActivityId(), 0);// �õ������б�
			Iterator activityCommentListIterator = activityCommentList
					.iterator();// �����б� ȥuserDao���ҳ���Ӧ��User��Ȼ����װ���� uc list
			List<UserComment> userCommentList = new ArrayList();// �õ�volist
			while (activityCommentListIterator.hasNext()) {
				ActivityComment ac = (ActivityComment) activityCommentListIterator
						.next();
				UserComment uc = new UserComment();// ��ʼ��װuc
				uc.setCommentContent(ac.getCommentContent());
				uc.setUserName(userDao.getUserById(ac.getUserId())
						.getUserAlias());
				uc.setUserPic(userDao.getUserById(ac.getUserId())
						.getUserAlias());// uc��װ����
				userCommentList.add(uc);// ��ӵ�vo list
			}
			u.setActivity(a);
			while (userCommentList.size() < 2) {
				userCommentList.add(null);
			}
			u.setUserComment(userCommentList);
			UserComment.add(u);

			List<ActivityGoing> activityGoingList = activityGoingDao
					.getActivityGoingByCondition(a.getActivityId(), 0);// �õ��˻��userId
			Iterator activityGoingListIterator = activityGoingList.iterator();
			while (activityGoingListIterator.hasNext()) {
				ActivityGoing activityGoing = (ActivityGoing) activityGoingListIterator
						.next();
				followList.add(userDao.getUserById(activityGoing.getUserId()));
			}
		}		
		
		List result = new ArrayList();// ���յĽ����

		result = getListByPage(UserComment, 6, page);// 4��һҳ �õ���pageҳ��
		while (result.size() < 6) {
			result.add(null);
		}

		int pageNumbers = getPageNumber(UserComment, 6);// 4��һҳ ���õ�һ����ҳ
		int[] pages = new int[pageNumbers];
		for (int i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 ;
		}

		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		List recommendActivity = activityDao.getAllActivity();// ȡ�Ƽ��
		recommendActivity = recommendActivity.subList(1, 4);// ȡ�Ƽ��
		view.addObject("tagName", tagName);// ȡ����ǩ��ѯ���� ������������ҳ�棬������ҳ֮;
		view.addObject("recommendActivity", recommendActivity);// ��recommendActivity��������//
		// // ����ҳ�棡
		view.addObject("followList", followList);// ��followList��������// ����ҳ�棡
		view.addObject("topTribusCity", topTribusCity);// �����ų��з�������// ����ҳ�棡
		view.addObject("activityList", result);// ��activityList��������// ����ҳ�棡
		view.addObject("activityTagsList", activityTagsList);// �����б�ǩlist��������
		view.setViewName("activity/tagResult");// ���ö�Ӧ����ͼview/activity/showPic.jsp
		return view;
	}

	@RequestMapping("myActivity")
	// �ϴ��ͼƬ
	public ModelAndView myActivity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		String owner = null;// Host״̬
		String joined = null;// ����״̬
		String followed = null;// ��ע״̬
		User u = (User) session.getAttribute("user");
		if (u == null) {
			userId = 123;
		} else {
			userId = u.getUserId();
		}

		List<ActivityFollow> activityFollowList = activityFollowDao
				.getActivityFollowByCondition(0, userId);// �õ���ǰ�û�follow�Ļ
		List<MyActivity> activityList = new ArrayList();
		Iterator activityFollowListIterator = activityFollowList.iterator();
		while (activityFollowListIterator.hasNext()) {
			ActivityFollow af = (ActivityFollow) activityFollowListIterator
					.next();
			Activity activity = activityDao.getActivityById(af.getActivityId());

			List activityCommentList = activityCommentDao
					.getActivityCommentByCondition(activity.getActivityId(), 0);// �õ������б�
			Iterator activityCommentListIterator = activityCommentList
					.iterator();// �����б� ȥuserDao���ҳ���Ӧ��User��Ȼ����װ���� uc list
			List<UserComments> userCommentsList = new ArrayList();// �õ�volist
			while (activityCommentListIterator.hasNext()) {
				ActivityComment ac = (ActivityComment) activityCommentListIterator
						.next();
				UserComments uc = new UserComments();// ��ʼ��װuc
				uc.setUserComment(ac.getCommentContent());
				uc.setUserId(ac.getUserId());
				uc.setUserPic(userDao.getUserById(ac.getUserId())
						.getUserAlias());// uc��װ����
				userCommentsList.add(uc);// ��ӵ�vo list

				List followList = activityFollowDao
						.getActivityFollowByCondition(activity.getActivityId(),
								userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻
				if (userId == activity.getUserId()) {// �жϵ�ǰ�û��Ƿ��Ǵ˻�ķ�����
					owner = "true";
				} else {
					if (followList.size() > 0) {
						followed = "true";// ��ʾ�ѹ�ע
					} else {
						followed = "false";// ��ʾδ��ע
					}
					List goingList = activityGoingDao
							.getActivityGoingByCondition(activity
									.getActivityId(), userId);// �����ݿ����˫������ѯ��̽������û��Ƿ��ע�˻

					if (goingList.size() > 0) {
						joined = "true";// ��ʾ��join
					} else {
						joined = "false";// ��ʾδjoin
					}
				}
			}

			MyActivity myActivity = new MyActivity();
			myActivity.setActivity(activity);// ��װvo���Ž��������
			myActivity.setUser(userDao.getUserById(activity.getUserId()));// ��װvo,�Ž��û�������
			myActivity.setUserComments(userCommentsList);// ��װvo���Ž�����list����
			myActivity.setFollowed(followed);
			myActivity.setJoined(joined);
			myActivity.setOwner(owner);
			activityList.add(myActivity);
		}

		List recommentActivity = activityDao.getAllActivity();// ȡ�Ƽ��
		recommentActivity = recommentActivity.subList(7, 10);// ȡ�Ƽ��
		List topTribusCity = activityDao.getTopTribusCity();
		List<User> friends = followDao.getAllFriends(userId);// �õ���userId����followee����

		List result = new ArrayList();// ���յĽ����

		int page;

		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));// ��Ӧ�ڼ�ҳ
		} // �� һ������list
		result = getListByPage(activityList, 4, page);// 4��һҳ �õ���pageҳ��

		int pageNumbers = getPageNumber(activityList, 4);// 4��һҳ ���õ�һ����ҳ
		String[] pages = new String[pageNumbers];
		for (Integer i = 0; i < pageNumbers; i++) {
			pages[i] = i + 1 + "";
		}

		User user = userDao.getUserById(userId);// �õ���ǰ�û�����
		view.addObject("pageNumbers", pages);// ��һ������ҳ������������ҳ��
		view.addObject("user", user);// ��user��������
		view.addObject("friends", friends);// ��friends��������
		view.addObject("topTribusCity", topTribusCity);// ��topTribusCity��������
		view.addObject("recommentActivity", recommentActivity);// ��recommentActivity��������
		view.addObject("activityList", result);// ��activityList��������
		// ����ҳ�棡
		view.setViewName("activity/my_Activity");// ���ö�Ӧ����ͼview/activity/myActivity.jsp
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
				.getActivityPicByCondition(activityPicId, 0);// �õ���Ƭid
		view.addObject("activityPics", activityPics.get(0));// ��activityPics��������
		// ����ҳ�棡
		view.setViewName("activity/showPic");// ���ö�Ӧ����ͼview/activity/showPic.jsp
		return view;
	}

	// ��ҳ������ÿ��Ԫ�� ���������� һ����ʵ�����ԣ�һ��������ҳ��
	public List paging(List l, int watershed) { // �����list��ÿҳ��ʾ������Ŀ.
		int pageNumbers;// һ����ҳ
		if (l.size() % watershed == 0) {
			pageNumbers = (int) l.size() / watershed;// ���û������������£���ȡ��
		} else {
			pageNumbers = (int) l.size() / watershed + 1;// ���������������£���ȡ��+1
		}
		List resultList = new ArrayList();// �����
		for (int p = 0; p < pageNumbers; p++) {

			if (p + 1 == pageNumbers) {// ���һҳ

				Iterator iterator1 = l.subList(p * 5, l.size()).iterator();// ��ȡ���һҳ
				while (iterator1.hasNext()) {
					Ele e = new Ele();
					e.setEle(iterator1.next());
					e.setPageNumber(p + 1);
					resultList.add(e);
				}
			} else {
				Iterator iterator = l.subList(p * 5, (p + 1) * 5).iterator();// from
				// ���һҳ֮ǰ��
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

	// ��ҳ�����򵥰�
	// ��ҳ������ÿ��Ԫ�� ���������� һ����ʵ�����ԣ�һ��������ҳ��
	public List getListByPage(List l, int watershed, int p) { // �����list��ÿҳ��ʾ������Ŀ.����ȡ�ڎ�퓵�ֵ
		int pageNumbers = getPageNumber(l, watershed);
		if (p == pageNumbers) {// ���һҳ

			return l.subList((p - 1) * watershed, l.size());// ��ȡ���һҳ

		} else {
			return l.subList((p - 1) * watershed, p * watershed);// from//
			// ���һҳ֮ǰ��

		}

	}

	public int getPageNumber(List l, int watershed) {// �õ�һ����ҳ
		int pageNumbers;// һ����ҳ
		if (l.size() % watershed == 0) {
			pageNumbers = (int) (l.size() / watershed);// ���û������������£���ȡ��
		} else {
			pageNumbers = (int) (l.size() / watershed) + 1;// ���������������£���ȡ��+1
		}
		return pageNumbers;
	}

}
