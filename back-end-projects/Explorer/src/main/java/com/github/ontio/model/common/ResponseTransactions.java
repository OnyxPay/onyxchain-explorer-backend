package com.github.ontio.model.common;

import java.util.List;

import com.github.ontio.model.dto.TransferTxDto;

import lombok.Data;

@Data
public class ResponseTransactions {

    private Integer code;

    private String msg;

    private List<TransferTxDto> transactions;

    private Integer totalTransactionsCount;

    public ResponseTransactions(Integer code, String msg, List<TransferTxDto> transactions, Integer totalTransactionsCount){
        this.code = code;
        this.msg = msg;
        this.transactions = transactions;
        this.totalTransactionsCount = totalTransactionsCount;
    }

    public ResponseTransactions(){}
}
