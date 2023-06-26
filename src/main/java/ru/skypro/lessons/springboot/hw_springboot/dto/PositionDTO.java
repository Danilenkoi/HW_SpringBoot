package ru.skypro.lessons.springboot.hw_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.lessons.springboot.hw_springboot.position.Position;
@Data
@AllArgsConstructor
public class PositionDTO {

    private Long id;
    private String positionName;
    public static PositionDTO fromPosition(Position position) {
        return position == null ? null : new PositionDTO(position.getId(), position.getPositionName());
    }

    public Position toPosition() {
        return new Position()
                .setId(id)
                .setPositionName(positionName);
    }
}
