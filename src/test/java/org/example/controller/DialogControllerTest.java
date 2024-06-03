package org.example.controller;

import jakarta.persistence.EntityExistsException;
import org.example.dto.DialogDto;
import org.example.dto.FullDialogDto;
import org.example.dto.UserForDialogDto;
import org.example.entity.Dialog;
import org.example.entity.Message;
import org.example.entity.User;
import org.example.repository.DialogRepository;
import org.example.repository.UserRepository;
import org.example.service.DialogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.remote.JMXPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DialogControllerTest {
    @InjectMocks
    private DialogController dialogController;

    @Mock
    private DialogService dialogService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDialogs() throws Exception {
        ArrayList<Message> messageList = new ArrayList<Message>(Arrays.asList(new Message(1,"a",null, null, null)));
        ArrayList<User> userList = new ArrayList<User>(Arrays.asList(new User(1, "a", "", "", "","", "",null, null,
                null, null, null, null, null)));
        Dialog dialog3 = new Dialog(1, messageList, userList);
        List<UserForDialogDto> userDtoList = new ArrayList<UserForDialogDto>(Arrays.asList(new UserForDialogDto(1, "","")));
        ArrayList<DialogDto> dtos = new ArrayList<>(Arrays.asList(new DialogDto(1, userDtoList)));
        when(dialogService.getAllDialogs(1)).thenReturn(dtos);
        User u = new User(1, "a", "", "", "","", "",null, null,
                null, null, null, null, null);
        when(userRepository.findByEmail("")).thenReturn(Optional.of(u));

        ResponseEntity<List<DialogDto>> response = dialogController.getAllDialogs(new JMXPrincipal(""));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getDialog() {
        ArrayList<Message> messageList = new ArrayList<Message>(Arrays.asList(new Message(1,"a",null, null, null)));
        ArrayList<User> userList = new ArrayList<User>(Arrays.asList(new User(1, "a", "", "", "","", "",null, null,
                null, null, null, null, null)));
        Dialog dialog = new Dialog(1, messageList, userList);
        ArrayList<UserForDialogDto> userForDtoList = new ArrayList<>(Arrays.asList(new UserForDialogDto(1,"a","b")));
        ArrayList<Message> messageList2 = new ArrayList<Message>(Arrays.asList(new Message(1,"a",null, null, null)));
        FullDialogDto dDto = new FullDialogDto(1, userForDtoList, messageList2);
        when(dialogService.getDialog(1,"")).thenReturn(dDto);
        when(dialogService.getDialog(null,"")).thenReturn(dDto);

        ResponseEntity<?> response = dialogController.getDialog(1, new JMXPrincipal(""));
        ResponseEntity<?> response2 = dialogController.getDialog(null, new JMXPrincipal(" "));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(200, response2.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNull(response2.getBody());
    }

    @Test
    void createDialog() {
        ArrayList<Message> messageList = new ArrayList<Message>(Arrays.asList(new Message(1,"a",null, null, null)));
        ArrayList<User> userList = new ArrayList<User>(Arrays.asList(new User(1, "a", "", "", "","", "",null, null,
                null, null, null, null, null)));
        Dialog dialog = new Dialog(1, messageList, userList);
        when(dialogService.createDialog(1,2)).thenReturn(dialog);
        when(dialogService.createDialog(1,3)).thenThrow(new EntityExistsException ("bebebe"));
        User u = new User(2, "", "", "", "","", "",null, null,
                null, null, null, null, null);
        User u2 = new User(3, " ", "", "", "","", "",null, null,
                null, null, null, null, null);
        when(userRepository.findByEmail("")).thenReturn(Optional.of(u));
        when(userRepository.findByEmail(" ")).thenReturn(Optional.of(u2));

        ResponseEntity<?> response = dialogController.createDialog(1, new JMXPrincipal(""));
        ResponseEntity<?> response2 = dialogController.createDialog(1, new JMXPrincipal(" "));
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(409, response2.getStatusCodeValue());
        assertNotNull(response2.getBody());
    }
}