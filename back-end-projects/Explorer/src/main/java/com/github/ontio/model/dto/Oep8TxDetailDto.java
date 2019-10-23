package com.github.ontio.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.ontio.model.dao.Oep8TxDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_oep8_tx_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Oep8TxDetailDto extends Oep8TxDetail {
}
