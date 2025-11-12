package ar.edu.unicen.userservice.domain.dtos.response;

public record CancelAccountDTO(
        Long accountId,
        String accountName
) {
}
