package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.Administration;

import java.util.Optional;

public interface IAdministration extends JpaRepository<Administration, Integer> {

}
