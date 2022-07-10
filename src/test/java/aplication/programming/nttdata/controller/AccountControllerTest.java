package aplication.programming.nttdata.controller;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.service.interfaces.IAccountService;
import aplication.programming.nttdata.vo.request.AccountRequestVO;
import aplication.programming.nttdata.vo.response.AccountResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class AccountControllerTest {

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private AccountRequestVO accountRequestVO;

    private AccountResponseVO accountResponseVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        accountRequestVO = AccountRequestVO.builder()
                .id(Long.valueOf(1))
                .accountNumber("478758")
                .accountType("Ahorro")
                .initialBalance(200.00)
                .status(Boolean.TRUE)
                .identification("1234567890")
                .name("Jose Lema")
                .build();

        accountResponseVO = AccountResponseVO.builder()
                .accountNumber("478758")
                .accountType("Ahorro")
                .initialBalance(2000.00)
                .status(Boolean.TRUE)
                .name("Jose Lema")
                .build();
    }

    @Test
    void create() {
        when(accountService.create(accountRequestVO)).thenReturn(new AccountResponseVO());
    }

    @Test
    void allAccount() {
        when(accountService.allAccount()).thenReturn(Arrays.asList(accountResponseVO));
        assertNotNull(accountController.allAccount());
    }

    @Test
    void update() {
        doThrow(new NttdataException()).when(accountService).update(accountRequestVO.getId(), accountRequestVO);
    }

    @Test
    void delete() {
        doThrow(new NttdataException()).when(accountService).delete(accountRequestVO.getId());
    }
}