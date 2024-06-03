package com.wilczek.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {
    @Test
    void mapRow() throws SQLException {
        // Given
        int rowNumber = 1;
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();
        ResultSet rs = mock(ResultSet.class);
        Mockito.when(rs.getLong("id")).thenReturn(1L);
        Mockito.when(rs.getString("name")).thenReturn("jakub");
        Mockito.when(rs.getString("email")).thenReturn("jakub@cpk.pl");
        Mockito.when(rs.getInt("age")).thenReturn(33);
        // When
        Customer customerRM = customerRowMapper.mapRow(rs, rowNumber);
        System.out.println(customerRM);
        // Then
        Customer customer = new Customer(1L,"jakub","jakub@cpk.pl",33);
        assertThat(customerRM.getId()).isEqualTo(customer.getId());
        assertThat(customerRM.getName()).isEqualTo(customer.getName());
        assertThat(customerRM.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerRM.getAge()).isEqualTo(customer.getAge());
    }
}