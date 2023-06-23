package com.zte.base.dao.home;

import com.zte.base.entity.home.MessageAndReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 留言回复Dao
 */
@Repository
public interface MessageAndReplyDao extends JpaRepository<MessageAndReply,Long>, JpaSpecificationExecutor<MessageAndReply> {

    @Query("select m from MessageAndReply m where m.id = :id")
    MessageAndReply find(@Param("id")Long id);
}
