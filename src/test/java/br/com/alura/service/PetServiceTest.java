package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private PetService petService = new PetService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Pet pet = new Pet("Teste", "Marcus", "Vira-Lata", 12, "branco", 10F);

    @Test
    public void deveVerificarQuandoHaPets() throws IOException, InterruptedException {
        pet.setId(0L);
        String idOuNome = "Pets cadastrados:";
        String jsonResponse = "[{\"id\":0,\"nome\":\"Teste\",\"raca\":\"Vira-Lata\",\"tipo\":\"Cachorro\",\"idade\":12,\"peso\":10.1,\"cor\":\"branco\"}]";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn(jsonResponse);
        when(client.getRequest(anyString())).thenReturn(response);

        petService.listarPetsDoAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualPetsCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(idOuNome, actualPetsCadastrados);
        Assertions.assertEquals(jsonResponse, actualIdENome);
    }
}
