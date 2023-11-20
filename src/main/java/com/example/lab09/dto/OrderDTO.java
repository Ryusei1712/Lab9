package com.example.lab09.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class OrderDTO {
    private String orderNumber;
    private float totalSellingPrice;
    private List<ProductDTO> productList;
}
