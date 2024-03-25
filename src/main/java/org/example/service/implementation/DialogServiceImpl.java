package org.example.service.implementation;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.DialogMapper;
import org.example.dto.DialogDto;
import org.example.dto.FullDialogDto;
import org.example.entity.Dialog;
import org.example.repository.DialogRepository;
import org.example.repository.MessageRepository;
import org.example.repository.UserRepository;
import org.example.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    private final DialogRepository dialogRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DialogMapper dialogMapper;

    public Dialog createDialog(Integer userToId, Integer userFromId) {
        var res = dialogRepository.findDialogBetweenUsers(userToId, userFromId);
        if (res != null) {
            throw new EntityExistsException("Dialog already exists");
        }

        var dialog = Dialog.builder().id(0).build();
        dialogRepository.save(dialog);

        var userTo = userRepository.findById(userToId);
        var userFrom = userRepository.findById(userFromId);

        userTo.get().getDialogs().add(dialog);
        userFrom.get().getDialogs().add(dialog);
        userRepository.save(userTo.get());
        userRepository.save(userFrom.get());

        return dialog;
    }

    public FullDialogDto getDialog(Integer id, String name) {
        try {
            var res = dialogRepository.findById(id).get();
            if (res.getUsers().stream().noneMatch(user -> user.getEmail().equals(name))) {
                throw new AccessDeniedException("You can access only your dialogs");
            }
            return dialogMapper.toFullDto(res);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Dialog not found");
        }
    }

    public List<DialogDto> getAllDialogs(Integer id) {
        return dialogMapper.toDtos(dialogRepository.findAllByUserId(id));
    }
}
