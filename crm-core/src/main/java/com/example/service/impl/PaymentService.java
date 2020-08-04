package com.example.service.impl;

import com.example.converter.PaymentConverter;
import com.example.dto.PaymentDTO;
import com.example.entity.PaymentEntity;
import com.example.paging.Pageable;
import com.example.repository.IPaymentRepository;
import com.example.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private PaymentConverter paymentConverter;

    @Override
    public PaymentDTO getOnePaymentById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findOne(id);
        PaymentDTO paymentDTO = paymentConverter.convertToDto(paymentEntity);
        return paymentDTO;
    }

    @Override
    public List<PaymentDTO> searchPayments(PaymentDTO modelSearch, Pageable pageable) {

        List<?> paymentEntities = new ArrayList<>();
        List<PaymentDTO> result = new ArrayList<>();

        paymentEntities = paymentRepository.findAll(modelSearch, pageable);
        for (Object item : paymentEntities) {
            PaymentEntity paymentEntity = new PaymentEntity();
            try {
                paymentEntity = (PaymentEntity) item;
            } catch (Exception e) {
                paymentEntity = (PaymentEntity) ((Object[]) item)[0];
            }
            PaymentDTO paymentDTO = paymentConverter.convertToDto(paymentEntity);

            result.add(paymentDTO);
        }

        return result;
    }

    @Override
    public int getTotalItems(PaymentDTO modelSearch) {
        int totalItem = 0;
        totalItem = paymentRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public void savePayment(PaymentDTO paymentDto) {
        if(paymentDto.getId() !=null && paymentRepository.findOne(paymentDto.getId()) != null){
            PaymentEntity paymentEntity = paymentRepository.findOne(paymentDto.getId());
            paymentEntity.setAmountPaid(paymentDto.getAmountPaid());
            paymentEntity.setAmountPayable(paymentDto.getAmountPayable());
            paymentEntity.setConsume_id(paymentDto.getConsume_id());
            paymentRepository.save(paymentEntity);
        }
        else {
            PaymentEntity paymentEntity = paymentConverter.convertToEntity(paymentDto);
            paymentRepository.save(paymentEntity);
        }
    }

    @Override
    public void deletePayments(List<Long> payments) {
        for(Long paymentId : payments){
            paymentRepository.delete(paymentId);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<PaymentEntity> payments = paymentRepository.findAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<PaymentDTO>();
        for(PaymentEntity payment : payments){
            PaymentDTO paymentDTO = paymentConverter.convertToDto(payment);
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }
}
