package com.incoorp.inventory.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HISTORY_MODIFIED_COMMODITIES")
public class ModifiedCommodityEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modifiedId;
    
    @OneToOne
    @JoinColumn(name = "USER_MODIFIER_ID")
    private UserEntity userModifier;
    
    @ManyToOne
    @JoinColumn(name = "MODIFIED_COMMODITY_ID")
    private CommodityEntity commodity;
    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modificationDate;
}
