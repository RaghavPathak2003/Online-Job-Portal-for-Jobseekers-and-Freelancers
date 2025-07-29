package com.kodvix.OnlineJobPortal.entity;

import com.kodvix.OnlineJobPortal.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobProjectApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------- References --------------------
    @ManyToOne
    @JoinColumn(name = "job_project_post_id")
    private JobProjectPost jobProjectPost;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant; // JobSeeker or Freelancer

    // -------------------- Application Details --------------------
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // APPLIED, SHORTLISTED, REJECTED, HIRED

    private LocalDateTime appliedDate;

    @Column(length = 2000)
    private String coverLetter; // JobSeeker or Freelancer message

    private Double projectFee; // For project bidding (freelancer)
    private String deliveryTimeframe; // Example: "2 weeks", "5 days"

    private String proposedMilestones; // Text field for milestone-based project bids

    private String communicationPreference; // email, chat, call (like Upwork)

    // -------------------- Feedback / Rating (optional) --------------------
    private String recruiterFeedback;
    private Double applicantRating;
}
