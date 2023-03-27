package com.TradinGplatforM.TradinGplatforM.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@DynamicUpdate
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    private float tradingFluctuation;

    private long traderId;
    @UpdateTimestamp()
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public History() {
    }

    public History(float tradingFluctuation, long traderId, LocalDateTime updatedOn) {
        this.tradingFluctuation = tradingFluctuation;
        this.traderId = traderId;
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", tradingFluctuation=" + tradingFluctuation +
                ", traderId=" + traderId +
                ", updatedOn=" + updatedOn +
                '}';
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public float getTradingFluctuation() {
        return tradingFluctuation;
    }

    public void setTradingFluctuation(float tradingFluctuation) {
        this.tradingFluctuation = tradingFluctuation;
    }

    public long getTraderId() {
        return traderId;
    }

    public void setTraderId(long traderId) {
        this.traderId = traderId;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}






