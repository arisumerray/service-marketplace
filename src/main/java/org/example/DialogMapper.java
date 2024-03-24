package org.example;

import org.example.dto.DialogDto;
import org.example.dto.FullDialogDto;
import org.example.entity.Dialog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DialogMapper {

    DialogDto toDto(Dialog dialog);

    List<DialogDto> toDtos(List<Dialog> dialogs);

    FullDialogDto toFullDto(Dialog dialog);
}
