package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.Account;
import org.springframework.stereotype.Repository;


@Repository
public interface IAccount extends JpaRepository<Account, Integer>{
    Account findByUserName(String username);
}
