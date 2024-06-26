package com.alpha.kooing.board.controller

import com.alpha.kooing.board.dto.*
import com.alpha.kooing.board.service.CollegeService
import com.alpha.kooing.common.dto.ApiResponse
import com.alpha.kooing.config.jwt.JwtTokenProvider
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class CollegeController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val collegeService: CollegeService
) {

    @GetMapping("/volunteers")
    @Operation(summary = "봉사활동 목록 조회")
    fun getVolunteers(
        httpServletRequest: HttpServletRequest,
        @RequestParam("query") query: String?,
    ): ResponseEntity<ApiResponse<List<VolunteerSummary>>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getVolunteers(token, query)
            )
        )
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "봉사활동 상세 조회")
    fun getVolunteer(
        httpServletRequest: HttpServletRequest,
        @PathVariable("volunteerId") volunteerId: Long,
    ): ResponseEntity<ApiResponse<VolunteerDetail>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getVolunteer(volunteerId)
            )
        )
    }

    @PostMapping("/volunteer")
    @Operation(summary = "봉사활동 추가")
    fun createVolunteer(
        httpServletRequest: HttpServletRequest,
        @RequestBody request: CreateVolunteerRequest
    ): ResponseEntity<ApiResponse<Unit>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        collegeService.createVolunteer(token, request)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/clubs")
    @Operation(summary = "동아리 및 소모임 목록 조회")
    fun getClubs(
        httpServletRequest: HttpServletRequest,
        @RequestParam("query") query: String?,
    ): ResponseEntity<ApiResponse<List<ClubSummary>>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getClubs(token, query)
            )
        )
    }

    @GetMapping("/club/{clubId}")
    @Operation(summary = "동아리 및 소모임 상세 조회")
    fun getClub(
        httpServletRequest: HttpServletRequest,
        @PathVariable("clubId") clubId: Long,
    ): ResponseEntity<ApiResponse<ClubDetail>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getClub(clubId)
            )
        )
    }

    @PostMapping("/club")
    @Operation(summary = "동아리 및 소모임 추가")
    fun createClub(
        httpServletRequest: HttpServletRequest,
        @RequestPart request: CreateClubRequest,
        @RequestPart("image") image: MultipartFile?
    ): ResponseEntity<ApiResponse<Unit>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        collegeService.createClub(token, request, image)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/studies")
    @Operation(summary = "스터디 목록 조회")
    fun getStudies(
        httpServletRequest: HttpServletRequest,
        @RequestParam("query") query: String?,
    ): ResponseEntity<ApiResponse<List<StudySummary>>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getStudies(token, query)
            )
        )
    }

    @GetMapping("/study/{studyId}")
    @Operation(summary = "스터디 상세 조회")
    fun getStudy(
        httpServletRequest: HttpServletRequest,
        @PathVariable("studyId") studyId: Long,
    ): ResponseEntity<ApiResponse<StudyDetail>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                collegeService.getStudy(studyId)
            )
        )
    }

    @PostMapping("/study")
    @Operation(summary = "스터디 추가")
    fun createStudy(
        httpServletRequest: HttpServletRequest,
        @RequestBody request: CreateStudyRequest
    ): ResponseEntity<ApiResponse<Unit>> {
        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        collegeService.createStudy(token, request)
        return ResponseEntity.ok().build()
    }
}