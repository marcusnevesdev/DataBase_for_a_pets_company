package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsCommand implements Command{
    @Override
    public void execute() {
        try{
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetService petservice = new PetService(client);
            petservice.listarPetsDoAbrigo();
        } catch (IOException | InterruptedException e ){
            System.out.println(e.getMessage());
        }

    }
}
