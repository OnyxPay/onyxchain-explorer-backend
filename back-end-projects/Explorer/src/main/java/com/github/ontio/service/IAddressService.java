package com.github.ontio.service;

import com.github.ontio.model.common.ResponseBean;
import com.github.ontio.model.common.ResponseTransactions;

public interface IAddressService {

    ResponseBean queryAddressBalance(String address, String tokenType);

    ResponseBean queryAddressBalanceByAssetName(String address, String assetName);

    ResponseBean queryAddressBalanceByAssetName4Onto(String address, String assetName);

    ResponseTransactions queryTransferTxsByPage(String address, String assetName, Integer pageNumber, Integer pageSize);

    ResponseTransactions queryTransferTxsByTime(String address, String assetName, Long beginTime, Long endTime);

    ResponseTransactions queryTransferTxsByTime4Onto(String address, String assetName, Long beginTime, Long endTime, String addressType);

    ResponseTransactions queryTransferTxsByTimeAndPage4Onto(String address, String assetName, Long endTime, Integer pageSize, String addressType);

}
