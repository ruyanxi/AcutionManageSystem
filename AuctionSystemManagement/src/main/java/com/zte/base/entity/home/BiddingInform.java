package com.zte.base.entity.home;

import com.zte.base.entity.admin.BaseEntity;
import com.zte.base.entity.common.BiddingProject;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 竞拍物公告
 */
@Entity
@Table(name = "zte_bidding_inform")
@EntityListeners(AuditingEntityListener.class)
public class BiddingInform extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="bidding_project_id")
    private BiddingProject biddingProject;//竞拍项目

    @Column(name = "remark")
    private String remark;//备注

    public BiddingProject getBiddingProject() {
        return biddingProject;
    }

    public void setBiddingProject(BiddingProject biddingProject) {
        this.biddingProject = biddingProject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BiddingInform{" +
                "biddingProject=" + biddingProject +
                ", remark='" + remark + '\'' +
                '}';
    }
}
