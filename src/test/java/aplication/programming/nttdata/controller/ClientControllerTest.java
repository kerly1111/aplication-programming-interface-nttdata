package aplication.programming.nttdata.controller;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.service.interfaces.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        client = Client.builder()
                .id(Long.valueOf(1))
                .identification("1234567890")
                .name("Jose Lema")
                .gender("Masculino")
                .age(20)
                .address("Otavalo sn y principal")
                .phone("098254785")
                .password("1234")
                .status(Boolean.TRUE)
                .build();
    }

    @Test
    void create() {
        when(clientService.create(client)).thenReturn(new Client());
        assertNotNull(clientController.create(client));
    }

    @Test
    void allClient() {
        when(clientService.allClient()).thenReturn(Arrays.asList(client));
        assertNotNull(clientController.allClient());
    }

    @Test
    void update() {
        doThrow(new NttdataException()).when(clientService).update(client.getId(), client);
    }

    @Test
    void delete() {
        doThrow(new NttdataException()).when(clientService).delete(client.getId());
    }
}