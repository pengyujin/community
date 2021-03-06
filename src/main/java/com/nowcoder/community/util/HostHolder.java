package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用Threadlocal实现持有用户的信息，用于代替session对象.
 */
@Component
public class HostHolder {
    
    //以线程为key存储值
    
    private ThreadLocal<User> users = new ThreadLocal<User>();
    
    public void setUser(User user) {
        users.set(user);
    }
    
    public User getUser() {
        return users.get();
    }
    
    public void clear() {
        users.remove();
    }
}
