package ar.edu.unicen.userservice.domain.dtos.report;

public record ReportADTO(
        Long scooterId,
        Long kilometers,
        Integer pause
) {
}
