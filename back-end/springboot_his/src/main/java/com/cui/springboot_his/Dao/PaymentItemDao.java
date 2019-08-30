package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Patient;
import com.cui.springboot_his.Entity.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentItemDao extends JpaRepository<PaymentItem, Integer> {
    @Procedure(name = "pay")
    void pay(@Param("n_paymentItem_id") Integer paymentItem_id, @Param("n_others_id") Integer others_id);

    @Procedure(name = "refund")
    void refund(@Param("n_paymentItem_id") Integer paymentItem_id, @Param("n_others_id") Integer others_id);

    List<PaymentItem> findByStatusAndPatient(String status, Patient patient);
}
