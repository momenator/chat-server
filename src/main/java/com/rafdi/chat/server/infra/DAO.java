package com.rafdi.chat.server.infra;

import com.rafdi.chat.server.model.user.User;

public interface DAO {
	public void save(User user);

	public Object get(Object key);

}
