package us.luckylu.dev.client.service.impl;

import org.springframework.stereotype.Service;
import us.luckylu.dev.client.dao.ClientUserMapper;
import us.luckylu.dev.client.service.UserService;
import us.luckylu.dev.model.User;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private ClientUserMapper clientUserMapper;
	
	@Override
	public User findUserById(Integer id) {
		return clientUserMapper.findUserById(id);
	}
	
	public void saveUser(User user) {
		clientUserMapper.saveUser(user);
	}
}
