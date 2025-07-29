package com.kodvix.OnlineJobPortal.ServiceImpl;

import com.kodvix.OnlineJobPortal.dto.*;
import com.kodvix.OnlineJobPortal.entity.*;
import com.kodvix.OnlineJobPortal.enums.Role;
import com.kodvix.OnlineJobPortal.exception.ResourceNotFoundException;
import com.kodvix.OnlineJobPortal.repository.*;
import com.kodvix.OnlineJobPortal.service.FreelancerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FreelancerServiceImpl implements FreelancerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public FreelancerResponseDto save(FreelancerRequestDto dto) {
        User user = mapToUser(dto);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        User savedUser = userRepository.save(user);
        return mapToResponseDto(savedUser);
    }

    @Override
    public FreelancerResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.FREELANCER) {
            throw new IllegalArgumentException("Only users with FREELANCER role can be fetched by this endpoint.");
        }

        return mapToResponseDto(user);
    }

    @Override
    public List<FreelancerResponseDto> getAll() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.FREELANCER)
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.FREELANCER) {
            throw new IllegalArgumentException("Only users with FREELANCER role can be deleted by this endpoint.");
        }

        userRepository.delete(user);
    }

    @Override
    public void updateById(Long id, FreelancerRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        if (user.getRole() != Role.FREELANCER) {
            throw new IllegalArgumentException("Only users with FREELANCER role can be updated by this endpoint.");
        }

        updateUserFromDto(user, dto);
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    // ---------------- Manual Mapping Methods ----------------

    private User mapToUser(FreelancerRequestDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setDob(dto.getDob());
        //user.setProfileImage(dto.getProfileImage());
        //user.setUploadResume(dto.getUploadResume());
        user.setPassword(dto.getPassword());
        user.setRole(Role.FREELANCER);
        user.setPreferredDomain(dto.getPreferredDomain());
        user.setExpectedHourlyPayRate(dto.getExpectedHourlyPayRate());
        user.setProfileSummary(dto.getProfileSummary());
        user.setPortfolioLink(dto.getPortfolioLink());
        //user.setAvailabilityToJoin(dto.getAvailabilityToJoin());
        user.setPreferredLocation(dto.getPreferredLocation());

        user.setLanguages(mapLanguages(dto, user));
        user.setCertifications(mapCertifications(dto, user));
        user.setAddresses(mapAddresses(dto, user));
        user.setSkills(mapSkills(dto, user));
        user.setEducations(mapEducations(dto, user));
        user.setExperiences(mapExperiences(dto, user));

        return user;
    }

    private void updateUserFromDto(User user, FreelancerRequestDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setDob(dto.getDob());
        //user.setProfileImage(dto.getProfileImage());
        //user.setUploadResume(dto.getUploadResume());
        user.setPassword(dto.getPassword());
        user.setPreferredDomain(dto.getPreferredDomain());
        user.setExpectedHourlyPayRate(dto.getExpectedHourlyPayRate());
        user.setProfileSummary(dto.getProfileSummary());
        user.setPortfolioLink(dto.getPortfolioLink());
        //user.set(dto.getAvailabilityToJoin());
        user.setPreferredLocation(dto.getPreferredLocation());

        user.setLanguages(mapLanguages(dto, user));
        user.setCertifications(mapCertifications(dto, user));
        user.setAddresses(mapAddresses(dto, user));
        user.setSkills(mapSkills(dto, user));
        user.setEducations(mapEducations(dto, user));
        user.setExperiences(mapExperiences(dto, user));
    }

    private List<Language> mapLanguages(FreelancerRequestDto dto, User user) {
        if (dto.getLanguages() == null) return null;
        return dto.getLanguages().stream().map(languageDto -> {
            Language language = new Language();
            language.setName(languageDto.getName());
            language.setProficiency(languageDto.getProficiency());
            language.setUser(user);
            return language;
        }).collect(Collectors.toList());
    }

    private List<Certification> mapCertifications(FreelancerRequestDto dto, User user) {
        if (dto.getCertifications() == null) return null;
        return dto.getCertifications().stream().map(certDto -> {
            Certification cert = new Certification();
            cert.setName(certDto.getName());
            cert.setIssuingOrganization(certDto.getIssuingOrganization());
            cert.setIssueDate(certDto.getIssueDate());
            cert.setExpiryDate(certDto.getExpiryDate());
            cert.setCredentialId(certDto.getCredentialId());
            cert.setCredentialUrl(certDto.getCredentialUrl());
            cert.setUser(user);
            return cert;
        }).collect(Collectors.toList());
    }

    private List<Address> mapAddresses(FreelancerRequestDto dto, User user) {
        if (dto.getAddress() == null) return null;
        return dto.getAddress().stream().map(addrDto -> {
            Address addr = new Address();
            addr.setCity(addrDto.getCity());
            addr.setState(addrDto.getState());
            addr.setCountry(addrDto.getCountry());
            addr.setStreet(addrDto.getStreet());
            addr.setPostalCode(addrDto.getPostalCode());
            addr.setUser(user);
            return addr;
        }).collect(Collectors.toList());
    }

    private List<Skill> mapSkills(FreelancerRequestDto dto, User user) {
        if (dto.getSkills() == null) return null;
        return dto.getSkills().stream().map(skillDto -> {
            Skill skill = new Skill();
            skill.setName(skillDto.getName());
            skill.setProficiency(skillDto.getProficiency());
            skill.setYearsOfExperience(skillDto.getYearsOfExperience());
            skill.setUser(user);
            return skill;
        }).collect(Collectors.toList());
    }

    private List<Education> mapEducations(FreelancerRequestDto dto, User user) {
        if (dto.getEducations() == null) return null;
        return dto.getEducations().stream().map(eduDto -> {
            Education edu = new Education();
            edu.setDegree(eduDto.getDegree());
            edu.setInstitute(eduDto.getInstitute());
            edu.setUniversity(eduDto.getUniversity());
            edu.setPassingYear(eduDto.getPassingYear());
            edu.setPercentage(eduDto.getPercentage());
            edu.setSpecialization(eduDto.getSpecialization());
            edu.setStartDate(eduDto.getStartDate());
            edu.setEndDate(eduDto.getEndDate());
            edu.setTenthBoard(eduDto.getTenthBoard());
            edu.setTenthSchoolName(eduDto.getTenthSchoolName());
            edu.setTenthPassingYear(eduDto.getTenthPassingYear());
            edu.setTenthPercentage(eduDto.getTenthPercentage());
            edu.setTwelfthBoard(eduDto.getTwelfthBoard());
            edu.setTwelfthSchoolName(eduDto.getTwelfthSchoolName());
            edu.setTwelfthPassingYear(eduDto.getTwelfthPassingYear());
            edu.setTwelfthPercentage(eduDto.getTwelfthPercentage());
            edu.setUser(user);
            return edu;
        }).collect(Collectors.toList());
    }

    private List<Experience> mapExperiences(FreelancerRequestDto dto, User user) {
        if (dto.getExperiences() == null) return null;
        return dto.getExperiences().stream().map(expDto -> {
            Experience exp = new Experience();
            exp.setCompanyName(expDto.getCompanyName());
            exp.setPosition(expDto.getPosition());
            exp.setStartDate(expDto.getStartDate());
            exp.setEndDate(expDto.getEndDate());
            exp.setDescription(expDto.getDescription());
            exp.setJobTitle(expDto.getJobTitle());
            exp.setCtc(expDto.getCtc());
            exp.setNoticePeriod(expDto.getNoticePeriod());
            exp.setLocation(expDto.getLocation());
            exp.setEmploymentType(expDto.getEmploymentType());
            exp.setYearsOfExperience(expDto.getYearsOfExperience());
            exp.setIndustry(expDto.getIndustry());
            exp.setDepartment(expDto.getDepartment());
            exp.setSkills(expDto.getSkills());
            exp.setUser(user);
            return exp;
        }).collect(Collectors.toList());
    }

    private FreelancerResponseDto mapToResponseDto(User user) {
        FreelancerResponseDto dto = new FreelancerResponseDto();

        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setGender(user.getGender());
        dto.setDob(user.getDob());
        //dto.setProfileImage(user.getProfileImage());
        //dto.setUploadResume(user.getUploadResume());
        dto.setPreferredDomain(user.getPreferredDomain());
        dto.setExpectedHourlyPayRate(user.getExpectedHourlyPayRate());
        dto.setProfileSummary(user.getProfileSummary());
        dto.setPortfolioLink(user.getPortfolioLink());
        //dto.setAvailabilityToJoin(user.getAvailabilityToJoin());
        dto.setPreferredLocation(user.getPreferredLocation());

        // Address Mapping
        if (user.getAddresses() != null) {
            List<AddressDto> addressDtos = user.getAddresses().stream().map(address -> {
                AddressDto dto1 = new AddressDto();
                dto1.setStreet(address.getStreet());
                dto1.setCity(address.getCity());
                dto1.setState(address.getState());
                dto1.setCountry(address.getCountry());
                dto1.setPostalCode(address.getPostalCode());
                dto1.setType(address.getType());
                return dto1;
            }).toList();
            dto.setAddress(addressDtos);
        }

        // Certification Mapping
        if (user.getCertifications() != null) {
            List<CertificationDto> certificationDtos = user.getCertifications().stream().map(cert -> {
                CertificationDto certDto = new CertificationDto();
                certDto.setName(cert.getName());
                certDto.setIssuingOrganization(cert.getIssuingOrganization());
                certDto.setIssueDate(cert.getIssueDate());
                certDto.setExpiryDate(cert.getExpiryDate());
                certDto.setCredentialId(cert.getCredentialId());
                certDto.setCredentialUrl(cert.getCredentialUrl());
                return certDto;
            }).toList();
            dto.setCertifications(certificationDtos);
        }

        // Language Mapping
        if (user.getLanguages() != null) {
            List<LanguageDto> languageDtos = user.getLanguages().stream().map(lang -> {
                LanguageDto langDto = new LanguageDto();
                langDto.setName(lang.getName());
                langDto.setProficiency(lang.getProficiency());
                return langDto;
            }).toList();
            dto.setLanguages(languageDtos);
        }

        // Skill Mapping
        if (user.getSkills() != null) {
            List<SkillDto> skillDtos = user.getSkills().stream().map(skill -> {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                skillDto.setProficiency(skill.getProficiency());
                skillDto.setYearsOfExperience(skill.getYearsOfExperience());
                return skillDto;
            }).toList();
            dto.setSkills(skillDtos);
        }

        // Education Mapping
        if (user.getEducations() != null) {
            List<EducationDto> educationDtos = user.getEducations().stream().map(edu -> {
                EducationDto eduDto = new EducationDto();
                eduDto.setDegree(edu.getDegree());
                eduDto.setInstitute(edu.getInstitute());
                eduDto.setPassingYear(edu.getPassingYear());
                eduDto.setPercentage(edu.getPercentage());
                eduDto.setSpecialization(edu.getSpecialization());
                eduDto.setUniversity(edu.getUniversity());
                eduDto.setStartDate(edu.getStartDate());
                eduDto.setEndDate(edu.getEndDate());

                eduDto.setTenthBoard(edu.getTenthBoard());
                eduDto.setTenthSchoolName(edu.getTenthSchoolName());
                eduDto.setTenthPassingYear(edu.getTenthPassingYear());
                eduDto.setTenthPercentage(edu.getTenthPercentage());

                eduDto.setTwelfthBoard(edu.getTwelfthBoard());
                eduDto.setTwelfthSchoolName(edu.getTwelfthSchoolName());
                eduDto.setTwelfthPassingYear(edu.getTwelfthPassingYear());
                eduDto.setTwelfthPercentage(edu.getTwelfthPercentage());

                return eduDto;
            }).toList();
            dto.setEducations(educationDtos);
        }

        // Experience Mapping
        if (user.getExperiences() != null) {
            List<ExperienceDto> experienceDtos = user.getExperiences().stream().map(exp -> {
                ExperienceDto expDto = new ExperienceDto();
                expDto.setCompanyName(exp.getCompanyName());
                expDto.setPosition(exp.getPosition());
                expDto.setStartDate(exp.getStartDate());
                expDto.setEndDate(exp.getEndDate());
                expDto.setDescription(exp.getDescription());
                expDto.setJobTitle(exp.getJobTitle());
                expDto.setCtc(exp.getCtc());
                expDto.setNoticePeriod(exp.getNoticePeriod());
                expDto.setLocation(exp.getLocation());
                expDto.setEmploymentType(exp.getEmploymentType());
                expDto.setYearsOfExperience(exp.getYearsOfExperience());
                expDto.setIndustry(exp.getIndustry());
                expDto.setDepartment(exp.getDepartment());
                expDto.setSkills(exp.getSkills());
                return expDto;
            }).toList();
            dto.setExperiences(experienceDtos);
        }

        return dto;
    }

    @Override
    public void uploadUserImage(Long userId, MultipartFile image) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        try {
            user.setProfileImage(image.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }

    @Override
    public byte[] getUserProfileImageById(Long id) {
        User user = userRepository.findById(id).get();

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
    public byte[] getUserResumeById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        byte[] resume = user.getResume();
        if (resume == null) {
            throw new ResourceNotFoundException("Resume", "userId", id);
        }
        return resume;
    }

}
