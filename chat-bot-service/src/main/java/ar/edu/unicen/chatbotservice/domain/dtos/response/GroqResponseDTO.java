package ar.edu.unicen.chatbotservice.domain.dtos.response;

import java.util.List;

/*
*
*
  **STRUCTURE OF A GROQ API RESPONSE**
  {
  "id": "ft_0123456789abcdef",
  "object": "chat.completion",
  "created": 1700000000,
  "model": "llama3-8b-8192",

  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "response of the LLM goes here..."
      },
      "finish_reason": "stop"
    }
  ],


  "usage": {
    "prompt_tokens": 20,
    "completion_tokens": 45,
    "total_tokens": 65,
    "prompt_time": 0.005,
    "completion_time": 0.012,
    "total_time": 0.017
  },
  "x_groq": {
    "id": "req_0123456789abcdef",
    "usage": {
      "prompt_tokens": 20,
      "completion_tokens": 45,
      "total_tokens": 65,
      "prompt_time": 0.005,
      "completion_time": 0.012,
      "total_time": 0.017
    }
  }
}
*
*
* */


public record GroqResponseDTO(
        List<Choice> choices
) {
}

