package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Invoice;
import com.cui.springboot_his.Entity.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
    Invoice findByPaymentItem(PaymentItem paymentItem);
}
