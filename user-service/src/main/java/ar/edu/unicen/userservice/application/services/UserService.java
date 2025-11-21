package ar.edu.unicen.userservice.application.services;

import ar.edu.unicen.userservice.application.repositories.UserRepository;
import ar.edu.unicen.userservice.domain.dtos.report.*;
import ar.edu.unicen.userservice.domain.dtos.request.*;
import ar.edu.unicen.userservice.domain.dtos.response.CancelAccountDTO;
import ar.edu.unicen.userservice.domain.dtos.response.LLMResponseDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserResponseDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserScooterUsageResponseDTO;
import ar.edu.unicen.userservice.domain.entities.User;
import ar.edu.unicen.userservice.domain.model.account.AccountType;
import ar.edu.unicen.userservice.domain.model.trip.Fee;
import ar.edu.unicen.userservice.infrastructure.feignClients.AccountFeignClient;
import ar.edu.unicen.userservice.infrastructure.feignClients.ChatBotFeignClient;
import ar.edu.unicen.userservice.infrastructure.feignClients.ScooterFeignClient;
import ar.edu.unicen.userservice.infrastructure.feignClients.TripFeignClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ScooterFeignClient scooterFeignClient;
    private final TripFeignClient tripFeignClient;
    private final AccountFeignClient accountFeignClient;
    private final ChatBotFeignClient  chatBotFeignClient;

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

    //Report A
    public List<ReportADTO> getScootersReportByKilometers(Boolean withPause){
        return scooterFeignClient.getScootersByKilometers(withPause);
    }
    //Report B
    public CancelAccountDTO toggleAccountState(Long accountId) {
        return accountFeignClient.toggleAccountState(accountId);
    }

    //Report C
    public List<ReportScooterByYearDTO> getScootersReportByTravels(int year, int countTrips) {
        return scooterFeignClient.getScootersByTravels(year, countTrips);
    }

    //Report D
    public InvoiceReportDTO getTotalInvoiceReport(int year, int startMonth, int endMonth) {
        return tripFeignClient.getTotalInvoice(year, startMonth, endMonth);
    }

    //Report E
    public List<UserScooterUsageResponseDTO> getScooterUserUsage(int monthStart, int monthEnd, AccountType userType){
        return accountFeignClient.getScooterUserUsage(monthStart,monthEnd,userType);
    }

    //Report G
    public List<NearScooterReportDTO> getNearScooters(double latitude, double longitude, double radius) {
        return scooterFeignClient.getNearScooters(latitude, longitude, radius);
    }

    //Report F
    public FeeResponseDTO createFee(@RequestBody Fee requestFee) {
        return tripFeignClient.createFee(requestFee);
    }

    //Report H
    public List<UserScooterPeriodUsageDTO> getScooterUsesByPeriod(
            Long userId,
            int year,
            int monthStart,
            int monthEnd,
            Boolean withRelatedToMyAccount )
    {

        if (withRelatedToMyAccount == null || !withRelatedToMyAccount) {
            //return all uses of my scooters by a specific period
            return tripFeignClient.getScooterUsesByPeriod( userId, year ,monthStart, monthEnd );
        }

        //Get all usersId related to the userId account
        List<Long> relatedUsers = accountFeignClient.getUsersRelatedToMyAccount(userId);

        //Return all scooter usages by user related to the userId account
        return tripFeignClient.getUsagePeriodForUsersByAccount(relatedUsers, year ,monthStart, monthEnd);
    }

    //Chatbot
    public LLMResponseDTO chat(PromptRequestDTO request) {
        return chatBotFeignClient.chat(request);
    }

    public LLMResponseDTO historicalTripData(Long userId, PromptRequestDTO request) {
        return chatBotFeignClient.historicalTripData(userId, request);
    }


}
