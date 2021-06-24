package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.InRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface IInRoom extends JpaRepository<InRoom, Integer> {

}
