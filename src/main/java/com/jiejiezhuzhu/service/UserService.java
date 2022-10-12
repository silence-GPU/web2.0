package com.jiejiezhuzhu.service;
import com.jiejiezhuzhu.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    /**
     * 保存
     * @param user
     *
     * @return
     */
    public boolean save(User user);

    /**
     * 修改
     * @param user
     *
     * @return
     */
    public boolean update(User user);

    /**
     * 按id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public User getById(Integer id);

    /**
     * 按name查
     * @param user
     * @return
     */
    public User getByName(User user);
    /**
     * 按name and pasword查询
     * @param user
     * @return
     */
    public User getByNP(User user);

    /**
     * 查询全部
     * @return
     */
    public List<User> getAll();
}
