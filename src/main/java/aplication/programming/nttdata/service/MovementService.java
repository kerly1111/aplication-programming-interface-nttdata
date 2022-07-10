package aplication.programming.nttdata.service;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.model.Movement;
import aplication.programming.nttdata.repository.AccountRepository;
import aplication.programming.nttdata.repository.ClientRepository;
import aplication.programming.nttdata.repository.MovementRepository;
import aplication.programming.nttdata.service.interfaces.IMovementService;
import aplication.programming.nttdata.vo.request.BalanceValueVO;
import aplication.programming.nttdata.vo.request.MovementRequestVO;
import aplication.programming.nttdata.vo.response.AccountResponseVO;
import aplication.programming.nttdata.vo.response.MovementResponseVO;
import aplication.programming.nttdata.vo.response.StatementResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService implements IMovementService {

    @Resource
    private MovementRepository movementRepository;

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public MovementResponseVO create(MovementRequestVO request) {
        try{
            Account account = this.accountRepository.findByNumberByType(request.getAccountNumber(), request.getAccountType())
                    .orElseThrow(() -> new NttdataException("La cuenta no existe"));

            Double balance = account.getInitialBalance() +
                    Optional.ofNullable(this.movementRepository.getBalance(account.getId(), "Deposito"))
                    .orElse(0.00) -
                    Optional.ofNullable(this.movementRepository.getBalance(account.getId(), "Retiro"))
                            .orElse(0.00);

            if(request.getMovementType().equals("Retiro") && (balance - request.getValue()) < 0){
                throw new NttdataException("Saldo insuficiente para realizar la transacción");
            }else {
                Long idMovement = this.movementRepository.save(Movement.builder()
                        .date(request.getDate())
                        .movementType(request.getMovementType())
                        .value(request.getValue())
                        .idAccount(account.getId())
                        .balance(request.getMovementType().equals("Deposito") ?
                                balance + request.getValue() : balance - request.getValue())
                        .build()).getId();
                Movement movement = this.movementRepository.findMovementById(idMovement)
                        .orElseThrow(() -> new NttdataException("Error al crear el movimiento"));

                return MovementResponseVO.builder()
                        .accountNumber(movement.getAccount().getAccountNumber())
                        .accountType(movement.getAccount().getAccountType())
                        .date(movement.getDate())
                        .movementType(movement.getMovementType())
                        .value(movement.getValue())
                        .balance(movement.getBalance())
                        .status(movement.getAccount().getStatus())
                        .build();
            }
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    public List<MovementResponseVO> allMovement() {
        List<MovementResponseVO> movements = new ArrayList<>();

        this.movementRepository.allMovement()
                .forEach(movement -> movements.add(MovementResponseVO.builder()
                        .accountNumber(movement.getAccount().getAccountNumber())
                        .accountType(movement.getAccount().getAccountType())
                        .date(movement.getDate())
                        .movementType(movement.getMovementType())
                        .value(movement.getValue())
                        .balance(movement.getBalance())
                        .status(movement.getAccount().getStatus())
                        .build()
                ));

        return movements;
    }

    @Override
    @Transactional
    public void update(Long idMovement, MovementRequestVO request) {
        try{
            Account account = this.accountRepository.findByNumberByType(request.getAccountNumber(), request.getAccountType())
                    .orElseThrow(() -> new NttdataException("La cuenta no existe"));

            Double balance = account.getInitialBalance() +
                    Optional.ofNullable(this.movementRepository.getBalance(account.getId(), "Deposito"))
                            .orElse(0.00) -
                    Optional.ofNullable(this.movementRepository.getBalance(account.getId(), "Retiro"))
                            .orElse(0.00);

            if(request.getMovementType().equals("Retiro") && (balance - request.getValue()) < 0){
                throw new NttdataException("Saldo insuficiente para realizar la transacción");
            }else {
                this.movementRepository.update(idMovement, Movement.builder()
                        .date(request.getDate())
                        .movementType(request.getMovementType())
                        .value(request.getValue())
                        .balance(request.getMovementType().equals("Deposito") ?
                                balance + request.getValue() : balance - request.getValue())
                        .build()
                );
            }
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long idMovement) {
        try{
            this.movementRepository.deleteById(idMovement);
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    public List<StatementResponseVO> report(Date dateStart, Date dateEnd, String identification) {
        List<StatementResponseVO> statement = new ArrayList<>();

        List<Account> accounts = this.accountRepository.findAccountByClientId(
                this.clientRepository.findByIdentification(identification)
                        .orElseThrow(() -> new NttdataException("El usuario no asignado a la cuenta no existe."))
                        .getId()
        );

        accounts.forEach(account -> {
                    BalanceValueVO values = this.processValues(account ,dateStart, dateEnd);
                    statement.add(StatementResponseVO.builder()
                            .client(account.getClient().getName())
                            .accountNumber(account.getAccountNumber())
                            .accountType(account.getAccountType())
                            .initialBalance(values.getInitialBalance())
                            .totalCredits(values.getTotalCredits())
                            .totalDebits(values.getTotalDebits())
                            .balance(values.getBalance())
                            .status(account.getStatus())
                            .build());
                }
        );

        return statement;
    }

    private BalanceValueVO processValues(Account account, Date dateStart, Date dateEnd){

        Double initialBalance = account.getInitialBalance() +
                Optional.ofNullable(this.movementRepository.getBalancePrevious(account.getId(),
                        "Deposito", dateStart)).orElse(0.00) -
                Optional.ofNullable(this.movementRepository.getBalancePrevious(account.getId(),
                        "Retiro", dateStart)).orElse(0.00);

        Double balance = initialBalance +
                Optional.ofNullable(this.movementRepository.getBalanceByDate(account.getId(),
                        "Deposito", dateStart, dateEnd)).orElse(0.00) -
                Optional.ofNullable(this.movementRepository.getBalanceByDate(account.getId(),
                        "Retiro", dateStart, dateEnd)).orElse(0.00);

        return BalanceValueVO.builder()
                .initialBalance(initialBalance)
                .totalCredits(Optional.ofNullable(this.movementRepository.getBalanceByDate(account.getId(),
                        "Deposito", dateStart, dateEnd)).orElse(0.00))
                .totalDebits(Optional.ofNullable(this.movementRepository.getBalanceByDate(account.getId(),
                        "Retiro", dateStart, dateEnd)).orElse(0.00))
                .balance(balance)
                .build();
    }
}
