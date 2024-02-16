package com.ccsw.tutorial.loan.model;

import java.time.LocalDate;

public class LoanFilterDto {

    private Long id_game;
    private Long id_client;
    private LocalDate filtered_date;

    /**
     * @return game
     */
    public Long getIdGame() {

        return this.id_game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setIdGame(Long id_game) {

        this.id_game = id_game;
    }

    /**
     * @return client
     */
    public Long getIdClient() {

        return this.id_client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setIdClient(Long id_client) {

        this.id_client = id_client;
    }

    /**
     * @return init_date
     */
    public LocalDate getFilteredDate() {

        return this.filtered_date;
    }

    /**
     * @param init_date new value of {@link #getInitDate}.
     */
    public void setFilteredDate(LocalDate filtered_date) {

        this.filtered_date = filtered_date;
    }
}
