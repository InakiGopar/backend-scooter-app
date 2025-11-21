package ar.edu.unicen.chatbotservice.service;

import ar.edu.unicen.chatbotservice.dtos.request.GroqRequestDTO;
import ar.edu.unicen.chatbotservice.dtos.request.PromptDTO;
import ar.edu.unicen.chatbotservice.dtos.response.groq.GroqResponseDTO;
import ar.edu.unicen.chatbotservice.dtos.response.LLMResponseDTO;
import ar.edu.unicen.chatbotservice.dtos.response.trip.TripResponseDTO;
import ar.edu.unicen.chatbotservice.dtos.response.trip.TripStatsDTO;
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


    private String extractTripId(String prompt) {
        // Regex detects UUID in the prompt
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        Matcher matcher = pattern.matcher(prompt);

        if (matcher.find()) {
            return matcher.group(); //returns UUID to STRING
        }
        return null;
    }

    private LLMResponseDTO tripSummary(String tripId, String prompt) {
        TripResponseDTO trip = tripFeignClient.findTripById(tripId);

        long pauseMinutes = 0;

        if (trip.startPause() != null && trip.endPause() != null) {
            pauseMinutes = Duration.between(trip.startPause(), trip.endPause()).toMinutes();
        }


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

    public LLMResponseDTO historicalTripData(Long userId, PromptDTO request) {
        TripStatsDTO stats = tripFeignClient.getTripsStats(userId);

        if (stats == null) {
            return new LLMResponseDTO("No pude obtener información de los viajes del usuario " + userId);
        }

        TripResponseDTO longest = stats.longestTrip();
        TripResponseDTO mostExpensive = stats.mostExpensiveTrip();

        String context = """
        A continuación se muestra el historial REAL de viajes del usuario %s:

        Nombre del usuario: %s

        • Total gastado: $%.2f
        • Kilómetros recorridos: %s km
        • Scooters distintos utilizados: %s

        VIAJE MÁS LARGO:
            - ID: %s
            - Kilómetros: %s
            - Inicio: %s
            - Fin: %s

        VIAJE MÁS CARO:
            - ID: %s
            - Precio: $%s
            - Kilómetros: %s
            - Fecha: %s

        Utiliza estos datos para generar un resumen claro,
        cercano y fácil de entender para el usuario.
        """.formatted(
                userId,
                stats.userName(),
                stats.totalSpent(),
                stats.totalKm(),
                stats.scooterUsed(),
                longest != null ? longest.tripId() : "N/A",
                longest != null ? longest.kmTraveled() : "N/A",
                longest != null ? longest.startTime() : "N/A",
                longest != null ? longest.endTime() : "N/A",
                mostExpensive != null ? mostExpensive.tripId() : "N/A",
                mostExpensive != null ? mostExpensive.totalPrice() : "N/A",
                mostExpensive != null ? mostExpensive.kmTraveled() : "N/A",
                mostExpensive != null ? mostExpensive.endTime() : "N/A"
        );

        // Send the request to LLM with SYSTEM + CONTEXTO + USER PROMPT
        GroqRequestDTO groqRequest = GroqRequestDTO.fromUserPrompt(
                context,
                request.message(),
                model,
                temperature
        );

        GroqResponseDTO groqResponse = groqFeignClient.sendToGroq(groqRequest);

        return new LLMResponseDTO(
                groqResponse.choices().getFirst().message().content()
        );
    }


}
