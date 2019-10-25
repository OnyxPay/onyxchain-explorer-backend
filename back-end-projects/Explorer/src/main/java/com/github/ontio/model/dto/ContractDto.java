package com.github.ontio.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.ontio.model.dao.Contract;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_contract")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDto extends Contract {
}
