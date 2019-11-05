package com.github.ontio.controller;

import com.github.ontio.aop.RequestLimit;
import com.github.ontio.model.common.ResponseBean;
import com.github.ontio.model.common.ResponseTransactions;
import com.github.ontio.service.IAddressService;
import com.github.ontio.util.ConstantParam;
import com.github.ontio.util.ErrorInfo;
import com.github.ontio.util.Helper;
import com.github.ontio.model.dto.TransferTxDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/4/28
 */
@Validated
@Slf4j
@RestController
@RequestMapping(value = "/v2/addresses")
public class AddressController {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }


    @RequestLimit(count = 120)
    @ApiOperation(value = "Get address balance")
    @GetMapping(value = "/{address}/{token_type}/balances")
    public ResponseBean queryAddressBalance(@PathVariable("address") @Length(min = 34, max = 34, message = "Incorrect address format") String address,
                                            @PathVariable("token_type") @Pattern(regexp = "oep4|OEP4|oep5|OEP5|oep8|OEP8|native|NATIVE|ALL|all", message = "Incorrect token type") String tokenType) {

        log.info("####{}.{} begin...address:{},token_type:{}", CLASS_NAME, Helper.currentMethod(), address, tokenType);

        ResponseBean rs = addressService.queryAddressBalance(address, tokenType);
        return rs;
    }


    @RequestLimit(count = 120)
    @ApiOperation(value = "Get address balance by assetName")
    @GetMapping(value = "/{address}/balances")
    public ResponseBean queryAddressBalanceByAssetName(@PathVariable("address") @Length(min = 34, max = 34, message = "Incorrect address format") String address,
                                                       @RequestParam("asset_name") String assetName,
                                                       @RequestParam(value = "channel", required = false) String channel) {

        log.info("####{}.{} begin...address:{},assetName:{}", CLASS_NAME, Helper.currentMethod(), address, assetName);

        ResponseBean rs = new ResponseBean();
        if (Helper.isNotEmptyOrNull(channel) && ConstantParam.CHANNEL_ONTO.equals(channel)) {
            rs = addressService.queryAddressBalanceByAssetName4Onto(address, assetName);
        } else {
            rs = addressService.queryAddressBalanceByAssetName(address, assetName);
        }
        return rs;
    }


    @RequestLimit(count = 120)
    @ApiOperation(value = "Get address transfer transaction list by params", notes = "(begin_time+end_time) or (page_number+page_size)")
    @GetMapping(value = "/{address}/transactions")
    public ResponseTransactions queryAddressTransferTxsByPage(@PathVariable("address") @Length(min = 34, max = 34, message = "Incorrect address format") String address,
                                                              @RequestParam(name = "page_size", required = false) @Min(1) @Max(20) Integer pageSize,
                                                              @RequestParam(name = "page_number", required = false) @Min(1) Integer pageNumber,
                                                              @RequestParam(name = "begin_time", required = false) Long beginTime,
                                                              @RequestParam(name = "end_time", required = false) Long endTime) {

        log.info("####{}.{} begin...address:{}", CLASS_NAME, Helper.currentMethod(), address);
        return queryAddressTransferTxsByPageAndAssetName(address, "", pageSize, pageNumber, beginTime, endTime);
    }

    @RequestLimit(count = 120)
    @ApiOperation(value = "Get address transfer transaction list by params+assetName", notes = "(begin_time+end_time) or (page_number+page_size) or (end_time+page_size)")
    @GetMapping(value = "/{address}/{asset_name}/transactions")
    public ResponseTransactions queryAddressTransferTxsByPageAndAssetName(@PathVariable("address") @Length(min = 34, max = 34, message = "error address format") String address,
                                                                          @PathVariable("asset_name") String assetName,
                                                                          @RequestParam(name = "page_size", required = false) @Min(1) @Max(20) Integer pageSize,
                                                                          @RequestParam(name = "page_number", required = false) @Min(1) Integer pageNumber,
                                                                          @RequestParam(name = "begin_time", required = false) Long beginTime,
                                                                          @RequestParam(name = "end_time", required = false) Long endTime) {

        log.info("###{}.{} begin...address:{}", CLASS_NAME, Helper.currentMethod(), address);

        if (Helper.isNotEmptyOrNull(pageNumber, pageSize)) {
            return addressService.queryTransferTxsByPage(address, assetName, pageNumber, pageSize);

        } else if (Helper.isNotEmptyOrNull(beginTime, endTime)) {

            //request time max range is one week
            if (Helper.isTimeRangeExceedWeek(beginTime, endTime)) {
                return new ResponseTransactions(ErrorInfo.TIME_RANGE_EXCEED.code(), ErrorInfo.TIME_RANGE_EXCEED.desc(),
                                                new ArrayList<TransferTxDto>(), new Integer(0));
            }

            return addressService.queryTransferTxsByTime(address, assetName, beginTime, endTime);
        }

        return new ResponseTransactions();
    }


}
