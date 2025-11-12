package ar.edu.unicen.userservice.application.services;

import ar.edu.unicen.userservice.application.repositories.UserRepository;
import ar.edu.unicen.userservice.domain.dtos.request.*;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserScooterUsageResponseDTO;
import ar.edu.unicen.userservice.domain.entities.User;
import ar.edu.unicen.userservice.domain.model.account.AccountType;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import ar.edu.unicen.userservice.domain.model.trip.report.InvoiceReport;
import ar.edu.unicen.userservice.infrastructure.feignClients.AccountFeignClient;
import ar.edu.unicen.userservice.infrastructure.feignClients.ScooterFeignClient;
import ar.edu.unicen.userservice.infrastructure.feignClients.TripFeignClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ScooterFeignClient scooterFeignClient;
    private final TripFeignClient tripFeignClient;
    private final AccountFeignClient accountFeignClient;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request){
        User user = request.toEntity();

        userRepository.save(user);

        return UserResponseDTO.toDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long userId, UserRequestDTO request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

        user.setRole(request.role());
        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhone(request.phone());

        userRepository.save(user);

        return UserResponseDTO.toDTO(user);
    }

    public List<Scooter> getScootersReportByKilometers(boolean withPause){
        return scooterFeignClient.findAllByKilometers(withPause);
    }

    public UserResponseDTO getUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

        return UserResponseDTO.toDTO(user);
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    //Reporte D
    public InvoiceReport getTotalInvoiceReport(int year, int startMonth, int endMonth) {
        return tripFeignClient.getTotalInvoice(year, startMonth, endMonth);
    }

    public List<Scooter> getScootersReportByTravels(int year, int countTrips) {
        return scooterFeignClient.getScootersByTravels(year, countTrips);
    }
    public List<UserScooterUsageResponseDTO> getScooterUserUsage(int monthStart, int monthEnd, AccountType userType){
        return accountFeignClient.getScooterUserUsage(monthStart,monthEnd,userType);
    }

}
