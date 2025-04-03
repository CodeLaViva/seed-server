package com.nullpointer.seed.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequest {
    @NotNull(message = "ID不能为空")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;
}
