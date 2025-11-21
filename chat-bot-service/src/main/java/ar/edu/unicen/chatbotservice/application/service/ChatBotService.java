package ar.edu.unicen.chatbotservice.application.service;

import ar.edu.unicen.chatbotservice.domain.dtos.request.GroqRequestDTO;
import ar.edu.unicen.chatbotservice.domain.dtos.response.GroqResponseDTO;
import ar.edu.unicen.chatbotservice.domain.dtos.response.LLMResponseDTO;
import ar.edu.unicen.chatbotservice.domain.dtos.response.TripResponseDTO;
import ar.edu.unicen.chatbotservice.infrastructure.feignClients.GroqFeignClient;
import ar.edu.unicen.chatbotservice.infrastructure.feignClients.TripFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ChatBotService {
    private final GroqFeignClient groqFeignClient;
    private final TripFeignClient tripFeignClient;

    @Value("${groq.model}")
    private String model;

    @Value("${groq.temperature}")
    private Double temperature;

    public LLMResponseDTO processPrompt(String prompt) {

        if (prompt.toLowerCase().contains("viaje")) {
            String id = extractTripId(prompt);
            if (id != null) {
                return tripSummary(id, prompt);
            }
        }

        //Build the request that the API GROQ is waiting
        GroqRequestDTO request = GroqRequestDTO.fromUserPrompt( "" , prompt, model, temperature);
        //Received the response
        GroqResponseDTO response = groqFeignClient.sendToGroq(request);
        //Return the LLM response
        return LLMResponseDTO.toDTO(response.choices().getFirst().message().content());
    }


    private LLMResponseDTO tripSummary(String tripId, String prompt) {
        TripResponseDTO trip = tripFeignClient.findTripById(tripId);

        long pauseMinutes = 0;

        if (trip.startPause() != null && trip.endPause() != null) {
            pauseMinutes = Duration.between(trip.startPause(), trip.endPause()).toMinutes();
        }

        System.out.println(trip);

        String context = """
            Información REAL del viaje solicitado:

            tripId: %s
            userId: %s
            scooterId: %s
            stopStartId: %s
            stopEndId: %s
            startTime: %s
            endTime: %s
            kmTraveled: %s
            pauseMinutes: %s minutos
            totalPrice: %s $
            fee: %s

            Usa estos datos para generar un resumen útil, claro y conciso del viaje.
            """.formatted(
                trip.tripId(),
                trip.userId(),
                trip.scooterId(),
                trip.stopStartId(),
                trip.stopEndId(),
                trip.startTime(),
                trip.endTime(),
                trip.kmTraveled(),
                pauseMinutes,
                trip.totalPrice(),
                trip.feeId()
        );

        // 3 — send to LLM API  CONTEXT + USER PROMPT
        GroqRequestDTO request = GroqRequestDTO.fromUserPrompt(
                context, prompt, model, temperature
        );

        //Received the response
        GroqResponseDTO response = groqFeignClient.sendToGroq(request);

        //Return the LLM response
        return LLMResponseDTO.toDTO(response.choices().getFirst().message().content());
    }


    private String extractTripId(String prompt) {
        // Regex detects UUID in the prompt
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        Matcher matcher = pattern.matcher(prompt);

        if (matcher.find()) {
            return matcher.group(); //returns UUID to STRING
        }
        return null;
    }
}
