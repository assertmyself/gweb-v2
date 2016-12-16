package com.gbcom.system.manager;

import com.hc.core.security.count.CountUser;
import com.hc.core.utils.DateTimeHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: Administrator Date: 2010-4-9 Time: 10:13:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OnlineUserManager {

	/**
	 * @param onlineUsers
	 *            List<CountUser>
	 * @return List<Map>
	 * @throws Exception
	 *             Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getRows(List<CountUser> onlineUsers) throws Exception {
		List<Map> rows = new ArrayList<Map>();
		for (CountUser onlineUser : onlineUsers) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("loginName", onlineUser.getUsername());
			map.put("displayName", onlineUser.getRealName());
			map.put("userIp", onlineUser.getUserIp());
			map.put("userArea", "");
			map.put("loginDate", DateTimeHelper.formatTimestamp(onlineUser
					.getLoginDate(), "YYYY-MM-DD HH:MM:SS"));
			map.put("lastActName", "");
			map.put("onlineTime",
					(int) ((System.currentTimeMillis() - onlineUser
							.getLoginDate().getTime()) / 60000));

			rows.add(map);
		}
		return rows;
	}

}
