package com.udemy.mapstruct.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.udemy.mapstruct.dto.GetProduct;
import com.udemy.mapstruct.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { CategoryMapper.class })
public interface ProductMapper {

  // cuando los atributos se llaman igual no es necesario hacer mapping,
  // principalmente se usa para atributos diferentes
  @Mappings({
      // cuando los nombres del atributo son diferentes
      @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
      @Mapping(source = "name", target = "productName"),
      @Mapping(source = "id", target = "productId"),
      @Mapping(source = "price", target = "price", numberFormat = "$#.00")
  })
  GetProduct productToGetDTO(Product product);

  // realiza la operacion anterior a la inversa
  @InheritInverseConfiguration
  @Mappings({
      // ignorar el mapeo de un atributo
      @Mapping(target = "creationDate", ignore = true)
  })
  Product toEntity(GetProduct getProduct);

  List<GetProduct> toGetProductList(List<Product> productList);

  List<Product> toEntityList(List<GetProduct> getProductList);

}
