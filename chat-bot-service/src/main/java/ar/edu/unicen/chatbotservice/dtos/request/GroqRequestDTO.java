package ar.edu.unicen.chatbotservice.dtos.request;

import java.util.List;
import java.util.Map;



/*
  **BASIC STRUCTURE OF A GROQ API REQUEST**
{
  "messages": [
    {
      "role": "system",
      "content": "Eres un asistente útil que responde a las preguntas de forma concisa."
    },
    {
      "role": "user",
      "content": "¿Qué es la Groq Cloud?"
    }
  ],
  "model": "llama3-8b-8192",
  "temperature": 0.7,
  "max_tokens": 1024,
  "top_p": 1,
  "stream": false
}
* */


public record GroqRequestDTO(
        String model,
        List<Map<String,String>> messages,
        Double temperature
) {
    public static GroqRequestDTO fromUserPrompt(
            String context,
            String prompt,
            String model,
            Double temperature
    )
    {
        return new GroqRequestDTO(
                model,
                List.of(
                        Map.of(
                        "role", "system",
                        "content", getSystemPrompt() + " " +  context
                        ),
                        Map.of(
                        "role", "user",
                        "content", prompt
                )),
                temperature
        );
    }


    private static String getSystemPrompt() {
        return """
                                Eres ChatBot, un asistente especializado en la aplicación backend-scooter-app. 
                                Tu tarea es ayudar a los usuarios y microservicios proporcionando información clara sobre:
                                
                                - Scooters (estado, uso, kilometraje, ubicación)
                                - Viajes (costo, duración, pasos)
                                - Usuarios (identificación, comportamiento esperado)
                                - Wallet y Account (saldos, movimientos)
                                - Reglas internas del sistema y buenas prácticas
                                - Arquitectura de microservicios
                                
                                Responde siempre de forma breve, útil y orientada a resolver el problema.
                        """;
    }
}
