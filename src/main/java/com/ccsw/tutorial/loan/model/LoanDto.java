package com.ccsw.tutorial.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * @author apiquere
 */

public class LoanDto {

    private Long id;
    private GameDto game;
    private ClientDto client;
    private LocalDate init_date;
    private LocalDate fin_date;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }

    /**
     * @return client
     */
    public ClientDto getClient() {

        return this.client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setClient(ClientDto client) {

        this.client = client;
    }

    /**
     * @return init_date
     */
    public LocalDate getInitDate() {

        return this.init_date;
    }

    /**
     * @param init_date new value of {@link #getInitDate}.
     */
    public void setInitDate(LocalDate init_date) {

        this.init_date = init_date;
    }

    /**
     * @return fin_date
     */
    public LocalDate getFinDate() {

        return this.fin_date;
    }

    /**
     * @param init_date new value of {@link #getFinDate}.
     */
    public void setFinDate(LocalDate fin_date) {

        this.fin_date = fin_date;
    }

}
