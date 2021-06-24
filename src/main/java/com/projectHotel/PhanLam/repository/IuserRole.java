package com.projectHotel.PhanLam.repository;

import com.projectHotel.PhanLam.entity.Account;
import com.projectHotel.PhanLam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IuserRole extends JpaRepository<Role, Integer> {
    List<Role> findByAccount(Account account);
}
