package com.incoorp.inventory.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Michael Rendón Villa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMODITIES")
public class CommodityEntity implements Serializable {

    @Id
    private String commodityName;

    @Column
    private Integer amount;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entryDate;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

}
