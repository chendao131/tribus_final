package cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.User;

/**
 * 
 * cache is on the top level of user select object interface, every thing should
 * first find from cache , if doesnt exits then go to DB
 * 
 **/
public class CacheUtil {
	public static Map<Integer, Object> cache;

	public static final int USER = 1;

	public static final int ACTIVITY = 2;

	// only can save 100000 objects
	public static final int CACHE_SIZE = 100000;

	public static final int REFRESH_TIME = 60 * 60 * 2;

	static {

		// synchronize
		cache = new HashMap<Integer, Object>();
	}

	public User getUser(int userId) {

		// first select from cache , if exits then return , or select from DB
		// and put into cache

		return null;
	}

	public int deleteUser(int userId) {

		// 1 :success; 0: no user in cache; -1: error maybe synchronized
		return 1;
	}

	public int updateUser(User user) {
		// 1 :success; 0: no user in cache; -1: error maybe synchronized
		return 1;
	}

	public List<User> getUserFriends(int userId) {

		return null;
	}

	/**
	 * delete user's all friends from cache
	 * 
	 **/
	public List<User> deleteUserFriends(int userId) {
		return null;
	}

	public int updateUserFriends(int userId) {
		return 1;
	}

	public List manageCacheUser() {
		// return cache.get(1);
		return null;
	}
	
	public static void main(String[] arg){
		System.out.println(3333333);
	}

}
