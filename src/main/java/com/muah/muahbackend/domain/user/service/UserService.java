package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.estimate.dto.ProposalDto;
import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.repository.ProposalRepository;
import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.user.dto.FuneralUserDto;
import com.muah.muahbackend.domain.user.dto.UserDto;
import com.muah.muahbackend.domain.user.dto.UserInfoUpdateDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final ProposalRepository proposalRepository;

    @Transactional
    public UserDto updateUserInfo(UserInfoUpdateDto updateInfo, Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateInfo.getName());
                    user.setPhone(updateInfo.getPhone());
                    user.setAddress(updateInfo.getAddress().convert());
                    user.setRole(updateInfo.getRole());
                    user.setIsNew(updateInfo.getIsNew());
                    user.setIsApproved(updateInfo.getIsApproved());
                    user.setNickName(updateInfo.getNickName());
                    return new UserDto(userRepository.save(user));
                })
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfo(Long id){

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {

            UserDto response = new UserDto(user.get());
            setReservationCount(user.get(), response);
            return response;
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public FuneralUserDto getFuneralUserInfo(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        FuneralUserDto response = new FuneralUserDto(user);
        setReservedProposalList(user, response);
        setCompletedProposalList(user, response);

        return response;
    }

    private void getPets(User owner, UserDto userDto) {
        Collection<Pet> petsData = petRepository.findAllByOwner(owner);
        ArrayList<PetDto> pets = petsData.stream().map(p-> new PetDto(p)).collect(toCollection(ArrayList::new));
        System.out.println(pets);

    }

    private void setReservationCount(User user, UserDto userDto) {
        Collection<Proposal> proposals = proposalRepository.findReservedByPetOwner(user);
        userDto.setReservationCount(proposals.size());
    }

    private void setReservedProposalList(User user, FuneralUserDto funeralUserDto){
        Collection<Proposal> proposalsData = proposalRepository.findReservedByWriter(user);
        ArrayList<ProposalDto> proposals = proposalsData.stream().map
                (p-> new ProposalDto(p)).collect(toCollection(ArrayList::new));
        funeralUserDto.setReservedProposals(proposals);
    }

    private void setCompletedProposalList(User user, FuneralUserDto funeralUserDto) {
        Collection<Proposal> proposalsData = proposalRepository.findCompletedByWriter(user);
        ArrayList<ProposalDto> proposals = proposalsData.stream().map
                (p-> new ProposalDto(p)).collect(toCollection(ArrayList::new));
        funeralUserDto.setCompletedProposals(proposals);
    }
}
