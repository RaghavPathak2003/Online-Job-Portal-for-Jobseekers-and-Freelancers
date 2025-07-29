package com.kodvix.OnlineJobPortal.serviceImpl;

import com.kodvix.OnlineJobPortal.dto.*;
import com.kodvix.OnlineJobPortal.entity.*;
import com.kodvix.OnlineJobPortal.enums.Role;
import com.kodvix.OnlineJobPortal.exception.InvalidUserRoleException;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.*;
import com.kodvix.OnlineJobPortal.service.JobSeekerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final AddressRepository addressRepository;

    @Override
    public JobSeekerResponseDto registerJobSeekerProfile(JobSeekerRequestDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setCreatedAt(LocalDateTime.now().toLocalDate());
        user.setUpdatedAt(LocalDateTime.now().toLocalDate());
        user.setRole(Role.JOBSEEKER);

        user.setSkills(dto.getSkills().stream().map(s -> mapToSkill(s, user)).collect(Collectors.toList()));
        user.setEducations(dto.getEducations().stream().map(e -> mapToEducation(e, user)).collect(Collectors.toList()));
        user.setExperiences(dto.getExperiences().stream().map(e -> mapToExperience(e, user)).collect(Collectors.toList()));
        user.setLanguages(dto.getLanguages().stream().map(l -> mapToLanguage(l, user)).collect(Collectors.toList()));
        user.setAddresses(dto.getAddresses().stream().map(a -> mapToAddress(a, user)).collect(Collectors.toList()));

        userRepository.save(user);
        return mapToJobSeekerResponseDto(user);
    }



    @Override
    public JobSeekerResponseDto getJobSeekerProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        if (!user.getRole().equals(Role.JOBSEEKER)) {
            throw new InvalidUserRoleException("User with ID " + userId + " is not a Job Seeker.");
        }

        return mapToJobSeekerResponseDto(user);
    }

    @Override
    public List<JobSeekerResponseDto> getAllJobSeekers() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.JOBSEEKER)
                .map(this::mapToJobSeekerResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobSeekerResponseDto updateJobSeekerProfile(Long userId, JobSeekerRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        if (!user.getRole().equals(Role.JOBSEEKER)) {
            throw new InvalidUserRoleException("User with ID " + userId + " is not a Job Seeker. Update not allowed.");
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDob(dto.getDob());
        user.setGender(dto.getGender());
        user.setUpdatedAt(LocalDateTime.now().toLocalDate());
        user.setPortfolioLink(dto.getPortfolioLink());
        user.setCurrentCtc(dto.getCurrentCtc());
        user.setExpectedCtc(dto.getExpectedCtc());

        user.setProfileSummary(dto.getProfileSummary());
        user.setNoticePeriod(dto.getNoticePeriod());
//        user.setCertifications(dto.getCertifications());
        user.setPreferredLocation(dto.getPreferredLocation());
        user.setPreferredDomain(dto.getPreferredDomain());


        List<Skill> existingSkills = skillRepository.findByUserId(userId);
        List<Skill> updatedSkills = new ArrayList<>();
        for (SkillDto skillDto : dto.getSkills()) {
            Skill matchedSkill = existingSkills.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(skillDto.getName()))
                    .findFirst().orElse(null);
            if (matchedSkill != null) {
                matchedSkill.setProficiency(skillDto.getProficiency());
                matchedSkill.setYearsOfExperience(skillDto.getYearsOfExperience());
                updatedSkills.add(matchedSkill);
            } else {
                updatedSkills.add(mapToSkill(skillDto, user));
            }
        }
        user.setSkills(updatedSkills);

        if (dto.getCertifications() != null) {
            List<Certification> certificate = dto.getCertifications().stream().map(certificationDto -> {
                Certification certi = modelMapper.map(certificationDto, Certification.class);
                certi.setUser(user); // manually assign parent
                return certi;
            }).collect(Collectors.toList());
            user.setCertifications(certificate);
        }

        List<Language> existingLanguages = languageRepository.findByUserId(userId);
        List<Language> updatedLanguages = new ArrayList<>();

        for (LanguageDto languageDto : dto.getLanguages()) {
            Language matchedLanguage = existingLanguages.stream()
                    .filter(l -> l.getName().equalsIgnoreCase(languageDto.getName()))
                    .findFirst()
                    .orElse(null);

            if (matchedLanguage != null) {
                matchedLanguage.setProficiency(languageDto.getProficiency());
                updatedLanguages.add(matchedLanguage);
            } else {
                updatedLanguages.add(mapToLanguage(languageDto, user));
            }
        }

        user.setLanguages(updatedLanguages);

        List<Education> existingEducations = educationRepository.findByUserId(userId);
        List<Education> updatedEducations = new ArrayList<>();
        for (EducationDto e : dto.getEducations()) {
            Education matched = existingEducations.stream()
                    .filter(ed -> ed.getDegree().equalsIgnoreCase(e.getDegree()))
                    .findFirst().orElse(null);
            if (matched != null) {
                matched.setInstitute(e.getInstitute());
                matched.setPassingYear(e.getPassingYear());
                matched.setPercentage(e.getPercentage());
                matched.setUniversity(e.getUniversity());
                matched.setStartDate(e.getStartDate());
                matched.setEndDate(e.getEndDate());
                matched.setSpecialization(e.getSpecialization());
                matched.setTenthBoard(e.getTenthBoard());
                matched.setTenthSchoolName(e.getTenthSchoolName());
                matched.setTenthPassingYear(e.getTenthPassingYear());
                matched.setTenthPercentage(e.getTenthPercentage());
                matched.setTwelfthBoard(e.getTwelfthBoard());
                matched.setTwelfthSchoolName(e.getTwelfthSchoolName());
                matched.setTwelfthPassingYear(e.getTwelfthPassingYear());
                matched.setTwelfthPercentage(e.getTwelfthPercentage());
                updatedEducations.add(matched);
            } else {
                updatedEducations.add(mapToEducation(e, user));
            }
        }
        user.setEducations(updatedEducations);

        List<Experience> existingExperiences = experienceRepository.findByUserId(userId);
        List<Experience> updatedExperiences = new ArrayList<>();
        for (ExperienceDto e : dto.getExperiences()) {
            Experience matched = existingExperiences.stream()
                    .filter(ex -> ex.getCompanyName().equalsIgnoreCase(e.getCompanyName()))
                    .findFirst().orElse(null);
            if (matched != null) {
                matched.setPosition(e.getPosition());
                matched.setStartDate(e.getStartDate());
                matched.setEndDate(e.getEndDate());
                matched.setDescription(e.getDescription());
                matched.setJobTitle(e.getJobTitle());
                updatedExperiences.add(matched);
            } else {
                updatedExperiences.add(mapToExperience(e, user));
            }
        }
        user.setExperiences(updatedExperiences);

        List<Address> existingAddresses = addressRepository.findByUserId(userId);
        List<Address> updatedAddresses = new ArrayList<>();
        for (AddressDto ad : dto.getAddresses()) {
            Address matched = existingAddresses.stream()
                    .filter(a -> a.getType().equalsIgnoreCase(ad.getType()))
                    .findFirst().orElse(null);
            if (matched != null) {
                matched.setCity(ad.getCity());
                matched.setCountry(ad.getCountry());
                matched.setStreet(ad.getStreet());
                matched.setPostalCode(ad.getPostalCode());
                matched.setState(ad.getState());
                updatedAddresses.add(matched);
            } else {
                updatedAddresses.add(mapToAddress(ad, user));
            }
        }
        user.setAddresses(updatedAddresses);

        userRepository.save(user);
        return mapToJobSeekerResponseDto(user);
    }

    @Override
    public void uploadUserImage(Long userId, MultipartFile imageFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));


        try {
            user.setProfileImage(imageFile.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }

    @Override
    public byte[] getUserProfileImageById(Long userId) {
        User user = userRepository.findById(userId).get();

        return user.getProfileImage(); // assuming this is a byte[]
    }

    @Override
    public void uploadUserResume(Long userId, MultipartFile resume) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        try {
            user.setResume(resume.getBytes());

            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload resume", e);
        }
    }

    @Override
    public byte[] getUserResumeById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        byte[] resume = user.getResume();
        if (resume == null) {
            throw new ResourceNotFoundException("Resume", "userId", userId);
        }
        return resume;
    }







    @Override
    public void deleteJobSeeker(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        if (user.getRole() != Role.JOBSEEKER) {
            throw new IllegalStateException("User is not a JobSeeker.");
        }
        userRepository.delete(user);
    }

    // === Mapping Helpers ===

    private Skill mapToSkill(SkillDto dto, User user) {
        Skill skill = modelMapper.map(dto, Skill.class);
        System.out.println(skill);
        skill.setUser(user);
        return skill;
    }

    private Education mapToEducation(EducationDto dto, User user) {
        Education education = modelMapper.map(dto, Education.class);
        System.out.println(education);
        education.setUser(user);
        return education;
    }

    private Experience mapToExperience(ExperienceDto dto, User user) {
        Experience experience = modelMapper.map(dto, Experience.class);
        experience.setUser(user);
        return experience;
    }

    private Language mapToLanguage(LanguageDto dto, User user) {
        Language language = modelMapper.map(dto, Language.class);
        language.setUser(user);
        return language;
    }

    private Address mapToAddress(AddressDto dto, User user) {
        Address address = modelMapper.map(dto, Address.class);
        address.setUser(user);
        return address;
    }

    private JobSeekerResponseDto mapToJobSeekerResponseDto(User user) {
        return new JobSeekerResponseDto(
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getPortfolioLink(),
                user.getCurrentCtc(),
                user.getExpectedCtc(),
                user.getProfileSummary(),
                user.getNoticePeriod(),
//                user.getCertifications(),
                user.getPreferredLocation(),
                user.getPreferredDomain(),
                        user.getCertifications().stream()
                                .map(cert -> modelMapper.map(cert, CertificationDto.class)).collect(Collectors.toList()),
                user.getSkills().stream().map(s -> modelMapper.map(s, SkillDto.class)).collect(Collectors.toList()),
                user.getEducations().stream().map(e -> modelMapper.map(e, EducationDto.class)).collect(Collectors.toList()),
                user.getExperiences().stream().map(e -> modelMapper.map(e, ExperienceDto.class)).collect(Collectors.toList()),
                user.getLanguages().stream().map(l -> modelMapper.map(l, LanguageDto.class)).collect(Collectors.toList()),
                user.getAddresses().stream().map(a -> modelMapper.map(a, AddressDto.class)).collect(Collectors.toList())
        );
    }
}
