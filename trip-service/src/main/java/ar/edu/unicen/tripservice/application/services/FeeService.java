package ar.edu.unicen.tripservice.application.services;

import ar.edu.unicen.tripservice.application.repositories.FeeRepository;
import ar.edu.unicen.tripservice.domain.dtos.request.fee.FeeRequestDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.fee.FeeResponseDTO;
import ar.edu.unicen.tripservice.domain.documents.Fee;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeService {
    private final FeeRepository feeRepository;

    public FeeResponseDTO createFee(FeeRequestDTO request) {
        Fee fee = request.toEntity();
        feeRepository.save(fee);
        return FeeResponseDTO.toDTO(fee);
    }

    public FeeResponseDTO updateFee( String feeId, FeeRequestDTO request) {
        Fee fee = feeRepository.findById(feeId).orElseThrow(
                ()-> new EntityNotFoundException("Fee not found with id " + feeId)
        );

        fee.setStartDate(request.startDate());
        fee.setEndDate(request.endDate());
        fee.setPricePerHour(request.pricePerHour());
        fee.setExtraHourFee(request.extraHourFee());

        feeRepository.save(fee);
        return FeeResponseDTO.toDTO(fee);
    }

    public FeeResponseDTO updateFeeExtraHour( String feeId, FeeRequestDTO request) {
        Fee fee = feeRepository.findById(feeId).orElseThrow(
                ()-> new EntityNotFoundException("Fee not found with id " + feeId)
        );
        fee.setExtraHourFee(request.extraHourFee());
        feeRepository.save(fee);
        return FeeResponseDTO.toDTO(fee);
    }

    public FeeResponseDTO getFeeById( String feeId ) {
        Fee fee = feeRepository.findById(feeId).orElseThrow(
                ()-> new EntityNotFoundException("Fee not found with id " + feeId)
        );
        return FeeResponseDTO.toDTO(fee);
    }

    public void deleteFee(String feeId) {
        Fee fee = feeRepository.findById(feeId).orElseThrow(
                ()-> new EntityNotFoundException("Fee not found with id " + feeId)
        );
        feeRepository.delete(fee);
    }
}
