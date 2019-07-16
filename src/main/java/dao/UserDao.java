package dao;

import org.apache.ibatis.annotations.Mapper;

import entity.User;
@Mapper
public interface UserDao {
	void save(User user);
    void delete(int id);
}
