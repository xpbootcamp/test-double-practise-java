package com.tw.practise.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public Object getUserBy(String userName) {
        return null;
    }

    public Long save(String userName, String password, String telephone) {
        return 1L;
    }
}
