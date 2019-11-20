package com.github.ontio.mapper;

import com.github.ontio.model.dao.Oep8;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component
public interface Oep8Mapper extends Mapper<Oep8> {

    List<Oep8> selectApprovedRecords();
    void copyContractDataToNewAddress(String oldContractAdress, String newContractAdress);
    void resetAuditFlag(String contractAdress);
}