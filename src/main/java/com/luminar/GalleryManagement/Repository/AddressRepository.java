package com.luminar.GalleryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.GalleryManagement.Entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository <AddressEntity, Long>  {

}
