package com.example.demo.cases.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {

    @NotNull(message = "id不能为空", groups = {EditValidationGroup.class})
    private Long id;

    @NotBlank(message = "名称不能为空", groups = {AddValidationGroup.class})
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;
}
