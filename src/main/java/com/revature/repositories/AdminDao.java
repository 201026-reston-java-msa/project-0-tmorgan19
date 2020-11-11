package com.revature.repositories;

import com.revature.models.Admin;

public interface AdminDao {
    public Admin findByUname(String Uname);
}
