package com.github.ontio.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.ontio.model.dao.Oep4TxDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_oep4_tx_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Oep4TxDetailDto extends Oep4TxDetail {
}
