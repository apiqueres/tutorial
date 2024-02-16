package com.ccsw.tutorial.loan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanFilterDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author apiquere
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        LoanFilterDto filter = dto.getAllParameters();

        Specification<Loan> spec = Specification.where(null);

        if (filter != null) {

            LoanSpecification filterGame = new LoanSpecification(
                    new SearchCriteria("game.id", ":", filter.getIdGame()));
            LoanSpecification filterClient = new LoanSpecification(
                    new SearchCriteria("client.id", ":", filter.getIdClient()));
            LoanSpecification filterInitDate = new LoanSpecification(
                    new SearchCriteria("init_date", ">=", filter.getFilteredDate()));
            LoanSpecification filterFinDate = new LoanSpecification(
                    new SearchCriteria("fin_date", "<=", filter.getFilteredDate()));

            spec = Specification.where(filterGame).and(filterClient).and(filterInitDate).and(filterFinDate);
        }

        return this.loanRepository.findAll(spec, dto.getPageable().getPageable());

    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void save(Long id, LoanDto dto) throws Exception {

        Loan loan = new Loan();
        List<Loan> listOfLoans = findAll();

        BeanUtils.copyProperties(dto, loan, "client", "game");

        loan.setClient(clientService.get(dto.getClient().getId()));
        loan.setGame(gameService.get(dto.getGame().getId()));

        if (id == null) {
            loan = new Loan();
        } else {
            loan = this.loanRepository.findById(id).orElse(null);
        }
        Long idGame = loan.getGame().getId();
        Long idCliente = loan.getClient().getId();
        LocalDate ini_date = loan.getInitDate();
        LocalDate fin_date = loan.getFinDate();

        // returns a list of Loans that are in the range of data and the same game
        List<Loan> listFilteredRangeGame = listOfLoans.stream().filter(e -> e.getGame().getId().equals(idGame)
                && e.getInitDate().compareTo(ini_date) >= 0 && e.getFinDate().compareTo(fin_date) <= 0)
                .collect(Collectors.toList());
        /**
         * returns a list of Loans that are in the range of data and are made by the
         * same client
         */

        List<Loan> listFilteredRangeClient = listOfLoans
                .stream().filter(e -> e.getClient().getId().equals(idCliente)
                        && e.getInitDate().compareTo(ini_date) >= 0 && e.getFinDate().compareTo(fin_date) <= 0)
                .collect(Collectors.toList());

        if (ini_date.isBefore(fin_date)) {
            if (ChronoUnit.DAYS.between(ini_date, fin_date) <= 14) {
                if (listFilteredRangeGame.isEmpty()) {
                    if (listFilteredRangeClient.isEmpty()) {

                        this.loanRepository.save(loan);

                    } else {
                        throw new Exception("Client is already in a open loan");
                    }
                } else {
                    throw new Exception("Game is already borrowed");
                }
            } else {
                throw new Exception("Final date cannot be more than 14 days");
            }
        } else {
            throw new Exception("Final date cannot be before initial date");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {
        if (this.loanRepository.findById(id).orElse(null) == null) {
            throw new Exception("Loan not Exist");
        }
        this.loanRepository.deleteById(id);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElse(null);
    }

}
