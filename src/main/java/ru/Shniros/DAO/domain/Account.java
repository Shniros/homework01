package ru.Shniros.DAO.domain;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Account {
    private long id;
    private String name;
    private BigDecimal balance;
    private int pirsonId;
}
