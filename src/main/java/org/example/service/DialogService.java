package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.DialogDto;
import org.example.dto.FullDialogDto;
import org.example.entity.Dialog;

import java.security.Principal;
import java.util.List;

public interface DialogService {
    Dialog createDialog(Integer userToId, Integer userFromId);

    FullDialogDto getDialog(Integer id);

    List<DialogDto> getAllDialogs(Integer id);
}
