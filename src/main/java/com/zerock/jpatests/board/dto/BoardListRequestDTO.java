package com.zerock.jpatests.board.dto;

import com.zerock.jpatests.common.dto.ListRequestDTO;
import lombok.Data;

@Data
public class BoardListRequestDTO extends ListRequestDTO {

    private String type;

}
