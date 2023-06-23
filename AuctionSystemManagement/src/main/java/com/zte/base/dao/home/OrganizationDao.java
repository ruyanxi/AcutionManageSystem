package com.zte.base.dao.home;

import com.zte.base.entity.home.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDao extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    @Query("select o from Organization o where o.id = :id")
    Organization find(@Param("id")Long id);

    Organization findByEmail(String email);

    Organization findByPhone(String phone);

    Organization findByName(String name);
}
