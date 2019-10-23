package com.github.ontio.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.ontio.model.dao.TxDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "tbl_tx_detail")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TxDetailDto extends TxDetail {

    private JSONObject detail;

}
