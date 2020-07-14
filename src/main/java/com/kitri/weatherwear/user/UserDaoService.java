package com.kitri.weatherwear.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static{
        users.add(new User("aaaa","1234","gywjd","37.562631599999996","126.83578220000001"));
        users.add(new User("bbbb","1234","chaewon","37.562631599999996","126.83578220000001"));
        users.add(new User("cccc","1234","aerim","37.562631599999996","126.83578220000001"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        String newId = Integer.toString(usersCount + 1);
        if (user.getId() == null) {
            user.setId(newId);
        }
        users.add(user);
        return user;
    }

    public User findOne(String id) {
        for (User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(String id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();

            if (id.equals(user.getId())) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public User changeLocationById(String id, UserUpdateRequestDto requestDto) {
        for (User user : users) {
            if (id.equals(user.getId())) {
                user.setLoc_latitude(requestDto.getLoc_latitude());
                user.setLoc_longitude(requestDto.getLoc_longitude());
                return user;
            }
        }
        return null;
    }
}
