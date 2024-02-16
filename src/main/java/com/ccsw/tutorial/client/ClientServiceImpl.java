package com.ccsw.tutorial.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import jakarta.transaction.Transactional;

/**
 * @author apiquere
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClientDto dto) throws Exception {

        Client client;

        List<Client> clients = (List<Client>) this.clientRepository.findAll();
        List<Client> existingClient = clients.stream().filter(c -> c.getName().equals(dto.getName()))
                .collect(Collectors.toList());

        if (existingClient.isEmpty()) {
            if (dto.getName() == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }

            if (id == null) {
                client = new Client();
            } else {
                client = this.get(id);
            }

            client.setName(dto.getName());

            this.clientRepository.save(client);

        } else {
            throw new Exception("This name is already used");

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not Exists");
        }
        this.clientRepository.deleteById(id);

    }

}
