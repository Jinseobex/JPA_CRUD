package com.zerock.jpatests.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseDto<DTO> {
    //boardDTO일수도 있고, todoDTO일수도 있고... 이럴 때 제네릭을 쓴다.

    private ListRequestDTO listRequestDTO;

    private List<DTO> dtoList;

    private PageMaker pageMaker;

}
